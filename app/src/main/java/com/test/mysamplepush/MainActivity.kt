package com.test.mysamplepush

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Base64
import android.util.Log
import com.kakao.auth.KakaoSDK
import com.kakao.util.helper.Utility.getPackageInfo
import kotlinx.android.synthetic.main.activity_main.*
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class MainActivity : AppCompatActivity() {

    private var hashKey: String = ""
    private var target_url: String? = "https://www.daum.net/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        KakaoSDK.init(GlobalApplication.KakaoSDKAdapter())

        KakaoSession.instance.setListener(object : KakaoListener {
            override fun success() {

            }
        })

        hashKey = getKeyHash(this)

        Log.e("hashKey ", "HashKey : $hashKey")

        com_kakao_login.setOnClickListener {

        }

    }

    private fun getKeyHash(context: Context): String {
        val packageInfo = getPackageInfo(context, PackageManager.GET_SIGNATURES) ?: return ""

        for (signature in packageInfo!!.signatures) {
            try {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                return Base64.encodeToString(md.digest(), Base64.NO_WRAP)
            } catch (e: NoSuchAlgorithmException) {
                Log.e("Exceptoin", e.printStackTrace().toString())
            }
        }
        return ""
    }
}
