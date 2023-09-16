package com.farmani.xreminder.db

import androidx.datastore.core.Serializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

class RemindersListSerializer : Serializer<RemindersListHolder> {
    override val defaultValue = RemindersListHolder()

    override suspend fun readFrom(input: InputStream): RemindersListHolder {
        return Json.decodeFromString(
            RemindersListHolder.serializer(),
            input.readBytes().decodeToString()
        )
    }

    override suspend fun writeTo(t: RemindersListHolder, output: OutputStream) {
        withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(RemindersListHolder.serializer(), t).encodeToByteArray()
            )
        }
    }
}