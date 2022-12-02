package dev.goobar.androidstudyguide.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import dev.goobar.androidstudyguide.BuildConfig
import dev.goobar.androidstudyguide.network.GitHubFeaturedReposService
import dev.goobar.androidstudyguide.network.StudyGuideService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideStudyGuideService(): StudyGuideService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.STUDY_GUIDE_SERVICE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(StudyGuideService::class.java)
    }

    @Provides
    fun provideGitHubFeaturedReposService(): GitHubFeaturedReposService {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/search/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(GitHubFeaturedReposService::class.java)
    }
}