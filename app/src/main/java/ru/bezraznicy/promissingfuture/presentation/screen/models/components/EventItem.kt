package ru.bezraznicy.promissingfuture.presentation.screen.models.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.DismissState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import org.quartz.CronExpression
import ru.bezraznicy.promissingfuture.domain.model.Event
import ru.bezraznicy.promissingfuture.presentation.theme.PromissingFutureTheme
import java.time.temporal.ChronoUnit
import java.util.Date
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventItem(
    event: Event,
    dismissState: DismissState,
    onClick: () -> Unit,
    onSwipeRemove: () -> Unit,
    onSwipeShare: () -> Unit
) {
    ModelItem(
        dismissState = dismissState,
        onClick = onClick,
        onSwipeRemove = onSwipeRemove,
        onSwipeShare = onSwipeShare) {
        ListItem(
            headlineContent = {
                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                    Text(text = event.name)
                    Row {
                        if (event.time != null) {
                            val remainingTime = getCronInterval(event.time)
                            // Если осталось меньше минуты, то показывать не нужно таймер
                            if (remainingTime > 60000L) {
                                Text(text = formatDuration(remainingTime))
                            }
                            Icon(imageVector = Icons.Outlined.Timer, contentDescription = null)
                        }
                    }
                }
            },
            supportingContent = {
                event.description?.let { Text(text = it, maxLines = 2, overflow = TextOverflow.Ellipsis) }
            }
        )
    }
}

fun getCronInterval(cronExpression: String): Long {
    val cron = CronExpression(cronExpression)
    val nextExecutionTime = cron.getNextValidTimeAfter(Date())
    return nextExecutionTime.time - Date().time
}

fun formatDuration(milliseconds: Long): String {
    val days = (milliseconds / (1000 * 60 * 60 * 24)).toInt()
    val hours = ((milliseconds / (1000 * 60 * 60)) % 24).toInt()
    val minutes = ((milliseconds / (1000 * 60)) % 60).toInt()
    return if (days > 0) {
        String.format("%d:%02d:%02d", days, hours, minutes)
    } else if (hours > 0) {
        String.format("%02d:%02d", hours, minutes)
    } else {
        String.format("%02d", minutes)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun EventItemPreviewLight() {
    PromissingFutureTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            EventItem(
                event = Event(
                    "Название",
                    "Lorem ipsum de falso nor astranda Lorem ipsum de falso nor astranda Lorem ipsum de falso nor astranda Lorem ipsum de falso nor astranda",
                    "* * * 18 * ? *"
                ),
                dismissState = rememberDismissState(),
                onClick = { /*TODO*/ },
                onSwipeRemove = { /*TODO*/ }) {

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun EventItemPreviewDark() {
    PromissingFutureTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            EventItem(
                event = Event(
                    "Название",
                    "Lorem ipsum de falso nor astranda Lorem ipsum de falso nor astranda Lorem ipsum de falso nor astranda Lorem ipsum de falso nor astranda",
                    "* * * ? * * *"
                ),
                dismissState = rememberDismissState(),
                onClick = { /*TODO*/ },
                onSwipeRemove = { /*TODO*/ }) {

            }
        }
    }
}