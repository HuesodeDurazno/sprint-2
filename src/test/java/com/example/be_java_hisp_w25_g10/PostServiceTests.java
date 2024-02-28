package com.example.be_java_hisp_w25_g10;

import com.example.be_java_hisp_w25_g10.dtos.PostDto;
import com.example.be_java_hisp_w25_g10.dtos.PostsDto;
import com.example.be_java_hisp_w25_g10.dtos.ProductDto;
import com.example.be_java_hisp_w25_g10.entities.Post;
import com.example.be_java_hisp_w25_g10.entities.Product;
import com.example.be_java_hisp_w25_g10.entities.RolEnum;
import com.example.be_java_hisp_w25_g10.entities.User;
import com.example.be_java_hisp_w25_g10.exceptions.NotFoundException;
import com.example.be_java_hisp_w25_g10.repositories.IRepository;
import com.example.be_java_hisp_w25_g10.services.posts.PostService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class PostServiceTests {

    @Mock
    IRepository repository;

    @InjectMocks
    PostService service;

    private static final List<Post> postsExpected = List.of(
            new Post(
                    1,
                    new User(1, "Martin", "Ushima", RolEnum.SELLER),
                    LocalDate.now().minusDays(19),
                    new Product(1,
                            123,
                            29.99,
                            "Producto3",
                            "Hogar",
                            "MarcaC",
                            "Azul",
                            "Notas sobre el producto")
            ),
            new Post(
                    1,
                    new User(1, "Camilo", "Jaramillo", RolEnum.SELLER),
                    LocalDate.now().minusDays(17),
                    new Product(1,
                            123,
                            29.99,
                            "Producto3",
                            "Hogar",
                            "MarcaC",
                            "Azul",
                            "Notas sobre el producto"
                    )
            )
    );

    @Test
    public void getPostsFollowedTest() {
        //Arrange
        LocalDate dateTest = LocalDate.now().minusDays(20);
        when(repository.getFollowedPosts(2)).thenReturn(postsExpected);

        //Act
        PostsDto postsDto = service.getPostsFollowed(2,"date_desc");

        //Assert
        Assertions.assertTrue(postsDto.posts().get(0).date().isAfter(dateTest));
    }

    @Test
    public void getCountPostsFollowed(){
        //Arrange
        when(repository.getFollowedPosts(2)).thenReturn(postsExpected);

        //Act
        PostsDto postsDto = service.getPostsFollowed(2,"date_desc");

        //Assert
        Assertions.assertEquals(2, postsDto.posts().size());
    }

    @Test
    public void getPostFollowedUnsuccessful(){
        //Arrange
        List<Post> posts = new ArrayList<>();
        when(repository.getFollowedPosts(2)).thenReturn(posts);

        //Act & Assert
        Assertions.assertThrows(NotFoundException.class, () -> service.getPostsFollowed(1,"date_desc"));
    }
    @Test
    public void VerifyListPostDesc(){
        //Arrage
        PostsDto expected = new PostsDto(
                3,
                List.of(
                        new PostDto(
                                3,
                                1,
                                LocalDate.of (2024, 02, 28),
                                new ProductDto(1,
                                        "Producto1",
                                        "Electrónico",
                                        "MarcaA",
                                        "Rojo",
                                        "Notas sobre el producto",
                                        123,
                                        29.99)

                        ),
                        new PostDto(
                                3,
                                1,
                                LocalDate.of (2024, 02, 22),
                                new ProductDto(1,
                                        "Producto1",
                                        "Electrónico",
                                        "MarcaA",
                                        "Rojo",
                                        "Notas sobre el producto",
                                        123,
                                        29.99)

                        ),
                        new PostDto(
                                3,
                                1,
                                LocalDate.of (2024, 02, 15),
                                new ProductDto(1,
                                        "Producto1",
                                        "Electrónico",
                                        "MarcaA",
                                        "Rojo",
                                        "Notas sobre el producto",
                                        123,
                                        29.99)

                        )
                )
        );

        List<Post> posts = new ArrayList<>(Arrays.asList(new Post[]{

                new Post(1,
                        new User(3, "user3", "lastName", RolEnum.BUYER),
                        LocalDate.of (2024, 02, 15),
                        new Product(1,
                                123,
                                29.99,
                                "Producto1",
                                "Electrónico",
                                "MarcaA",
                                "Rojo",
                                "Notas sobre el producto"
                        )
                ),
                new Post(1,
                        new User(3, "user3", "lastName", RolEnum.BUYER),
                        LocalDate.of (2024, 02, 28),
                        new Product(1,
                                123,
                                29.99,
                                "Producto1",
                                "Electrónico",
                                "MarcaA",
                                "Rojo",
                                "Notas sobre el producto"
                        )
                ),
                new Post(1,
                        new User(3, "user3", "lastName", RolEnum.BUYER),
                        LocalDate.of (2024, 02, 22),
                        new Product(1,
                                123,
                                29.99,
                                "Producto1",
                                "Electrónico",
                                "MarcaA",
                                "Rojo",
                                "Notas sobre el producto"
                        )
                )

        }));
        int userId = 3;
        String sortOrder = "date_desc";
        //act
        when(repository.getFollowedPosts(userId)).thenReturn(posts);
        PostsDto actual = service.getPostsFollowed(userId, sortOrder);
        //assert
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void VerifyListPostAsc(){
        //Arrage
        PostsDto expected = new PostsDto(
                3,
                List.of(
                        new PostDto(
                                3,
                                1,
                                LocalDate.of (2024, 02, 15),
                                new ProductDto(1,
                                        "Producto1",
                                        "Electrónico",
                                        "MarcaA",
                                        "Rojo",
                                        "Notas sobre el producto",
                                        123,
                                        29.99)

                        ),
                        new PostDto(
                                3,
                                1,
                                LocalDate.of (2024, 02, 22),
                                new ProductDto(1,
                                        "Producto1",
                                        "Electrónico",
                                        "MarcaA",
                                        "Rojo",
                                        "Notas sobre el producto",
                                        123,
                                        29.99)

                        ),
                        new PostDto(
                                3,
                                1,
                                LocalDate.of (2024, 02, 28),
                                new ProductDto(1,
                                        "Producto1",
                                        "Electrónico",
                                        "MarcaA",
                                        "Rojo",
                                        "Notas sobre el producto",
                                        123,
                                        29.99)

                        )
                )
        );

        List<Post> posts = new ArrayList<>(Arrays.asList(new Post[]{

                new Post(1,
                        new User(3, "user3", "lastName", RolEnum.BUYER),
                        LocalDate.of (2024, 02, 15),
                        new Product(1,
                                123,
                                29.99,
                                "Producto1",
                                "Electrónico",
                                "MarcaA",
                                "Rojo",
                                "Notas sobre el producto"
                        )
                ),
                new Post(1,
                        new User(3, "user3", "lastName", RolEnum.BUYER),
                        LocalDate.of (2024, 02, 28),
                        new Product(1,
                                123,
                                29.99,
                                "Producto1",
                                "Electrónico",
                                "MarcaA",
                                "Rojo",
                                "Notas sobre el producto"
                        )
                ),
                new Post(1,
                        new User(3, "user3", "lastName", RolEnum.BUYER),
                        LocalDate.of (2024, 02, 22),
                        new Product(1,
                                123,
                                29.99,
                                "Producto1",
                                "Electrónico",
                                "MarcaA",
                                "Rojo",
                                "Notas sobre el producto"
                        )
                )

        }));
        int userId = 3;
        String sortOrder = "date_asc";
        //act
        when(repository.getFollowedPosts(userId)).thenReturn(posts);
        PostsDto actual = service.getPostsFollowed(userId, sortOrder);
        //assert
        Assertions.assertEquals(expected, actual);
    }
}
