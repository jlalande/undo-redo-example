package com.jlalande.example.undo_redo.command;

import com.jlalande.example.undo_redo.service.Calculator;

public class AddNumberCommand implements UndoableCommand {

  private Calculator calculator;
  private double valueToAdd;

  public AddNumberCommand(Calculator calculator, double valueToAdd) {
    this.calculator = calculator;
    this.valueToAdd = valueToAdd;
  }

  /**
   * @see com.jlalande.example.undo_redo.command.Command#execute()
   */
  @Override
  public boolean execute() {
    calculator.add(valueToAdd);
    return true;
  }

  /**
   * @see com.jlalande.example.undo_redo.command.UndoableCommand#undo()
   */
  @Override
  public void undo() {
    calculator.subtract(valueToAdd);
  }

}
