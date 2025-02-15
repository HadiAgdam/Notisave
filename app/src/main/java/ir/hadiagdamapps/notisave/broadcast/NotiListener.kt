package ir.hadiagdamapps.notisave.broadcast

import android.app.Notification
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import android.widget.Toast
import java.io.File

class NotiListener : NotificationListenerService() {


    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)
        val file = File(baseContext.cacheDir, "file.txt")
        if (!file.exists()) file.createNewFile()
        var content = file.readText()

        content += "\n----------------------------------------------------------------------------\n"
        content += sbn?.notification?.extras?.getString(Notification.EXTRA_TEXT, "NULL")
        content += "\n"
        content += sbn?.notification?.extras?.getString(Notification.EXTRA_TITLE, "NULL")
        content += "\n"

        file.writeText(content)

        Log.e("notification", "\n${file.readText()}\n")
    }

}