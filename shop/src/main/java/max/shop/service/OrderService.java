package max.shop.service;

import lombok.RequiredArgsConstructor;
import max.shop.domain.*;
import max.shop.repository.AddressRepository;
import max.shop.repository.ItemRepository;
import max.shop.repository.OrderRepository;
import max.shop.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final AddressRepository addressRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(String memberId, Long itemId, int count) {
        //엔티티 조회
        Optional<User> user = userRepository.findById(memberId);
        Optional<Item> item = itemRepository.findById(itemId);

        //배송정보 생성
        Delivery delivery = Delivery.createDelivery(user.get().getAddress());

        //주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item.get(), item.get().getPrice(), count);


        //주문 생성
        Order order = Order.createOrder(user.get(), delivery, orderItem);

        //주문 저장
        orderRepository.save(order);

        return order.getId();
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
}
