package com.storeflow.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform