/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

/**
 *
 * @author amanda
 */
public class ProductValidationException extends Exception {

    ProductValidationException(String message) {
        super(message);
    }

    ProductValidationException(String message, Throwable cause) {
        super(message, cause);
    }

}
