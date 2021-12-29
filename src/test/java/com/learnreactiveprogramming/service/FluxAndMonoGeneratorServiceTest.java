package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class FluxAndMonoGeneratorServiceTest {
    FluxAndMonoSchedulersService fluxAndMonoSchedulersService
            = new FluxAndMonoSchedulersService();

    @Test
    void namesFlux() {
        //given

        //when
        var namesFlux = fluxAndMonoSchedulersService.namesFlux();

        //then
        StepVerifier.create(namesFlux)
                .expectNext("alex", "ben", "chloe")
                .verifyComplete();
    }

    @Test
    void nameMono() {
        //given

        //when
        var nameMono = fluxAndMonoSchedulersService.nameNono();

        //then
        StepVerifier.create(nameMono)
                .expectNext("alex")
                .verifyComplete();
    }
}
