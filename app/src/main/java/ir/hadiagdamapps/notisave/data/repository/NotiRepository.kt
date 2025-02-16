package ir.hadiagdamapps.notisave.data.repository

import ir.hadiagdamapps.notisave.data.local.dao.NotiDao
import ir.hadiagdamapps.notisave.data.local.entities.NotiEntity
import kotlinx.coroutines.flow.Flow

class NotiRepository(private val dao: NotiDao) {

    val allItems: Flow<List<NotiEntity>> = dao.getItems()

    suspend fun insert(noti: NotiEntity) {
        dao.insertItem(noti)
    }

}