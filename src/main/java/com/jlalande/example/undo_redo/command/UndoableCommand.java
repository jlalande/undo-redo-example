package com.jlalande.example.undo_redo.command;

public interface UndoableCommand extends Command {
  public void undo();
}
