package br.com.production.webui.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static br.com.production.webui.description.Descriptions.ADDITIONAL_INFO;
import static br.com.production.webui.description.Descriptions.CLIENT_ID;
import static br.com.production.webui.description.Descriptions.DATE;
import static br.com.production.webui.description.Descriptions.ID;
import static br.com.production.webui.description.Descriptions.ITEMS_ORDER;
import static br.com.production.webui.description.Descriptions.STATUS;
import static br.com.production.webui.description.Descriptions.TOTAL_VALUE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class UpdateOrderStatusResponse {

    @Schema(description = ID)
    private String id;
    @Schema(description = DATE)
    private LocalDateTime date;
    @Schema(description = CLIENT_ID)
    private String clientId;
    @Schema(description = TOTAL_VALUE)
    private BigDecimal totalValue;
    @Schema(description = STATUS)
    private String status;
    @Schema(description = ITEMS_ORDER)
    private List<UpdateOrderItem> items;
    @Schema(description = ADDITIONAL_INFO)
    private String additionalInfo;
}
