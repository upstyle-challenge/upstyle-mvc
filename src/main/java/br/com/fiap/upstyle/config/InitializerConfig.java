package br.com.fiap.upstyle.config;

import br.com.fiap.upstyle.model.AppUser;
import br.com.fiap.upstyle.model.Product;
import br.com.fiap.upstyle.repository.AppUserRepository;
import br.com.fiap.upstyle.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Component
public class InitializerConfig {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostConstruct
    public void initUser() {
        if (appUserRepository.findByUsername("admin") == null && appUserRepository.findByUsername("user") == null) {
            List<AppUser> initialUsers = Arrays.asList(
                    new AppUser("admin", passwordEncoder.encode("admin123"), "admin@email.com", "Admin","User", "male", 20, 20.000, new HashSet<>(Collections.singletonList("ROLE_ADMIN"))),
                    new AppUser("user", passwordEncoder.encode("user123"), "user.test@email.com", "User","Test", "male", 20, 20.000, new HashSet<>(Collections.singletonList("ROLE_USER")))
            );

            appUserRepository.saveAll(initialUsers);
        }
    }

    @PostConstruct
    public void initProduct() {
        if (productRepository.count() == 0) {
            List<Product> initialProducts = Arrays.asList(
                    new Product("Camiseta Básica", "Camiseta de algodão 100% com cores neutras.", 49.90, "https://static.netshoes.com.br/produtos/kit-camiseta-basica-masculina-c-5-pecas-/06/MSX-0007-006/MSX-0007-006_zoom1.jpg?ts=1695700660"),
                    new Product("Calça Jeans", "Calça jeans clássica azul.", 129.90, "https://m.media-amazon.com/images/I/51NubPhhNKL._AC_.jpg"),
                    new Product("Tênis Casual", "Tênis casual confortável para o dia a dia.", 499.90, "https://lojavirus.fbitsstatic.net/img/p/tenis-nike-air-force-1-07-flyease-white-white-dx5883-100-74994/312644-1.jpg?w=1200&h=1200&v=no-value"),
                    new Product("Jaqueta de Couro", "Jaqueta de couro sintético para dias frios.", 299.90, "https://cavallier.com.br/cdn/shop/files/product-image-1974445864_1000x_62588cb6-9b4c-414c-8192-2ac108f6fd1a.webp?v=1685248366"),
                    new Product("Relógio de Pulso", "Relógio analógico elegante com pulseira de couro.", 159.90, "https://http2.mlstatic.com/D_NQ_NP_628707-MLB72840396911_112023-O.webp"),
                    new Product("Mochila Esportiva", "Mochila leve e resistente para atividades esportivas.", 89.90, "https://www.lojaspresidente.com.br/media/catalog/product/cache/1/image/1000x/9df78eab33525d08d6e5fb8d27136e95/m/o/mochila_nike_elemental_unissex_verde_4.jpg")
            );

            productRepository.saveAll(initialProducts);
        }
    }
}
