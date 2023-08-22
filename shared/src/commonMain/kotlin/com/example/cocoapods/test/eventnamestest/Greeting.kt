package com.example.cocoapods.test.eventnamestest

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        val foo = SpeechStartEvent.serializer()
        return "Hello, ${platform.name}!"
    }
}