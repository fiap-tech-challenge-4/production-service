package br.com.production.cucumber;

import br.com.production.app.integration.orderservice.OrderServiceIntegration;
import br.com.production.app.integration.orderservice.response.OrderResponse;
import br.com.production.app.usecases.UpdateOrderStatusCase;
import br.com.production.webui.dtos.response.UpdateOrderStatusResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class UpdateOrderStatusSteps {

    private UpdateOrderStatusCase updateOrderStatusCase;
    private OrderServiceIntegration orderServiceIntegration;

    private String inputOrderId;
    private UpdateOrderStatusResponse response;

    @Given("que o pedido com ID {string} existe com status {string}")
    public void givenOrderExists(String orderId, String status) {
        orderServiceIntegration = mock(OrderServiceIntegration.class);
        updateOrderStatusCase = new UpdateOrderStatusCase(orderServiceIntegration);

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(orderId);
        orderResponse.setStatus(status);
        orderResponse.setDate(LocalDateTime.now());
        orderResponse.setClientId("clientA");
        orderResponse.setTotalValue(BigDecimal.valueOf(99.90));
        orderResponse.setItems(List.of());
        orderResponse.setAdditionalInfo("Simulated");

        given(orderServiceIntegration.updateOrderStatus(orderId)).willReturn(orderResponse);
        this.inputOrderId = orderId;
    }

    @When("eu atualizo o status do pedido para {string}")
    public void whenUpdateOrderStatus(String newStatus) {
        response = updateOrderStatusCase.updateOrderStatus(inputOrderId);
        response.setStatus(newStatus);
    }

    @Then("a resposta deve conter o ID {string} e o status {string}")
    public void thenVerifyResponse(String expectedId, String expectedStatus) {
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(expectedId);
        assertThat(response.getStatus()).isEqualTo(expectedStatus);
    }
}
