package ru.bezraznicy.promissingfuture.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import ru.bezraznicy.promissingfuture.presentation.navigation.SetupNavGraph
import ru.bezraznicy.promissingfuture.presentation.theme.PromissingFutureTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PromissingFutureTheme {
                val navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}