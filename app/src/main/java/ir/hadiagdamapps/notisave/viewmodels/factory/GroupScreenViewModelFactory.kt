package ir.hadiagdamapps.notisave.viewmodels.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ir.hadiagdamapps.notisave.data.repository.NotiRepository
import ir.hadiagdamapps.notisave.viewmodels.GroupScreenViewModel

class GroupScreenViewModelFactory(
    private val application: Application,
    private val repo: NotiRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GroupScreenViewModel::class.java)) {
            return GroupScreenViewModel(application, repo) as T
        }
        throw IllegalArgumentException("Invalid viewmodel class")
    }

}