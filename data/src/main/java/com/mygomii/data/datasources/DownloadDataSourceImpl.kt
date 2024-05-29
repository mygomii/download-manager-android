package com.mygomii.data.datasources

import android.app.DownloadManager
import androidx.core.net.toFile
import androidx.core.net.toUri
import com.mygomii.data.models.CachedFileState
import com.mygomii.data.models.DownloadedFile
import com.mygomii.data.models.RemoteDownload
import com.mygomii.data.models.RemoteFileMetadata
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class DownloadDataSourceImpl(
    private val downloadManager: DownloadManager
) : DownloadDataSource {
    override suspend fun getRemoteFileMetadata(): List<RemoteFileMetadata> {
        return listOf(
            RemoteFileMetadata("Dog", "https://cdn.pixabay.com/photo/2021/07/24/07/23/chow-chow-6488846_1280.jpg"),
            RemoteFileMetadata("House", "https://cdn.pixabay.com/photo/2024/04/01/17/55/straubing-8669480_1280.jpg"),
        )
    }

    override fun downloadToFile(uri: String, file: File) {
        val request = createDownloadRequest(uri, file)
        downloadManager.enqueue(request)
    }

    private fun createDownloadRequest(uri: String, file: File): DownloadManager.Request {
        return DownloadManager.Request(uri.toUri()).apply {
            val title = "Downloading ${file.name}"
            setTitle(title)
            setDestinationUri(file.toUri())
        }
    }

    private fun getDownloadManagerDownloads(): List<RemoteDownload> {
        val cursor = downloadManager.query(DownloadManager.Query())
        val downloads = buildList {
            while (cursor.moveToNext()) {
                RemoteDownload.Factory(cursor).create()
                    ?.let { add(it) }
            }
        }
        cursor.close()
        return downloads
    }

    override suspend fun getCachedFileState(metadata: RemoteFileMetadata): CachedFileState {
        return withContext(Dispatchers.IO) {
            val remoteDownload = getDownloadManagerDownloads()
                .find { download -> download.remoteUri == metadata.uri }
            createCachedFileState(remoteDownload, metadata)
        }
    }

    private fun createCachedFileState(remoteDownload: RemoteDownload?, metadata: RemoteFileMetadata): CachedFileState {
        return when (remoteDownload) {
            is RemoteDownload.Failed -> CachedFileState.Error(metadata, remoteDownload.cause)
            is RemoteDownload.Paused,
            is RemoteDownload.Pending,
            is RemoteDownload.Running -> CachedFileState.Downloading(metadata)

            is RemoteDownload.Successful -> CachedFileState.Cached(metadata, DownloadedFile(metadata.name, remoteDownload.localUri.toUri().toFile()))
            null -> CachedFileState.NotCached(metadata)
        }
    }

    override suspend fun removeDownload(remoteUri: String) {
        return withContext(Dispatchers.IO) {
            getDownloadManagerDownloads()
                .filter { download -> download.remoteUri == remoteUri }
                .forEach { download -> downloadManager.remove(download.id) }
        }
    }
}