Serviço REST com documentação interativa e possibilidade de autenticação, para utilização em testes de ferramentas de consumo de serviços REST

Para rodar, basta compilar o projeto e startar o servidor:

```sh
mvn clean package
java -jar target/RestService.jar
```

Por default, caso nenhum parâmetro seja informado, o serviço irá rodar em `localhost`, na porta 8888. Caso queira informar um host ou porta diferentes basta informá-los separando por espaço (host sempre em primeiro)
