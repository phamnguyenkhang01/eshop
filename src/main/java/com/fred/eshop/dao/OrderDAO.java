package com.fred.eshop.dao;

import java.sql.SQLException;
import java.util.List;

import com.fred.eshop.model.Order;

public interface OrderDAO {
    // CRUD
    void create(Order order) throws SQLException;
    Order read(int id) throws SQLException;
    List<Order> readAll() throws SQLException;
    int update(Order order) throws SQLException;
    int delete(int id) throws SQLException;       
}
