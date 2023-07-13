package br.com.dmsec.streamhub.gateway.resilience;


import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackApi {

  @GetMapping("/fallback")
  ResponseEntity<String> customerFallback() {
    return new ResponseEntity<>("We are sorry, but the service is currently out of service. \nPlease try later",
        HttpStatusCode.valueOf(503));
  }
}
