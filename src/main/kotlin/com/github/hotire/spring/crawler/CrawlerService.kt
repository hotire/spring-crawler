package com.github.hotire.spring.crawler

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class CrawlerService {
    fun crawling(url: String): Mono<Document> = Mono.create { it.success(Jsoup.connect(url).get()) }
}
