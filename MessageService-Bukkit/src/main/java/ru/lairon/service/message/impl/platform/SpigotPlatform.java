package ru.lairon.service.message.impl.platform;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.lairon.service.message.impl.BukkitAdapter;
import ru.lairon.service.message.impl.PlatformAdapter;

public class SpigotPlatform implements PlatformAdapter {
    @Override
    public void sendChat(CommandSender sender, String message) {
        sender.sendMessage(prepareMessage(message));
    }

    @Override
    public void sendActionBar(CommandSender sender, String message) {
        if (!(sender instanceof Player player)) return;
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                TextComponent.fromLegacyText(prepareMessage(message)));
    }

    @Override
    public void sendTitle(CommandSender sender, String title, String subTitle) {
        if (!(sender instanceof Player player)) return;
        player.sendTitle(prepareMessage(title), prepareMessage(subTitle));
    }

    @Override
    public void announceChat(String message, String permission) {
        String preparedMessage = prepareMessage(message);
        BukkitAdapter.getOnline().forEach(sender1 -> {
            if(permission != null && !sender1.hasPermission(permission)) return;
            sender1.sendMessage(preparedMessage);
        });
    }

    @Override
    public void announceChat(String message) {
        announceChat(message, null);
    }

    @Override
    public void announceActionBar(String message, String permission) {
        String preparedMessage = prepareMessage(message);
        Bukkit.getOnlinePlayers().forEach(player -> {
            if(permission != null && !player.hasPermission(permission)) return;
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                    TextComponent.fromLegacyText(preparedMessage));
        });
    }

    @Override
    public void announceActionBar(String message) {
        announceActionBar(message, null);
    }

    @Override
    public void announceTitle(String title, String subTitle, String permission) {
        String preparedTitle = prepareMessage(title),
                preparedSubTitle = prepareMessage(title);
        Bukkit.getOnlinePlayers().forEach(player -> {
            if(permission != null && !player.hasPermission(permission)) return;
            player.sendTitle(preparedTitle, preparedSubTitle);
        });
    }

    @Override
    public void announceTitle(String title, String subTitle) {
        announceTitle(title, subTitle, null);
    }

    private String prepareMessage(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
