package dev.goobar.analytics

import android.util.Log
import javax.inject.Inject

class LogcatLogger @Inject constructor() : Logger {
  override fun log(eventName: String, properties: Map<String, Any>) {
    Log.d(eventName, properties.toString())
  }
}