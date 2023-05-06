package ru.lairon.service.message.impl.platform;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.title.Title;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import ru.lairon.service.message.impl.BukkitAdapter;
import ru.lairon.service.message.impl.PlatformAdapter;

public class PaperPlatform implements PlatformAdapter {
    @Override
    public void sendChat(CommandSender sender, String message) {
        sender.sendMessage(prepareMessage(message));
    }

    @Override
    public void sendActionBar(CommandSender sender, String message) {
        sender.sendActionBar(prepareMessage(message));
    }

    @Override
    public void sendTitle(CommandSender sender, String title, String subTitle) {
        sender.showTitle(Title.title(prepareMessage(title), prepareMessage(subTitle)));
    }

    @Override
    public void announceChat(String message, String permission) {
        Component preparedMessage = prepareMessage(message);
        BukkitAdapter.getOnline().forEach(sender -> {
            if (permission != null && !sender.hasPermission(permission)) return;
            sender.sendMessage(preparedMessage);
        });
    }

    @Override
    public void announceChat(String message) {
        announceChat(message, null);
    }

    @Override
    public void announceActionBar(String message, String permission) {
        Component preparedMessage = prepareMessage(message);
        BukkitAdapter.getOnline().forEach(sender -> {
            if (permission != null && !sender.hasPermission(permission)) return;
            sender.sendActionBar(preparedMessage);
        });
    }

    @Override
    public void announceActionBar(String message) {
        announceActionBar(message, null);
    }

    @Override
    public void announceTitle(String title, String subTitle, String permission) {
        Component preparedTitle = prepareMessage(title), preparedSubTitle = prepareMessage(subTitle);
        BukkitAdapter
                .getOnline()
                .forEach(sender -> {
                    if (permission != null && !sender.hasPermission(permission)) return;
                    sender.showTitle(Title.title(preparedTitle, preparedSubTitle));
                });
    }

    @Override
    public void announceTitle(String title, String subTitle) {
        announceTitle(title, subTitle, null);
    }

    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    private Component prepareMessage(String message) {
        return miniMessage.deserialize(ChatColor.translateAlternateColorCodes('&', message));
    }

}
