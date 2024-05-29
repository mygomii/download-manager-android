package com.mygomii.data.repositories

import com.mygomii.data.datasources.DownloadDataSource
import com.mygomii.data.datasources.LocalFileDataSource
import com.mygomii.data.models.CachedFileState
import com.mygomii.data.models.RemoteFileMetadata
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.milliseconds

class DownloadRepositoryImpl(
    private val appCoroutineScope: CoroutineScope,
    private val downloadDatasource: DownloadDataSource,
    private val localFileDataSource: LocalFileDataSource
) : DownloadRepository {
    private val metadataCachedFileStateStreamMap = mutableMapOf<List<RemoteFileMetadata>, Flow<List<CachedFileState>>>()

    override suspend fun getAvailableDownloads(): List<RemoteFileMetadata> {
        return downloadDatasource.getRemoteFileMetadata()
    }

    override suspend fun getCachedFileStateStream(metadata: List<RemoteFileMetadata>): Flow<List<CachedFileState>> {
        return withContext(Dispatchers.IO) {
            val cachedFileStateStream = metadataCachedFileStateStreamMap[metadata] ?: flow {
                while (true) {
                    val cacheStates = metadata.mapNotNull {
                        try {
                            getCacheState(it)
                        } catch (e: Exception) {
                            // TODO; 에러 처리: 기본 상태 또는 null 반환 등
                            null  // 실제 처리 로직에 맞게 수정
                        }
                    }
                    emit(cacheStates)
                    delay(2000.milliseconds)
                }
            }
                .distinctUntilChanged()
                .shareIn(
                    scope = appCoroutineScope,
                    started = SharingStarted.WhileSubscribed(),
                    replay = 1
                )
            metadataCachedFileStateStreamMap[metadata] = cachedFileStateStream
            cachedFileStateStream
        }
    }

    private suspend fun getCacheState(metadata: RemoteFileMetadata): CachedFileState {
        return downloadDatasource.getCachedFileState(metadata)
    }

    override fun downloadFile(cachedFileState: CachedFileState) {
        val metadatum = cachedFileState.metadata
        val file = localFileDataSource.getDownloadedFile(metadatum.name)
        downloadDatasource.downloadToFile(metadatum.uri, file)
    }

    override suspend fun deleteFile(cachedFileState: CachedFileState) {
        if (cachedFileState is CachedFileState.Cached) {
            localFileDataSource.deleteFile(cachedFileState.downloadedFile.file)
        }
        downloadDatasource.removeDownload(cachedFileState.metadata.uri)
    }

}