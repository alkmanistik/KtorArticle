package ru.alkmanistik.models

@kotlinx.serialization.Serializable
data class Client(
    val id: Int,
    val name: String
)

@kotlinx.serialization.Serializable
data class ClientCreateRequest(
    val name: String
)

@kotlinx.serialization.Serializable
data class ClientUpdateRequest(
    val id: Int,
    val name: String
)

@kotlinx.serialization.Serializable
data class ClientSearchRequest(
    val name: String
)