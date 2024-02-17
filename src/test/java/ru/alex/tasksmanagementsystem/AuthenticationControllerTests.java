package ru.alex.tasksmanagementsystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@SpringBootTest
public class AuthenticationControllerTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public  void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void registrationTestOnSuccessResultWithNonBoundaryCases() throws Exception {
        mockMvc.perform(post("/api/v1/auth/registration")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content( " \"email\" : \"2048453@gmail.com\",\n" +
                "\"username\" : \"testUser\",\n" +
                "    \"fullName\" : \"testUser\",\n" +
                "    \"password\" :\"MyP@ssw0r1\"\n" + "}")
        ).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

}
