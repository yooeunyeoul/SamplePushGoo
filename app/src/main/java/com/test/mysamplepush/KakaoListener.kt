package com.test.mysamplepush

import com.kakao.network.ErrorResult
import com.kakao.usermgmt.response.model.UserProfile

interface KakaoListener {
    fun success(userProfile : UserProfile)
    fun onFailure(errorResult : ErrorResult)
}