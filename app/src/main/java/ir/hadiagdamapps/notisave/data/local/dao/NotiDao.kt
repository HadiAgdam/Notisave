package ir.hadiagdamapps.notisave.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ir.hadiagdamapps.notisave.data.local.entities.NotiEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotiDao {

    @Insert
    suspend fun insertItems(items: List<NotiEntity>)

    @Insert
    suspend fun insertItem(noti: NotiEntity)


    @Query("SELECT * from notifications")
    fun getItems(): Flow<List<NotiEntity>>

}