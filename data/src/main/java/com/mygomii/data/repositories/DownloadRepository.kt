package com.mygomii.data.repositories

import com.mygomii.data.models.CachedFileState
import com.mygomii.data.models.RemoteFileMetadata
import kotlinx.coroutines.flow.Flow

interface DownloadRepository {
    suspend fun getAvailableDownloads(): List<RemoteFileMetadata>
    suspend fun getCachedFileStateStream(metadata: List<RemoteFileMetadata>): Flow<List<CachedFileState>>
    fun downloadFile(cachedFileState: CachedFileState)
    suspend fun deleteFile(cachedFileState: CachedFileState)
}