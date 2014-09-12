package com.jlalande.example.undo_redo.service;

/**
 * @copyright ©2014, Bell Canada
 * @author jean.lalande1 (CTS)
 */
public class Calculator {

  private double currentValue = 0;

  public double getCurrentValue() {
    return currentValue;
  }

  public void add(double valueToAdd) {
    currentValue += valueToAdd;
  }

  public void subtract(double valueToSubtract) {
    currentValue -= valueToSubtract;
  }

  @Override
  public String toString() {
    return "{'value': '" + Double.toString(currentValue) + "'}";
  }
}
