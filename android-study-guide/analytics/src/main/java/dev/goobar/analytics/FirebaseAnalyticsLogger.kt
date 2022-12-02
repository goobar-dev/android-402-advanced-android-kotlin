package dev.goobar.analytics

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class FirebaseAnalyticsLogger @Inject constructor(@ApplicationContext private val context: Context) : Logger {

    override fun log(eventName: String, properties: Map<String, Any>) {
        FirebaseAnalytics.getInstance(context).logEvent(
            eventName.replace(" ", "_").lowercase(),
            Bundle().apply {
                properties.forEach { entry ->
                    val (key, value) = entry
                    putString(key, value.toString())
                }
            }
        )
    }
}