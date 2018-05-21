/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author amanda
 */
public interface OrdersDao {

    List<Order> getOrders(LocalDate dateChoice) throws DataPersistenceException;

    Order addOrder(Order o) throws DataPersistenceException;

    Order editOrder(Order editedOrder) throws DataPersistenceException;

    Order removeOrder(Order o) throws DataPersistenceException;

}
