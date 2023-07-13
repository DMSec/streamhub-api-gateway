/***
package br.com.dmsec.streamhub.apigateway.adapters.inbound.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateOrUpdateApiRouteRequest {

  @NotBlank
  private String path;

  private String method;
  private String description;

  @NotBlank
  private String uri;
}
***/