package dev.goobar.androidstudyguide.sync

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dev.goobar.androidstudyguide.db.RepoDao
import dev.goobar.androidstudyguide.network.GitHubFeaturedReposService
import dev.goobar.data.RepoEntity

@HiltWorker
class FeaturedReposSyncWorker @AssistedInject constructor(
  private val featuredReposService: GitHubFeaturedReposService,
  private val repoDao: RepoDao,
  @Assisted appContext: Context,
  @Assisted workerParams: WorkerParameters
): CoroutineWorker(appContext, workerParams) {

  override suspend fun doWork(): Result {

    // todo fetch featured repos and save them to the database

    return Result.success()
  }
}