package ir.hadiagdamapps.notisave.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import ir.hadiagdamapps.notisave.data.local.entities.NotiEntity
import ir.hadiagdamapps.notisave.data.repository.NotiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class AppNotificationsScreenViewModel(
    application: Application,
    private val repository: NotiRepository,
    private val packageName: String
) : AndroidViewModel(application) {

    val notifications: Flow<List<NotiEntity>> = repository.getItemsByPackageName(packageName)

    init {
        Log.e("pppp", packageName)
    }


    fun onItemClick(notiEntity: NotiEntity) {
        viewModelScope.launch {
            repository.updateToSeen(notiEntity.notificationId)
        }
    }

}