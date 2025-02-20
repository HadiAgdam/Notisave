package ir.hadiagdamapps.notisave.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.hadiagdamapps.notisave.data.local.entities.NotiEntity
import ir.hadiagdamapps.notisave.utils.toLongDate

@Composable
fun NotificationScreen(
    noti: NotiEntity,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ir.hadiagdamapps.notisave.ui.theme.Color.background)
            .padding(8.dp)
            .padding(top = 16.dp)
    ) {
        Text(text = noti.packageName, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(8.dp))
        noti.title?.let {
            Text(text = it, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        }
        noti.text?.let {
            Text(text = it, fontSize = 16.sp, color = Color.Gray)
        }
        noti.subText?.let {
            Text(text = "Subtext: $it", fontSize = 14.sp, color = Color.Gray)
        }
        noti.bigText?.let {
            Text(text = "Big text: $it", fontSize = 14.sp, color = Color.Gray)
        }
        noti.extraMessages?.let {
            Text(text = "Extra Messages: $it", fontSize = 14.sp, color = Color.Gray)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Posted: ${noti.postTime.toLongDate()}", fontSize = 12.sp, color = Color.Gray)
        Text(
            text = "Ongoing: ${if (noti.isOngoing) "Yes" else "No"}",
            fontSize = 12.sp,
            color = Color.Gray
        )
        Text(text = "Seen: ${if (noti.seen) "Yes" else "No"}", fontSize = 12.sp, color = Color.Gray)
    }
}




