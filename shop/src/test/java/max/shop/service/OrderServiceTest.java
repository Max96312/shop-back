package max.shop.service;

import jakarta.persistence.EntityManager;
import max.shop.common.exception.NotEnoughStockException;
import max.shop.domain.Item;
import max.shop.domain.Order;
import max.shop.domain.User;
import max.shop.domain.embed.Address;
import max.shop.domain.type.Gender;
import max.shop.domain.type.OrderStatus;
import max.shop.dto.request.ItemCreateForm;
import max.shop.dto.request.UserRegisterForm;
import max.shop.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    @Test
    void 상품_주문() throws Exception {
        //given
        User user = createUser();
        Item item = createItem("바지" ,1000, 100);

        int orderCount = 2;

        //when
        Long orderId = orderService.order(user.getId(), item.getId(), orderCount);

        //then
        Optional<Order> getOrder = orderRepository.findById(orderId);

        assertEquals(OrderStatus.ORDER, getOrder.get().getStatus(), "상품 주문시 상태는 ORDER");
        assertEquals(1, getOrder.get().getOrderItems().size(), "주문한 상품 종류 수가 정확해야 한다.");
        assertEquals(1000 * orderCount, getOrder.get().getTotalPrice(), "주문 가격은 가격 * 수량이다.");
        assertEquals(98, item.getStockQuantity(), "주문 수량만큼 재고가 줄어야 한다.");
    }

    @Test
    void 상품주문_재고수량초과() throws Exception {
        //given
        User user = createUser();
        Item item = createItem("상의", 1000, 100);

        int orderCount = 111;

        //when
        //then
        assertThrows(NotEnoughStockException.class, () -> orderService.order(user.getId(), item.getId(), orderCount));
    }

    @Test
    void 주문취소() throws Exception {
        //given
        User user = createUser();
        Item item = createItem("바지" ,1000, 100);

        int orderCount = 2;

        Long orderId = orderService.order(user.getId(), item.getId(), orderCount);

        //when
        orderService.cancelOrder(orderId);

        //then
        Optional<Order> getOrder = orderRepository.findById(orderId);

        assertEquals(OrderStatus.CANCEL, getOrder.get().getStatus(), "주문 취소시 상태는 CANCEL이다.");
        assertEquals(100, item.getStockQuantity(), "주문이 취소된 상품은 재고가 증가해야 한다.");
    }

    private Item createItem(String name, int price, int stockQuantity) {
        ItemCreateForm itemForm = ItemCreateForm.of(name, price, stockQuantity, "바지 상품 등록 테스트 테스트 테스트");
        Item item = Item.createItem(itemForm);
        em.persist(item);
        return item;
    }

    private User createUser() {
        UserRegisterForm userForm = UserRegisterForm.of("testId", "testName", "qwer1234!", "010-1234-1234", LocalDateTime.now(), Gender.MALE);
        User user = User.createUser(userForm);
        Address address = new Address("서울", "청계천로", "1길", "000");
        user.setAddress(address);
        em.persist(user);
        return user;
    }
}