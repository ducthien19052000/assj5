package com.assj5.thien.assj5.controller;

import com.assj5.thien.assj5.model.Product;
import com.assj5.thien.assj5.service.ProductSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller

public class ProductController {

    @Autowired
    private ProductSevice productSevice;

    @GetMapping("product")
    public String showProduct(Model model, @PageableDefault(size = 8)Pageable pageable){
        model.addAttribute( "product",productSevice.findAll(pageable));
        model.addAttribute("productMen",productSevice.findMen(pageable));
        model.addAttribute("productWomen",productSevice.findWomen(pageable));
        model.addAttribute("productSale",productSevice.findSale(pageable));
        return "product-listing";
    }
    @GetMapping("addproduct")
    public String save(Model model){
        model.addAttribute("product", new Product());
        return "AddProduct";
    }
    @PostMapping("addproduct")
    public String save(@ModelAttribute Product product){
        productSevice.save(product);
        return "redirect:product/";

    }

}
