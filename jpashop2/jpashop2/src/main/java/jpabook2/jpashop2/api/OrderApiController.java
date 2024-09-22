package jpabook2.jpashop2.api;

import jpabook2.jpashop2.Domain.Address;
import jpabook2.jpashop2.Domain.Order;
import jpabook2.jpashop2.Domain.OrderItem;
import jpabook2.jpashop2.Domain.OrderStatus;
import jpabook2.jpashop2.repository.OrderRepository;
import jpabook2.jpashop2.repository.OrderSearch;
import jpabook2.jpashop2.repository.order.query.OrderFlatDto;
import jpabook2.jpashop2.repository.order.query.OrderItemQueryDto;
import jpabook2.jpashop2.repository.order.query.OrderQueryDto;
import jpabook2.jpashop2.repository.order.query.OrderQueryRepository;
import jpabook2.jpashop2.service.query.OrderDto;
import jpabook2.jpashop2.service.query.OrderQueryService;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderApiController {
    private final OrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;
    private final OrderQueryService orderQueryService;

    @GetMapping("/api/v1/orders")
    private List<Order> ordersV1()
    {
        List<Order> all = orderRepository.findAll(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName();
            order.getDelivery().getAddress();

            List<OrderItem> orderItems = order.getOrderItems();
            for (OrderItem orderItem : orderItems) {
                orderItem.getItem().getName();
            }

        }
        return all;
    }
    @GetMapping("/api/v2/orders")
    public List<OrderDto> ordersV2(){
        List<OrderDto> orderDtos = orderQueryService.ordersV2();
        return orderDtos;
    }

    @GetMapping("/api/v3/orders")
    public List<OrderDto> ordersV3(){
        List<Order> orders= orderRepository.findAllWithItem();
        return orders.stream()
                .map(o->new OrderDto(o))
                .collect(Collectors.toList());
    }

    @GetMapping("/api/v3.1/orders")
    public List<OrderDto> ordersV3_page(
            @RequestParam(value = "offset", defaultValue = "0")int offset,
            @RequestParam(value = "limit", defaultValue = "100")int limit
    ){
        List<Order> orders= orderRepository.findAllWithMemberDelivery(offset, limit);



        return orders.stream()
                .map(o->new OrderDto(o))
                .collect(Collectors.toList());
    }

    @GetMapping("/api/v4/orders")
    public List<OrderQueryDto>ordersV4(){
        return orderQueryRepository.findOrderQueryDtos();

    }

    @GetMapping("/api/v5/orders")
    public List<OrderQueryDto>ordersV5(){
        return orderQueryRepository.findAllByDto_optimization();

    }

    @GetMapping("/api/v6/orders")
    public List<OrderQueryDto>ordersV6(){
        List<OrderFlatDto> flats = orderQueryRepository.findAllByDto_flat();

        // OrderId 기준으로 OrderQueryDto와 그 안의 OrderItemQueryDto 리스트로 변환
        return flats.stream()
                .collect(Collectors.groupingBy(
                        flat -> new OrderQueryDto(flat.getOrderId(), flat.getName(), flat.getOrderDate(), flat.getOrderStatus(), flat.getAddress()),
                        Collectors.mapping(flat -> new OrderItemQueryDto(flat.getOrderId(), flat.getItemName(), flat.getOrderPrice(), flat.getCount()), Collectors.toList())
                ))
                .entrySet().stream()
                .map(entry -> {
                    OrderQueryDto orderQueryDto = entry.getKey();
                    orderQueryDto.setOrderItems(entry.getValue());
                    return orderQueryDto;
                })
                .collect(Collectors.toList());

    }




}
