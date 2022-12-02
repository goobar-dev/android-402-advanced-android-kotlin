package dev.goobar.androidstudyguide.trending

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.goobar.androidstudyguide.db.RepoDao
import dev.goobar.data.RepoEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.SharingStarted.Companion
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMap
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class FeaturedRepoViewItem(
  val name: String,
  val displayName: String,
  val description: String,
  val createdBy: String,
)
fun RepoEntity.toViewItem() = FeaturedRepoViewItem(name, displayName, description, createdBy)

@HiltViewModel
class TrendingViewModel @Inject constructor(
  private val repoDao: RepoDao
) : ViewModel() {

  val repos: StateFlow<List<FeaturedRepoViewItem>> = repoDao
    .getAll()
    .map { it.map { it.toViewItem() } }
    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

}