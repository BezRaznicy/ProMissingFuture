package ru.bezraznicy.promissingfuture.presentation.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf

class NotificationPublisher : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val workData = workDataOf("id" to intent.getLongExtra("id", -1))
        val workRequest = OneTimeWorkRequestBuilder<EventNotificationWorker>().setInputData(workData).build()
        WorkManager.getInstance(context).enqueue(workRequest)
    }
}
