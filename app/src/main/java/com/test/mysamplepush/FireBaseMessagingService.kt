package com.test.mysamplepush

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.support.v4.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import java.net.URL


class FireBaseMessagingService : FirebaseMessagingService() {

    private var bigPicture: Bitmap? = null

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

    /**

    ## FLAG_CANCEL_CURRENT : 이전에 생성한 PendingIntent는 취소하고 새로 만든다.

    ## FLAG_IMMUTABLE : 생성된 PendingIntent 는 수정 불가능하도록 한다.

    ## FLAG_NO_CREATE : 생성된 PendingIntent 를 반환한다.(재사용 가능)

    ## FLAG_ONE_SHOT : 해당 Flag로 생성한 PendingIntent 는 일회성이다.

    ## FLAG_UPDATE_CURRENT : 이미 생성된 PendingIntent가 존재하면 해당 Intent의 내용을 바꾼다.

     */
    private fun sendNotification(remoteMessage: RemoteMessage?) {
        val title = remoteMessage?.data?.get("title")
        val message = remoteMessage?.data?.get("message")
        val myImgUrl = remoteMessage?.data?.get("image")
        val linkUrl = remoteMessage?.data?.get("linkurl")

//        val intent = Intent(this, MainActivity::class.java)

        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(linkUrl))
//        browerIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//
//        val bundle = Bundle()
//        bundle.putString("url", linkUrl)
//
//        intent.putExtras(bundle)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, browserIntent,
            PendingIntent.FLAG_ONE_SHOT
        )

        //이미지 온라인 링크를 가져와 비트맵으로 바꾼다.
        try {
            val url = URL(myImgUrl)
            bigPicture = BitmapFactory.decodeStream(url.openConnection().getInputStream())
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val channel = "채널"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

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
                .setLargeIcon(bigPicture)
                .setContentText(message)
                .setChannelId(channel)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND or Notification.DEFAULT_VIBRATE)
                .setStyle(
                    NotificationCompat.BigTextStyle()
                        .setBigContentTitle("FCM PUSH BIG TEXT")
                        .bigText(message)
                )
                .setStyle(
                    NotificationCompat.BigPictureStyle()
                        .bigPicture(bigPicture)
                        .setBigContentTitle("FCM Push")
                        .setSummaryText(message)
                )
                .setContentIntent(pendingIntent)

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(9999, notificationBuilder.build())

        } else {
            val notificationBuilder = NotificationCompat.Builder(this, channel)
                .setSmallIcon(R.drawable.ic_action_name)
                .setContentTitle(title)
                .setLargeIcon(bigPicture)
                .setContentTitle(message)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND or Notification.DEFAULT_VIBRATE)
                .setColor(Color.BLUE)
                .setStyle(
                    NotificationCompat.BigTextStyle()
                        .setBigContentTitle("FCM PUSH BIG TEXT")
                        .bigText(message)
                )
                .setStyle(
                    NotificationCompat.BigPictureStyle()
                        .bigPicture(bigPicture)
                        .setBigContentTitle("FCM Push")
                        .setSummaryText(message)
                )
                .setContentIntent(pendingIntent)


            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(9999, notificationBuilder.build())

        }

    }

}
