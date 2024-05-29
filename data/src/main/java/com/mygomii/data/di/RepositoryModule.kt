package com.mygomii.data.di

import com.mygomii.data.datasources.DownloadDataSource
import com.mygomii.data.datasources.LocalFileDataSource
import com.mygomii.data.repositories.AppCoroutineScope
import com.mygomii.data.repositories.AppCoroutineScopeImpl
import com.mygomii.data.repositories.DownloadRepository
import com.mygomii.data.repositories.DownloadRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideDownloadRepository(
        appCoroutineScope: AppCoroutineScope,
        downloadDatasource: DownloadDataSource,
        localFileDataSource: LocalFileDataSource
    ): DownloadRepository {
        return DownloadRepositoryImpl(
            appCoroutineScope,
            downloadDatasource,
            localFileDataSource
        )
    }

    @Provides
    @Singleton
    fun provideAppCoroutineScope(): AppCoroutineScope = AppCoroutineScopeImpl()
}