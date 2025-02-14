package ir.hadiagdamapps.notisave.notification

import android.app.NotificationManager

enum class NotiChannel(
    val id: String,
    val channelName: String,
    val description: String,
    val importance: Int
) {

    SERVICE_NOTIFICATION_CHANNEL(
        id = "service channel",
        channelName = "Service",
        description = "This is for keeping service alive",
        importance = NotificationManager.IMPORTANCE_LOW
    )

}