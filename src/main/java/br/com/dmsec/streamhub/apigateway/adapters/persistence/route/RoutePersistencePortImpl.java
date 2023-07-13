/***
package br.com.dmsec.streamhub.apigateway.adapters.persistence.route;



import br.com.dmsec.streamhub.apigateway.adapters.persistence.entities.RouteEntity;
import br.com.dmsec.streamhub.apigateway.core.domain.Route;
import br.com.dmsec.streamhub.apigateway.core.ports.route.RoutePersistencePort;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.vavr.control.Try;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cloud.gateway.handler.predicate.PathRoutePredicateFactory;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RoutePersistencePortImpl implements RoutePersistencePort {

  private final ModelMapper modelMapper;
  private final RouteJpaRepository routeJpaRepository;
  private final RouteLocator routeLocator;

  @Override
  @CircuitBreaker(name = "getAllRoutes", fallbackMethod = "getAllRoutesFallback")
  public List<Route> getAllRoutes() {
    return Try.ofSupplier(() -> routeJpaRepository.findAll().stream()
            .map(entity -> modelMapper.map(entity, Route.class))
            .collect(Collectors.toList()))
        .getOrElseGet(this::getAllRoutesFallback);
  }

  public List<Route> getAllRoutesFallback(Throwable throwable) {
    // Implemente o fallback aqui, como retornar uma lista vazia ou uma resposta alternativa.
    if (throwable instanceof RuntimeException) {
      throw (RuntimeException) throwable;
    }

    return List.of();
  }

  @Override
  @CircuitBreaker(name = "saveRoute")
  public void saveRoute(Route route) {
    Try.run(() -> routeJpaRepository.save(modelMapper.map(route, RouteEntity.class)))
        .onSuccess(obj -> refreshRoutes())
        .onFailure(throwable -> saveRouteFallback(throwable, route));

  }

  public void saveRouteFallback(Throwable throwable, Route route) {
    // Implemente o fallback aqui, como logar o erro ou enviar uma mensagem para uma fila.
    if (throwable instanceof RuntimeException) {
      throw (RuntimeException) throwable;
    }
  }

  @Override
  @CircuitBreaker(name = "deleteRoute")
  public void deleteRoute(String routeId) {
    Try.run(() -> routeJpaRepository.deleteById(routeId))
        .onSuccess(obj -> refreshRoutes())
        .onFailure(throwable -> deleteRouteFallback(throwable, routeId));
  }

  @Override
  public void refreshRoutes() {

    }




  public void refreshRoutesFallback(Throwable throwable) {
    // Implemente o fallback aqui, como logar o erro ou enviar uma mensagem para uma fila.
    if (throwable instanceof RuntimeException) {
      throw (RuntimeException) throwable;
    }
  }

  public void deleteRouteFallback(Throwable throwable, String routeId) {
    // Implemente o fallback aqui, como logar o erro ou enviar uma mensagem para uma fila.
    if (throwable instanceof RuntimeException) {
      throw (RuntimeException) throwable;
    }
  }
}
***/