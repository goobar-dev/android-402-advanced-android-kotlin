package dev.goobar.androidstudyguide.topics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.goobar.analytics.Logger
import dev.goobar.data.Topic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopicsViewModel @Inject constructor(private val logger: Logger) : ViewModel() {

    val topics: StateFlow<List<Topic>> = MutableStateFlow(dev.goobar.data.SAMPLE_TOPICS)

    private val _events: MutableSharedFlow<Events> = MutableSharedFlow()
    val events = _events.asSharedFlow()

    fun onTopicClicked(topic: Topic) {
        logger.log("Topic Clicked", mapOf("topic_title" to topic.title))
        viewModelScope.launch(Dispatchers.Main.immediate) { _events.emit(Events.ShowTopicClickedMessage("Clicked ${topic.title}")) }
    }

    sealed class Events {
        data class ShowTopicClickedMessage(val message: String): Events()
    }

}