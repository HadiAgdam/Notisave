package ir.hadiagdamapps.notisave

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import ir.hadiagdamapps.notisave.data.local.database.AppDatabase
import ir.hadiagdamapps.notisave.data.repository.NotiRepository
import ir.hadiagdamapps.notisave.navigation.route.AppNotificationRoute
import ir.hadiagdamapps.notisave.navigation.route.GroupScreenRoute
import ir.hadiagdamapps.notisave.navigation.route.NotificationRoute
import ir.hadiagdamapps.notisave.service.NotiService
import ir.hadiagdamapps.notisave.ui.screen.AppNotificationsScreen
import ir.hadiagdamapps.notisave.ui.screen.GroupsScreen
import ir.hadiagdamapps.notisave.ui.screen.NotificationScreen
import ir.hadiagdamapps.notisave.ui.theme.ApplicationTheme
import ir.hadiagdamapps.notisave.viewmodels.AppNotificationsScreenViewModel
import ir.hadiagdamapps.notisave.viewmodels.GroupScreenViewModel
import ir.hadiagdamapps.notisave.viewmodels.factory.AppNotificationsScreenViewModelFactory
import ir.hadiagdamapps.notisave.viewmodels.factory.GroupScreenViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


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
            val items = repo.getItems().collectAsState(listOf())
            items.value.forEach {
                Log.e("packageName", it.packageName)
                Log.e("text", "\'${it.text.toString()}'")
                Log.e("\n", "\n")
            }

            ApplicationTheme {

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = GroupScreenRoute) {

                    composable<GroupScreenRoute> {
                        val viewmodel by viewModels<GroupScreenViewModel> {
                            GroupScreenViewModelFactory(
                                application= application,
                                repo = repo
                            )
                        }

                        GroupsScreen(navController, viewmodel)
                    }

                    composable<AppNotificationRoute> {

                        val args = it.toRoute<AppNotificationRoute>()

                        val viewmodel by viewModels<AppNotificationsScreenViewModel> { AppNotificationsScreenViewModelFactory(
                            application = application,
                            repository = repo,
                            packageName = args.packageName
                        ) }

                        AppNotificationsScreen(navController, viewmodel)
                    }


                    composable<NotificationRoute> {

                        val args = it.toRoute<NotificationRoute>()

                        val notification = repo.getNotificationById(args.notificationId).collectAsState(null)

                        notification.value?.let { noti ->
                            NotificationScreen(noti)
                        }
                    }

                }
            }
        }
    }
}