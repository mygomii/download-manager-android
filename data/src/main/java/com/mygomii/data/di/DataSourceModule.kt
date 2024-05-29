package com.mygomii.data.di

import android.app.Application
import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.content.getSystemService
import com.mygomii.data.datasources.DownloadDataSource
import com.mygomii.data.datasources.DownloadDataSourceImpl
import com.mygomii.data.datasources.LocalFileDataSource
import com.mygomii.data.datasources.LocalFileDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideDownloadDataSource(
        application: Application,
    ): DownloadDataSource {
        return DownloadDataSourceImpl(
            application.applicationContext.getSystemService<DownloadManager>()!!
        )
    }

    @Provides
    @Singleton
    fun provideLocalFileDataSource(
        context: Context
    ): LocalFileDataSource {
        return LocalFileDataSourceImpl(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS))
    }

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext
}