package hello.itemservice.domain.item;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    void save(){
        Item item = new Item("ItemA", 10000, 10);

        Item savedItem = itemRepository.save(item);

        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(savedItem);

        /**
         * 내가 직접 짠 코드
         * Item item = new Item("hi", 1000,5);
         * Item save = itemRepository.save(item);
         *Assertions.assertThat(itemRepository.findById(save.getId())).isEqualTo(item);
         */


        
    }

    @Test
    void findAll(){
        /**
         * 직접 짠 코드
         * Item item1 = new Item("hi", 1000,5);
         *         itemRepository.save(item1);
         *         Item item2 = new Item("hello", 2000,3);
         *         itemRepository.save(item2);
         *         Assertions.assertThat(itemRepository.findAll().size()).isEqualTo(2);
         */

        Item item1 = new Item("Item1", 10000, 10);
        Item item2 = new Item("Item2", 20000, 20);
        itemRepository.save(item1);
        itemRepository.save(item2);

        List<Item> result = itemRepository.findAll();

        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1, item2);


    }
    @Test
    void updateItem(){
        /**
         * 직접 짠 코드
         * Item item1 = new Item("hi", 1000,5);
         *         itemRepository.save(item1);
         *
         *         Item item2 = new Item("hello", 2000,3);
         *         itemRepository.update(item1.getId(), item2);
         *         Assertions.assertThat(itemRepository.findById(item1.getId()).getItemName()).isEqualTo(item2.getItemName());
         *         Assertions.assertThat(itemRepository.findById(item1.getId()).getPrice()).isEqualTo(2000);
         */

        Item item = new Item("Item1", 10000, 10);
        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();

        Item updateParam = new Item("Item2", 20000, 30);
        itemRepository.update(itemId, updateParam);

        Item findItem = itemRepository.findById(itemId);
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());


    }

}
