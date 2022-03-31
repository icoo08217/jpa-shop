package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    // JPA 변경감지
    @Transactional // 트랜잭션이 있어야함.
    public void updateItem(Long itemId, String name, int price , int stockQuantity) {
        Item findItem = itemRepository.findOne(itemId); // 영속성 컨텍스트에 올라왔다.

        // 실무에서는 set을 열지 않고 의미있는 메서드를 만들어내자.
        // 어디서 변경되었는지 역추적이 가능해진다.
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
        // 값들이 변경 되었음을 인지함.
    }

    /**
     * 엔티티를 변경할 때는 항상 변경감지를 사용하세요.
     *
     */

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
