package co.ghostnotes.sample.compose.tasklist.ui.task

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class TaskViewModel @Inject constructor(): ViewModel() {

    private val _title = MutableStateFlow("")
    val title: StateFlow<String> = _title
    fun setTitle(title: String) {
        _title.value = title
    }

    private val _description = MutableStateFlow("")
    val description: StateFlow<String> = _description
    fun setDescription(description: String) {
        _description.value = description
    }

    val addTaskButtonEnabled: StateFlow<Boolean> = _title.mapLatest { title -> title.isNotBlank() }
        .stateIn(viewModelScope, SharingStarted.Lazily, false)
}
