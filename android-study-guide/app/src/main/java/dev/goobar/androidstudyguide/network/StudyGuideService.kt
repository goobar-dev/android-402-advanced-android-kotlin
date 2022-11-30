package dev.goobar.androidstudyguide.network

import dev.goobar.data.Topic
import retrofit2.http.GET

interface StudyGuideService {
    @GET("/topics")
    suspend fun getTopics(): List<Topic>
}