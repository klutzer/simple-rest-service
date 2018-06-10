Serviço REST com documentação interativa e possibilidade de autenticação, para utilização em testes de ferramentas de consumo de serviços REST

Para rodar, basta compilar o projeto e startar o servidor:

```shell
mvn clean package && java -jar target/RestService.jar
```

Por default, caso nenhum parâmetro seja informado, o serviço irá rodar em `localhost`, na porta 8888. Caso queira informar um host ou porta diferentes basta informá-los separando-os por espaço (host sempre em primeiro).

Após startar, a documentação interativa pode ser acessada na raíz do serviço (endereço `http://{host}:{port}`).