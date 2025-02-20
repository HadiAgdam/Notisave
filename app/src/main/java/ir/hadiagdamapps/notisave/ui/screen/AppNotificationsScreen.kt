package ir.hadiagdamapps.notisave.ui.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ir.hadiagdamapps.notisave.ui.component.NotificationItem
import ir.hadiagdamapps.notisave.ui.theme.Color
import ir.hadiagdamapps.notisave.viewmodels.AppNotificationsScreenViewModel

@Composable
fun AppNotificationsScreen(
    navController: NavController,
    viewModel: AppNotificationsScreenViewModel
) {

    val notifications = viewModel.notifications.collectAsState(listOf()).also {
        Log.e("found count", it.value.size.toString())
        it.value.forEachIndexed { index, noti ->
            Log.e("items collected $index", noti.toString())
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.background)
            .padding(top = 24.dp)
    ) {
        Text(
            "Notifications", Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        LazyColumn {

            items(notifications.value) {
                NotificationItem(
                    modifier = Modifier.clickable {
                        // TODO navigate
                        viewModel.onItemClick(it)
                    },
                    title = it.title ?: "NULL",
                    text = it.text ?: "NULL",
                    time = it.postTime,
                    seen = it.seen
                )
            }

        }
    }
}