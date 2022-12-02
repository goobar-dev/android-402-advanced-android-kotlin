package dev.goobar.androidstudyguide.network

import dev.goobar.data.FeaturedRepo
import dev.goobar.data.FeaturedRepoResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface GitHubFeaturedReposService {

  @Headers("Accept: application/vnd.github+json")
  @GET("topics?q=Android+is:featured")
  suspend fun getFeaturedAndroidRepos(): FeaturedRepoResponse
}