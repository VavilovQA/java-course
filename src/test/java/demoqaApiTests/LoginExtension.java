package demoqaApiTests;

import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.lang.reflect.AnnotatedElement;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class LoginExtension implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        // 1. Проверяем тип параметра
        boolean isUserSession = parameterContext.getParameter().getType().equals(UserSession.class);

        // 2. Ищем аннотацию @WithLogin на методе ИЛИ на классе
        boolean hasAnnotation = extensionContext.getTestMethod()
                .map(m -> m.isAnnotationPresent(WithLogin.class))
                .orElse(false)
                || extensionContext.getTestClass()
                .map(c -> c.isAnnotationPresent(WithLogin.class))
                .orElse(false);

        // 🔴 ОТЛАДКА: выведет в консоль true/false
        System.out.println("🔍 supportsParameter: isUserSession=" + isUserSession + " | hasAnnotation=" + hasAnnotation);

        return isUserSession && hasAnnotation;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        System.out.println("✅ resolveParameter вызван! Логинимся...");

        return Allure.step("Автоматическая авторизация через @WithLogin", () -> {
            String authData = "{\"userName\":\"" + TestBase.login + "\",\"password\":\"" + TestBase.password + "\"}";

            Response response = given()
                    .contentType(JSON)
                    .body(authData)
                    .when()
                    .post("/Account/v1/Login")
                    .then()
                    .statusCode(200)
                    .extract().response();

            return new UserSession(
                    response.path("userId"),
                    response.path("token"),
                    response.path("expires")
            );
        });
    }
}
