package com.mygomii.data.models

sealed interface FileManagerScreenState {

    object Loading : FileManagerScreenState

    object Error : FileManagerScreenState

    data class Loaded(val cachedFileState: List<CachedFileState>) : FileManagerScreenState
}
