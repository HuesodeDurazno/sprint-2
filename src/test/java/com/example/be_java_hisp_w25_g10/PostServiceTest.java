package com.example.be_java_hisp_w25_g10;

import com.example.be_java_hisp_w25_g10.dtos.PostsDto;
import com.example.be_java_hisp_w25_g10.entities.Post;
import com.example.be_java_hisp_w25_g10.entities.Product;
import com.example.be_java_hisp_w25_g10.entities.RolEnum;
import com.example.be_java_hisp_w25_g10.entities.User;
import com.example.be_java_hisp_w25_g10.exceptions.BadRequestException;
import com.example.be_java_hisp_w25_g10.repositories.Repository;
import com.example.be_java_hisp_w25_g10.services.posts.PostService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @Mock
    private Repository postRepository;
    @InjectMocks
    private PostService postService;


    @Test
    public void testAscSortExist() {

        String sortOrder = "date_asc";
        List<Post> samplePosts = new ArrayList<>(Arrays.asList(new Post[]{
                new Post(1,
                        new User(1, "user1", "lastName", RolEnum.SELLER),
                        LocalDate.now(),
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
                new Post(2,
                        new User(1, "user1", "lastName", RolEnum.SELLER),
                        LocalDate.now().minusDays(19),
                        new Product(1,
                                123,
                                29.99,
                                "Producto2",
                                "Tecnología",
                                "MarcaB",
                                "Amarillo",
                                "Notas sobre el producto"
                        )
                )
        }));


        when(postRepository.getFollowedPosts(1,sortOrder)).thenReturn(samplePosts);

        PostsDto result = postService.getPostsFollowed(1, sortOrder);

        assertNotNull(result);

    }

    @Test
    public void testDescSortExist() {

        String sortOrder = "date_desc";
        List<Post> samplePosts = new ArrayList<>(Arrays.asList(new Post[]{
                new Post(1,
                        new User(1, "user1", "lastName", RolEnum.SELLER),
                        LocalDate.now(),
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
                new Post(2,
                        new User(1, "user1", "lastName", RolEnum.SELLER),
                        LocalDate.now().minusDays(19),
                        new Product(1,
                                123,
                                29.99,
                                "Producto2",
                                "Tecnología",
                                "MarcaB",
                                "Amarillo",
                                "Notas sobre el producto"
                        )
                )
        }));


        when(postRepository.getFollowedPosts(1,sortOrder)).thenReturn(samplePosts);

        PostsDto result = postService.getPostsFollowed(1, sortOrder);

        assertNotNull(result);

    }

    @Test
    public void testInvalidSortOrder(){
        String sortOrder = "imvalid";
        List<Post> samplePosts = new ArrayList<>(Arrays.asList(new Post[]{
                new Post(1,
                        new User(1, "user1", "lastName", RolEnum.SELLER),
                        LocalDate.now(),
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
                        new User(1, "user1", "lastName", RolEnum.SELLER),
                        LocalDate.now().minusDays(19),
                        new Product(1,
                                123,
                                29.99,
                                "Producto2",
                                "Tecnología",
                                "MarcaB",
                                "Amarillo",
                                "Notas sobre el producto"
                        )
                )
        }));

        when(postRepository.getFollowedPosts(1,sortOrder)).thenReturn(samplePosts);

        assertThrows(BadRequestException.class , () -> postService.getPostsFollowed(1, sortOrder));
    }
}
