/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.State;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author amanda
 */
public class StatesDaoFileImpl implements StatesDao {

    private static final String STATES_FILE = "Taxes.txt";
    private static final String DELIMITER = ",";

    @Override
    public State getState(String stateAbbr) throws DataPersistenceException {
        List<State> states = loadStates();
        if (states == null) {
            return null;
        } else {
            State chosenState = states.stream()
                    .filter(s -> s.getStateAbbr().equalsIgnoreCase(stateAbbr))
                    .findFirst().orElse(null);
            return chosenState;
        }
    }

    private List<State> loadStates() throws DataPersistenceException {
        Scanner scanner;
        List<State> states = new ArrayList<>();

        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(STATES_FILE)));
        } catch (FileNotFoundException e) {
            throw new DataPersistenceException(
                    "-_- Could not load states data into memory.", e);
        }

        String currentLine;
        String[] currentTokens;
        scanner.nextLine();//Skips scanning document headers
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            if (currentTokens.length == 2) {
                State currentState = new State();
                currentState.setStateAbbr(currentTokens[0]);
                currentState.setTaxRate(new BigDecimal(currentTokens[1]));
                // Put currentState into the map using stateAbbr as the key
                states.add(currentState);
            } else {
                //Ignores line if delimited wrong or empty.
            }
        }
        scanner.close();

        if (!states.isEmpty()) {
            return states;
        } else {
            return null;
        }
    }
}
