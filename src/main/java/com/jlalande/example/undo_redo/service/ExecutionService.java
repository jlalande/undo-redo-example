package com.jlalande.example.undo_redo.service;

import com.jlalande.example.undo_redo.command.Command;

public interface ExecutionService {
  public boolean execute(Command command);

  public boolean undo();

  public boolean redo();

  public boolean hasUndoableCommand();

  public boolean hasRedoableCommand();

  public void clear();

}
