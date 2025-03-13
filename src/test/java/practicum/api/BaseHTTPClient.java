package practicum.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static practicum.Constants.*;

public class BaseHTTPClient {

    private RequestSpecification baseRequestSpec = new RequestSpecBuilder()
            .setBaseUri(SITE_URL)
            .addHeader(CONTENT_TYPE_HEADER_NAME, JSON_HEADER_VALUE)
            .setRelaxedHTTPSValidation()
            .addFilter(new RequestLoggingFilter())
            .addFilter(new ResponseLoggingFilter())
            .build();


    protected Response makePostRequest(String path, Object body){
        return given().spec(baseRequestSpec).body(body).post(path).thenReturn();
    }

    protected Response makeDeleteRequest(String path, String token){
        return given().spec(baseRequestSpec).auth().oauth2(token).delete(path).thenReturn();
    }
}

