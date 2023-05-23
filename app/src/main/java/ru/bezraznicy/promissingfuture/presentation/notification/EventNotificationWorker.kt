package ru.bezraznicy.promissingfuture.presentation.notification

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import ru.bezraznicy.promissingfuture.R
import ru.bezraznicy.promissingfuture.common.PromissingFuture

class EventNotificationWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        val id = inputData.getLong("id", -1L)
        if (id == -1L) {
            val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val notification = NotificationCompat.Builder(applicationContext, "promissingfuture-events")
                .setContentTitle("Какое-то событие наступило!")
                .setContentText("Мы не знаем какое событие из-за предвиденной ошибки")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .build()
            notificationManager.notify(id.toInt(), notification)
            return Result.failure()
        }
        // Perform the database operation here
        val eventRepo = (applicationContext as PromissingFuture).eventRepository
        val event = eventRepo.selectById(id)
        // ...
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationCompat.Builder(applicationContext, "promissingfuture-events")
            .setContentTitle("Событие ${event.name}!")
            .setContentText("Описание: ${event.description}")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setAutoCancel(true)
            .build()
        notificationManager.notify(id.toInt(), notification)
        if (event.time?.split("$")?.size == 3)
            scheduleEventNotification(applicationContext, event)
        return Result.success()
    }
}