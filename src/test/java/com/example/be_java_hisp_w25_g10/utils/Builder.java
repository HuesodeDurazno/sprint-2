package com.example.be_java_hisp_w25_g10.utils;

import com.example.be_java_hisp_w25_g10.entities.Post;
import com.example.be_java_hisp_w25_g10.entities.Product;
import com.example.be_java_hisp_w25_g10.entities.RolEnum;
import com.example.be_java_hisp_w25_g10.entities.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Builder {
    public static User[] VerifyCountTestBuilder(){
        return new User[]{
                new User(1, "user1", "lastName", RolEnum.SELLER),
                new User(3, "user3", "lastName", RolEnum.BUYER),
        };
    }

    public static List<Post> testSortExistBuilder(){
        return new ArrayList<>(Arrays.asList(new Post[]{
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
    }
}
