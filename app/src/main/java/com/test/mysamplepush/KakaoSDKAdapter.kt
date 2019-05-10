package com.test.mysamplepush

import com.kakao.auth.*

//class KakaoSDKAdapter : KakaoAdapter() {
//    override fun getApplicationConfig(): IApplicationConfig {
//        return object : IApplicationConfig {
//            override fun getApplicationContext(): Context {
//                return applicationContext
//            }
//        }
//    }
//
//    override fun getSessionConfig(): ISessionConfig {
//        return object : ISessionConfig {
//            override fun getAuthTypes(): Array<AuthType> {
//                return arrayOf(AuthType.KAKAO_LOGIN_ALL)
//            }
//
//            override fun isUsingWebviewTimer(): Boolean {
//                return false
//            }
//
//            override fun isSecureMode(): Boolean {
//                return false
//            }
//
//            override fun getApprovalType(): ApprovalType {
//                return ApprovalType.INDIVIDUAL
//            }
//
//            override fun isSaveFormData(): Boolean {
//                return true
//            }
//        }
//    }
//}

class KakaoSDKAdapter : KakaoAdapter() {

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

            override fun getApprovalType(): ApprovalType? {
                return ApprovalType.INDIVIDUAL
            }

            override fun isSaveFormData(): Boolean {
                return false
            }
        }
    }

    override fun getApplicationConfig(): IApplicationConfig {

        return IApplicationConfig { GlobalApplication.context() }
    }
}