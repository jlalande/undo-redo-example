package com.jlalande.example.undo_redo.service;

import java.util.Stack;

import com.jlalande.example.undo_redo.command.Command;
import com.jlalande.example.undo_redo.command.UndoableCommand;

public class ExecutionServiceImpl implements ExecutionService {
  private Stack<UndoableCommand> undoStack = new Stack<UndoableCommand>();
  private Stack<UndoableCommand> redoStack = new Stack<UndoableCommand>();

  @Override
  public boolean execute(Command command) {
    if (command instanceof UndoableCommand) {
      undoStack.push((UndoableCommand) command);
    }

    if (command.execute()) {
      redoStack.clear();
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean undo() {
    if (undoStack.isEmpty()) {
      return false;
    }

    UndoableCommand command = undoStack.pop();

    if (command != null) {
      redoStack.push(command);
      command.undo();
      return true;
    } else {
      return false;
    }

  }

  @Override
  public boolean redo() {
    if (redoStack.isEmpty()) {
      return false;
    }

    UndoableCommand command = redoStack.pop();

    if (command != null) {
      undoStack.push(command);
      command.execute();
      return true;
    } else {
      return false;
    }
  }

  /**
   * @see com.jlalande.example.undo_redo.service.ExecutionService#hasUndoableCommand()
   */
  @Override
  public boolean hasUndoableCommand() {
    return !undoStack.isEmpty();
  }

  /**
   * @see com.jlalande.example.undo_redo.service.ExecutionService#hasRedoableCommand()
   */
  @Override
  public boolean hasRedoableCommand() {
    return !redoStack.isEmpty();
  }

  /**
   * @see com.jlalande.example.undo_redo.service.ExecutionService#clear()
   */
  @Override
  public void clear() {
    undoStack.clear();
    redoStack.clear();
  }

  @Override
  public String toString() {
    return "{'undo': '" + undoStack.size() + "', 'redo': '" + redoStack.size() + "'}";
  }

}
