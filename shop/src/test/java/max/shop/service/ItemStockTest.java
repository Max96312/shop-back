package max.shop.service;

import max.shop.common.exception.ItemNotFoundException;
import max.shop.domain.Item;
import max.shop.dto.request.item.ItemCreateForm;
import max.shop.repository.ItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
public class ItemStockTest {
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemRepository itemRepository;

    @BeforeEach
    public void before(){
         itemService.saveItem(new ItemCreateForm("멋진 상의", 10000, 100, "테스트 설명란"));
    }

    @AfterEach
    public void after(){
        itemRepository.deleteAll();
    }

    @Test
    void 재고감소() throws Exception {
        //given
        Item findItem = itemRepository.findById(1L).orElseThrow(ItemNotFoundException::new);
        findItem.removeStock(1);

        //when
        Assertions.assertEquals(99, findItem.getStockQuantity());
    }

    // race condition 발생 테스트
    @Test
    public void 동시에_100개의_요청() throws InterruptedException {
        //given
        Item findItem = itemRepository.findById(1L).orElseThrow(ItemNotFoundException::new);

        //when
        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    findItem.removeStock(1);
                }finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        ///then
        Assertions.assertEquals(0, findItem.getStockQuantity());
    }
}
