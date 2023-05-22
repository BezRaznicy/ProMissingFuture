package ru.bezraznicy.promissingfuture.presentation

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import ru.bezraznicy.promissingfuture.presentation.navigation.SetupNavGraph
import ru.bezraznicy.promissingfuture.presentation.theme.PromissingFutureTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val channelId = "promissingfuture-events"
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationChannel = notificationManager.getNotificationChannel(channelId)
        if (notificationChannel == null) {
            createNotificationChannel()
        }
        setContent {
            PromissingFutureTheme {
                val navController = rememberNavController()
                Surface(color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()) {
                    SetupNavGraph(navController = navController)
                }
            }
        }
    }

    private fun createNotificationChannel() {
        val channelId = "promissingfuture-events"
        val channelName = "События"
        val channelDescription = "Канал уведомления для событий"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val notificationChannel = NotificationChannel(channelId, channelName, importance)
        notificationChannel.description = channelDescription
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)
    }
}