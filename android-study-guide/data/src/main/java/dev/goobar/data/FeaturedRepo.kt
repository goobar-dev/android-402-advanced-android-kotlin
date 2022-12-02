package dev.goobar.data

import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.awt.Taskbar.Feature

@JsonClass(generateAdapter = true)
data class FeaturedRepo(
  val name: String,
  @Json(name = "display_name")
  val displayName: String,
  @Json(name = "short_description")
  val shortDescription: String,
  val description: String,
  @Json(name = "created_by")
  val createdBy: String,
)

@JsonClass(generateAdapter = true)
data class FeaturedRepoResponse(
  val items: List<FeaturedRepo>
)