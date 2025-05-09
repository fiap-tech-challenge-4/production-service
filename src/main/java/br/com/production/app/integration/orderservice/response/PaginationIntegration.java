package br.com.production.app.integration.orderservice.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class PaginationIntegration<T> {

  private Boolean hasNext;
  private Boolean hasPrevious;
  private Integer pageNumber;
  private Integer pageSize;
  private List<T> items;

}
