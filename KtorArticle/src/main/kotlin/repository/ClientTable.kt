package ru.alkmanistik.repository

import org.jetbrains.exposed.sql.Table

object ClientTable : Table("clients") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 255)

    override val primaryKey = PrimaryKey(id)
}