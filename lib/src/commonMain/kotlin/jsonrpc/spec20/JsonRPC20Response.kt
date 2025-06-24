@file:OptIn(ExperimentalSerializationApi::class)

package jsonrpc.spec20

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonPrimitive

@Serializable(with = JsonRPC20ResponseSerializer::class)
sealed class JsonRPC20Response<T> : JsonRPC20 {
    inline fun <T> JsonRPC20Response<T>.resultOrNull() = this as? Success<T>
    inline fun <T> JsonRPC20Response<T>.errorOrNull() = this as? Error<T>

    @Serializable
    data class Error<T>(
        val error: JsonRPC20Error,
        override val id: JsonPrimitive,
    ) : JsonRPC20Response<T>() {
        @EncodeDefault override val jsonrpc: String = JsonRPC20.VERSION
    }

    @Serializable
    data class Success<T>(
        val result: T,
        override val id: JsonPrimitive,
    ) : JsonRPC20Response<T>() {
        @EncodeDefault override val jsonrpc: String = JsonRPC20.VERSION
    }
}


