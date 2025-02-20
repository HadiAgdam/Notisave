package ir.hadiagdamapps.notisave.viewmodels.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ir.hadiagdamapps.notisave.data.repository.NotiRepository
import ir.hadiagdamapps.notisave.viewmodels.AppNotificationsScreenViewModel

class AppNotificationsScreenViewModelFactory(
    private val application: Application,
    private val repository: NotiRepository,
    private val packageName: String
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AppNotificationsScreenViewModel::class.java))
            return AppNotificationsScreenViewModel(
                application = application,
                repository = repository,
                packageName =packageName,
            ) as T
        throw IllegalArgumentException("Invalid viewmodel class")
    }
}