package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm(){

        return "basic/addForm";
    }

    //@PostMapping("/add")
    /**
     * save에서 쓰는 RequestParam 이름은 addForm의 name = ~~ 에서 찾아서 쓴 것
     */
    public String addItemV1(
                       @RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam int quantity,
                       Model model){
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);
        itemRepository.save(item);

        model.addAttribute("item", item);


        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item//, Model model(얘도 생략 가능)
                            ){
        itemRepository.save(item);
        /**
         * @ModelAttribute("~")이런 식으로 써주게 되면 model.addAttribute작업을 수행해줌
         * 따라서 아래 코드는 생략 가능(생략해도 동일한 효과 줌)
         */
        //model.addAttribute("item", item); //자동 추가, 생략 가능
        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item){
        //이렇게 modelAttribute 뒤에 아무것도 없으면 클래스명 Item 첫글자만 소문자로 바꾸어 modelAttribute에 담기게됨(item으로)
        itemRepository.save(item);
        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV4(Item item){
        //@ModelAttribute 생략 가능
        //생략하면 String은 RequestParam이 오고 객체는 ModelAttribute가 옴
        itemRepository.save(item);
        return "basic/item";
    }

    //post redirect get 방식 활용(중복 저장되는 효과 방지)
    //url encoding안되는 문제 해결하기 위해 RedirectAttributes 사용해야한다
    //@PostMapping("/add")
    public String addItemV5(Item item){
        //@ModelAttribute 생략 가능
        //생략하면 String은 RequestParam이 오고 객체는 ModelAttribute가 옴
        itemRepository.save(item);
        return "redirect:/basic/items/" + item.getId();
    }

    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes){

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item){
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }



    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA", 10000,10));
        itemRepository.save(new Item("itemB", 20000,20));
    }

}
