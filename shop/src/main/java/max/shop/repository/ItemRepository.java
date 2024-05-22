package max.shop.repository;

import max.shop.domain.Item;
import max.shop.domain.type.ItemType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("select i from Item i where i.type = :request and i.itemStatus not in ('HIDE', 'REMOVE')")
    Page<Item> findItemPage(ItemType request, Pageable pageable);
}
