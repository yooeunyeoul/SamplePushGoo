package com.test.mysamplepush

import com.facebook.FacebookException
import org.json.JSONObject

interface FaceBookListener {
    fun success(jsonObj : JSONObject)
    fun onFailure(error : FacebookException)
}