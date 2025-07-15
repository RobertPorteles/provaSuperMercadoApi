# Etapa de build: compila o projeto com Maven sem rodar testes
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

# Copia o projeto inteiro para o container
COPY . .

# Executa o build usando o Maven wrapper
RUN ./mvnw clean package -DskipTests

# Etapa de execução: imagem final leve somente com o JAR
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copia o JAR gerado da etapa anterior e renomeia para app.jar
COPY --from=builder /app/target/*.jar app.jar

# Expondo a porta padrão do Spring Boot
EXPOSE 8080

# Comando que executa o app
ENTRYPOINT ["java", "-jar", "app.jar"]
