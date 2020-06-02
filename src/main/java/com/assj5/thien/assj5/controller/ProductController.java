package com.assj5.thien.assj5.controller;

import com.assj5.thien.assj5.model.Product;
import com.assj5.thien.assj5.service.ProductSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product/")
public class ProductController {

    @Autowired
    private ProductSevice productSevice;
    @GetMapping("/")
    public String showProduct(Model model){
        model.addAttribute("product",productSevice.findAll());
        return "Product";
    }
    @GetMapping("/addproduct")
    public String save(Model model){
        model.addAttribute("product", new Product());
        return "AddProduct";
    }
    @PostMapping("/addproduct")
    public String save(@ModelAttribute Product product){
        productSevice.save(product);
        return "redirect:/";

    }
    @GetMapping(/)
}
