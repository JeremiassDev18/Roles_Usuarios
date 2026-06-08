# 🔐 RolesUsuarios API

API REST para gestión de usuarios con roles y autenticación JWT.

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.3-green)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![JWT](https://img.shields.io/badge/JWT-0.12.6-red)

---

## 🛠️ Tecnologías

- Java 21
- Spring Boot 4.0.3
- Spring Security
- Spring Data JPA / Hibernate
- MySQL
- JWT (JJWT 0.12.6)
- Lombok

---

## ⚙️ Configuración local

### 1. Clona el repositorio
```bash
git clone https://github.com/JeremiassDev18/Roles_Usuarios.git
cd Roles_Usuarios
```

### 2. Crea la base de datos
```sql
CREATE DATABASE rolusuario;
```

### 3. Configura el archivo de propiedades
```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```

Edita `application.properties` con tus credenciales:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/rolusuario
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_PASSWORD
jwt.secret=GENERA_CON: openssl rand -base64 64
jwt.expiration=3600000
```

### 4. Corre el proyecto
```bash
mvn spring-boot:run
```

---

## 📌 Endpoints

### 🔓 Autenticación
| Método | Ruta | Descripción | Requiere token |
|--------|------|-------------|----------------|
| POST | `/auth/login` | Login y obtener JWT | No |

### 👤 Usuarios
| Método | Ruta | Descripción | Requiere token |
|--------|------|-------------|----------------|
| GET | `/usuarios` | Listar usuarios | Sí |
| GET | `/usuarios/{id}` | Buscar por id | Sí |
| POST | `/usuarios` | Crear usuario | No |
| PUT | `/usuarios/{id}` | Actualizar usuario | ADMIN |
| DELETE | `/usuarios/{id}` | Eliminar usuario | ADMIN |

### 🏷️ Roles
| Método | Ruta | Descripción | Requiere token |
|--------|------|-------------|----------------|
| GET | `/roles` | Listar roles | Sí |
| GET | `/roles/{id}` | Buscar por id | Sí |
| POST | `/roles` | Crear rol | Sí |
| DELETE | `/roles/{id}` | Eliminar rol | Sí |

---

## 🔑 Cómo usar la autenticación

**1. Crea un rol:**
```http
POST /roles
Content-Type: application/json

{
  "nombre": "ADMIN"
}
```

**2. Crea un usuario:**
```http
POST /usuarios
Content-Type: application/json

{
  "nombre": "Jeremy",
  "cedula": "1234567890",
  "password": "123456",
  "edad": 20,
  "rol": "ADMIN"
}
```

**3. Haz login:**
```http
POST /auth/login
Content-Type: application/json

{
  "cedula": "1234567890",
  "password": "123456"
}
```

**4. Usa el token en cada request:**
```http
GET /usuarios
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

---

## 📁 Estructura del proyecto
src/main/java/RolesUsuarios/
├── Config/
│   └── PaswordConfig.java
├── Controller/
│   ├── AuthController.java
│   ├── RolController.java
│   └── UsuarioController.java
├── DTO/
│   ├── LoginRequestDTO.java
│   ├── LoginResponseDTO.java
│   ├── UsuarioRequestDTO.java
│   └── UsuarioResponseDTO.java
├── Entity/
│   ├── Rol.java
│   └── Usuario.java
├── Exception/
│   ├── ErrorResponse.java
│   ├── GlobalExceptionHandler.java
│   ├── RolNotFoundException.java
│   └── UsuarioNotFoundException.java
├── Repository/
│   ├── RolRepository.java
│   └── UsuarioRepository.java
├── Security/
│   ├── JwtAuthenticationFilter.java
│   ├── JwtUtil.java
│   └── SecurityConfig.java
└── Service/
├── AuthService.java
├── RolService.java
└── UsuarioRolesService.java

## 👨‍💻 Autor

**Jeremias** — [@JeremiassDev18](https://github.com/JeremiassDev18)
