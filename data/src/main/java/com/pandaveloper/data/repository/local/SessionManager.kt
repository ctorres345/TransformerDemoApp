package com.pandaveloper.data.repository.local

import android.content.Context
import android.content.SharedPreferences
import com.pandaveloper.data.util.SHARED_PREFERENCE_APP
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(@ApplicationContext private val context: Context) {

    private val sf: SharedPreferences by lazy {
        context.getSharedPreferences(SHARED_PREFERENCE_APP, Context.MODE_PRIVATE)
    }

    fun saveToken(token: String) {
        sf.edit().apply {
            putString(ALL_SPARK_TOKEN, token)
            apply()
        }
    }

    fun getToken() : String? {
        return sf.getString(ALL_SPARK_TOKEN, null)
    }

    fun clearData() {
        sf.edit().clear().apply()
    }

    companion object {
        const val ALL_SPARK_TOKEN = "all_spark_token"
    }
}