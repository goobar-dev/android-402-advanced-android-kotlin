package dev.goobar.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Topic(
  val title: String,
  val categories: List<String>,
  val content: String
)