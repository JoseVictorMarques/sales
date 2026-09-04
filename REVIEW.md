# Revisão do Projeto - Car Sales Backend

## 📋 Resumo
Este é um projeto Spring Boot para gerenciar vendas de carros. Identificamos **11 pontos de melhoria** que precisam de atenção para melhorar segurança, qualidade de código e funcionalidade.

---

## 🔴 Problemas Críticos (Segurança)

### 1. **Credenciais de Banco de Dados Expostas** ⚠️
**Arquivo:** `src/main/resources/application.properties`
```properties
spring.datasource.username=postgres
spring.datasource.password=dbajv
```

**Problema:** Credenciais hardcoded no repositório (comprometimento de segurança)

**Solução:**
```properties
# application.properties (sem credenciais)
spring.datasource.url=jdbc:postgresql://localhost:5432/db_sales
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

Usar variáveis de ambiente ou arquivo `application-local.properties` (gitignored)

---

## 🟡 Problemas Importantes (Qualidade)

### 2. **Typo no DTO** - Método `toEntiy()` ao invés de `toEntity()`
**Arquivo:** `src/main/java/com/example/sales/model/dtos/CarDTO.java:22`

**Problema:** Nome incorreto do método causa confusão
```java
// Atual
public Car toEntiy(){  // ❌ Typo

// Correto
public Car toEntity(){  // ✅
```

---

### 3. **Testes no Local Errado**
**Arquivo:** `src/main/java/com/example/sales/tests/`

**Problema:** Testes estão em `src/main/java` ao invés de `src/test/java`

**Solução:** Mover arquivos:
- `src/main/java/com/example/sales/tests/UserTest.java` → `src/test/java/com/example/sales/UserTest.java`
- `src/main/java/com/example/sales/tests/BrandTest.java` → `src/test/java/com/example/sales/BrandTest.java`
- `src/main/java/com/example/sales/tests/TransmissionTest.java` → `src/test/java/com/example/sales/TransmissionTest.java`

Isso evita compilar testes na produção.

---

### 4. **Tratamento de Exceções Genérico**
**Arquivo:** `src/main/java/com/example/sales/business/UserBusiness.java:23-27`

**Problema:** Captura `Exception` genérica
```java
// ❌ Atual
try {
    userDB = userRepository.findUserByUsername(user.getUsername());
} catch(Exception e){
    throw new RuntimeException(" Username does not exists! ");
}

// ✅ Melhor
Optional<User> userOptional = userRepository.findByUsername(user.getUsername());
if (userOptional.isEmpty()) {
    throw new RuntimeException("Username does not exist!");
}
User userDB = userOptional.get();
```

---

### 5. **Inconsistência em Endpoints**
**Arquivo:** Controllers

**Problema:** Endpoints com nomes inconsistentes
- `/users` (plural) ✓
- `/car` (singular) ✗

**Solução:** Usar padrão consistente (plural para todos)
```java
@RequestMapping("cars")  // ao invés de "car"
public class CarController { ... }
```

---

## 🟠 Problemas de Funcionalidade

### 6. **CarController Incompleto**
**Arquivo:** `src/main/java/com/example/sales/controller/CarController.java`

**Problema:** Apenas endpoint de criar
```java
// Faltam:
@GetMapping("/{id}")           // Obter 1 carro
@GetMapping                    // Listar todos
@PutMapping("/{id}")          // Atualizar
@DeleteMapping("/{id}")       // Deletar
```

---

### 7. **Falta Validação de Entrada**
**Problema:** Sem anotações de validação nas DTOs e Entities

**Solução:**
```java
@Getter
@Setter
public class UserDTO {
    @NotBlank(message = "Username é obrigatório")
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank(message = "Email é obrigatório")
    @Email
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 8, message = "Senha deve ter no mínimo 8 caracteres")
    private String newPassword;
}
```

---

### 8. **Sem Tratamento Global de Exceções**
**Problema:** Não há `@RestControllerAdvice` para centralizar tratamento de erros

**Solução:** Criar classe `GlobalExceptionHandler.java`
```java
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
        Map<String, String> error = Map.of("error", ex.getMessage());
        return ResponseEntity.status(500).body(error);
    }
}
```

---

### 9. **Sem Logging**
**Problema:** Nenhuma linha de log em toda aplicação

**Solução:** Adicionar logs
```java
@Service
public class UserBusiness {
    private static final Logger logger = LoggerFactory.getLogger(UserBusiness.class);

    public User registerUser(User user) {
        logger.info("Registrando novo usuário: {}", user.getUsername());
        String hashPassword = PasswordUtils.hashPassword(user.getPassword());
        user.setPassword(hashPassword);
        return userRepository.save(user);
    }
}
```

---

### 10. **Problema de Lazy Loading**
**Arquivo:** `src/main/java/com/example/sales/model/entities/Car.java:44-45`

**Problema:** LAZY fetch pode causar LazyInitializationException
```java
@Lob
@Basic(fetch = FetchType.LAZY)  // ⚠️ Pode causar problemas
@Column(name = "image")
private byte[] image;
```

---

### 11. **Sem Configuração CORS**
**Problema:** Se há frontend separado, APIs não vão funcionar

**Solução:**
```java
@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/sales-api/**")
                    .allowedOrigins("http://localhost:3000")
                    .allowedMethods("*")
                    .allowedHeaders("*");
            }
        };
    }
}
```

---

## ✅ O Que Está Bom

- ✓ Estrutura de pacotes bem organizada
- ✓ Uso de DTOs corretamente
- ✓ Hashing de senha com BCrypt (seguro)
- ✓ Teste unitário com Mockito
- ✓ Use de Lombok para reduzir boilerplate
- ✓ Separação clara entre Controller/Business/Repository

---

## 📋 Checklist de Ações

### Crítico (Fazer Imediatamente)
- [ ] Remover credenciais do `application.properties`
- [ ] Usar variáveis de ambiente para configs sensíveis
- [ ] Mover testes para `src/test/java`
- [ ] Corrigir typo `toEntiy()` → `toEntity()`

### Importante (Sprint Atual)
- [ ] Implementar validação com `@Valid` e anotações
- [ ] Criar `GlobalExceptionHandler` para tratamento centralizado
- [ ] Adicionar logging com SLF4J
- [ ] Completar endpoints do CarController (GET, PUT, DELETE)
- [ ] Padronizar nomes dos endpoints (tudo plural)

### Melhorias (Futuro)
- [ ] Adicionar autenticação JWT
- [ ] Implementar paginação nos GET (list)
- [ ] Adicionar CORS configuration
- [ ] Melhorar tratamento de lazy loading
- [ ] Documentação Swagger/OpenAPI

---

## 🚀 Prioridade de Implementação

1. **Semana 1:** Segurança (credenciais) + Validação
2. **Semana 2:** Testes + Exception Handler + Logging
3. **Semana 3:** Endpoints faltantes + CORS
4. **Depois:** Autenticação JWT + Documentação

