/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import com.sg.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author amanda
 */
public class View {

    private UserIO io;

    public View(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("=== SWG Corp Main Menu ===");
        io.print("1. Display Orders");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Exit");

        return io.readInt("Please select from the"
                + " above options.", 1, 5);
    }

    public void displayDisplayOrdersBanner() {
        io.print("=== Display Orders ===");
    }

    public LocalDate inputDate() {
        return io.readDate("Please enter a date. (MM-DD-YYYY)",
                LocalDate.of(2005, 1, 1), LocalDate.of(2050, 1, 31));
    }

    public String inputCustomerName() {
        return io.readString("Please enter your company's name.");
    }

    public String inputStateAbbr() {
        return io.readString("Please enter your state's USPS abbreviation. "
                + "(Ex. MI)", 2);
    }

    public String inputProductType() {
        return io.readString("Please enter the product you will be using.", 15);
    }

    public BigDecimal inputArea() {
        return io.readBigDecimal("Please enter the area of your project "
                + "in square feet.", 2, BigDecimal.ZERO);
    }

    public void displayDateBanner(LocalDate dateChoice) {
        System.out.printf("=== Orders on %s ===\n", dateChoice);
    }

    public void displayDateOrders(List<Order> ordersByDate) {
        for (Order o : ordersByDate) {
            io.print(o.getOrderNumber() + " | " + o.getCustomerName() + " | "
                    + io.formatCurrency(o.getTotal()));
        }
    }

    public Order getOrder() {
        Order o = new Order();
        o.setDate(inputDate());
        o.setCustomerName(inputCustomerName());
        o.setStateAbbr(inputStateAbbr());
        o.setProductType(inputProductType());
        o.setArea(inputArea());
        return o;
    }

    public void displayOrder(Order o) {
        io.print("Date: " + o.getDate());
        io.print("Customer: " + o.getCustomerName());
        io.print("State: " + o.getStateAbbr());
        io.print("Tax Rate: " + o.getTaxRate() + "%");
        io.print("Product: " + o.getProductType());
        io.print("Material Cost per sq ft: "
                + io.formatCurrency(o.getMaterialCostPerSquareFoot()));
        io.print("Labor Cost per sq ft: "
                + io.formatCurrency(o.getLaborCostPerSquareFoot()));
        io.print("Area: " + o.getArea() + " sq ft");
        io.print("Material Cost: " + io.formatCurrency(o.getMaterialCost()));
        io.print("Labor Cost: " + io.formatCurrency(o.getLaborCost()));
        io.print("Tax: " + io.formatCurrency(o.getTax()));
        io.print("=== TOTAL: " + io.formatCurrency(o.getTotal()) + " ===");
    }

    public String askSave() {
        return io.readString("Would you like to save this order? Y/N", 1);
    }

    public void displayAddOrderSuccess(boolean success, Order o) {
        if (success == true) {
            io.print("Order #" + o.getOrderNumber() + " was successfully added!");
        } else {
            io.print("Order was not saved.");
            displayContinue();
        }
    }

    public void displayEditOrderBanner() {
        io.print("=== Edit Order ===");
    }

    public int inputOrderNumber() {
        return io.readInt("Please enter an order number.");
    }

    public Order editOrderInfo(Order o) {
        Order editedOrder = new Order();

        io.print("Customer: " + o.getCustomerName());
        editedOrder.setCustomerName(inputCustomerName());

        io.print("State: " + o.getStateAbbr());
        editedOrder.setStateAbbr(inputStateAbbr());

        io.print("Product: " + o.getProductType());
        editedOrder.setProductType(inputProductType());

        io.print("Area: " + o.getArea() + " sq ft");
        editedOrder.setArea(inputArea());

        return editedOrder;
    }

    public void displayEditOrderSuccess(boolean success, Order o) {
        if (success == true) {
            io.print("Order #" + o.getOrderNumber() + " was successfully edited!");
        } else {
            io.print("Order was not edited.");
            displayContinue();
        }
    }

    public void displayRemoveOrderBanner() {
        io.print("=== Remove Order ===");
    }

    public String askRemove() {
        return io.readString("Would you like to remove this order? Y/N", 1);
    }

    public void displayRemoveOrderSuccess(boolean success, Order o) {
        if (success == true) {
            io.print("Order #" + o.getOrderNumber() + " was successfully removed!");
        } else {
            io.print("Order was not removed.");
            displayContinue();
        }
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
        displayContinue();
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
        displayContinue();
    }

    public void displayContinue() {
        io.readString("Please hit enter to continue.");
    }

}
