package ru.lairon.service.message.impl;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.title.Title;
import ru.lairon.service.message.MessageService;
import ru.lairon.service.namedentity.NamedEntity;

import com.velocitypowered.api.proxy.ProxyServer;

import java.util.Objects;

public class VelocityMessageService implements MessageService {

    private final ProxyServer proxyServer;
    private final VelocityAdapter adapter;

    public VelocityMessageService(ProxyServer proxyServer) {
        Objects.requireNonNull(proxyServer, "proxyServer can not be null");
        this.proxyServer = proxyServer;
        adapter = new VelocityAdapter(proxyServer);
    }

    @Override
    public void sendChat(NamedEntity entity, String message) {
        adapter.adapt(entity).ifPresent(audience -> audience.sendMessage(prepareMessage(message)));
    }

    @Override
    public void sendActionBar(NamedEntity entity, String message) {
        adapter.adapt(entity).ifPresent(audience -> audience.sendActionBar(prepareMessage(message)));
    }

    @Override
    public void sendTitle(NamedEntity entity, String title, String subTitle) {
        Title preparedTitle = Title.title(prepareMessage(title), prepareMessage(subTitle));
        adapter.adapt(entity).ifPresent(audience -> audience.showTitle(preparedTitle));
    }

    @Override
    public void announceChat(String message, String permission) {
        Component preparedMessage = prepareMessage(message);
        proxyServer.getAllPlayers()
                .forEach(player -> {
                    if(permission != null && !player.hasPermission(permission)) return;
                    player.sendMessage(preparedMessage);
                });
        proxyServer.getConsoleCommandSource().sendMessage(preparedMessage);
    }

    @Override
    public void announceChat(String message) {
        announceChat(message, null);
    }

    @Override
    public void announceActionBar(String message, String permission) {
        Component preparedMessage = prepareMessage(message);
        proxyServer.getAllPlayers()
                .forEach(player -> {
                    if(permission != null && !player.hasPermission(permission)) return;
                    player.sendActionBar(preparedMessage);
                });
    }

    @Override
    public void announceActionBar(String message) {
        announceActionBar(message, null);
    }

    @Override
    public void announceTitle(String title, String subTitle, String permission) {
        Title preparedTitle = Title.title(prepareMessage(title), prepareMessage(subTitle));
        proxyServer.getAllPlayers()
                .forEach(player -> {
                    if(permission != null && !player.hasPermission(permission)) return;
                    player.showTitle(preparedTitle);
                });
    }

    @Override
    public void announceTitle(String title, String subTitle) {
        announceTitle(title, subTitle, null);
    }

    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    private Component prepareMessage(String message){
        Objects.requireNonNull(message, "message can not be null");

        char[] messageArr = message.toCharArray();
        for (int i = 0; i < messageArr.length; i++) {
            if(messageArr[i] != '&') continue;
            if("0123456789AaBbCcDdEeFfKkLlMmNnOoRrXx".indexOf(messageArr[i + 1]) == -1) continue;
            messageArr[i] = '§';
            messageArr[i + 1] = Character.toLowerCase(messageArr[i + 1]);
        }

        return miniMessage.deserialize(new String(messageArr));
    }
}
