@file:Suppress("unused")

package jsonrpc.spec20

import jsonrpc.spec20.JsonRPC20Error.Companion.SERVER_ERROR_MAX
import jsonrpc.spec20.JsonRPC20Error.Companion.SERVER_ERROR_MIN
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class JsonRPC20Error(
    val code: Int,
    override val message: String,
    val data: JsonElement? = null
): Error() {
    companion object {
        /**
         * Invalid JSON was received by the server.
         * An error occurred on the server while parsing the JSON text.
         *
         * message: Parse error
         */
        const val PARSE_ERROR = -32700

        /**
         * The JSON sent is not a valid Request object.
         *
         * message: Invalid Request
         */
        const val INVALID_REQUEST = -32600

        /**
         * The method does not exist / is not available.
         *
         * message: Method not found
         */
        const val METHOD_NOT_FOUND = -32601

        /**
         * Invalid method parameter(s).
         *
         * message: Invalid params
         */
        const val INVALID_PARAMS = -32602

        /**
         * Internal JSON-RPC error.
         *
         * message: Internal error
         */
        const val INTERNAL_ERROR = -32603

        /**
         * Reserved for implementation-defined server-errors.
         * Range from [SERVER_ERROR_MIN] to [SERVER_ERROR_MAX]
         *
         * message: Server error
         */
        const val SERVER_ERROR_MIN = -32000

        /**
         * Reserved for implementation-defined server-errors.
         * Range from [SERVER_ERROR_MIN] to [SERVER_ERROR_MAX]
         *
         * message: Server error
         */
        const val SERVER_ERROR_MAX = -32099
    }
}