package br.com.production.webui.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static br.com.production.webui.description.Descriptions.DESCRIPTION_PRODUCT;
import static br.com.production.webui.description.Descriptions.PRODUCT_ID;
import static br.com.production.webui.description.Descriptions.QUANTITY;
import static br.com.production.webui.description.Descriptions.VALUE;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class UpdateOrderItem {

    @Schema(description = PRODUCT_ID)
    private Long productId;
    @Schema(description = QUANTITY)
    private Integer quantity;
    @Schema(description = VALUE)
    private BigDecimal value;
    @Schema(description = DESCRIPTION_PRODUCT)
    private String description;
}
