package ir.hadiagdamapps.notisave.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import ir.hadiagdamapps.notisave.models.NotiGroup
import ir.hadiagdamapps.notisave.ui.theme.ApplicationTheme
import ir.hadiagdamapps.notisave.ui.theme.Color

@Composable
fun GroupItem(
    group: NotiGroup
) {

    Row(
        Modifier
            .height(86.dp)
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.surface)
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    )
    {

        Box(
            Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(androidx.compose.ui.graphics.Color.White)
        ) {
            group.icon?.let {
                Image(
                    painter = rememberDrawablePainter(it),
                    contentDescription = "App Icon",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }


        Text(
            group.title,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f).padding(horizontal = 16.dp)
        )



        Box(
            Modifier
                .size(24.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(androidx.compose.ui.graphics.Color.LightGray),
            contentAlignment = Alignment.Center
            ) {
            Text(
                text = "11",
                textAlign = TextAlign.Center,
                color = androidx.compose.ui.graphics.Color.Black,
                style = MaterialTheme.typography.labelSmall,
            )
        }


    }

}


@Preview
@Composable
private fun GroupItemPreview() {
    ApplicationTheme {
        Column(Modifier.fillMaxSize()) {
            GroupItem(
                NotiGroup(
                    "Test Title",
                    null,
                    0
                )
            )
        }
    }
}