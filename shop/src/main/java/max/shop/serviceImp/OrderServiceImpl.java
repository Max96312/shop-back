package max.shop.serviceImp;

import lombok.RequiredArgsConstructor;
import max.shop.common.exception.ItemNotFoundException;
import max.shop.common.exception.UserNotFoundException;
import max.shop.domain.*;
import max.shop.dto.request.order.OrderCreateForm;
import max.shop.dto.request.order.OrderListCreateFrom;
import max.shop.dto.response.order.OrderResponseDto;
import max.shop.repository.ItemRepository;
import max.shop.repository.OrderRepository;
import max.shop.repository.MemberRepository;
import max.shop.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 단건 주문
     */
    @Transactional
    @Override
    public Long order(OrderCreateForm form) {
        //엔티티 조회
        Member member = memberRepository.findById(form.getMemberId()).orElseThrow(UserNotFoundException::new);
        Item item = itemRepository.findById(form.getOrderItemInfo().getItemId()).orElseThrow(ItemNotFoundException::new);

        //배송정보 생성
        Delivery delivery = Delivery.createDelivery(member.getAddress());

        //주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(),form.getOrderItemInfo().getCount());


        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        //주문 저장
        orderRepository.save(order);

        return order.getId();
    }

    /**
     * List 주문
     */
    @Transactional
    @Override
    public Long orderFromItemList(OrderListCreateFrom form) {
        //엔티티 조회
        Member member = memberRepository.findById(form.getMemberId()).orElseThrow(UserNotFoundException::new);

        // 엔티티 조회 & orderItem 생성
        OrderItem[] orderItems = form.getOrderItemInfoList().stream()
                .map(o -> {
                    Item item = itemRepository.findById(o.getItemId()).orElseThrow(ItemNotFoundException::new);
                    return OrderItem.createOrderItem(item, item.getPrice(), o.getCount());
                })
                .toList().toArray(new OrderItem[0]);

        //배송정보 생성
        Delivery delivery = Delivery.createDelivery(member.getAddress());

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItems);

        // 주문 저장
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

    @Override
    public List<Order> findOrders() {
        return List.of();
    }

    @Override
    public OrderResponseDto getOrder() {
        return null;
    }
}
