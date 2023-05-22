package ru.bezraznicy.promissingfuture.presentation.screen.create.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import ru.bezraznicy.promissingfuture.presentation.theme.PromissingFutureTheme
import java.time.Duration
import java.time.Period
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date

@Composable
fun TimeNotificationField(onValueChange: (String) -> Unit, modifier: Modifier = Modifier) {
    // Date
    var startDateTime by remember { mutableStateOf(ZonedDateTime.now(ZoneId.systemDefault())) }
    // Period
    var isPeriodic by remember { mutableStateOf(true) }
    var period by remember { mutableStateOf(Period.ZERO) }
    // Duration
    var hasDuration by remember { mutableStateOf(true) }
    var duration by remember { mutableStateOf(Duration.ZERO) }

    LaunchedEffect(key1 = startDateTime, key2 = period, key3 = duration) {
        val result =
            if (isPeriodic)
                "$startDateTime$$duration$$period"
            else
                "$startDateTime$$duration"

        onValueChange(result)
    }

    Column(modifier = modifier) {
        Text(
            text = "Точка отсчёта: " +
                    startDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
        )
        // Start date&time
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            StartDatePicker(
                startDateTime,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
            ) { year, month, dayOfMonth ->
                startDateTime = startDateTime
                    .withYear(year)
                    .withMonth(month)
                    .withDayOfMonth(dayOfMonth)
            }
            StartTimePicker(
                startDateTime,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
            ) { hour, minute ->
                startDateTime = startDateTime
                    .withHour(hour)
                    .withMinute(minute)
            }
        }
        // Is pereodic
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 18.dp)
        ) {
            Checkbox(
                checked = isPeriodic,
                onCheckedChange = { isPeriodic = it }
            )
            Text(
                text = "Повторять напоминание",
                style = MaterialTheme.typography.bodyLarge
            )
        }
        if (isPeriodic) {
            Text(
                text = "Повторять каждые:",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 12.dp)
            )
            var days by remember { mutableStateOf("") }
            var months by remember { mutableStateOf("") }
            var years by remember { mutableStateOf("") }
            Row {
                OutlinedTextField(
                    value = days,
                    onValueChange = {
                        days = it; if (it.isDigitsOnly()) period = period.withDays(it.toInt())
                    },
                    label = { Text("День") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 6.dp)
                )
                OutlinedTextField(
                    value = months,
                    onValueChange = {
                        months = it; if (it.isDigitsOnly()) period = period.withMonths(it.toInt())
                    },
                    label = { Text("Месяц") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .weight(2f)
                        .padding(horizontal = 6.dp)
                )
                OutlinedTextField(
                    value = years,
                    onValueChange = {
                        years = it; if (it.isDigitsOnly()) period = period.withYears(it.toInt())
                    },
                    label = { Text("Год") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 6.dp)
                )
            }
        }
        // Has duration
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 18.dp)
        ) {
            Checkbox(
                checked = hasDuration,
                onCheckedChange = { hasDuration = it },
            )
            Text(
                text = "Указать длительность для постоянного уведомления",
                style = MaterialTheme.typography.bodyLarge
            )
        }
        if (hasDuration) {
            Text(
                text = "Длительность:",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 12.dp, top = 6.dp)
            )
            var minutes by remember { mutableStateOf("") }
            var hours by remember { mutableStateOf("") }
            var days by remember { mutableStateOf("") }
            Row {
                OutlinedTextField(
                    value = minutes,
                    onValueChange = {
                        minutes = it
                        if (it.isDigitsOnly())
                            duration = duration.withSeconds(it.toLong() * 60L)
                    },
                    label = { Text("Минут") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 6.dp)
                )
                OutlinedTextField(
                    value = hours,
                    onValueChange = {
                        hours = it
                        if (it.isDigitsOnly())
                            duration = duration.withSeconds(it.toLong() * 60L * 60L)
                    },
                    label = { Text("Часов") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 6.dp)
                )
                OutlinedTextField(
                    value = days,
                    onValueChange = {
                        days = it
                        if (it.isDigitsOnly())
                            duration = duration.withSeconds(it.toLong() * 60 * 60 * 24)
                    },
                    label = { Text("Дней") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 6.dp)
                )
            }
        }
        // Notifications
        if (hasDuration || isPeriodic) {
            Text(
                text = "Уведомление:",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 12.dp, top = 6.dp)
            )
            val format = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
            LazyColumn {
                items(if (isPeriodic) 5 else 1) {
                    var date = startDateTime
                    for (i in 0..it) {
                        date = date.plus(period)
                    }
                    Text(
                        text = date.format(format)
                                + if (hasDuration) " - " + date.plus(duration).format(format) else "",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 12.dp, top = 3.dp)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartDatePicker(
    zonedDateTime: ZonedDateTime,
    modifier: Modifier = Modifier,
    onValueChange: (Int, Int, Int) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    if (showDialog) {
        val state = rememberDatePickerState(zonedDateTime.toEpochSecond() * 1000)
        DatePickerDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                val cal = Calendar.getInstance()
                cal.time = Date(state.selectedDateMillis!!)
                onValueChange(cal[Calendar.YEAR], cal[Calendar.MONTH], cal[Calendar.DAY_OF_MONTH])
            }
        ) {
            DatePicker(state = state, title = {})
        }
    }
    OutlinedButton(onClick = { showDialog = true }, modifier = modifier) {
        Text(text = "Указать дату", color = Color.DarkGray)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartTimePicker(
    zonedDateTime: ZonedDateTime,
    modifier: Modifier = Modifier,
    onValueChange: (Int, Int) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    if (showDialog) {
        val timePickerState by remember {
            mutableStateOf(TimePickerState(zonedDateTime.hour, zonedDateTime.minute, true))
        }
        AlertDialog(onDismissRequest = { showDialog = false }) {
            Surface(color = MaterialTheme.colorScheme.background, shape = MaterialTheme.shapes.large) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    TimePicker(state = timePickerState)
                    TextButton(onClick = {
                        showDialog = false
                        onValueChange(timePickerState.hour, timePickerState.minute)
                    }) {
                        Text(text = "Подтвердить", color = Color.DarkGray)
                    }
                }
            }
        }
    }
    OutlinedButton(onClick = { showDialog = true }, modifier = modifier) {
        Text(text = "Указать время", color = Color.DarkGray)
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    showSystemUi = true
)
@Composable
fun CronFormatFieldPreviewLight() {
    PromissingFutureTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            TimeNotificationField(onValueChange = {})
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    showSystemUi = true
)
@Composable
fun CronFormatFieldPreviewDark() {
    PromissingFutureTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            TimeNotificationField(onValueChange = {})
        }
    }
}