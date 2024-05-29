package com.mygomii.data.datasources

import java.io.File

class LocalFileDataSourceImpl(
    private val downloadDirectory: File?
) : LocalFileDataSource {
    override fun getDownloadedFile(fileName: String): File {
        val file = File(downloadDirectory, fileName)
        if (!file.exists()) {
            file.parentFile?.mkdirs()
        }

        return file
    }

    override fun getDownloadedFiles(): List<File> {
        return downloadDirectory?.listFiles()?.toList() ?: emptyList()
    }

    override fun deleteFile(file: File) {
        file.delete()
    }
}