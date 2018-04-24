package com.advisor;

import com.advisor.model.request.LoginRequest;
import com.advisor.model.response.UserProfileResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.Map;

import static org.testng.Assert.fail;

public class OAuth2Test {

    private static final String PARENT_URL = "http://localhost:8091/";

    @BeforeClass
    public void setUp() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForEntity(PARENT_URL + "/populate",String.class);
    }

    @Test
    public void normalFlowTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("grant_type", "password");
        map.add("username", "m@m.mm");
        map.add("password", "111111");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("frontendClientId","frontendClientSecret"));

        ResponseEntity<Object> response = restTemplate.postForEntity( PARENT_URL + "/oauth/token", request , Object.class );
        String accessToken = (String) ((Map) response.getBody()).get("access_token");
        restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization","Bearer " + accessToken);

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        try{
            restTemplate.getForEntity(PARENT_URL + "/getUserProfile", UserProfileResponse.class, entity);
        } catch (HttpClientErrorException e) {
            e.getMessage();
        }
    }

    @Test
    public void implicitFlowTest() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("111111");
        loginRequest.setUsername("m@m.mm");

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<?> jwtResponse = restTemplate.postForEntity(PARENT_URL + "/login", loginRequest, Object.class);
        String jwt =  jwtResponse.getHeaders().getFirst("authorization").substring(7);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization","Bearer " + jwt);


        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("grant_type", "password");
        map.add("client_id", "frontendClientId");
        map.add("redirect_uri","https://www.google.pl");

        HttpEntity entity = new HttpEntity(headers);
        //HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        final HttpClient httpClient = HttpClientBuilder.create()
                .setRedirectStrategy(new LaxRedirectStrategy() {
                    @Override
                    protected boolean isRedirectable(final String method) {
                        return false;
                    }
                })
                .build();
        factory.setHttpClient(httpClient);
        restTemplate.setRequestFactory(factory);
        try {
            ResponseEntity<String> response = restTemplate
                    .exchange(
                            PARENT_URL + "oauth/authorize?redirect_uri=http://google.pl/&client_id=frontendClientId&response_type=token&audience=fdsfdsf&scope=read&state=fsdfsdfsdfsdf",
                            HttpMethod.GET,
                            entity,
                            String.class);
            response.getStatusCode();
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}
