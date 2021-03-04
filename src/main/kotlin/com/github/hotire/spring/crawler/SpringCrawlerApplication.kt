package com.github.hotire.spring.crawler

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringCrawlerApplication

fun main(args: Array<String>) {
    runApplication<SpringCrawlerApplication>(*args)
}
