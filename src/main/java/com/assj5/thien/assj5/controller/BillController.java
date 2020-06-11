package com.assj5.thien.assj5.controller;

import com.assj5.thien.assj5.model.Cart;
import com.assj5.thien.assj5.model.Product;
import com.assj5.thien.assj5.model.ProductDetail;
import com.assj5.thien.assj5.model.Size;
import com.assj5.thien.assj5.repository.ProductDetailRepository;
import com.assj5.thien.assj5.service.BillService;
import com.assj5.thien.assj5.service.CategoryService;
import com.assj5.thien.assj5.service.ProductDetailService;
import com.assj5.thien.assj5.service.ProductSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BillController {

    @Autowired
    private ProductSevice productSevice;

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/cart")
    public String cart9(Model model) {
        model.addAttribute("category",categoryService.findAll());
        return "Cart";
    }

    @GetMapping("/checkout")
    public String checkOut(Model model) {
        model.addAttribute("category",categoryService.findAll());
        return "CheckOut";
    }

    @PostMapping("/cart/{productId}")
    public String addToCart(@PathVariable("productId") Long productId, @RequestParam("total") int total, @RequestParam("size") String size, HttpSession session) {
        if (session.getAttribute("cart") == null) {
            List<Cart> cartItems = new ArrayList<>();
            List<ProductDetail> productDetails = productDetailRepository.findAllByProductId(productId);
            if (productDetails != null) {
                for (ProductDetail productDetail : productDetails) {
                    if (productDetail.getSizes().getSizeName().equals(size)) {
                        cartItems.add(new Cart(productSevice.findByID(productId), total, productDetail.getSizes(), productDetail.getProductAmount()));
                    }
                }
            }
            session.setAttribute("cart", cartItems);
            session.setAttribute("myCartTotal", totalPriceItems(cartItems));
            session.setAttribute("myCartNum", cartItems.size());
        } else {
            List<Cart> carts = (List<Cart>) session.getAttribute("cart");
            int index = this.existsItems(productId, carts,size);
            if (index == -1) {
                addToCart(productId, size, total, session);
            } else {
                int quantity = carts.get(index).getQuantity() + total ;
                carts.get(index).setQuantity(quantity);
            }
            session.setAttribute("cart", carts);
            session.setAttribute("myCartTotal", totalPriceItems(carts));
            session.setAttribute("myCartNum", carts.size());
        }
        return "redirect:/cart";

    }

    public double totalPriceItems(List<Cart> cartItems) {
        int count = 0;
        for (Cart list : cartItems) {
            count += (list.getProduct().getProductPrice() - (list.getProduct().getProductPrice() / 100 * list.getProduct().getProductSale())) * list.getQuantity();
        }
        return count;
    }

    public int existsItems(Long produtId, List<Cart> carts,String size) {
        for (int i = 0; i < carts.size(); i++) {
            if (carts.get(i).getProduct().getProductId().equals(produtId) && carts.get(i).getSize().getSizeName().equals(size)) {
                return i;
            }
        }
        return -1;
    }

    public List<Cart> addToCart(Long productId, String size, int total, HttpSession session) {
        List<Cart> carts = (List<Cart>) session.getAttribute("cart");
        List<ProductDetail> productDetails = productDetailRepository.findAllByProductId(productId);
        if (productDetails != null) {
            for (ProductDetail productDetail : productDetails) {
                if (productDetail.getSizes().getSizeName().equals(size)) {
                    carts.add(new Cart(productSevice.findByID(productId), total, productDetail.getSizes(), productDetail.getProductAmount()));
                }
            }
        }
        return carts;
    }
}
