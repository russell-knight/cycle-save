package android.example.cyclesave

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CostViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CostRepository
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allCosts: LiveData<List<Cost>>

    init {
        val costsDao = CostRoomDatabase.getDatabase(application, viewModelScope).costDao()
        repository = CostRepository(costsDao)
        allCosts = repository.allCosts
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(cost: Cost) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(cost)
    }
}