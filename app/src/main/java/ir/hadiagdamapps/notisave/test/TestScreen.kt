package ir.hadiagdamapps.notisave.test

import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ir.hadiagdamapps.notisave.data.local.database.AppDatabase
import ir.hadiagdamapps.notisave.service.NotiService
import ir.hadiagdamapps.notisave.ui.component.GroupItem

@Composable
fun TestScreen(context: Context) {

    val dao = AppDatabase.getDatabase(context).notiDao()
    val content = dao.getItems().collectAsState(listOf())

    context.apply {
    Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Button(onClick = {
            val intent = Intent(this@apply, NotiService::class.java)
            startService(intent)
        }) {
            Text("start notification service")
        }

        Button(onClick = {
            TestNotification(this@apply).post()
        }) { Text("post test notification") }

        Button(onClick = {
            val intent = Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)
            startActivity(intent)
        }) { Text("permission settings") }

        LazyColumn {
            items(content.value) {
                Text(it.text.toString())
            }
        }


    }
    }
}