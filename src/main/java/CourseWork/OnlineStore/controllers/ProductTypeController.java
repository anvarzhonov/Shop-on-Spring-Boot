package CourseWork.OnlineStore.controllers;

import CourseWork.OnlineStore.models.ProductType;
import CourseWork.OnlineStore.repo.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductTypeController {
    @Autowired
    ProductTypeRepository  productTypeRepository;

    /* -------------------------Контроллеры для редактипования и добавления типов продукта-------------------------*/
    @GetMapping("/productTypeList")
    public String productTypeList(Model model) {
        Iterable<ProductType> types = productTypeRepository.findAll();
        model.addAttribute("types", types);
        return "productType-FORM/productTypeList";
    }

    @GetMapping("productTypeList/add")
    public String productTypeListAdd(Model model) {
        ProductType productType = new ProductType();
        model.addAttribute("productType", productType);
        return "productType-FORM/productTypeForm";
    }

    @PostMapping("productTypeList/add")
    public String productTypeListAddSubmit(@ModelAttribute ProductType productType, Model model){
        productTypeRepository.save(productType);
        model.addAttribute("types", productTypeRepository.findAll());
        return "productType-FORM/productTypeList";
    }

    @GetMapping("/productTypeList/delete/{productTypeId}")
    public String productTypeListDelete(@PathVariable("productTypeId") long id, Model model) {
        productTypeRepository.deleteById(id);
        model.addAttribute("types", productTypeRepository.findAll());
        return "productType-FORM/productTypeList";
    }

    //    редактирование типа продукта
    @GetMapping("/productTypeList/edit/{productTypeId}")
    public String productTypeListEdit(@PathVariable("productTypeId") long id, Model model) {
        ProductType productType = productTypeRepository.findById(id).orElse(null);
        model.addAttribute("productType", productType);
        return "productType-FORM/productTypeForm";
    }
    /*==============================================================================================================*/
}
