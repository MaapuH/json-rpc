package jsonrpc.spec20

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlin.reflect.KClass

class JsonRPC20ResponseSerializer<T>(private val resultSerializer: KSerializer<T>) :
    JsonContentPolymorphicSerializer<JsonRPC20Response<T>>(
        JsonRPC20Response::class as KClass<JsonRPC20Response<T>>
    ) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<JsonRPC20Response<T>> {
        return when {
            element.jsonObject.containsKey("error") -> JsonRPC20Response.Error.serializer(resultSerializer)
            element.jsonObject.containsKey("result") -> JsonRPC20Response.Success.serializer(resultSerializer)
            else -> throw SerializationException("Incorrect JsonRPC20Response")
        } as DeserializationStrategy<JsonRPC20Response<T>>
    }
}