package com.example.be_java_hisp_w25_g10.integrityTests;

import com.example.be_java_hisp_w25_g10.dtos.PostsDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrityTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getPostsFollowed() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/products/followed/3/list")).
                andDo(print()).
                andExpect(status().isOk()).
                andExpect(MockMvcResultMatchers.jsonPath("$").exists()).
                andExpect(MockMvcResultMatchers.jsonPath("$.posts.size()").value(3)).
                andExpect(MockMvcResultMatchers.jsonPath("$.posts[0].date").value("2024-02-29")).andReturn();

        String response = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        PostsDto postsDto = mapper.readValue(response, PostsDto.class);

        LocalDate date = postsDto.posts().get(0).date();

        Assertions.assertTrue(date.isAfter(LocalDate.now().minusDays(20)));

    }

}
