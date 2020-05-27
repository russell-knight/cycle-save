package android.example.cyclesave

import androidx.lifecycle.LiveData

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class CostRepository(private val costDao: CostDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allCosts: LiveData<List<Cost>> = costDao.getCosts()

    suspend fun insert(cost: Cost) {
        costDao.insertCost(cost)
    }
}