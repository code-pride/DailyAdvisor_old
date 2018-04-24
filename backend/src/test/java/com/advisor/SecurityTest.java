package com.advisor;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.servlet.Filter;
import java.util.Arrays;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.testng.Assert.fail;

@SpringBootTest
@DataJpaTest
@ContextConfiguration
@WebAppConfiguration
public class SecurityTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private Filter springSecurityFilterChain;

    @Autowired
    private MockMvc mvc;

    public void setUp() throws Exception{


    }

    @Test
    public void normalFlowTest() throws Exception{

        MvcResult mvcResult = mvc.perform(get("/populate")).andReturn();
        if(mvcResult.getResponse().getStatus() == 403) {
            fail();
        }

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("grant_type", "password");
        map.add("username", "m@m.mm");
        map.add("password", "111111");
        try {
            mvcResult = mvc
                    .perform(post("/oauth/token")
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                            .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
                                    new BasicNameValuePair("grant_type", "password"),
                                    new BasicNameValuePair("username", "m@m.mm"),
                                    new BasicNameValuePair("password", "111111")
                            ))))
                            .with(httpBasic("frontendClientId","frontendClientSecret")))
                    .andReturn();
            if(mvcResult.getResponse().getStatus() == 403) {
                fail();
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}
