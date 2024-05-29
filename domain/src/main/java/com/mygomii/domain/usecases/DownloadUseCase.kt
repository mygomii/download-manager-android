package com.mygomii.domain.usecases

import com.mygomii.data.models.CachedFileState
import com.mygomii.data.models.RemoteFileMetadata
import com.mygomii.data.repositories.DownloadRepository
import javax.inject.Inject

class DownloadUseCase @Inject constructor(
    private val downloadRepository: DownloadRepository
) {
    fun download(
        cachedFileState: CachedFileState
    ) = downloadRepository.downloadFile(cachedFileState)

    suspend fun delete(
        cachedFileState: CachedFileState
    ) = downloadRepository.deleteFile(cachedFileState)

    suspend fun getAvailableDownloads() = downloadRepository.getAvailableDownloads()
    suspend fun getCachedFileStateStream(metadata: List<RemoteFileMetadata>) = downloadRepository.getCachedFileStateStream(metadata)
}


