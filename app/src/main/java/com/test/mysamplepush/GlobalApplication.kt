package com.test.mysamplepush

import android.app.Application
import com.kakao.auth.*


class GlobalApplication : Application() {
    public class KakaoSDKAdapter : KakaoAdapter() {
        /**
         * Session Config에 대해서는 default값들이 존재한다.
         * 필요한 상황에서만 override해서 사용하면 됨.
         *
         * @return Session의 설정값.
         */
        override fun getSessionConfig(): ISessionConfig {
            return object : ISessionConfig {
                override fun getAuthTypes(): Array<AuthType> {
                    return arrayOf(AuthType.KAKAO_LOGIN_ALL)
                }

                override fun isUsingWebviewTimer(): Boolean {
                    return false
                }

                override fun isSecureMode(): Boolean {
                    return false
                }

                override fun getApprovalType(): ApprovalType {
                    return ApprovalType.INDIVIDUAL
                }

                override fun isSaveFormData(): Boolean {
                    return true
                }
            }
        }

        override fun getApplicationConfig(): IApplicationConfig {
            return IApplicationConfig { globalApplicationContext }
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private var instance: GlobalApplication? = null
        val globalApplicationContext: GlobalApplication
            get() {
                if (instance == null)
                    throw IllegalStateException("this application does not inherit com.kakao.GlobalApplication")
                return instance as GlobalApplication
            }
    }
}



