package jsonrpc.spec20

import jsonrpc.JsonRPC
import kotlinx.serialization.json.JsonPrimitive

interface JsonRPC20 : JsonRPC {
    override val jsonrpc: String get() = VERSION
    val id: JsonPrimitive
    companion object {
        const val VERSION = "2.0"
    }
}