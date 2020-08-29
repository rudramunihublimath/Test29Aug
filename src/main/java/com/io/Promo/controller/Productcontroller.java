package com.io.Promo.controller;

import com.io.Promo.model.*;
import com.io.Promo.repo.Discountrepo;
import com.io.Promo.repo.Productrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class Productcontroller {

    @Autowired
    private Productrepo productrepo;
    @Autowired
    private Discountrepo discountrepo;

    @PostMapping("/api/totalprice")
    public ResponseEntity<ProductResp>  findtotalprice(@RequestBody Orderlist orderlist) {
        int total = 0;
        List<Order> oList = orderlist.getOrderList();
        ArrayList<String> pl= new ArrayList<>();

        for (Order o :oList)  {
           pl.add(o.getName());
           int qty= o.getQuantity();
           Product rate = productrepo.findByName(o.getName());
           Discount discount=  discountrepo.findByName(o.getName());

            int tqty1;
            int tqty2;

            if (o.getName().equals(discount.getName())){

                if(qty==1){
                    total += rate.getPrice();
                    System.out.println("without Discount :" + 1 +" times for Product - " +o.getName() +
                            " Price is - " + rate.getPrice());
                }
                else if(qty>=discount.getQuantity()  ) {
                       tqty1 = qty / discount.getQuantity();
                       tqty2 = qty % discount.getQuantity();
                       for (int i = 1; i <= tqty1; i++) {
                          total += discount.getPrice();
                           System.out.println("Discount applied :"+i + " times for Product - " +o.getName() +
                                   " Price is - " + discount.getPrice());
                       }
                       for (int i = 1; i <= tqty2; i++) {
                          total += rate.getPrice();
                           System.out.println("without Discount :"+i + " times for Product - " +o.getName() +
                                   " Price is - " + rate.getPrice());
                       }
                  }
           }
        }

        // check for C & D combination
        int sum1=0;
        for (Order o :oList) {
            Product rate = productrepo.findByName(o.getName());
            //Discount discount=  discountrepo.findByName(o.getName());
            if (    (rate.getName().equals("C") || rate.getName().equals("D"))  &&
                    (pl.contains("C") && pl.contains("D"))  )
                 sum1+= rate.getPrice();  // 20+15-30
        }
        int sum2=0;
        for (Order o :oList) {
            Discount discount=  discountrepo.findByName(o.getName());
            if (    (discount.getName().equals("C") || discount.getName().equals("D")    ) &&
                    (pl.contains("C") && pl.contains("D"))  )
                sum2+= discount.getPrice();  // 20+15-30
        }
        System.out.println("C+D => Orignal Price - "+sum1 +" : "+"Discounted Price - "+ sum2);
        total = total - (sum1-sum2);
        System.out.println("Total Price = "+total);
        ProductResp resp = new ProductResp();
        resp.setTotal(total);
        //return total;
        return new ResponseEntity<ProductResp>(resp, HttpStatus.OK);
    }


    @RequestMapping(value = "/api/save/product", method = RequestMethod.POST)
    public ResponseEntity<Product> saveproducts(@RequestBody Product product) {
        Product product1  = productrepo.save(product);
        return new ResponseEntity<Product>(product1, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/api/erase/product", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void DeleteAllproducts() {
        productrepo.deleteAll();
    }

    @RequestMapping(value = "/api/getall", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getAllEvents() {

        List<Product>  products = productrepo.findAll();

        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }


}
