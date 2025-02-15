package ir.hadiagdamapps.notisave.broadcast

import android.app.Notification
import android.content.Context
import android.graphics.Bitmap
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.widget.Toast
import ir.hadiagdamapps.notisave.data.local.database.AppDatabase
import ir.hadiagdamapps.notisave.data.local.entities.NotiEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

class NotiListener : NotificationListenerService() {

    private val notiDao by lazy {
        AppDatabase.getDatabase(applicationContext).notiDao()
    }


    override fun onNotificationPosted(sbn: StatusBarNotification?) {
//        Toast.makeText(applicationContext, "got notification", Toast.LENGTH_SHORT).show()
        sbn?.let {
            val notiEntity = extractNotiEntity(baseContext, it)
            CoroutineScope(Dispatchers.IO).launch {
                notiDao.insertItem(notiEntity)
            }
        }
    }

    private fun extractNotiEntity( // chatgpt generated
        context: Context,
        sbn: StatusBarNotification
    ): NotiEntity {
        val notification = sbn.notification
        val extras = notification.extras

        val packageName = sbn.packageName
        val title = extras.getString(Notification.EXTRA_TITLE)
        val text = extras.getString(Notification.EXTRA_TEXT)
        val subText = extras.getString(Notification.EXTRA_SUB_TEXT)
        val bigText = extras.getString(Notification.EXTRA_BIG_TEXT)
        val postTime = sbn.postTime
        val isOngoing = sbn.isOngoing

        // Extract largeIcon (Profile picture)
        val largeIconBitmap = extras.getParcelable<Bitmap>(Notification.EXTRA_LARGE_ICON)
        val largeIconPath =
            largeIconBitmap?.let { saveImageToCache(context, it, "large_icon_${sbn.key}") }

        // Extract picture (Big image, like YouTube thumbnail)
        val pictureBitmap = extras.getParcelable<Bitmap>(Notification.EXTRA_PICTURE)
        val picturePath = pictureBitmap?.let { saveImageToCache(context, it, "picture_${sbn.key}") }

        // Extract extra messages (For messaging apps like Telegram)
        val messages = extras.get(Notification.EXTRA_MESSAGES) as? Array<*>
        val extraMessages = messages?.joinToString("\n")

        return NotiEntity(
            packageName = packageName,
            title = title,
            text = text,
            subText = subText,
            bigText = bigText,
            postTime = postTime,
            isOngoing = isOngoing,
            picture = picturePath ?: "",
            largeIcon = largeIconPath,
            extraMessages = extraMessages
        )
    }

    private fun saveImageToCache(context: Context, bitmap: Bitmap, fileName: String): String {
        val file = File(context.cacheDir, "$fileName.png")
        FileOutputStream(file).use { out ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
        }
        return file.absolutePath
    }


}