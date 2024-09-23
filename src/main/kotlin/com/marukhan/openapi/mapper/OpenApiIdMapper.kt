package com.marukhan.openapi.mapper

import java.util.*

abstract class OpenApiIdMapper {
    fun uuidToString(uuid: UUID): String = uuid.toString()

    fun stringToUuid(string: String): UUID = UUID.fromString(string)
}