package com.example.be_java_hisp_w25_g10;

import com.example.be_java_hisp_w25_g10.entities.Post;
import com.example.be_java_hisp_w25_g10.entities.Product;
import com.example.be_java_hisp_w25_g10.entities.RolEnum;
import com.example.be_java_hisp_w25_g10.entities.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class pruebav {

    //-Para el repo
    User[] users = {
            new User(1, "user1", "lastName", RolEnum.SELLER),
            new User(2, "user2", "lastName", RolEnum.SELLER),
            new User(3, "user3", "lastName", RolEnum.BUYER),
            new User(4, "user4", "lastName", RolEnum.BUYER),
    };

    List<Post> posts = new ArrayList<>(Arrays.asList(new Post[]{

            new Post(2,
                    new User(4, "user4", "lastName", RolEnum.BUYER),
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
            ),
            new Post(3,
                    new User(4, "user4", "lastName", RolEnum.BUYER),
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
            ),
            new Post(1,
                    new User(4, "user4", "lastName", RolEnum.BUYER),
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
    }));

}
