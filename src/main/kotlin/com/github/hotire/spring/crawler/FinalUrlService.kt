

import io.netty.handler.codec.http.HttpResponseStatus
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import reactor.netty.http.client.HttpClient

@Service
class FinalUrlService(private val webClientBuilder: WebClient.Builder) {
    fun get(url: String): Mono<String> {
        var lastUrl = url
        return webClientBuilder
            .clientConnector(
                ReactorClientHttpConnector(
                    HttpClient.create().followRedirect { _, res ->
                        res.responseHeaders().get("Location")?.let { lastUrl = it }
                        HttpResponseStatus.FOUND == res.status() || HttpResponseStatus.MOVED_PERMANENTLY == res.status()
                    }
                )
            )
            .build()
            .head()
            .uri(url)
            .retrieve()
            .toEntity(String::class.java)
            .map { lastUrl }
    }
}
