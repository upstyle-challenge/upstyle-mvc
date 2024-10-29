package br.com.fiap.upstyle.service;


import br.com.fiap.upstyle.config.RabbitMqConfig;
import br.com.fiap.upstyle.model.Product;
import br.com.fiap.upstyle.repository.ProductRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RabbitMqConfig mqConfig;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product save(Product product) {
        Product productSave = productRepository.save(product);

        System.out.println("Enviando produto para fila: " + productSave);
        mqConfig.send(productSave);

        return productSave;
    }

    public void edit(Long id, Product product) {
        Product product1 = productRepository.findById(id).get();
        product1.edit(product);
        productRepository.save(product1);
    }

    public void delete(Long id) {
        Product referenceById = productRepository.getReferenceById(id);
        productRepository.delete(referenceById);
    }

    @RabbitListener(queues = {"product-save-queue"})
    public void productReciverQueue(@Payload Product product) {
        System.out.println("Produto recebido da fila: " + product);

        messagingTemplate.convertAndSend("/topic/products", product);
    }

    public Integer totalCount() {
        return productRepository.totalProductsCount();
    }
}