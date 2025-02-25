package ir.hadiagdamapps.notisave.ui.screen

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
import ir.hadiagdamapps.notisave.navigation.route.AppNotificationRoute
import ir.hadiagdamapps.notisave.ui.component.GroupItem
import ir.hadiagdamapps.notisave.ui.theme.Color
import ir.hadiagdamapps.notisave.viewmodels.GroupScreenViewModel

@Composable
fun GroupsScreen(
    navController: NavController,
    viewmodel: GroupScreenViewModel
) {
    val groups = viewmodel.groupedNotifications.collectAsState(listOf())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.background)
            .padding(top = 24.dp)
    ) {

        Text(
            "Applications", modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        LazyColumn {
            items(groups.value) {
                GroupItem(it, modifier = Modifier.clickable { navController.navigate(AppNotificationRoute(it.packageName)) })
            }
        }

    }
}
