package com.mygomii.data.datasources

import com.mygomii.data.models.CachedFileState
import com.mygomii.data.models.RemoteFileMetadata
import java.io.File

interface DownloadDataSource {

    suspend fun getRemoteFileMetadata(): List<RemoteFileMetadata>

    fun downloadToFile(uri: String, file: File)
    suspend fun getCachedFileState(metadata: RemoteFileMetadata): CachedFileState

    suspend fun removeDownload(remoteUri: String)
}