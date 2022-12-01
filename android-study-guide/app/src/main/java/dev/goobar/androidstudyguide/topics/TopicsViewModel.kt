package dev.goobar.androidstudyguide.topics

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.goobar.analytics.Logger
import dev.goobar.androidstudyguide.network.StudyGuideService
import dev.goobar.data.Topic
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject


data class TopicViewItem(
    val title: String,
    val categories: ImmutableList<String>,
    val content: String
)

private fun Topic.toViewItem() = TopicViewItem(title, categories.toImmutableList(), content)

@HiltViewModel
class TopicsViewModel @Inject constructor(
    private val logger: Logger,
    private val service: StudyGuideService
    ) : ViewModel() {

    val topics = flow {
        emit(service.getTopics().map { it.toViewItem() })
    }.stateIn(viewModelScope + Dispatchers.IO, SharingStarted.WhileSubscribed(3000), emptyList())

    private val _events: MutableSharedFlow<Events> = MutableSharedFlow()
    val events = _events.asSharedFlow()

    fun onTopicClicked(topic: TopicViewItem) {
        logger.log("Topic Clicked", mapOf("topic_title" to topic.title))
        viewModelScope.launch(Dispatchers.Main.immediate) { _events.emit(Events.ShowTopicClickedMessage("Clicked ${topic.title}")) }
    }

    sealed class Events {
        data class ShowTopicClickedMessage(val message: String): Events()
    }

}