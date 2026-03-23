# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Spring Boot 4.0.0 demo application showcasing:
- Dependency injection patterns (constructor injection, setter injection)
- Spring bean configuration and lifecycle management
- JPA/Hibernate entity relationships
- Flyway database migrations
- MySQL integration

**Key Technologies:**
- Java 21
- Spring Boot 4.0.0
- Spring Data JPA
- Flyway
- MySQL
- Lombok
- Maven

## Build and Run Commands

### Build and Test
```bash
# Clean and build the project
./mvnw clean package

# Run tests
./mvnw test

# Run a single test class
./mvnw test -Dtest=SpringDemoApplicationTests

# Run the application
./mvnw spring-boot:run
```

### Database Management
```bash
# Run Flyway migrations
./mvnw flyway:migrate

# Clean database (CAUTION: drops all objects)
./mvnw flyway:clean

# View migration info
./mvnw flyway:info

# Validate migrations
./mvnw flyway:validate
```

**Database Connection:**
- URL: `jdbc:mysql://localhost:3306/demo-db`
- Username: `root`
- Password: `admin`
- Database auto-created if not exists

### Development Tools
```bash
# Skip tests during build
./mvnw clean package -DskipTests

# Run with specific profile
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

## Architecture and Design Patterns

### Dependency Injection Strategy

**Preferred: Bean-based configuration via AppConfig**
- Located in `AppConfig.java`
- Uses `@Configuration` and `@Bean` annotations
- Enables conditional logic for bean selection at runtime
- Allows centralized application configuration separate from business logic
- Example: Payment gateway selection via `payment-gateway` property in `application.yaml`

**Alternative: Component scanning with @Service**
- Simpler for straightforward cases
- Use when no conditional logic needed

**Important:** The codebase uses **constructor injection** as the default pattern. Setter injection is only for optional dependencies.

### Bean Lifecycle Configuration

Beans support custom lifecycle management:
- `@Lazy` - delays initialization until first use (e.g., Stripe payment service)
- `@Scope` - controls instance creation:
  - `singleton` (default) - one instance per Spring container
  - `prototype` - new instance per request (e.g., PayPal service)
  - `request` - per HTTP request
  - `session` - per HTTP session
- `@PostConstruct` and `@PreDestroy` for lifecycle hooks

### JPA Entity Design Approach

**Database-First Design** (current approach):
1. Define schema in Flyway migrations (`src/main/resources/db/migration/`)
2. Generate JPA entities from database (Right click entities directory > New > JPA Entities from DB)
3. Minimal `@Column` directives needed since schema drives constraints

**Model-First Alternative:**
1. Write entity classes with full JPA annotations
2. Generate Flyway migration (Right click db.migration directory > New > Flyway migration)
3. Clean up redundant directives after migration created

**Recommendation:** Use one technique consistently. If models are easier to express, write them first, generate migration, then remove redundant annotations.

### Entity Relationships

**Relationship Management Pattern:**
All bidirectional relationships use helper methods to maintain consistency:

```java
// In User entity
public void addAddress(Address address) {
    addresses.add(address);
    address.setUser(this);  // Maintain bidirectional link
}
```

**Key Relationship Types:**
- **OneToMany**: User → Addresses (with `CascadeType.PERSIST` and `CascadeType.REMOVE`)
- **ManyToMany**: User ↔ Tags (uses `user_tags` join table with `@JoinTable`)
- **OneToOne**: User ↔ Profile (with `CascadeType.REMOVE`)
- **ManyToOne**: Product → Category

**Important Annotations:**
- `mappedBy` - identifies relationship owner (where foreign key exists)
- `@Builder.Default` - required for collections when using Lombok's `@Builder`
- `@ToString.Exclude` - prevents lazy loading issues and circular references in toString()
- `orphanRemoval = true` - deletes child entities when removed from parent collection

### Package Structure

```
com.devopssean.spring_demo/
├── entities/           # JPA entity classes
│   ├── User.java
│   ├── Address.java
│   ├── Profile.java
│   ├── Tag.java
│   ├── Product.java
│   └── Category.java
├── repositories/       # Spring Data repositories
│   ├── UserRepository.java
│   └── AddressRepository.java
├── AppConfig.java      # Bean configuration
├── OrderService.java   # Business logic
├── PaymentService.java # Interface
├── *PaymentService.java # Implementations (Stripe, PayPal, Blik)
└── SpringDemoApplication.java # Main class
```

## Configuration Files

### application.yaml
- Database connection settings
- `payment-gateway` property controls which payment service bean is injected
- `stripe.supported-currencies` demonstrates configuration injection
- JPA `show-sql: true` for SQL logging

### Flyway Migrations
- Location: `src/main/resources/db/migration/`
- Naming: `V{version}__{description}.sql` (e.g., `V3__init.sql`)
- Current schema includes: users, addresses, profiles, tags, user_tags, products, categories

## Development Notes

### Lombok Usage
All entities use Lombok annotations:
- `@Getter`, `@Setter` - accessors
- `@AllArgsConstructor`, `@NoArgsConstructor` - constructors
- `@Builder` - builder pattern
- `@ToString` - toString implementation

Always use `@Builder.Default` for initialized collections and `@ToString.Exclude` for relationships to avoid lazy loading issues.

### Repository Pattern
Repositories extend `CrudRepository<Entity, ID>` or `JpaRepository<Entity, ID>` for data access. No implementation needed - Spring Data JPA provides runtime proxies.

### Payment Service Example
Demonstrates strategy pattern with runtime selection via configuration. To change payment gateway, modify `payment-gateway` value in `application.yaml` to: `stripe`, `paypal`, or `blik`.
