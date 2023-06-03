package ru.bezraznicy.promissingfuture.presentation.screen.models.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material.icons.outlined.TimerOff
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
import ru.bezraznicy.promissingfuture.domain.model.Event
import ru.bezraznicy.promissingfuture.presentation.theme.PromissingFutureTheme
import java.time.Duration
import java.time.Period
import java.time.ZonedDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventItem(
    event: Event,
    dismissState: DismissState = rememberDismissState(),
    onClick: () -> Unit = {},
    onLongClick: () -> Unit = {},
    onSwipeRemove: () -> Unit = {},
    onSwipeShare: () -> Unit = {}
) {
    ModelItem(
        dismissState = dismissState,
        onClick = onClick,
        onLongClick = onLongClick,
        onSwipeRemove = onSwipeRemove,
        onSwipeShare = onSwipeShare
    ) {
        ListItem(
            headlineContent = {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = event.name)
                    Row {
                        if (event.time != null) {
                            val remainingTime = getRemainingTime(event.time)
                            // Если осталось меньше минуты, то показывать не нужно таймер
                            val s = remainingTime.seconds
                            Text(text = "%02d:%02d".format(s / 3600, (s % 3600) / 60))
                            Icon(
                                imageVector = if (remainingTime == Duration.ZERO) Icons.Outlined.TimerOff else Icons.Outlined.Timer,
                                contentDescription = null
                            )
                        }
                    }
                }
            },
            supportingContent = {
                event.description?.let {
                    Text(
                        text = it,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        )
    }
}

fun getRemainingTime(cronExpression: String): Duration {
    val parts = cronExpression.split("$")
    val startDateTime = ZonedDateTime.parse(parts[0])
    val period = if (parts.size == 3) Period.parse(parts[2]) else Period.ZERO
    var triggerTime = startDateTime.plus(period)
    if (period != Period.ZERO && triggerTime < ZonedDateTime.now()) {
        do {
            triggerTime = triggerTime.plus(period)
        } while (triggerTime < ZonedDateTime.now())
    } else if (triggerTime < ZonedDateTime.now()) {
        return Duration.ZERO
    }
    return Duration.between(ZonedDateTime.now(), triggerTime)
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
                    ""
                )
            )
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
                )
            )
        }
    }
}