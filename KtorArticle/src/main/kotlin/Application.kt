package ru.alkmanistik

import io.ktor.server.application.*
import ru.alkmanistik.plugins.configureDatabase
import ru.alkmanistik.plugins.configureRouting
import ru.alkmanistik.plugins.configureSerialization

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureDatabase()
    configureSerialization()
    configureRouting()
}
