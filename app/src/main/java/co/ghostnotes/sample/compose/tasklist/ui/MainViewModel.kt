package co.ghostnotes.sample.compose.tasklist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel  @Inject constructor() : ViewModel() {

    private val _appBarTitle = MutableStateFlow("")
    val appBarTitle: StateFlow<String> = _appBarTitle

    fun setAppBarTitle(title: String) {
        _appBarTitle.value = title
    }

    private val _fabVisible = MutableStateFlow(true)
    val fabVisible: StateFlow<Boolean> = _fabVisible

    fun setFabVisible(visible: Boolean) {
        _fabVisible.value = visible
    }
}
