package ir.hadiagdamapps.notisave.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.hadiagdamapps.notisave.ui.theme.ApplicationTheme
import ir.hadiagdamapps.notisave.ui.theme.Color
import ir.hadiagdamapps.notisave.utils.toMinutes

@Composable
fun NotificationItem(
    modifier: Modifier = Modifier,
    title: String,
    text: String,
    time: Long,
    seen: Boolean
) {

    Row(
        modifier
            .height(80.dp)
            .fillMaxWidth()
            .padding(2.dp)
            .background(Color.surface)
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    )
    {
        Column(
            Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxWidth(),
                overflow = TextOverflow.Ellipsis
            )
        }


        Column(Modifier.fillMaxHeight().padding(vertical = 14.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                Modifier
                    .size(16.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (!seen)
                        androidx.compose.ui.graphics.Color.LightGray
                    else Color.surface),
            )

            Spacer(Modifier.weight(1f))

            Text(time.toMinutes(), style = MaterialTheme.typography.labelSmall)

        }


    }

}


@Preview
@Composable
private fun NotificationItemPreview() {
    ApplicationTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.background)
        ) {
            NotificationItem(
                title = "Title",
                text = "text".repeat(20),
                time = 0L,
                seen = false
            )
        }
    }
}
