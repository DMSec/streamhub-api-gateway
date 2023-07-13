package br.com.dmsec.streamhub.gateway.resilience;

import java.util.Objects;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class ProductKeyResolver implements KeyResolver {

  private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ProductKeyResolver.class);
  @Override
  public Mono<String> resolve(ServerWebExchange exchange) {
    logger.info("Request from {}", Objects.requireNonNull(exchange.getRequest().getLocalAddress().getHostName()));
    return Mono.just(Objects.requireNonNull(Objects.requireNonNull(exchange.getRequest().getLocalAddress()).getHostName()));
  }
}
