package ir.hadiagdamapps.notisave.utils

import android.app.NotificationChannel
import androidx.annotation.RequiresApi
import ir.hadiagdamapps.notisave.notification.NotiChannel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@RequiresApi(26)
fun NotificationChannel(channel: NotiChannel): NotificationChannel {
    return NotificationChannel(
        channel.id,
        channel.channelName,
        channel.importance
    ).apply { description = channel.description }
}

fun Long.toMinutes(): String = SimpleDateFormat("hh:mm a", Locale.US).format(this)

fun Long.toLongDate(): String = SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.getDefault()).format(Date(this))
