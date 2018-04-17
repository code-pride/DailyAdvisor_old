package com.advisor;

import com.advisor.model.response.UserProfileResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.Map;

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
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("grant_type", "password");
        map.add("username", "m@m.mm");
        map.add("password", "111111");
        map.add("client_id", "frontendClientId");
        map.add("redirect_uri","https://www.google.pl");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> response = restTemplate.postForEntity( PARENT_URL + "/oauth/authorize", request , Object.class );
        response.getStatusCode();
    }
}
