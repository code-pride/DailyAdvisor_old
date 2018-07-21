package com.advisor;

import com.advisor.model.request.LoginRequest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SecurityTest {

    private static String FRONTEND_URL = "http://localhost:8091/";
    private static String CSRF_TOKEN_COOKIE_NAME = "XSRF-TOKEN";
    private static String CSRF_TOKEN_HEADER_NAME = "X-XSRF-TOKEN";

    private String csrf;
    private String jwt;

    @BeforeClass
    public void setUp() {
        this.csrf = given()
                .when()
                .get(FRONTEND_URL + "csrf")
                .then()
                .statusCode(200)
                .and()
                .cookie(CSRF_TOKEN_COOKIE_NAME)
                .and()
                .extract()
                .cookie(CSRF_TOKEN_COOKIE_NAME);

        given()
                .when()
                .get(FRONTEND_URL + "populate")
                .then()
                .statusCode(200);
    }

    @Test(priority = 0)
    public void loginTest() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("m@m.mm");
        loginRequest.setPassword("111111");

        this.jwt = given()
                .cookie(CSRF_TOKEN_COOKIE_NAME, csrf)
                .header(CSRF_TOKEN_HEADER_NAME, csrf)
                .body(loginRequest)
                .when()
                .post(FRONTEND_URL + "login")
                .then()
                .statusCode(200)
                .and()
                .cookie("_secu")
                .and()
                .extract()
                .cookie("_secu");
    }

    @Test(priority = 1)
    public void securedRequestTest() {
        given()
                .cookie(CSRF_TOKEN_COOKIE_NAME, csrf)
                .header(CSRF_TOKEN_HEADER_NAME, csrf)
                .cookie("_secu",jwt)
                .when()
                .get(FRONTEND_URL + "getUserProfile")
                .then()
                .statusCode(200);
    }

    @Test(priority = 2)
    public void logoutTest() {
        given()
                .cookie(CSRF_TOKEN_COOKIE_NAME, csrf)
                .header(CSRF_TOKEN_HEADER_NAME, csrf)
                .cookie("_secu",jwt)
                .when()
                .post(FRONTEND_URL + "logout")
                .then()
                .statusCode(200);
    }

    @Test(priority = 3)
    public void badJwtRequestTest() {
        given()
                .cookie(CSRF_TOKEN_COOKIE_NAME, csrf)
                .header(CSRF_TOKEN_HEADER_NAME, csrf)
                .cookie("_secu",jwt)
                .when()
                .get(FRONTEND_URL + "getUserProfile")
                .then()
                .statusCode(403);
    }
}
