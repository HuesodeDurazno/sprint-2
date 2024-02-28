package com.example.be_java_hisp_w25_g10;

import com.example.be_java_hisp_w25_g10.dtos.PostDto;
import com.example.be_java_hisp_w25_g10.dtos.PostsDto;
import com.example.be_java_hisp_w25_g10.dtos.ProductDto;
import com.example.be_java_hisp_w25_g10.entities.Post;
import com.example.be_java_hisp_w25_g10.entities.Product;
import com.example.be_java_hisp_w25_g10.entities.RolEnum;
import com.example.be_java_hisp_w25_g10.entities.User;
import com.example.be_java_hisp_w25_g10.repositories.IRepository;
import com.example.be_java_hisp_w25_g10.services.posts.PostService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class PostServiceTests {

    @Mock
    IRepository repository;

    @InjectMocks
    PostService service;

    @Test
    public void getPostsFollowedTest(){
        //Arrange
        List<Post> postsExpected = List.of(new Post(
                1,
                new User(1, "user1", "lastName", RolEnum.SELLER),
                LocalDate.now().minusDays(19),
                new Product(1,
                        123,
                        29.99,
                        "Producto3",
                        "Hogar",
                        "MarcaC",
                        "Azul",
                        "Notas sobre el producto"
                )
        ));

        when(repository.getFollowedPosts(2)).thenReturn(postsExpected);

        PostsDto postsDto = service.getPostsFollowed(2);

        Assertions.assertTrue(postsDto.posts().get(0).date().isAfter(LocalDate.now().minusDays(20)));
    }
}
