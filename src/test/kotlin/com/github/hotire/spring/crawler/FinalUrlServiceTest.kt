package com.github.hotire.spring.crawler

import FinalUrlService
import org.junit.jupiter.api.Test
import org.springframework.web.reactive.function.client.WebClient
import reactor.test.StepVerifier

internal class FinalUrlServiceTest {

    @Test
    fun get() {
        // given
        val finalUrlService = FinalUrlService(WebClient.builder())

        // when
        val resultMono = finalUrlService.get("https://section.cafe.naver.com/abc").log()

        // then
        StepVerifier
            .create(resultMono)
            .expectNextCount(1L)
            .verifyComplete()
    }
}
