package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import static com.learnreactiveprogramming.util.CommonUtil.delay;

public class FluxAndMonoSchedulersService {

    // 임의의 이름을 보유할 문자열의 플럭스를 반환
    public Flux<String> namesFlux() {
        // flux는 실제로 db 또는 호출되는 원격 서비스에서 올 수 있다...
        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .log();
    }

    public Flux<String> namesFlux_map(int stringLength) {
        return Flux.fromIterable(List.of("alex","ben","chloe"))
                .map(String::toUpperCase)
                // .map(s->s.toUpperCase())
                .filter(s->s.length()>stringLength)
                .map(s-> s.length() + "-"  + s) //4-ALEX, 5-CHOLE
                .log();
    }

    // stream은 불변의 특성을 가지고 있음음
   public Flux<String> namesFlux_immutability() {
        var namesFlux = Flux.fromIterable(List.of("alex","ben","chloe"));

        namesFlux.map(String::toUpperCase);

        return namesFlux;
    }

    // flatMap : Flux를 평면화한 다음 개별 요소를 반환
    public Flux<String> namesFlux_flatmap(int stringLength) {
        return Flux.fromIterable(List.of("alex","ben","chloe"))
                .map(String::toUpperCase)
                // .map(s->s.toUpperCase())
                .filter(s->s.length()>stringLength)
                // ALEX,CHLOE -> A,L,E,X,C,H,L,O,E
                .flatMap(this::splitString) // A,L,E,X,C,H,L,O,E
                //.flatMpa(s->splitString(s))
                .log();
    }

    // ALEX -> Flux(A,L,E,X)
    public Flux<String> splitString(String name) {
        var charArray = name.split("");
        return Flux.fromArray(charArray);
    }

    // 비동기
    public Flux<String> namesFlux_flatmap_async(int stringLength) {
        return Flux.fromIterable(List.of("alex","ben","chloe"))
                .map(String::toUpperCase)
                // .map(s->s.toUpperCase())
                .filter(s->s.length()>stringLength)
                // ALEX,CHLOE -> A,L,E,X,C,H,L,O,E
                .flatMap(this::splitString_withDelay) // A,L,E,X,C,H,L,O,E
                //.flatMpa(s->splitString(s))
                .log();
    }

    public Flux<String> splitString_withDelay(String name) {
        var charArray = name.split("");
        var delay = new Random().nextInt(1000);
        return Flux.fromArray(charArray)
                .delayElements(Duration.ofMillis(delay));
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
