package CourseWork.OnlineStore.controllers;

import CourseWork.OnlineStore.models.Product;
import CourseWork.OnlineStore.models.ProductType;
import CourseWork.OnlineStore.repo.ProductRepository;
import CourseWork.OnlineStore.repo.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@Controller
public class MainController {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductTypeRepository productTypeRepository;


    // Главная страница
    @GetMapping("/")
    public String greeting(Model model) {
        model.addAttribute("name", "Главная страница");
        return "home";
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
        model.addAttribute("product", product);

        return "catalog/product";
    }



}
