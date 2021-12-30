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
    void namesFlux_map() {
        //given
        int stringLength = 3;

        //when
        var namesFlux = fluxAndMonoSchedulersService.namesFlux_map(stringLength);

        //then
        StepVerifier.create(namesFlux)
                .expectNext("4-ALEX", "5-CHLOE")
                .verifyComplete();
    }

    @Test
    void namesFlux_immutability(){
        //given

        //when
        var namesFlux = fluxAndMonoSchedulersService.namesFlux_immutability();

        //then
        StepVerifier.create(namesFlux)
                .expectNext("alex", "ben", "chloe")
                .verifyComplete();

    }

    @Test
    void nameFlux_flatmap() {
        //given
        int stringLength = 3;

        //when
        var namesFlux = fluxAndMonoSchedulersService.namesFlux_flatmap(stringLength);

        //then
        StepVerifier.create(namesFlux)
                .expectNext("A","L","E","X","C","H","L","O","E")
                .verifyComplete();
    }

    @Test
    void namesFlux_flatmap_async() {
        //given
        int stringLength = 3;

        //when
        var namesFlux = fluxAndMonoSchedulersService.namesFlux_flatmap_async(stringLength);

        //then
        // 호출을 비동기적으로 수행하지만 모든 결과에 대해 응답을 기다림
        StepVerifier.create(namesFlux)
                //.expectNext("A","L","E","X","C","H","L","O","E")
                .expectNextCount(9)
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
