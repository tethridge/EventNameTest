package com.example.cocoapods.test.eventnamestest

//import kotlinx.serialization.*
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



private const val SN_UNHANDLED_EVENT = "unhandled_event"
private const val SN_SPEECH_START_EVENT = "speech_start_event"
private const val SN_SPEECH_END_EVENT = "speech_end_event"


@Serializable
enum class EventName constructor(
    private val serialName: String,
    val deserializationStrategy: DeserializationStrategy<out MyEvent> = UnhandledEvent.serializer()
) {
    @SerialName(SN_UNHANDLED_EVENT)
    UNHANDLED_BOT_EVENT(SN_UNHANDLED_EVENT),

    @SerialName(SN_SPEECH_START_EVENT)
    SPEECH_START_EVENT(SN_SPEECH_START_EVENT, SpeechStartEvent.serializer()),

    @SerialName(SN_SPEECH_END_EVENT)
    SPEECH_END_EVENT(SN_SPEECH_END_EVENT, SpeechEndEvent.serializer());

    companion object {
        private val SN_LIST = values().associateBy { it.serialName }

        fun valueOfSerialName(botSerialName: String): EventName {
            return SN_LIST[botSerialName] ?: UNHANDLED_BOT_EVENT
        }
    }
}

@Serializable
sealed class MyEvent {
    @SerialName("name")
    abstract val eventName: EventName
}

@Serializable
sealed class SpeechEvent : MyEvent()

@Serializable
class SpeechStartEvent : SpeechEvent() {
    @SerialName("name")
    override val eventName: EventName = EventName.SPEECH_START_EVENT
}

@Serializable
class SpeechEndEvent : SpeechEvent() {
    @SerialName("name")
    override val eventName = EventName.SPEECH_END_EVENT
}

@Serializable
data class UnhandledEvent(
    override val eventName: EventName = EventName.UNHANDLED_BOT_EVENT,
    @SerialName("name")
    val unhandledSerialName: String
) : MyEvent()