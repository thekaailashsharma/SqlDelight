package temp.learn.sqldelight

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import temp.learn.sqldelight.data.PersonDataSource
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val personDataSource: PersonDataSource
): ViewModel() {

    val persons = personDataSource.getAllPersons()

    var personDetails by mutableStateOf<Person?>(null)
        private set

    var firstNameText by mutableStateOf("")
        private set
    var lastNameText by mutableStateOf("")
        private set

    init {
//        for (i in 0..10) {
//           onInsertPersonClick("First $i", "Last $i")
//        }
    }

    fun onInsertPersonClick(firstNameText: String, lastNameText: String, age: Long? = null) {
        viewModelScope.launch {
            personDataSource.insertPerson(firstNameText, lastNameText, age = age)
        }
    }

    fun onDeleteClick(id: Long) {
        viewModelScope.launch {
            personDataSource.deletePersonById(id)
        }
    }

    fun getPersonById(id: Long) {
        viewModelScope.launch {
            personDetails = personDataSource.getPersonById(id)
        }
    }

    fun onFirstNameChange(value: String) {
        firstNameText = value
    }

    fun onLastNameChange(value: String) {
        lastNameText = value
    }

    fun onPersonDetailsDialogDismiss() {
        personDetails = null
    }
}