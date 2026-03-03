package ru.alkmanistik.plugins

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.alkmanistik.models.ClientCreateRequest
import ru.alkmanistik.models.ClientSearchRequest
import ru.alkmanistik.models.ClientUpdateRequest
import ru.alkmanistik.repository.ClientRepository

fun Application.configureRouting() {
    val repository = ClientRepository()

    routing {
        post("/client") {
            try {
                val request = call.receive<ClientCreateRequest>()
                val client = repository.createClient(request.name)
                call.respond(HttpStatusCode.Created, client)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, "Ошибка при создании клиента: ${e.message}")
            }
        }

        get("/client/all") {
            val clients = repository.getAllClients()
            call.respond(HttpStatusCode.OK, clients)
        }

        get("/client/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "Неверный ID")
                return@get
            }

            val client = repository.getClientById(id)
            if (client != null) {
                call.respond(HttpStatusCode.OK, client)
            } else {
                call.respond(HttpStatusCode.NotFound, "Клиент с ID $id не найден")
            }
        }

        post("/client/search") {
            try {
                val request = call.receive<ClientSearchRequest>()
                val clients = repository.searchClientsByName(request.name)
                call.respond(HttpStatusCode.OK, clients)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, "Ошибка при поиске: ${e.message}")
            }
        }

        put("/client") {
            try {
                val request = call.receive<ClientUpdateRequest>()
                val updated = repository.updateClient(request.id, request.name)

                if (updated) {
                    call.respond(HttpStatusCode.OK, "Клиент обновлен")
                } else {
                    call.respond(HttpStatusCode.NotFound, "Клиент с ID ${request.id} не найден")
                }
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, "Ошибка при обновлении: ${e.message}")
            }
        }

        delete("/client/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "Неверный ID")
                return@delete
            }

            val deleted = repository.deleteClient(id)
            if (deleted) {
                call.respond(HttpStatusCode.OK, "Клиент удален")
            } else {
                call.respond(HttpStatusCode.NotFound, "Клиент с ID $id не найден")
            }
        }
    }
}
