import jsonrpc.spec20.JsonRPC20Request
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.encodeToJsonElement
import kotlin.test.Test
import kotlin.test.assertEquals

class JsonRPC20RequestTests {
    @Test
    fun serialisationWithArray() {
        assertEquals(
            Json.decodeFromString(
                JsonElement.serializer(),
                "{\"jsonrpc\": \"2.0\", \"method\": \"subtract\", \"params\": [42, 23], \"id\": 1}"
            ),
            Json.encodeToJsonElement(
                JsonRPC20Request(
                    1,
                    "subtract",
                    arrayOf(
                        JsonPrimitive(42),
                        JsonPrimitive(23)
                    )
                )
            )
        )
    }

    @Test
    fun serialisationWithObject() {
        assertEquals(
            Json.decodeFromString(
                JsonElement.serializer(),
                "{\"jsonrpc\": \"2.0\", \"method\": \"subtract\", \"params\": {\"subtrahend\": 23, \"minuend\": 42}, \"id\": 3}"
            ),
            Json.encodeToJsonElement(
                JsonRPC20Request(
                    3,
                    "subtract",
                    mapOf(
                        "subtrahend" to JsonPrimitive(23),
                        "minuend" to JsonPrimitive(42)
                    )
                )
            )
        )
    }

    @Test
    fun deserialisationWithArray() {
        assertEquals(
            Json.Default.decodeFromString(
                "{\"jsonrpc\": \"2.0\", \"method\": \"subtract\", \"params\": [42, 23], \"id\": 1}"
            ),
            JsonRPC20Request(
                1,
                "subtract",
                arrayOf(
                    JsonPrimitive(42),
                    JsonPrimitive(23)
                )
            )
        )
    }

    @Test
    fun deserialisationWithObject() {
        assertEquals(
            Json.Default.decodeFromString(
                "{\"jsonrpc\": \"2.0\", \"method\": \"subtract\", \"params\": {\"subtrahend\": 23, \"minuend\": 42}, \"id\": 3}"
            ),
            JsonRPC20Request(
                3,
                "subtract",
                mapOf(
                    "subtrahend" to JsonPrimitive(23),
                    "minuend" to JsonPrimitive(42)
                )
            )
        )
    }
}