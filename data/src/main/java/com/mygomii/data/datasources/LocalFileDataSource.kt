package com.mygomii.data.datasources

import java.io.File


interface LocalFileDataSource {
    fun getDownloadedFile(fileName: String): File

    fun getDownloadedFiles(): List<File>

    fun deleteFile(file: File)
}
