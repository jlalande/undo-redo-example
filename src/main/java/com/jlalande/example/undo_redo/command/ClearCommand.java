package com.jlalande.example.undo_redo.command;

import com.jlalande.example.undo_redo.service.ExecutionService;

public class ClearCommand implements Command {

  private ExecutionService execService;

  /**
   * Creates a <code>ClearCommand</code>.
   */
  public ClearCommand(ExecutionService execService) {
    this.execService = execService;
  }

  /**
   * @see com.jlalande.example.undo_redo.command.Command#execute()
   */
  @Override
  public boolean execute() {
    execService.clear();
    return true;
  }

}
