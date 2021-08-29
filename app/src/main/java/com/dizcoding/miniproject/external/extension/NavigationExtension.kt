package com.dizcoding.miniproject.external.extension

import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.dizcoding.miniproject.external.Constant

fun Fragment.navigationTo(path : String, params : List<Any> = listOf()){
    var paramString = ""
    params.forEach {
        paramString+= "${it}/"
    }
    if (!paramString.isNullOrBlank()) paramString = "/$paramString".dropLast(1)
    val stringUri = "${Constant.BASE_DEEPLINK_PATH}$path$paramString"
    val request = NavDeepLinkRequest.Builder
        .fromUri(stringUri.toUri())
        .build()
    findNavController().navigate(request)
}