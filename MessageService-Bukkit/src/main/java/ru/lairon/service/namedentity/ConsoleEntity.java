package ru.lairon.service.namedentity;

import java.util.UUID;

public class ConsoleEntity implements NamedEntity{

    private ConsoleEntity() {
    }

    private static final ConsoleEntity instance = new ConsoleEntity();

    public static ConsoleEntity getInstance(){
        return instance;
    }

    @Override
    public UUID getUUID() {
        return UUID.fromString("00000000-00000000-00000000-00000000");
    }

    @Override
    public String getName() {
        return "CONSOLE";
    }
}
