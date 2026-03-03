package ru.alkmanistik.plugins

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import ru.alkmanistik.repository.ClientTable

fun Application.configureDatabase() {
    Database.connect(
        url = "jdbc:postgresql://localhost:5432/ktor-article",
        driver = "org.postgresql.Driver",
        user = "test",
        password = "test"
    )

    transaction {
        SchemaUtils.create(ClientTable)
    }
}