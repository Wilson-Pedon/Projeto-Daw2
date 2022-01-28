# Sistema de Leilão
Projeto final sobre a matéria desenvolvimento Web II

# Ferramentas Utilizadas
- PostgreSQL
- SpringBoot
- Java
- IntelliJ  (Ou da sua preferência).
- Eclipce
- Spring Security

# Tecnologias Back-end

<div style="display: inline_block"><br/>
    <img align="center" alt="SpringBoot" src="https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot" />
    <img align="center" alt="Postgresql" src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white" />
    <img align="center" alt="Hibernate" src="https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white" />
    <img align="center" alt="ApiRest" src="https://img.shields.io/badge/API%20REST-B50BEC?style=for-the-badge&logo=apirest&logoColor=white" />
</div><br/>
# IDE

<div style="display: inline_block"><br/>
    <img align="center" alt="IntelliJIDEA" src="https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white" />
</div><br/>

### SQL :

SQL para gerar um perfil de ADMIN no sistema.

Tabela Cliente
~~~SQL
INSERT INTO public.cliente(
	id, email, nome, senha)
	VALUES (?, ?, ?, ?);
 ~~~
 Tabela Perfil
 ~~~SQL
INSERT INTO public.perfil(
	id, nome)
	VALUES (1, ROLE_ADMIN);
  
  INSERT INTO public.perfil(
	id, nome)
	VALUES (1, ROLE_USER);
 ~~~
 Tabela cliente_perfil
 ~~~SQL
INSERT INTO public.cliente_perfil(
	cliente_id, perfil_id)
	VALUES (1, 1);
 ~~~
 
 ### Propriedades do Enviador de Emails
 ~~~java
 spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=Seu e-mail aqui
spring.mail.password=chave que o gmail gera.
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
 ~~~

