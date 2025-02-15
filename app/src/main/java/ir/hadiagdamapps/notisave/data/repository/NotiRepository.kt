package ir.hadiagdamapps.notisave.data.repository

import ir.hadiagdamapps.notisave.data.local.dao.NotiDao
import ir.hadiagdamapps.notisave.data.local.entities.NotiEntity
import ir.hadiagdamapps.notisave.models.NotiGroup
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NotiRepository(private val dao: NotiDao) {

    val allItems: Flow<List<NotiEntity>> = dao.getItems()

    suspend fun insertItems(items: List<NotiEntity>) {
        dao.insertItems(items)
    }

}