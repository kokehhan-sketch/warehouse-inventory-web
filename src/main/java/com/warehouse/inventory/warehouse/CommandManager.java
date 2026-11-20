package com.warehouse.inventory.warehouse;

import java.util.ArrayDeque;
import java.util.Deque;

public class CommandManager {

    private final Deque<Command> undoStack = new ArrayDeque<>();
    private final Deque<Command> redoStack = new ArrayDeque<>();

    public boolean executeCommand(Command command) {
        if (command == null) {
            return false;
        }

        boolean success = command.execute();
        if (success) {
            undoStack.push(command);
            redoStack.clear();
        }

        return success;
    }

    public boolean undoLastCommand() {
        if (undoStack.isEmpty()) {
            return false;
        }

        Command command = undoStack.pop();
        boolean success = command.undo();

        if (success) {
            redoStack.push(command);
        } else {
            undoStack.push(command);
        }

        return success;
    }

    public boolean redoLastUndoneCommand() {
        if (redoStack.isEmpty()) {
            return false;
        }

        Command command = redoStack.pop();
        boolean success = command.execute();

        if (success) {
            undoStack.push(command);
        } else {
            redoStack.push(command);
        }

        return success;
    }
}
