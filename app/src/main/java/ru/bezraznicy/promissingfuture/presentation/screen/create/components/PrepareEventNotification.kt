package ru.bezraznicy.promissingfuture.presentation.screen.create.components

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import ru.bezraznicy.promissingfuture.domain.model.Event
import ru.bezraznicy.promissingfuture.presentation.notification.NotificationPublisher
import java.time.Period
import java.time.ZonedDateTime

fun scheduleEventNotification(context: Context, event: Event) {
    if (!event.time.isNullOrEmpty()) {
        val parts = event.time.split("/")
        val startDateTime = ZonedDateTime.parse(parts[0])
        val period = if (parts.size == 3) Period.parse(parts[2]) else Period.ZERO

        val triggerTime = startDateTime.plus(period).toInstant().toEpochMilli()
        val notificationIntent = Intent(context, NotificationPublisher::class.java)
        notificationIntent.putExtra("id", event.id)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            event.id!!.toInt(),
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent)
    }
}
