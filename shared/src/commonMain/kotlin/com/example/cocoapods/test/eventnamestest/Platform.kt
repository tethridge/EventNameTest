package com.example.cocoapods.test.eventnamestest

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform