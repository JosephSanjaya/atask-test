package atask.sanjaya.ui.data.locale.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import atask.sanjaya.ui.data.locale.model.LocaleData.Companion.TABLE
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/*
 * Designed and developed by Joseph Sanjaya, S.T., M.Kom. on 9/7/2022
 * @Github (https://github.com/JosephSanjaya),
 * @LinkedIn (https://www.linkedin.com/in/josephsanjaya/))
 */
@Parcelize
@Entity(tableName = TABLE)
data class LocaleData(
    @ColumnInfo(name = "key")
    @PrimaryKey
    @Expose
    @field:SerializedName("key")
    val key: String = "",
    @ColumnInfo(name = "idn") @field:SerializedName("idn")
    @Expose
    val id: String? = null,
    @ColumnInfo(name = "en") @field:SerializedName("en")
    @Expose
    val en: String? = null
) : Parcelable {
    companion object {
        const val TABLE = "resourceString"
    }
}
