package com.jlalande.example.undo_redo;

import java.util.Scanner;

import org.apache.commons.lang3.math.NumberUtils;

import com.jlalande.example.undo_redo.command.AddNumberCommand;
import com.jlalande.example.undo_redo.command.ClearCommand;
import com.jlalande.example.undo_redo.service.Calculator;
import com.jlalande.example.undo_redo.service.ExecutionService;
import com.jlalande.example.undo_redo.service.ExecutionServiceImpl;

public class Main {

  public static void main(String[] args) {
    ExecutionService execService = new ExecutionServiceImpl();
    Calculator calculator = new Calculator();

    boolean quitApplication = false;
    Scanner scanIn = new Scanner(System.in);
    String userInput;

    displayHelp();

    while (!quitApplication) {
      System.out.print("> ");
      userInput = scanIn.nextLine();

      switch (userInput) {
      case "quit":
        quitApplication = true;
        break;
      case "clear":
        execService.execute(new ClearCommand(execService));
        break;
      case "status":
        printStatus(execService, calculator);
        break;
      case "undo":
        undoLastAction(execService, calculator);
        break;
      case "redo":
        redoLastAction(execService, calculator);
        break;
      default:
        if (NumberUtils.isNumber(userInput)) {
          double parsedInput = Double.parseDouble(userInput);

          execService.execute(new AddNumberCommand(calculator, parsedInput));

          printCurrentValue(calculator);
        } else {
          displayHelp();
        }
      }
    }

    System.out.println("Exiting application...");

    scanIn.close();
  }

  private static void redoLastAction(ExecutionService execService, Calculator calculator) {
    if (execService.hasRedoableCommand()) {
      execService.redo();
      printCurrentValue(calculator);
    } else {
      System.out.println("Redo stack is empty");
    }
  }

  private static void undoLastAction(ExecutionService execService, Calculator calculator) {
    if (execService.hasUndoableCommand()) {
      execService.undo();
      printCurrentValue(calculator);
    } else {
      System.out.println("Undo stack is empty");
    }
  }

  private static void printStatus(ExecutionService execService, Calculator calculator) {
    System.out.println("Calculator: " + calculator);
    System.out.println("Undo/Redo: " + execService);
  }

  private static void printCurrentValue(Calculator calculator) {
    System.out.println(calculator.getCurrentValue());
  }

  private static void displayHelp() {
    System.out.println("Enter a numeric value or one of the commands below:");
    displayCommand("help", "Displays this help text");
    displayCommand("status", "Displays the status of the undo/redo stack");
    displayCommand("clear", "Clears the undo/redo stack");
    displayCommand("undo", "Undo the last action");
    displayCommand("redo", "Redo the last action");
    displayCommand("quit", "Exit the application");

  }

  private static void displayCommand(String command, String commandDescription) {
    System.out.println("\t" + command + "\t\t" + commandDescription);
  }

}
