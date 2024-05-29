package com.mygomii.data.models

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.database.Cursor

sealed interface RemoteDownload {
    val id: Long
    val remoteUri: String

    data class Pending(
        override val id: Long,
        override val remoteUri: String,
    ) : RemoteDownload

    data class Running(
        override val id: Long,
        override val remoteUri: String,
    ) : RemoteDownload

    data class Paused(
        override val id: Long,
        override val remoteUri: String,
    ) : RemoteDownload

    data class Successful(
        override val id: Long,
        override val remoteUri: String,
        val localUri: String,
    ) : RemoteDownload

    data class Failed(
        override val id: Long,
        override val remoteUri: String,
        val cause: DownloadError,
    ) : RemoteDownload

    @SuppressLint("Range")
    class Factory(private val cursor: Cursor) {

        private val uri get() = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_URI))

        private val id get() = cursor.getLong(cursor.getColumnIndex(DownloadManager.COLUMN_ID))

        private val state get() = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))

        private val localFileUri get() = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI))

        private val downloadError: DownloadError
            get() {
                return when (val reason = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_REASON))) {
                    DownloadManager.ERROR_UNKNOWN -> DownloadError.Unknown
                    DownloadManager.ERROR_FILE_ERROR -> DownloadError.FileDownloadError
                    DownloadManager.ERROR_UNHANDLED_HTTP_CODE -> DownloadError.UnhandledHttpCode
                    DownloadManager.ERROR_HTTP_DATA_ERROR -> DownloadError.HttpData
                    DownloadManager.ERROR_TOO_MANY_REDIRECTS -> DownloadError.TooManyRedirects
                    DownloadManager.ERROR_INSUFFICIENT_SPACE -> DownloadError.InsufficientSpace
                    DownloadManager.ERROR_DEVICE_NOT_FOUND -> DownloadError.DeviceNotFound
                    DownloadManager.ERROR_CANNOT_RESUME -> DownloadError.CannotResume
                    DownloadManager.ERROR_FILE_ALREADY_EXISTS -> DownloadError.FileAlreadyExists
                    else -> DownloadError.Http(reason)
                }
            }

        fun create(): RemoteDownload? {
            return when (state) {
                DownloadManager.STATUS_PENDING -> Pending(id, uri)
                DownloadManager.STATUS_RUNNING -> Running(id, uri)
                DownloadManager.STATUS_PAUSED -> Paused(id, uri)
                DownloadManager.STATUS_SUCCESSFUL -> Successful(id, uri, localFileUri)
                DownloadManager.STATUS_FAILED -> Failed(id, uri, downloadError)
                else -> null
            }
        }
    }
}