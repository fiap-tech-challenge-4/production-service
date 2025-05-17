package br.com.production.webui.controllers;

import br.com.production.app.usecases.UpdateOrderStatusCase;
import br.com.production.webui.dtos.response.ErrorResponse;
import br.com.production.webui.dtos.response.GetListOrdersResponse;
import br.com.production.webui.dtos.response.PaginationResponse;
import br.com.production.webui.dtos.response.UpdateOrderStatusResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static br.com.production.webui.description.Descriptions.ID;
import static br.com.production.webui.description.Descriptions.LIMIT;
import static br.com.production.webui.description.Descriptions.MINIMAL_PAGE;
import static br.com.production.webui.description.Descriptions.ORDER_STATUS_FILTER;
import static br.com.production.webui.description.Descriptions.PAGE;

@Validated
@RestController
@RequiredArgsConstructor
@Tag(name = "Update Order Status")
@ApiResponse(responseCode = "400", description = "Bad Request",
        content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
@ApiResponse(responseCode = "422", description = "Unprocessable Entity",
        content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
@RequestMapping(value = "/api/v1/order")
public class OrderStatusController {

    private final UpdateOrderStatusCase updateOrderStatusCase;

    @Operation(summary = "Update order status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated order status completed successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UpdateOrderStatusResponse.class))})})
    @PatchMapping("/update-status/{orderId}")
    public ResponseEntity<UpdateOrderStatusResponse> updateOrderStatus(
            @Parameter(description = ID)
            @PathVariable(name = "orderId")
            final String orderId) {
        return ResponseEntity.ok(updateOrderStatusCase.updateOrderStatus(orderId));
    }

    @Operation(summary = "List orders by status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated order status completed successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UpdateOrderStatusResponse.class))})})
    @GetMapping("/list")
    public ResponseEntity<PaginationResponse<GetListOrdersResponse>> getListOrders(
            @Parameter(description = PAGE)
            @RequestParam(required = false, defaultValue = "1")
            @Min(value = 1, message = MINIMAL_PAGE) final Integer page,
            @Parameter(description = LIMIT)
            @RequestParam(required = false, defaultValue = "25")
            final Integer limit,
            @Parameter(description = ORDER_STATUS_FILTER)
            @RequestParam(required = false)
            final String orderStatus) {
        return ResponseEntity.ok(updateOrderStatusCase.getListOrders(page, limit, orderStatus));
    }

}
