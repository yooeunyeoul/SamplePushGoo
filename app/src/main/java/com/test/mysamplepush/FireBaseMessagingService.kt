package com.test.mysamplepush

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FireBaseMessagingService : FirebaseMessagingService() {
    override fun zzd(p0: Intent?) {

    }

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {

        if (remoteMessage?.data?.size!! > 0) {


        }

        sendNotification(remoteMessage?.notification?.body)

    }

    private fun sendNotification(messageBody: String?) {



    }


//    override fun zzd(p0: Intent?) {
//
//    }
//    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
//
//        Log.d(TAG, "From: " + remoteMessage!!.from!!)
//
//
//
//        if (remoteMessage.data.size > 0) {
//
//            Log.d(TAG, "Message data payload: " + remoteMessage.data)
//
//
//
//            if (true) {
//
//            } else {
//
//                handleNow()
//
//            }
//
//        }
//
//        if (remoteMessage.notification != null) {
//
//            Log.d(TAG, "Message Notification Body: " + remoteMessage.notification!!.body!!)
//
//            sendNotification(remoteMessage.notification!!.body)
//
//        }
//
//    }
//
//    private fun handleNow() {
//
//        Log.d(TAG, "Short lived task is done.")
//
//    }
//
//
//    private fun sendNotification(messageBody: String?) {
//
//        val intent = Intent(this, MainActivity::class.java)
//
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//
//        val pendingIntent = PendingIntent.getActivity(
//            this, 0, intent,
//
//            PendingIntent.FLAG_ONE_SHOT
//        )
//
//
//        //        String channelId = getString(R.string.default_notification_channel_id);
//
//        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
//
//        val notificationBuilder =
//
//            NotificationCompat.Builder(this, channelId)
//
//                .setSmallIcon(R.mipmap.ic_launcher)
//
//                .setContentTitle("FCM Message")
//
//                .setContentText(messageBody)
//
//                .setAutoCancel(true)
//
//                .setSound(defaultSoundUri)
//
//                .setContentIntent(pendingIntent)
//
//
//        val notificationManager =
//
//            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//
//            val channelName = getString(R.string.default_notification_channel_name)
//
//            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
//
//            notificationManager.createNotificationChannel(channel)
//
//        }
//
//        notificationManager.notify(0, notificationBuilder.build())
//
//    }
//
//    companion object {
//
//        private val TAG = "MyFirebaseMsgService"
//    }

}
