package ru.alkmanistik.plugins

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import ru.alkmanistik.repository.ClientTable

fun Application.configureDatabase() {
    val hikariConfig = HikariConfig().apply {
        driverClassName = "org.postgresql.Driver"
        jdbcUrl = "jdbc:postgresql://localhost:5432/ktor-article"
        username = "test"
        password = "test"

        maximumPoolSize = 6
        minimumIdle = 2
        idleTimeout = 600000
        connectionTimeout = 30000
        isAutoCommit = false
        validate()
    }

    val dataSource = HikariDataSource(hikariConfig)

    Database.connect(dataSource)

    transaction {
        SchemaUtils.create(ClientTable)
    }
}