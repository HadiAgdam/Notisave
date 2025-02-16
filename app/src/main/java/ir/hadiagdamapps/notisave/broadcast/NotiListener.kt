package ir.hadiagdamapps.notisave.broadcast

import android.app.Notification
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import ir.hadiagdamapps.notisave.data.local.database.AppDatabase
import ir.hadiagdamapps.notisave.data.local.entities.NotiEntity
import ir.hadiagdamapps.notisave.data.repository.NotiRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NotiListener : NotificationListenerService() {

    private val repo by lazy {
        NotiRepository(AppDatabase.getDatabase(applicationContext).notiDao())
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        sbn?.let {
            CoroutineScope(Dispatchers.IO).launch {
                repo.insert(it.toNoti().also {
                    Log.e("got notification", it.text.toString())
                })
            }
        }
    }


    private fun StatusBarNotification.toNoti(): NotiEntity { // chatgpt generated
        val extras = notification.extras

        val title = extras.getString(Notification.EXTRA_TITLE)
        val text = extras.getString(Notification.EXTRA_TEXT)
        val subText = extras.getString(Notification.EXTRA_SUB_TEXT)
        val bigText = extras.getString(Notification.EXTRA_BIG_TEXT)
        val postTime = postTime
        val isOngoing = isOngoing

        val messages = extras.get(Notification.EXTRA_MESSAGES) as? Array<*>
        val extraMessages = messages?.joinToString("\n")

        return NotiEntity(
            packageName = this.packageName.also { Log.e("package name", it) },
            title = title,
            text = text,
            subText = subText,
            bigText = bigText,
            postTime = postTime,
            isOngoing = isOngoing,
            extraMessages = extraMessages
        )
    }

}