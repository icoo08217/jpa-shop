package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    // 상품 저장
    public void save(Item item){
        if (item.getId() == null) { // db에 들어가기전에는 상품의 id는 비어있다.
            em.persist(item);
        } else {
            em.merge(item); // 병합
            // 변경 감지 기능을 사용하면 원하는 속성만 선택해서 변경할 수 있지만,
            // 병합을 사용하면 모든 속성이 변경된다. ---> 병합시 값이 없으면 null로 업데이트 할 위험도 있다. (병합은 모든 필드를 교체한다.)
        }
    }

    // 상품 하나 조회
    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    // 상품 여러개 조회
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
