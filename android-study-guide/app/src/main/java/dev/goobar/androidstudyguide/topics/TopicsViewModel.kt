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
        val topics = service.getTopics().map { it.toViewItem() }

        var count = 0
        while (true) {
            val updatedFirstTopic = topics[0].copy(title = "$count ${topics[0].title}")
            count++
            emit(listOf(updatedFirstTopic) + topics.subList(1, topics.lastIndex))
            delay(2000)
        }
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