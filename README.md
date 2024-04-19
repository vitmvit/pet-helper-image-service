# pet-helper-image-service

[Точка входа в приложение](https://github.com/vitmvit/pet-helper-api-gateway-service)

Данный микросервис предоставляет функционал для работы с изображениями и аватарами.

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.1/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.1/maven-plugin/reference/html/#build-image)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.2.1/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.2.1/reference/htmlsingle/index.html#web)
* [Validation](https://docs.spring.io/spring-boot/docs/3.2.1/reference/htmlsingle/index.html#io.validation)

### Guides

The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Validation](https://spring.io/guides/gs/validating-form-input/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)

## AvatarController (8086/api/v1/avatars)

Контроллер поддерживает следующие операции:

- получение base64 по uuid аватара (/base64)
- получение byte[] по uuid аватара (/bytes)
- получение ImageResponseDto по uuid аватара (/info)
- сохванение аватара с помощью MultipartFile (/save/img)
- удаление аватара по uuid (/remove)

## ImageController (8086/api/v1/ImageController)

Контроллер поддерживает следующие операции:

- получение base64 по uuid изображения (/base64)
- получение byte[] по uuid изображения (/bytes)
- получение ImageResponseDto по uuid изображения (/info)
- сохванение изображения с помощью MultipartFile (/save/img)
- обновление изображения с по dto (/save/dto)
- удаление изображения по uuid (/remove)