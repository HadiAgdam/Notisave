package ir.hadiagdamapps.notisave

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ir.hadiagdamapps.notisave.data.local.database.AppDatabase
import ir.hadiagdamapps.notisave.data.repository.NotiRepository
import ir.hadiagdamapps.notisave.navigation.route.GroupScreenRoute
import ir.hadiagdamapps.notisave.service.NotiService
import ir.hadiagdamapps.notisave.test.TestScreen
import ir.hadiagdamapps.notisave.ui.screen.GroupsScreen
import ir.hadiagdamapps.notisave.ui.theme.ApplicationTheme
import ir.hadiagdamapps.notisave.viewmodels.GroupScreenViewModel
import ir.hadiagdamapps.notisave.viewmodels.factory.GroupScreenViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TestScreen(this)
        }

        return

        val intent = Intent(this, NotiService::class.java)
        startService(intent)

        val enabledListeners = Settings.Secure.getString(
            contentResolver,
            "enabled_notification_listeners"
        )
        if (enabledListeners?.contains(packageName) != true)
            startActivity(Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS))

        val dao = AppDatabase.getDatabase(this).notiDao()
        val repo = NotiRepository(dao)

        setContent {
            ApplicationTheme {

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = GroupScreenRoute) {

                    composable<GroupScreenRoute> {
                        val viewmodel by viewModels<GroupScreenViewModel> {
                            GroupScreenViewModelFactory(
                                application,
                                repo
                            )
                        }

                        GroupsScreen(viewmodel)
                    }

                }
            }
        }
    }
}