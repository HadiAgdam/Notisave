package ir.hadiagdamapps.notisave.viewmodels

import android.app.Application
import android.content.pm.PackageManager
import androidx.lifecycle.AndroidViewModel
import ir.hadiagdamapps.notisave.data.repository.NotiRepository
import ir.hadiagdamapps.notisave.models.NotiGroup
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GroupScreenViewModel(
    application: Application,
    private val repository: NotiRepository
) : AndroidViewModel(application) {

    val groupedNotifications: Flow<List<NotiGroup>> = repository.allItems.map { notifications ->
        notifications.groupBy { it.packageName }
            .mapNotNull { (packageName, selectedNotifications) ->

                try {
                    val packageManager = getApplication<Application>().packageManager
                    val appInfo = packageManager.getApplicationInfo(packageName, 0)
                    val appTitle = packageManager.getApplicationLabel(appInfo).toString()
                    val appIcon = packageManager.getApplicationIcon(packageName)

                    NotiGroup(appTitle, appIcon, selectedNotifications.size)
                } catch (e: PackageManager.NameNotFoundException) {
                    null
                }
            }
    }
}