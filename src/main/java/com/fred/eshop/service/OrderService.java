package com.fred.eshop.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fred.eshop.dao.OrderDAO;
import com.fred.eshop.dao.OrderDAOMySql;
import com.fred.eshop.dao.ProductDAO;
import com.fred.eshop.dao.ProductDAOMySql;
import com.fred.eshop.model.Order;
import com.fred.eshop.model.Product;


import java.util.ArrayList;
import java.util.Random;

@Component
public class OrderService {
    @Autowired
    private OrderDAO odao;

    public static int findProductById(List<Product> productList, int newProductId){
        System.out.println("Line 27: " + productList);
        System.out.println("Line 28: " + newProductId);
        for (Product product : productList){
            if (product.getID() == newProductId){
                System.out.println("Line 31: " + product.getID() + " and " + newProductId);
                return product.getQuantity();
            }
        }
        return 0;
    }

    public void create(Order order) {
        // OrderDAO dao = new OrderDAOMySql();
            try {
                odao.create(order);
    
                // update stock in products
                List<Product> products = order.getProducts();
                ProductService pService = new ProductService();
                // decrease product stock by refunded product quantity
                for (Product product : products)
                    product.setQuantity(pService.read(product.getID()).getQuantity() - product.getQuantity());
                pService.updateAll(products);
            } catch (SQLException ex) {
                ex.printStackTrace();
             }
    }
       
    public Order read(int id) {
        // OrderDAO dao = new OrderDAOMySql();
   
        Order order = null;
        try {
            order = odao.read(id);
        } catch (SQLException ex) {
            System.out.println("Error reading order in db");
        }

        return order;
    }    

    public List<Order> readAll() {
        OrderDAO dao = new OrderDAOMySql();
        List<Order> orders = new ArrayList<>();
        try {
            orders = dao.readAll();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error reading orders in db");
        }

        return orders;
    }    
    @Transactional
    public void update(Order newOrder){        
            // cancel(order.getOrderID());
            // create(order);
            System.out.println("Line 82: " + newOrder);
            OrderDAO dao = new OrderDAOMySql();
        try {
            Order oldOrder = dao.read(newOrder.getOrderID());
            List<Product> oldProducts = oldOrder.getProducts();
            dao.update(newOrder);
            List<Product> newProducts = newOrder.getProducts();
            ProductService pService = new ProductService();

            System.out.println("Line 91:");
            for (Product product : oldProducts)
                System.out.println(product);
            for (Product product : newProducts)
                System.out.println(product);

            if (oldProducts.size() >= newProducts.size()){
                for (Product product : oldProducts){
                    int count = findProductById(newProducts, product.getID()) - product.getQuantity();
                    product.setQuantity(pService.read(product.getID()).getQuantity() - count);
                }
                pService.updateAll(oldProducts);
            }
            else{
                for (Product product : newProducts){
                    int count = product.getQuantity() - findProductById(oldProducts, product.getID());
                    System.out.println("Line 107: " + product.getQuantity() + " and "  + findProductById(oldProducts, product.getID()));
                    System.out.println("Line 107: " + count);
                    product.setQuantity(pService.read(product.getID()).getQuantity() - count);
                }
                pService.updateAll(newProducts);
            }
        } catch(SQLException ex){
            ex.printStackTrace();
            System.out.println("Error update order in db");
        }
    }

    @Transactional
    public void cancel(int id) {
        OrderDAO dao = new OrderDAOMySql();  

       try {
            // update stock in products
            Order order = dao.read(id);
            List<Product> products = order.getProducts();
            ProductService pService = new ProductService();
            // increase product stock by refunded product quantity
            for (Product product : products){
                System.out.println(product.getImage());
                product.setQuantity(pService.read(product.getID()).getQuantity() + product.getQuantity() );
            }
            pService.updateAll(products);

            dao.delete(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error cancel order in db");
        }              
    }
}
