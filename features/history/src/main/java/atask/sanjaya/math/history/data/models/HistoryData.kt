package atask.sanjaya.math.history.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import atask.sanjaya.math.history.data.models.HistoryData.Companion.TABLE_NAME
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = TABLE_NAME)
data class HistoryData(

    @ColumnInfo("id")
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo("isFromFile")
    @Expose
    val isFromFile: Boolean,

    @ColumnInfo("expression")
    @Expose
    val expression: String,

    @ColumnInfo("result")
    @Expose
    val result: String
) : Parcelable {

    constructor() : this(0, false, "", "")

    companion object {
        const val TABLE_NAME = "history"
    }
}

