package com.sys.gestaoatividades.e2e;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Testcontainers
class AtividadeE2ETest {

    @Container
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");

    @BeforeAll
    public static void setup() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @LocalServerPort
    private int port;

    @BeforeEach
    public void beforeEach() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
    }

    @Test
    void testCadastrar() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body("{\"nome\":\"Atividade 1\"}")
                .when()
                .post("/atividades")
                .then()
                .statusCode(201)
                .body("id", equalTo(2))
                .body("nome", equalTo("Atividade 1"));
    }

    @Test
    void testListar() {
        RestAssured.given()
                .when()
                .get("/atividades")
                .then()
                .statusCode(200)
                .body("[0].id", equalTo(1))
                .body("[1].id", equalTo(2))
                .body("[0].nome", equalTo("Atividade 1"))
                .body("[1].nome", equalTo("Atividade 2"));
    }

    @Test
    void testIniciar() {
        RestAssured.given()
                .when()
                .put("/atividades/1/iniciar")
                .then()
                .statusCode(204);
    }

    @Test
    void testFinalizar() {
        RestAssured.given()
                .when()
                .delete("/atividades/1/finalizar")
                .then()
                .statusCode(204);
    }
}
