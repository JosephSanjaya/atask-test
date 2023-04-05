package atask.sanjaya.ui.data.locale.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import atask.sanjaya.ui.data.locale.model.LocaleData

/*
 * Designed and developed by Joseph Sanjaya, S.T., M.Kom. on 6/26/2022
 * @Github (https://github.com/JosephSanjaya),
 * @LinkedIn (https://www.linkedin.com/in/josephsanjaya/))
 */
@Database(
    entities =
    [
        LocaleData::class
    ],
    version = 1,
    exportSchema = false
)
abstract class LocaleDatabase : RoomDatabase() {
    abstract fun getLocaleDao(): LocaleDao

    companion object {
        const val DATABASE_NAME = "localeDb"
        val MIGRATION_1_2: Migration =
            object : Migration(1, 2) {
                override fun migrate(database: SupportSQLiteDatabase) {
                    // Since we didn't alter the table, there's nothing else to do here.
                }
            }
    }
}
