package br.com.production.webui.converters;

import br.com.production.app.integration.orderservice.response.OrderItemResponse;
import br.com.production.app.integration.orderservice.response.OrderResponse;
import br.com.production.app.integration.orderservice.response.PaginationIntegration;
import br.com.production.webui.dtos.response.GetListOrdersResponse;
import br.com.production.webui.dtos.response.PaginationResponse;
import br.com.production.webui.dtos.response.UpdateOrderItem;
import br.com.production.webui.dtos.response.UpdateOrderStatusResponse;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class OrderConverter {

    public static UpdateOrderStatusResponse convertToUpdateOrderStatusResponse(OrderResponse orderResponse) {
        return UpdateOrderStatusResponse.builder()
                .id(orderResponse.getId())
                .date(orderResponse.getDate())
                .clientId(orderResponse.getClientId())
                .totalValue(orderResponse.getTotalValue())
                .status(orderResponse.getStatus())
                .items(convertToOrderResponseList(orderResponse.getItems()))
                .additionalInfo(orderResponse.getAdditionalInfo())
                .build();
    }

    public static PaginationResponse<GetListOrdersResponse> convertToPaginationGetListOrdersResponse(PaginationIntegration<OrderResponse> paginationOrderResponse) {
        List<GetListOrdersResponse> convertedItems = paginationOrderResponse.getItems()
                .stream()
                .map(order -> GetListOrdersResponse.builder()
                        .id(order.getId())
                        .date(order.getDate())
                        .clientId(order.getClientId())
                        .totalValue(order.getTotalValue())
                        .status(order.getStatus())
                        .items(convertToOrderResponseList(order.getItems()))
                        .additionalInfo(order.getAdditionalInfo())
                        .build())
                .toList();

        PaginationResponse<GetListOrdersResponse> response = new PaginationResponse<>();
        response.setHasNext(paginationOrderResponse.getHasNext());
        response.setHasPrevious(paginationOrderResponse.getHasPrevious());
        response.setPageNumber(paginationOrderResponse.getPageNumber());
        response.setPageSize(paginationOrderResponse.getPageSize());
        response.setItems(convertedItems);

        return response;
    }

    private static List<UpdateOrderItem> convertToOrderResponseList(List<OrderItemResponse> items) {
        List<UpdateOrderItem> responseList = new ArrayList<>();

        for (OrderItemResponse item : items) {
            var orderItem = UpdateOrderItem.builder()
                    .productId(item.getProductId())
                    .quantity(item.getQuantity())
                    .value(item.getValue())
                    .description(item.getDescription())
                    .build();
            responseList.add(orderItem);
        }

        return responseList.isEmpty()
                ? null
                : responseList;
    }
}
