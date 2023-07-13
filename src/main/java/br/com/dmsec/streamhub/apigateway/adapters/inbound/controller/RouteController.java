
/***
package br.com.dmsec.streamhub.apigateway.adapters.inbound.controller;

import br.com.dmsec.streamhub.apigateway.core.domain.Route;
import br.com.dmsec.streamhub.apigateway.core.ports.route.RouteServicePort;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.Collections;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.vavr.control.Try;


@RequestMapping("/streamhub/routes")
public class RouteController {

  private final RouteServicePort routePort;

  public RouteController(RouteServicePort routePort) {
    this.routePort = routePort;
  }


  @GetMapping
  @CircuitBreaker(name = "getAllRoutes", fallbackMethod = "getAllRoutesFallback")
  public List<Route> getAllRoutes() {
    return Try.of(routePort::getAllRoutes)
        .getOrElseGet(this::getAllRoutesFallback);
  }

  public List<Route> getAllRoutesFallback(Throwable throwable) {
    // Implemente o fallback aqui, como retornar uma lista vazia ou uma resposta alternativa.
    if (throwable instanceof RuntimeException) {
      throw (RuntimeException) throwable;
    }

    return Collections.emptyList();
  }

  @PostMapping
  public void addRoute(@RequestBody Route route) {
    routePort.saveRoute(route);
  }

  @DeleteMapping("/{routeId}")
  public void deleteRoute(@PathVariable String routeId) {
    routePort.deleteRoute(routeId);
  }
}
***/