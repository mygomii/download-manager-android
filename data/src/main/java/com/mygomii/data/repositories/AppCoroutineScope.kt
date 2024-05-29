package com.mygomii.data.repositories

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

interface AppCoroutineScope : CoroutineScope

class AppCoroutineScopeImpl : AppCoroutineScope {

    override val coroutineContext = SupervisorJob()
}