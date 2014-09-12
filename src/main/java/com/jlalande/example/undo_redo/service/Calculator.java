package com.jlalande.example.undo_redo.service;

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
