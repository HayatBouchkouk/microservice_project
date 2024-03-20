package com.example.orderservice;


import com.example.orderservice.dto.InventoryResponse;
import com.example.orderservice.dto.OrderItemsDto;
import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.exception.ProductNotInStockException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j

public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    public void placeOrder(OrderRequest orderRequest)
    {
        Order order=new Order();
        order.setOrderNumber(UUID.randomUUID().toString());//generate orderNumber automatically

        //The orderRequest contains a list of OrderItemsDto. These need to be converted to OrderItems, which are part of the Order object.
      List<OrderItems> orderLineItemsList=  orderRequest.getOrderItemsDtos()
                        .stream()
                        .map(this::mapToDto)
                        .toList();

      order.setOrderItemsList(orderLineItemsList);

        //return list of skuCodes: ["iPhone_13", "Samsung Galaxy", "iPad"]
        List<String> skuCodes = order.getOrderItemsList().stream()//stream gives you the value
                .map(OrderItems->OrderItems.getSkuCode())//what to do with this value is to get the skuCode
                .toList();

        System.out.println("SKU Codes:");
        skuCodes.forEach( System.out::println);

        // Call Inventory Service, and place order if product is in stock
        //A WebClient instance (webClient) is used to make an HTTP GET request to the inventory service's endpoint
        //we need to check if the products exists in inventory Repositoty are available or not


        InventoryResponse [] inventoryResponses= webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory",uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse [] .class)
                .block();


       boolean allProductsInStock= Arrays.stream(inventoryResponses)
               .allMatch(inventoryResponse -> inventoryResponse.isInStock());


        if (allProductsInStock)
        {
            orderRepository.save(order);
        }

         else{
              throw new ProductNotInStockException("Product is not in stock, please try again later");
         }


    }
    private OrderItems mapToDto(OrderItemsDto orderLineItemsDto) {

        OrderItems orderLineItems=new OrderItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());

        log.debug("Mapped OrderItems: {}", orderLineItems);

        return orderLineItems;
    }


    public List<Order> getAllOrders() {

      return  orderRepository.findAll();

    }
}
