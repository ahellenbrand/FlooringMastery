/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.DataPersistenceException;
import com.sg.flooringmastery.dto.Order;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author amanda
 */
public interface FloorService {

    List<Order> getOrders(LocalDate dateChoice) throws InvalidOrderNumberException,
            DataPersistenceException;

    Order calculateOrder(Order o) throws DataPersistenceException,
            OrderValidationException, StateValidationException, ProductValidationException;

    Order getOrder(LocalDate dateChoice, int orderNumber) throws
            DataPersistenceException, InvalidOrderNumberException;

    Order addOrder(Order o) throws DataPersistenceException;

    Order compareOrders(Order savedOrder, Order editedOrder)
            throws DataPersistenceException, StateValidationException,
            ProductValidationException;

    Order editOrder(Order updatedOrder) throws DataPersistenceException,
            InvalidOrderNumberException;

    Order removeOrder(Order removedOrder) throws DataPersistenceException,
            InvalidOrderNumberException;

}
