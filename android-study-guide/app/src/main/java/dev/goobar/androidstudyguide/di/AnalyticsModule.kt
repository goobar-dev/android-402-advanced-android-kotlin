package dev.goobar.androidstudyguide.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.goobar.analytics.LogcatLogger
import dev.goobar.analytics.Logger

@Module
@InstallIn(ViewModelComponent::class)
abstract class AnalyticsModule {

    @Binds
    abstract fun bindLogger(loggerImpl: LogcatLogger): Logger
}