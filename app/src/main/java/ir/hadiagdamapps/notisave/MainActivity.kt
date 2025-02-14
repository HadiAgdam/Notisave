package ir.hadiagdamapps.notisave

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ir.hadiagdamapps.notisave.service.NotiService
import ir.hadiagdamapps.notisave.test.TestNotification
import ir.hadiagdamapps.notisave.ui.theme.NotisaveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotisaveTheme {
                Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    Button(onClick = {
                        val intent = Intent(this@MainActivity, NotiService::class.java)
                        startService(intent)
                    }) {
                        Text("start notification service")
                    }

                    Button(onClick = {
                        TestNotification(this@MainActivity).post()
                    }) { Text("post test notification") }

                    Button(onClick = {
                        val intent = Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)
                        startActivity(intent)
                    }) { Text("permission settings") }
                }
            }
        }
    }
}