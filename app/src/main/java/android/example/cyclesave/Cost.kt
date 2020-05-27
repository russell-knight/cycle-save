package android.example.cyclesave

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**The Cost class is used to store data about costs incurred from cycling
 * e.g name=Service, price=100, datePurchased=03/12/20*/

@Entity(tableName = "cost")
data class Cost(
    /* Note for working prototype name will be PrimaryKey and datePurchased will be a String
    *  These will be modified down the line. Probably by using auto-generated PK ID*/
    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "price")
    val price: Int,

    @ColumnInfo(name = "date_purchased")
    val datePurchased: String) {

}