package android.example.cyclesave

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME = "CycleSaveDB"
val TABLE_NAME = "Costs"
val COL_ID = "id"
val COL_NAME = "name"
val COL_PRICE = "price"
val COL_DATE = "date purchased"

class DatabaseHelper(var context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null,1) {
    // This method will be executed when the device doesn't contain a DB
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME + " VARCHAR(256)," +
                COL_PRICE + " INTEGER," +
                COL_DATE + " TEXT)"

        db?.execSQL(createTable)
    }
    // Will be executed when an older version DB exists
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(cost: Cost) {6611
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_NAME, cost.name)
        cv.put(COL_PRICE, cost.price)
        cv.put(COL_DATE, cost.datePurchased.toString())
        var result = db.insert(TABLE_NAME,null,cv)
        if(result == -1.toLong())
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }
}
