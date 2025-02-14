package ir.hadiagdamapps.notisave.utils

import android.app.NotificationChannel
import androidx.annotation.RequiresApi
import ir.hadiagdamapps.notisave.notification.NotiChannel

@RequiresApi(26)
fun NotificationChannel(channel: NotiChannel): NotificationChannel {
    return NotificationChannel(channel.id, channel.channelName, channel.importance).apply { description = channel.description }
}