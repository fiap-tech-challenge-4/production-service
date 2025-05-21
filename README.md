# O problema

Há uma lanchonete de bairro que está expandindo devido seu grande sucesso. Porém o estabelecimento não estava preparado para sua expansão, com isso os pedidos sem um sistema que os gerencie se tornaram confusos e complicados, os funcionários começaram a perder os papéis que eram anotados os pedidos, a cozinha não tinha um direcionamento claro do que era cada pedido e os próprios antendentes não conseguiam lidar com a demanda total de clientes.

Com o crescimento da lanchonete e a adoção de uma arquitetura baseada em microsserviços, cada domínio do sistema passou a ser isolado para garantir escalabilidade, manutenção e evolução independentes.

## Solução Proposta

Este serviço é responsável por gerenciar o fluxo de produção dos pedidos dentro do sistema. Seu principal papel é atualizar o status dos pedidos conforme eles avançam na cozinha, garantindo que o order-service mantenha um acompanhamento preciso de cada etapa (ex: Recebido, Em Preparo, Pronto).

Além disso, o serviço expõe um endpoint público que lista todos os pedidos ainda não finalizados, permitindo que telas visíveis ao cliente (como painéis ou monitores) exibam em tempo real o andamento da produção. Isso oferece mais transparência ao processo e melhora a experiência do cliente durante a espera.

Centralizar essas ações no production-service ajuda a manter a lógica de produção isolada e desacoplada, facilitando a escalabilidade, manutenção e integração com outros componentes do sistema.

#### Principais Responsabilidades:
- Atualizar o status dos pedidos no order-service conforme o andamento da produção na cozinha (ex: "Em preparo", "Pronto")
- Listar todos os pedidos não finalizados, permitindo que uma tela pública (como um painel de chamada ou monitor de pedidos) exiba os pedidos em andamento para os clientes
- Atuar como ponte entre o time da cozinha e os demais serviços, garantindo visibilidade e rastreabilidade dos pedidos em tempo real

## Desenho Técnico Microsserviços
<div align="center">
  <img src="https://i.ibb.co/dsPzD37s/arquitetura.png" alt="Arquitetura de Microsserviços">
</div>

## Evidência de testes no SonarCloud
<div align="center">
  <img src="https://i.ibb.co/BHgBg23L/production-service.jpg" alt="Evidência de testes unitários no sonar">
</div>

## Tecnologias
- **Spring Boot**: Framework para construção de aplicações Java.
    - `spring-boot-starter-web`: Para construir aplicações web.
    - `spring-boot-starter-data-jpa`: Para integração com JPA (Java Persistence API).
    - `spring-boot-starter-validation`: Para validação de dados.
- **Lombok**: Biblioteca para reduzir o código boilerplate.
- **Springdoc OpenAPI**: Para gerar documentação da API.
- **Kubernates**: Para orquestrar contêineres de maneira eficiente e automatizada
- **Terraform**: Para gerenciamento do nosso IaC
- **AWS**: Toda a nossa infraestrutura em nuvem.

## Requisitos

- Java 21
- Docker
- Minikube e Kubernetes configurados localmente

## Estrutura do Projeto

- payment-service: responsável por toda a gestão de pagamentos, incluindo a integração com o gateway do Mercado Pago e o processamento de transações.
- register-service: gerencia os cadastros do sistema, como clientes, produtos e usuários.
- production-service: opera no ambiente da cozinha, sendo responsável por acompanhar e atualizar o status dos pedidos em produção.
- order-service: lida com a criação e o gerenciamento dos pedidos realizados pelos clientes, atuando como ponto central no fluxo de consumo.

## Documentação da API

- Após a aplicação estar em execução, a documentação estará disponível em:

```
http://<url_service>/swagger-ui/index.html
```

## Considerações Finais

Este projeto utiliza o Minikube para rodar uma instância local do Kubernetes, que gerencia a implantação dos serviços de backend e banco de dados. Certifique-se de ter o Minikube e o kubectl configurados corretamente para evitar problemas na execução.
