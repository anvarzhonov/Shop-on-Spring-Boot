package CourseWork.OnlineStore.controllers;

import CourseWork.OnlineStore.models.Product;
import CourseWork.OnlineStore.models.ProductType;
import CourseWork.OnlineStore.repo.ProductRepository;
import CourseWork.OnlineStore.repo.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class ProductController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductTypeRepository productTypeRepository;

    /* -------------------------Контроллеры для редактипования и добавления типов продукта-------------------------*/
    @GetMapping("/productList")
    public String productList(Model model) {
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "product-FORM/productList";
    }

    @GetMapping("/productList/add")
    public String productListAdd(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "product-FORM/productForm";
    }

    @PostMapping("/productList/add")
    public String productListAddSubmit(@ModelAttribute Product product, Model model){
        productRepository.save(product);
        model.addAttribute("products", productRepository.findAll());
        return "product-FORM/productList";
    }

    @GetMapping("/productList/delete/{productId}")
    public String productListDelete(@PathVariable("productId") long id, Model model) {
        productRepository.deleteById(id);
        model.addAttribute("products", productRepository.findAll());
        return "product-FORM/productList";
    }

    @GetMapping("/productList/edit/{productId}")
    public String productListEdit(@PathVariable("productId") long id, Model model) {
        Product product = productRepository.findById(id).orElse(null);
        model.addAttribute("product", product);
        return "product-FORM/productForm";
    }
    /*==============================================================================================================*/
}
