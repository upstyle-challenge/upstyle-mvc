package br.com.fiap.upstyle.service;

import br.com.fiap.upstyle.config.RabbitMqConfig;
import br.com.fiap.upstyle.model.AppUser;
import br.com.fiap.upstyle.model.Product;
import br.com.fiap.upstyle.repository.ProductRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.api.OllamaModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OllamaService {

    @Autowired
    private ChatModel chatModel;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RabbitMqConfig mqConfig;

    public Product analisarCliente(@Payload AppUser user) throws Exception {
        List<Product> products = productRepository.findAll();
        String prompt = "Analyze the purchase probability for the products on this list " + products +
                " , based on the profile of this customer: " + user +
                ". Each product is described as an instance of the Product type, with the following attributes: " +
                " id (Long), name (String), description (String), price (Double), imageUrl (String). " +
                "Consider the following factors for ranking: " +
                " 1) Compatibility with the customer's profile (gender, age, lifestyle). " +
                " 2) Price range accessible to the customer, with a preference for cheaper items within the budget. " +
                " 3) Style and utility, considering practical or attractive items for the young adult audience. " +
                "Return the product with the highest purchase probability, using the Product type and keeping the attributes id, name, description, price, imageUrl in JSON format." +
                " Attention! DO NOT include any information other than the JSON. Include all Product type properties in JSON (id, name, description, price and imageUrl). Don't respond in markdown, just plain text.";

        try {
            ChatResponse chatResponse = chatModel.call(
                    new Prompt(
                            prompt,
                            OllamaOptions.builder()
                                    .withModel(OllamaModel.LLAMA3_2)
                                    .withTemperature(0.5)
                                    .build()
                    )
            );

            String responseText = chatResponse.getResult().getOutput().getContent();
            System.out.println("Resultado da IA: " + responseText);

            if (!responseText.trim().startsWith("[") && !responseText.trim().startsWith("{")) {
                throw new RuntimeException("Resposta da IA não está em formato JSON: " + responseText);
            }

            return objectMapper.readValue(responseText, Product.class);
        }catch (Exception e){
            throw new Exception("Ocorreu um erro na análise com o Ollama" + e.getMessage());
        }
    }
}
