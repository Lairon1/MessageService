package ru.lairon.service.message.impl;


import org.bukkit.command.CommandSender;

public interface PlatformAdapter {

    void sendChat(CommandSender sender, String message);
    void sendActionBar(CommandSender sender, String message);
    void sendTitle(CommandSender sender, String title, String subTitle);
    void announceChat(String message, String permission);
    void announceChat(String message);
    void announceActionBar(String message, String permission);
    void announceActionBar(String message);
    void announceTitle(String title, String subTitle, String permission);
    void announceTitle(String title, String subTitle);

}
