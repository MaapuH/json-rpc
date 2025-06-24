@file:OptIn(ExperimentalSerializationApi::class)

package jsonrpc.spec20

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*

@Serializable
data class JsonRPC20Request(
    override val id: JsonPrimitive,
    val method: String,
    val params: JsonElement,
) : JsonRPC20 {
    @EncodeDefault override val jsonrpc: String = JsonRPC20.VERSION

    constructor(
        id: String,
        method: String,
        params: Array<out JsonElement>
    ) : this(
        JsonPrimitive(id),
        method,
        JsonArray(params.asList())
    )
    constructor(
        id: Int,
        method: String,
        params: Array<out JsonElement>
    ) : this(
        JsonPrimitive(id),
        method,
        JsonArray(params.asList())
    )

    constructor(
        id: Int,
        method: String,
        params: Map<String, JsonPrimitive>
    ) : this(
        JsonPrimitive(id),
        method,
        JsonObject(params)
    )
    constructor(
        id: String,
        method: String,
        params: Map<String, JsonPrimitive>
    ) : this(
        JsonPrimitive(id),
        method,
        JsonObject(params)
    )



    companion object {
        const val INTERNAL_PREFIX = "rpc."
        inline fun build(
            id: String,
            method: String,
            params: JsonArrayBuilder.() -> Unit
        ) = JsonRPC20Request(
            JsonPrimitive(id),
            method,
            buildJsonArray(params)
        )

        inline fun build(
            id: Int,
            method: String,
            params: JsonArrayBuilder.() -> Unit
        ) = JsonRPC20Request(
            JsonPrimitive(id),
            method,
            buildJsonArray(params)
        )
    }
}