package ir.hadiagdamapps.notisave.data.repository

import ir.hadiagdamapps.notisave.data.local.dao.NotiDao
import ir.hadiagdamapps.notisave.data.local.entities.NotiEntity
import kotlinx.coroutines.flow.Flow

class NotiRepository(private val dao: NotiDao) {

    suspend fun insert(noti: NotiEntity) {
        dao.insertItem(noti)
    }

    fun getItems(): Flow<List<NotiEntity>> {
        return dao.getItems()
    }


    fun getItemsByPackageName(packageName: String): Flow<List<NotiEntity>> {
        return dao.getItemsByPackageName(packageName)
    }

    suspend fun updateToSeen(id: Int) {
        dao.updateToSeen(id)
    }

    fun getNotificationById(notificationId: Int): Flow<NotiEntity?> {
        return dao.getItemById(notificationId)
    }
}