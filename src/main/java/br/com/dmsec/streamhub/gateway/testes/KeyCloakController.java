package br.com.dmsec.streamhub.gateway.testes;

import java.security.Principal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KeyCloakController {

  @GetMapping("/home")
  public String index(Principal principal) {
    return principal.getName();
  }
}
