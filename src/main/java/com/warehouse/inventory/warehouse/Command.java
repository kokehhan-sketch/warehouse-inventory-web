package com.warehouse.inventory.warehouse;

public interface Command {
    boolean execute();

    boolean undo();

    String getName();
}
