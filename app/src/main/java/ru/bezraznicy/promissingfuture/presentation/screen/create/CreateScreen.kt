package ru.bezraznicy.promissingfuture.presentation.screen.create

import android.content.res.Configuration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ru.bezraznicy.promissingfuture.presentation.screen.create.vm.CreateEvent
import ru.bezraznicy.promissingfuture.presentation.screen.create.vm.CreateState
import ru.bezraznicy.promissingfuture.presentation.theme.PromissingFutureTheme

@Composable
fun CreateScreen(
    createState: CreateState? = null,
    onEvent: (CreateEvent) -> Unit = {}
) {

}

@Preview(showSystemUi = true)
@Composable
fun CreatePreviewLightTheme() {
    PromissingFutureTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            CreateScreen()
        }
    }
}

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
)
@Composable
fun CreatePreviewDarkTheme() {
    PromissingFutureTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            CreateScreen()
        }
    }
}