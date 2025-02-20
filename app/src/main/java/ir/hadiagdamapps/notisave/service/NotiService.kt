package ir.hadiagdamapps.notisave.service

import android.Manifest
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.os.IBinder
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import ir.hadiagdamapps.notisave.R
import ir.hadiagdamapps.notisave.notification.NotiChannel

class NotiService : Service() {

    private fun showNotification() {
        val notification = NotificationCompat.Builder(
            applicationContext,
            NotiChannel.SERVICE_NOTIFICATION_CHANNEL.id
        )
            .setContentTitle("Listening for notifications...")
            .setSmallIcon(R.drawable.app_logo)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setOngoing(true)
            .build()

        if (ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            throw Exception("Post notification permission not granted")
        }

        startForeground(1, notification)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        showNotification()
        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? = null
}