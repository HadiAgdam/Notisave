package ir.hadiagdamapps.notisave.viewmodels

import android.app.Application
import android.content.pm.PackageManager
import android.content.pm.PackageManager.NameNotFoundException
import androidx.lifecycle.AndroidViewModel
import ir.hadiagdamapps.notisave.data.repository.NotiRepository
import ir.hadiagdamapps.notisave.models.NotiGroup
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class GroupScreenViewModel(
    application: Application,
    private val repository: NotiRepository
) : AndroidViewModel(application) {


    val groupedNotifications: Flow<List<NotiGroup>> = repository.getItems().map { list ->
        list.groupBy { it.packageName }.map { (packageName, grouped) ->
            NotiGroup(
                title = getAppTitle(packageName),
                count = grouped.filter { !it.seen }.size,
                packageName = packageName
            )
        }
    }


    private fun getAppTitle(packageName: String): String {
        val pm: PackageManager = getApplication<Application>().packageManager

        return try {
            pm.getApplicationInfo(packageName, 0)
        } catch (e: NameNotFoundException) {
            null
        }?.let { pm.getApplicationLabel(it).toString() } ?: packageName
    }
}