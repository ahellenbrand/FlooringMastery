/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.AuditDao;
import com.sg.flooringmastery.dao.AuditDaoStubImpl;
import com.sg.flooringmastery.dao.OrdersDao;
import com.sg.flooringmastery.dao.OrdersDaoStubImpl;
import com.sg.flooringmastery.dao.ProductsDao;
import com.sg.flooringmastery.dao.ProductsDaoFileImpl;
import com.sg.flooringmastery.dao.StatesDao;
import com.sg.flooringmastery.dao.StatesDaoFileImpl;
import com.sg.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author amanda
 */
public class FloorServiceImplTest {

    private FloorService service;

    public FloorServiceImplTest() {
        OrdersDao ordersDao = new OrdersDaoStubImpl();
        ProductsDao productsDao = new ProductsDaoFileImpl();
        StatesDao statesDao = new StatesDaoFileImpl();
        AuditDao auditDao = new AuditDaoStubImpl();

        service = new FloorServiceImpl(ordersDao, productsDao, statesDao, auditDao);
    }

    /**
     * Test of getOrders method, of class FloorServiceImpl.
     */
    @Test
    public void testGetOrders() throws Exception {

        assertEquals(1, service.getOrders(LocalDate.of(2001, 04, 30)).size());

        try {
            List<Order> orders = service.getOrders(LocalDate.of(1990, 01, 01));
            fail("Expected InvalidOrderNumberException was not thrown.");
        } catch (InvalidOrderNumberException e) {
        }
    }

    /**
     * Test of getOrder method, of class FloorServiceImpl.
     */
    @Test
    public void testGetOrder() throws Exception {
        Order order = service.getOrder(LocalDate.of(2001, 04, 30), 1);
        assertNotNull(order);

        try {
            order = service.getOrder(LocalDate.of(2001, 04, 30), 0);
            fail("Expected InvalidOrderNumberException was not thrown.");
        } catch (InvalidOrderNumberException e) {
        }

        try {
            service.getOrder(LocalDate.of(1990, 01, 01), 1);
            fail("Expected InvalidOrderNumberException was not thrown.");
        } catch (InvalidOrderNumberException e) {
        }

    }

    /**
     * Test of calculateOrder method, of class FloorServiceImpl.
     */
    @Test
    public void testCalculateOrder() throws Exception {

        Order order1 = new Order();
        order1.setCustomerName("Place LLC");
        order1.setStateAbbr("MI");
        order1.setProductType("Wood");
        order1.setArea(new BigDecimal("100"));

        Order order2 = new Order();
        order2.setCustomerName("Place LLC");
        order2.setStateAbbr("MI");
        order2.setProductType("Wood");
        order2.setArea(new BigDecimal("100"));

        assertEquals(service.calculateOrder(order1), service.calculateOrder(order2));

        order1.setCustomerName("");

        try {
            service.calculateOrder(order1);
            fail("Expected OrderValidationException was not thrown.");
        } catch (OrderValidationException e) {
        }

        order1.setCustomerName("Place LLC");
        order1.setStateAbbr("");

        try {
            service.calculateOrder(order1);
            fail("Expected OrderValidationException was not thrown.");
        } catch (OrderValidationException e) {
        }

        order1.setStateAbbr("MI");
        order1.setProductType("");

        try {
            service.calculateOrder(order1);
            fail("Expected OrderValidationException was not thrown.");
        } catch (OrderValidationException e) {
        }

        order1.setProductType("Wood");
        order1.setArea(new BigDecimal("0"));

        try {
            service.calculateOrder(order1);
            fail("Expected OrderValidationException was not thrown.");
        } catch (OrderValidationException e) {
        }

        order1.setArea(new BigDecimal("100"));
        order1.setStateAbbr("MN");

        try {
            service.calculateOrder(order1);
            fail("Expected StateValidationException was not thrown.");
        } catch (StateValidationException e) {
        }

        order1.setStateAbbr("MI");
        order1.setProductType("Glass");

        try {
            service.calculateOrder(order1);
            fail("Expected ProductValidationException was not thrown.");
        } catch (ProductValidationException e) {
        }

    }

    /**
     * Test of addOrder method, of class FloorServiceImpl.
     */
    @Test
    public void testAddOrder() throws Exception {

        Order order = new Order();
        order.setCustomerName("Place LLC");
        order.setStateAbbr("MI");
        order.setProductType("Wood");
        order.setArea(new BigDecimal("100"));
        service.addOrder(order);

        assertEquals(order, service.addOrder(order));

    }

    /**
     * Test of compareOrders method, of class FloorServiceImpl.
     */
    @Test
    public void testCompareOrders() throws Exception {

        Order savedOrder = service.getOrder(LocalDate.of(2001, 04, 30), 1);

        Order editedOrder = new Order();
        editedOrder.setCustomerName("Peanut Butter LLC");

        Order updatedOrder = service.compareOrders(savedOrder, editedOrder);

        assertEquals(updatedOrder, savedOrder);

    }

    /**
     * Test of editOrder method, of class FloorServiceImpl.
     */
    @Test
    public void testEditOrder() throws Exception {

        Order savedOrder = service.getOrder(LocalDate.of(2001, 04, 30), 1);
        assertNotNull(savedOrder);

        try {
            savedOrder = service.getOrder(LocalDate.of(2001, 04, 30), 0);
            fail("Expected InvalidOrderNumberException was not thrown.");
        } catch (InvalidOrderNumberException e) {
        }

    }

    /**
     * Test of removeOrder method, of class FloorServiceImpl.
     */
    @Test
    public void testRemoveOrder() throws Exception {

        Order removedOrder = service.getOrder(LocalDate.of(2001, 04, 30), 1);
        assertNotNull(removedOrder);

        try {
            removedOrder = service.getOrder(LocalDate.of(2001, 04, 30), 0);
            fail("Expected InvalidOrderNumberException was not thrown.");
        } catch (InvalidOrderNumberException e) {
        }

    }

}
