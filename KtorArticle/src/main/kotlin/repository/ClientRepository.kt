package ru.alkmanistik.repository

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import ru.alkmanistik.models.Client

class ClientRepository {

    fun getAllClients(): List<Client> = transaction {
        ClientTable.selectAll().map { rowToClient(it) }
    }

    fun getClientById(id: Int): Client? = transaction {
        ClientTable.selectAll().where { ClientTable.id eq id }
            .map { rowToClient(it) }
            .singleOrNull()
    }

    fun searchClientsByName(name: String): List<Client> = transaction {
        ClientTable.selectAll().where {
            ClientTable.name.lowerCase() like "%${name.lowercase()}%"
        }.map { rowToClient(it) }
    }

    fun createClient(name: String): Client = transaction {
        val insertStatement = ClientTable.insert {
            it[ClientTable.name] = name
        }
        rowToClient(insertStatement.resultedValues!!.first())
    }

    fun updateClient(id: Int, name: String): Boolean = transaction {
        ClientTable.update({ ClientTable.id eq id }) {
            it[ClientTable.name] = name
        } > 0
    }

    fun deleteClient(id: Int): Boolean = transaction {
        ClientTable.deleteWhere { ClientTable.id eq id } > 0
    }

    private fun rowToClient(row: ResultRow): Client {
        return Client(
            id = row[ClientTable.id],
            name = row[ClientTable.name]
        )
    }
}