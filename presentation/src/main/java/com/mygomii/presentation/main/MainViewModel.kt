package com.mygomii.presentation.main

import com.mygomii.data.models.CachedFileState
import com.mygomii.data.models.FileManagerScreenState
import com.mygomii.domain.usecases.DownloadUseCase
import com.mygomii.presentation.base.BaseViewModel
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject internal constructor(
    private val downloadUseCase: DownloadUseCase
) : BaseViewModel() {
    private val _screenStateStream = MutableStateFlow<FileManagerScreenState>(FileManagerScreenState.Loading)
    val screenStateStream: StateFlow<FileManagerScreenState> = _screenStateStream

    init {
        viewModelScope.launch {
            val availableDownloads = downloadUseCase.getAvailableDownloads()
            downloadUseCase.getCachedFileStateStream(availableDownloads)
                .map(FileManagerScreenState::Loaded)
                .onEach { loadedState ->
                    _screenStateStream.value = loadedState
                    Logger.i("###### Screen state updated: $loadedState")
                }
                .catch { e ->
                    Logger.i("###### Error occurred: ${e.message}")
                    _screenStateStream.value = FileManagerScreenState.Error
                }
                .launchIn(viewModelScope)
        }
    }

    fun download(cachedFileState: CachedFileState) {
        downloadUseCase.download(cachedFileState)
    }

    fun delete(cachedFileState: CachedFileState) {
        viewModelScope.launch {
            downloadUseCase.delete(cachedFileState)

        }
    }
}