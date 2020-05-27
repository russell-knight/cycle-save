package android.example.cyclesave

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// Annotates class to be a Room Database with a table (entity) of the Cost class
@Database(entities = arrayOf(Cost::class), version = 1, exportSchema = false)
public abstract class CostRoomDatabase : RoomDatabase() {

    abstract fun costDao(): CostDao

    private class CostDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var costDao = database.costDao()

                    // Delete all content here.
                    costDao.deleteAll()

                    // Add sample words.
                    var cost = Cost("Bicycle", 550, "12/05/17")
                    costDao.insertCost(cost)

                }
            }
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: CostRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
            ): CostRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CostRoomDatabase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}