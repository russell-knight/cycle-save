package android.example.cyclesave

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CostDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCost(cost: Cost)

    @Update
    suspend fun updateCost(cost: Cost)

    @Delete
    suspend fun deleteCost(cost: Cost)

    @Query("SELECT * FROM cost WHERE name == :name")
    fun getGenderByName(name: String): List<Cost>

    @Query("SELECT * FROM cost ORDER BY name ASC")
    fun getCosts(): LiveData<List<Cost>>

    @Query("DELETE FROM cost")
    suspend fun deleteAll()
}