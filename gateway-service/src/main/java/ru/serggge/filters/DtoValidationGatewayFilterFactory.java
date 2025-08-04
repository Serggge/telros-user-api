package ru.serggge.filters;

import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.serggge.dto.RegisterRequest;
import ru.serggge.util.DataBufferToDtoMapper;

@Component
public class DtoValidationGatewayFilterFactory extends AbstractGatewayFilterFactory<DtoValidationGatewayFilterFactory.Config> implements Ordered {

    private final DataBufferToDtoMapper dataBufferToDtoMapper;
    private final Validator validator;

    @Autowired
    public DtoValidationGatewayFilterFactory(DataBufferToDtoMapper dataBufferToDtoMapper, Validator validator) {
        super(Config.class);
        this.dataBufferToDtoMapper = dataBufferToDtoMapper;
        this.validator = validator;
    }

    public DtoValidationGatewayFilterFactory(Class<Config> configClass, DataBufferToDtoMapper dataBufferToDtoMapper, Validator validator) {
        super(configClass);
        this.dataBufferToDtoMapper = dataBufferToDtoMapper;
        this.validator = validator;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest()
                                                .mutate()
                                                .build();
            Flux<DataBuffer> body = request.getBody();
            Mono<RegisterRequest> registerRequestMono = dataBufferToDtoMapper.convert(body);
            var violations = validator.validate(registerRequestMono);
            if (violations.isEmpty()) {
                return chain.filter(exchange.mutate()
                                            .request(request)
                                            .build());
            } else {
                return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dto validation failed"));
            }
        };
    }

    @Override
    public int getOrder() {
        return -1;
    }

    public static class Config {

    }
}
