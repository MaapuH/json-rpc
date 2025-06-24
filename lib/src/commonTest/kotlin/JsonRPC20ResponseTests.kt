import jsonrpc.spec20.JsonRPC20Error
import jsonrpc.spec20.JsonRPC20Response
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.*
import kotlin.test.Test
import kotlin.test.assertEquals


class JsonRPC20ResponseTests {
    @Test
    fun serialisationForError() {
        assertEquals(
            Json.decodeFromString(
                JsonElement.serializer(),
                "{\"jsonrpc\": \"2.0\", \"error\": {\"code\": -32700, \"message\": \"Parse error\"}, \"id\": null}"
            ),
            Json.encodeToJsonElement(
                JsonRPC20Response.Error<Int>(
                    JsonRPC20Error(
                        -32700,
                        "Parse error"
                    ),
                    JsonNull
                )
            )
        )
    }

    @Test
    fun serialisationForSuccess() {
        assertEquals(
            Json.decodeFromString(
                JsonElement.serializer(),
                "{\"jsonrpc\": \"2.0\", \"result\": 19, \"id\": 3}"
            ),
            Json.encodeToJsonElement(
                JsonRPC20Response.Success(
                    19,
                    JsonPrimitive(3)
                )
            )
        )
    }

    @Test
    fun deserialisationForSuccess() {
        assertEquals(
            Json.decodeFromString(
                JsonRPC20Response.serializer(Int.serializer()),
                "{\"jsonrpc\": \"2.0\", \"result\": 19, \"id\": 3}"
            ),
            JsonRPC20Response.Success(
                19,
                JsonPrimitive(3)
            )
        )
    }

    @Test
    fun deserialisationForError() {
        assertEquals(
            Json.decodeFromString(
                JsonRPC20Response.serializer(Int.serializer()),
                "{\"jsonrpc\": \"2.0\", \"error\": {\"code\": -32700, \"message\": \"Parse error\"}, \"id\": null}"
            ),
            JsonRPC20Response.Error(
                JsonRPC20Error(
                    -32700,
                    "Parse error"
                ),
                JsonNull
            )
        )
    }
}
