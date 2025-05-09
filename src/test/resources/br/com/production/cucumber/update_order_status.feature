Feature: Atualização de status do pedido

  Scenario: Atualizar status de pedido existente
    Given que o pedido com ID "123" existe com status "PENDING"
    When eu atualizo o status do pedido para "APPROVED"
    Then a resposta deve conter o ID "123" e o status "APPROVED"