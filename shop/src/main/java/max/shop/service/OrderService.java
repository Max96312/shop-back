package max.shop.service;

import lombok.RequiredArgsConstructor;
import max.shop.domain.*;
import max.shop.dto.request.order.OrderSearchRequest;
import max.shop.repository.AddressRepository;
import max.shop.repository.ItemRepository;
import max.shop.repository.OrderRepository;
import max.shop.repository.MemberRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository userRepository;
    private final ItemRepository itemRepository;
    private final AddressRepository addressRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(String memberId, Long itemId, int count) {
        //엔티티 조회
        Optional<Member> user = userRepository.findById(memberId);
        Optional<Item> item = itemRepository.findById(itemId);

        //배송정보 생성
//        Delivery delivery = Delivery.createDelivery(user.get().getAddress());

        //주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item.get(), item.get().getPrice(), count);


        //주문 생성
//        Order order = Order.createOrder(user.get(), delivery, orderItem);

        //주문 저장
//        orderRepository.save(order);

//        return order.getId();
        return null;
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        //주문 엔티티 조회
        Optional<Order> order = orderRepository.findById(orderId);
        //주문 취소
        order.get().cancel();
    }

    //검색
//    public List<Order> findOrders(OrderSearchRequest searchRequest, Pageable pageable) {
//        return orderRepository.findOrders(searchRequest, pageable);
//    }
}
