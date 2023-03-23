<div><h1>65DSD-T1: Sockets com swing e spring </h1></div>

<div><h2>Membros: </h2></div>

* Augusto Rustick 
><p>- a.rustick@edu.udesc.br</p>

* Denis Zickuhr
><p>- denis.zickuhr16@edu.udesc.br</p>

<div><h2>Requisitos para executar o projeto: </h2></div>

* Ter uma IDE java
* Ter PostgreSQL com PgAdmin

<div><h2>Como fazer a execução do projeto: </h2></div>

* Entrar na pasta Api
* Entrar no arquivo application.properties e configurar o nome do projeto no banco de dados, login e senha
* Rodar o arquivo T1Application.java
* Entrar na pasta App
* Executar o DSD-65_Server.jar 
> java -jar ".\src\main\build\DSD_65_Server\DSD-65.jar" -p [VALOR DA PORTA] -m [NUMERO DE CONEXÕES] 
* Executar o DSD-65_ViewClient.jar 
> java -jar ".\src\main\build\DSD_65_ViewClient\DSD-65.jar" -h [ENDEREÇO IP / LOCALHOST] -p [VALOR DA PORTA]