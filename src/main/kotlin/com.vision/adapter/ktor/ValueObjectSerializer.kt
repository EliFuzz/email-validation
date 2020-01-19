package com.vision.adapter.ktor

import com.google.gson.JsonElement
import com.google.gson.JsonNull
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import com.vision.lib.ValueObject
import java.lang.reflect.Type


object ValueObjectSerializer : JsonSerializer<ValueObject<*>> {
    override fun serialize(src: ValueObject<*>, typeOfSrc: Type, context: JsonSerializationContext): JsonElement =
            context.serialize(src.value) ?: JsonNull.INSTANCE
}
