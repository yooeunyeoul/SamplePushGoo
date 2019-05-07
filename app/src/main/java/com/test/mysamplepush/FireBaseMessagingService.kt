package com.test.mysamplepush

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.support.v4.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FireBaseMessagingService : FirebaseMessagingService() {

    /**
     * 구글 토큰을 얻는 값입니다.
     * 아래 토큰은 앱이 설치된 디바이스에 대한 고유값으로 푸시를 보낼때 사용됩니다.
     * **/
    override fun onNewToken(s: String?) {
        super.onNewToken(s)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {

        if (remoteMessage?.data?.size!! > 0) {
            sendNotification(remoteMessage)
        }

    }

    private fun sendNotification(remoteMessage: RemoteMessage?) {

        val title = remoteMessage?.data?.get("title")
        val message = remoteMessage?.data?.get("message")


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = "채널"
            val channel_nm = "채널명"

            val notiChannel = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channelMessage = NotificationChannel(channel, channel_nm, NotificationManager.IMPORTANCE_DEFAULT)
            channelMessage.description = "채널에 대한 설명"
            channelMessage.enableLights(true)
            channelMessage.enableVibration(true)
            channelMessage.setShowBadge(false)
            channelMessage.vibrationPattern = longArrayOf(100, 200, 100, 200)
            notiChannel.createNotificationChannel(channelMessage)

            val notificationBuilder = NotificationCompat.Builder(this, channel)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentText(message)
                .setChannelId(channel)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND or Notification.DEFAULT_VIBRATE)

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(9999, notificationBuilder.build())

        } else {
            val notificationBuilder = NotificationCompat.Builder(this,"")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentTitle(message)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND or Notification.DEFAULT_VIBRATE)

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(9999, notificationBuilder.build())

        }

    }

}
