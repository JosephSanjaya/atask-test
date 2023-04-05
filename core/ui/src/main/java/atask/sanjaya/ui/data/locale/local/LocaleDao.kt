package atask.sanjaya.ui.data.locale.local

import androidx.room.*
import atask.sanjaya.ui.data.locale.model.LocaleData

/*
 * Designed and developed by Joseph Sanjaya, S.T., M.Kom. on 9/7/2022
 * @Github (https://github.com/JosephSanjaya),
 * @LinkedIn (https://www.linkedin.com/in/josephsanjaya/))
 */
@Dao
interface LocaleDao {
    @Delete
    fun remove(vararg data: LocaleData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(data: LocaleData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(vararg data: LocaleData)

    @Update
    fun update(vararg data: LocaleData)

    @Query("SELECT * FROM `resourceString`")
    fun getAll(): List<LocaleData?>?

    @Query("SELECT * FROM `resourceString` WHERE `key` == :key")
    fun getByKey(key: String): LocaleData?
}
