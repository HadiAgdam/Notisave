package ir.hadiagdamapps.notisave.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


@Entity(tableName = "notifications")
data class NotiEntity(
    @PrimaryKey(autoGenerate = true) val notificationId: Int = 0,
    val packageName: String,
    val title: String?,
    val text: String?,
    val subText: String?,
    val bigText: String?,
    val postTime: Long,
    val isOngoing: Boolean,
    val extraMessages: String?, // like telegram notification chat
    val seen: Boolean = false,
)