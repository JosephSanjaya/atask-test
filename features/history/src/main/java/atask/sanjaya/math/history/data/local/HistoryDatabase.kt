package atask.sanjaya.math.history.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import atask.sanjaya.math.history.data.models.HistoryData

@Database(entities = [HistoryData::class], version = 1)
abstract class HistoryDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao

    companion object {
        const val DATABASE_NAME = "history_db"
    }
}