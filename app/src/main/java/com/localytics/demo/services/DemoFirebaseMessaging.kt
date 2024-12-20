package com.localytics.demo.services

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.text.TextUtils
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.localytics.androidx.Localytics
import com.localytics.demo.MainActivity
import com.localytics.demo.R

class DemoFirebaseMessaging : FirebaseMessagingService() {

    private  val NOTIFICATION_TEST_CHANNEL: String = "test_channel"

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Localytics.setPushRegistrationId(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        val data: Map<String, String> = remoteMessage.getData()
        if (data.isEmpty()) {
            if (remoteMessage.getNotification() != null) {
                showNotification(remoteMessage.notification?.body!!)
            }
        } else if (!Localytics.handleFirebaseMessage(data)) {
            showNotification(remoteMessage.notification?.title ?: "Unable to display")
        }
    }

    private fun showNotification(message: String) {
        if (!TextUtils.isEmpty(message)) {
            val intent = Intent(
                this, MainActivity::class.java
            )
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            val pendingIntent = PendingIntent.getActivity(
                this, 0,  /* Request code */
                intent, PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
            )

            val notificationBuilder: NotificationCompat.Builder =
                NotificationCompat.Builder(this, NOTIFICATION_TEST_CHANNEL)
                    .setSmallIcon(R.mipmap.ic_launcher).setContentTitle("FCM Message")
                    .setContentText(message).setAutoCancel(true)
                    .setDefaults(Notification.DEFAULT_SOUND or Notification.DEFAULT_VIBRATE)
                    .setContentIntent(pendingIntent)
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(0, notificationBuilder.build())
        }
    }


}