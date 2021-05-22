package CourseWork.OnlineStore.controllers;

import CourseWork.OnlineStore.models.Message;
import CourseWork.OnlineStore.models.Product;
import CourseWork.OnlineStore.models.ProductType;
import CourseWork.OnlineStore.models.User;
import CourseWork.OnlineStore.repo.MessageRepository;
import CourseWork.OnlineStore.repo.ProductRepository;
import CourseWork.OnlineStore.repo.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class MainController {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductTypeRepository productTypeRepository;
    @Autowired
    MessageRepository messageRepo;


    // Главная страница
    @GetMapping("/")
    public String greeting(Model model) {
        model.addAttribute("name", "Главная страница");
        return "home";
    }
    //Страница логина
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("name","Авторизация");
        return "login";
    }



    //Страница с каталогами
    @GetMapping("/products")
    public String products(Model model){

        /**
         * Map в котором для каждого типа продукта хранится свой список продуктов
         */
        Iterable<ProductType> types =productTypeRepository.findAll();
        Map<ProductType, List<Product>> map = new HashMap<>();
        // Идет по каждому типу и для каждого типа в MAP закидывается ключ и значение.
        types.forEach(type -> map.put(type, productRepository.findByProductType(type)));
        model.addAttribute("map", map);
        return "products";
    }

    @GetMapping("/products/{id}")
    public String productDetails(@PathVariable(value = "id") long id, Model model){
        Product product = productRepository.findById(id).orElse(null);

        Iterable<Message> messages = messageRepo.findAll();

        model.addAttribute("product", product);
        model.addAttribute("messages", messages);

        return "catalog/product";
    }
//    @PostMapping("/products/{id}")
//    public String addMessage(@PathVariable(value = "id") long id,
//                             @AuthenticationPrincipal User user,
//                             Model model,
//                             @RequestParam String text){
//
//        Message message= new Message(text,user);
//        messageRepo.save(message);
//
//        Iterable<Message> messages = messageRepo.findAll();
//
//        model.addAttribute("messages", messages);
//        return "catalog/product";
//    }



}
