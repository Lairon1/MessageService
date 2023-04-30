package ru.lairon.service.message.impl;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import ru.lairon.service.namedentity.ConsoleEntity;
import ru.lairon.service.namedentity.NamedEntity;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BukkitAdapter {

    public static Optional<CommandSender> adapt(NamedEntity entity) {
        Objects.requireNonNull(entity, "entity can not be null");
        if (entity == ConsoleEntity.getInstance()) return Optional.of(Bukkit.getConsoleSender());
        return Optional.of(Bukkit.getPlayer(entity.getUUID()));
    }

    public static List<CommandSender> getOnline() {
        return Stream.concat(Bukkit.getOnlinePlayers()
                        .stream()
                        .map(player -> (CommandSender) player), Stream.of(Bukkit.getConsoleSender()))
                .collect(Collectors.toList());
    }


}
