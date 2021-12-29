package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.learnreactiveprogramming.util.CommonUtil.delay;

public class FluxAndMonoSchedulersService {

    // 임의의 이름을 보유할 문자열의 플럭스를 반환
    public Flux<String> namesFlux() {
        // flux는 실제로 db 또는 호출되는 원격 서비스에서 올 수 있다...
        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .log();
    }

    // 임의의 이름을 보유할 문자열의 모노를 반환
    public Mono<String> nameNono() {
        return Mono.just("alex")
                .log();
    }

    public static void main(String[] args) {
        // Flux
        // 플럭스를 어떻게 처리할까 ㅎㅎㅎ
        FluxAndMonoSchedulersService fluxAndMonoSchedulersService = new FluxAndMonoSchedulersService();

        // 위에서 만든 값을 액세스 하기 위해서는
        // 해당 플럭스를 구독해야한다
        // subscribe 함수를 호출하자
        fluxAndMonoSchedulersService.namesFlux()
                .subscribe(name -> {
                    System.out.println("Name is :" + name);
                }); // subscribe를 호출하면 해당 요소의 모든 항목에 액세스 할 수 있다
        // flux의 요소들은 하나씩 스트림 형태로 전송됨

        // Mono
        fluxAndMonoSchedulersService.nameNono()
                .subscribe(name -> {
                    System.out.println("Mono Name is" + name);}
                );
    }
}
