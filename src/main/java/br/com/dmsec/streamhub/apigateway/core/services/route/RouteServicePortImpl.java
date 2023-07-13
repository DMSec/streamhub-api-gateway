/***
 *

package br.com.dmsec.streamhub.apigateway.core.services.route;

import br.com.dmsec.streamhub.apigateway.core.domain.Route;
import br.com.dmsec.streamhub.apigateway.core.ports.route.RoutePersistencePort;
import br.com.dmsec.streamhub.apigateway.core.ports.route.RouteServicePort;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RouteServicePortImpl implements RouteServicePort {

  private final RoutePersistencePort routePersistencePort;

  @Override
  public List<Route> getAllRoutes() {
    return routePersistencePort.getAllRoutes();
  }

  @Override
  public void saveRoute(Route route) {
    routePersistencePort.saveRoute(route);

  }

  @Override
  public void deleteRoute(String routeId) {
    routePersistencePort.deleteRoute(routeId);

  }

  @Override
  public void refreshRoutes() {
    routePersistencePort.refreshRoutes();
  }
}
***/