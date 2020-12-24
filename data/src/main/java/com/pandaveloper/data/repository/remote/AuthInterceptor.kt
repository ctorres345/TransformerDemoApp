package com.pandaveloper.data.repository.remote

import android.content.Context
import com.pandaveloper.data.repository.local.SessionManager
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor(@ApplicationContext private val context: Context) : Interceptor{
    private val sessionManager = SessionManager(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        sessionManager.getToken()?.let {
            if(it.isNotEmpty()){
                requestBuilder.addHeader("Authorization", "Bearer $it")
            }
        }
        return chain.proceed(requestBuilder.build())
    }
}