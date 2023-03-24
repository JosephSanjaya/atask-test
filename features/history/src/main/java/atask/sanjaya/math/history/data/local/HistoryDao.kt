package atask.sanjaya.math.history.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import atask.sanjaya.math.history.data.models.HistoryData

@Dao
interface HistoryDao {

    @Query("SELECT * FROM ${HistoryData.TABLE_NAME}")
    suspend fun getAll(): List<HistoryData>

    @Insert
    suspend fun insertAll(vararg location: HistoryData)

    @Delete
    suspend fun delete(location: HistoryData)
}