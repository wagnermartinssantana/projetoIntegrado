# projeto integrado
O projeto integrado de Wagner Martins Santana para a PUC Minas apresenta uma aplicação MVC completa, com um front-end desenvolvido usando Thymeleaf, Bootstrap, HTML e CSS, integrado a um banco de dados PostgreSQL. Além disso, o projeto inclui testes JUnit e documentação detalhada feita com o Swagger OpenAPI.

##Tecnologias
Para o desenvolvimento da aplicação, foram utilizadas às tecnologias e libs abaixo:

| Nome                     | Versão         |
|:-------------------------|:---------------|
| Java                     | JDK 17         |
| Gradle                   |                |
| Spring Boot              | 3.1.3          |
| Junit                    | 5.8.0          | 
| 
##Pré requisitos de tecnologia
- Instalar o _**Java 17**_, por ventura se estiver a utilizar Linux ou Mac pode utilizar o
  <br>[SDK Man](https://sdkman.io/) para fazer a gestão de versões do _**Java**_;</br>
- Instalar o _**Gradle 3.5 ou 4.1**_, por ventura se estiver a utilizar Linux ou Mac pode utilizar o
  <br>[SDK Man](https://sdkman.io/) para fazer a gestão de versões do _**Gradle**_;</br>

##Build
- Fazer o build da aplicação através do comando `build gradle`(_**Necessário ter o gradle instalado**_) ou`./gradlew build`(Busca a partir do arquivo [gradle-wrapper.properties](gradle/wrapper/gradle-wrapper.properties)).

##Execução
- Executar a aplicação através do comando `java -jar build/libs/documents.jar `;
- Pontos a serem observados no processo de execução são:
    - Execução na porta _**8080**_;
    - Acesso através do swagger-ui = (http://localhost:8080/swagger-ui/index.html)
  

