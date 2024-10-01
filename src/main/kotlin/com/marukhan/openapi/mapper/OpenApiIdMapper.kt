package com.marukhan.openapi.mapper

import java.util.*

abstract class OpenApiIdMapper {

    open fun uuidToString(uuid: UUID): String = uuid.toString()

    open fun stringToUuid(string: String): UUID = UUID.fromString(string)
}