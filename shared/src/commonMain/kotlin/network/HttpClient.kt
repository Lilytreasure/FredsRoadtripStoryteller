package network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.Charsets
import io.ktor.client.plugins.UserAgent
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.charsets.Charsets
import kotlinx.serialization.json.Json

import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.cache.*
import io.ktor.client.plugins.cache.storage.*

val httpClient = HttpClient {
    developmentMode = true


    // todo Add to Actual/Expected
    // https://github.com/ktorio/ktor-documentation/blob/2.3.5/codeSnippets/snippets/client-caching/src/main/kotlin/com/example/Application.kt
//    import java.nio.file.*
//    install(HttpCache) {
//        val cacheFile = Files.createDirectories(Paths.get("build/cache")).toFile()
//        publicStorage(FileStorage(cacheFile))
//    }

    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }
    install(UserAgent) {
        agent = "Historical Crawler(1.0ß1)"
    }

    Charsets {
        // Marker page html response uses "windows-1252" encoding (aka "ISO-8859-1")
        // - This responseCharsetFallback is required for iOS (Android doesn't care)
        responseCharsetFallback = Charsets.ISO_8859_1
        sendCharset = Charsets.ISO_8859_1
    }

    expectSuccess = true
    followRedirects = true
}
