package dev.goobar.androidstudyguide

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.HiltAndroidApp
import dev.goobar.androidstudyguide.sync.FeaturedReposSyncWorker
import javax.inject.Inject

@HiltAndroidApp
class AndroidStudyGuideApplication : Application(), Configuration.Provider {

  @Inject lateinit var workerFactory: HiltWorkerFactory

  override fun getWorkManagerConfiguration() =
    Configuration.Builder()
      .setWorkerFactory(workerFactory)
      .build()

  override fun onCreate() {
    super.onCreate()

    FirebaseCrashlytics.getInstance().log("Setting SDK version")
    val crashlytics = FirebaseCrashlytics.getInstance().apply {
      setCustomKey("sdk-version", 1)
    }

    val uploadWorkRequest: WorkRequest =
      OneTimeWorkRequestBuilder<FeaturedReposSyncWorker>()
        .build()

    WorkManager
      .getInstance(this)
      .enqueue(uploadWorkRequest)
  }
}