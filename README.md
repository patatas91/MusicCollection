# MusicCollection

Aplicacion realizada con Spring Boot + Spring Security + Thymeleaf + H2

Esta aplicacion consiste en un gestor de artistas musicales. Para ello se ha optado por utilizar una base de datos embebida 
(H2), la cual al iniciar la aplicacion se carga con una serie de datos de ejemplo (estilos musicales, usuarios para simular
el contador de seguidores de artistas, ...). La aplicacion se divide en 2 secciones basicamente, la seccion a la que accederan los usuarios con rol usuario y a la que accedera el administrador, para ello contaremos con una pantalla de login:

![captura de pantalla 2018-10-07 a las 23 09 21](https://user-images.githubusercontent.com/16426967/46587012-c25b6c80-ca86-11e8-9a76-ab7cc087cbea.png)

Si no disponemos de usuario pulsaremos en darnos de alta:

![captura de pantalla 2018-10-07 a las 23 09 45](https://user-images.githubusercontent.com/16426967/46587013-c2f40300-ca86-11e8-960d-fc58ec71bce9.png)

Una vez tengamos nuestro usuario creado procederemos a introducirlo en el login y accederemos a la aplicacion.

## Seccion usuario 

### Artistas
Se mostrara un listado de los artistas disponibles y que aun no sigamos. En esta pantalla ademas de poder seguir a los usuarios se pueden filtrar por los estilos musicales a los que correspondan. Se podra ver tambien a los artistas relacionados de cada artista mediante un popup (FALTA de terminar)

![captura de pantalla 2018-10-07 a las 23 11 11](https://user-images.githubusercontent.com/16426967/46587014-c2f40300-ca86-11e8-8f80-5e739ff836b5.png)
![captura de pantalla 2018-10-07 a las 23 11 27](https://user-images.githubusercontent.com/16426967/46587015-c2f40300-ca86-11e8-91d6-82ba3a884da0.png)

### Seguidos
Se mostraran todos los artistas que estemos siguiendo de manera similar a la pantalla anterior permitiendo dejar de seguirlos.

![captura de pantalla 2018-10-07 a las 23 17 37](https://user-images.githubusercontent.com/16426967/46587047-3dbd1e00-ca87-11e8-9e27-79fa4a4c913f.png)

### Cuenta
Se mostraran los detalles de nuestra cuenta (nombre y permisos)

![captura de pantalla 2018-10-07 a las 23 11 56](https://user-images.githubusercontent.com/16426967/46587017-c2f40300-ca86-11e8-9c4f-13f5b74a5271.png)

## Seccion admin
Para acceder a esta seccion se ha optado por definir un usuario que se creara internamente al lanzar la aplicacion (admin / admin) que actuara como unico administrador de la plataforma.

![captura de pantalla 2018-10-07 a las 23 23 29](https://user-images.githubusercontent.com/16426967/46587102-0ac75a00-ca88-11e8-80e2-1daa132d3840.png)

### Crear nuevo artista
Permite crear un nuevo artista en el sistema, al cual le tendremos que asignar un nombre, año y elegir los estilos musicales a los que pertenece

![captura de pantalla 2018-10-07 a las 23 12 47](https://user-images.githubusercontent.com/16426967/46587018-c38c9980-ca86-11e8-8fc3-29f609ebffe4.png)

### Crear nueva persona
Permite crear un nuevo usuario en el sistema, para el cual deberemos introducir un nombre, contraseña. Todos los nuevos 
usuarios contaran con el rol usuario

![captura de pantalla 2018-10-07 a las 23 13 04](https://user-images.githubusercontent.com/16426967/46587019-c38c9980-ca86-11e8-82f3-e8f39f1d5710.png)

### Gestionar artistas relacionados
En el cual primeramente deberemos seleccionar el artista que queremos gestionar y se mostraran los artistas que tiene ya relacionados y los que no, permitiendo añadir y eliminar en la propia tabla mediante los botones

![captura de pantalla 2018-10-07 a las 23 13 46](https://user-images.githubusercontent.com/16426967/46587020-c38c9980-ca86-11e8-95ee-76a9a5e66d55.png)

## API Rest
Se ha dotado al sistema de 2 endpoint con el objetivo de facilitar la insercion de personas y artistas. Estos endpoints son:

### Artistas POST (localhost:8080/api/artist) 
pasandole en el body un JSON con la informacion del artista.

![captura de pantalla 2018-10-07 a las 23 21 21](https://user-images.githubusercontent.com/16426967/46587088-d18eea00-ca87-11e8-96bb-457afa3ad84e.png)

### Personas POST (localhost:8080/api/person) 
pasandole en el body un JSON con la informacion de la persona.

![captura de pantalla 2018-10-07 a las 23 21 47](https://user-images.githubusercontent.com/16426967/46587089-d18eea00-ca87-11e8-9a07-c97cd696eac1.png)

Se ha documentado esta API mediante Swagger, añadiendo la UI del propio Swagger (FALTA de configurar)
