package ru.lairon.service.message.impl;

import ru.lairon.service.message.MessageService;
import ru.lairon.service.message.impl.platform.PaperPlatform;
import ru.lairon.service.message.impl.platform.SpigotPlatform;
import ru.lairon.service.namedentity.NamedEntity;

import java.util.Objects;

public class BukkitMessageService implements MessageService {

    private final PlatformAdapter platform;

    public BukkitMessageService() {
        platform = loadPlatform();
    }

    private PlatformAdapter loadPlatform() {
        try{
            Class.forName("net.kyori.adventure.text.minimessage.MiniMessage");
            return new PaperPlatform();
        }catch (ClassNotFoundException e){
            return new SpigotPlatform();
        }
    }

    @Override
    public void sendChat(NamedEntity entity, String message) {
        Objects.requireNonNull(entity, "entity can not be null");
        Objects.requireNonNull(message, "message can not be null");

        BukkitAdapter.adapt(entity).ifPresent(sender -> platform.sendChat(sender, message));
    }

    @Override
    public void sendActionBar(NamedEntity entity, String message) {
        Objects.requireNonNull(entity, "entity can not be null");
        Objects.requireNonNull(message, "message can not be null");

        BukkitAdapter.adapt(entity).ifPresent(sender -> platform.sendActionBar(sender, message));
    }

    @Override
    public void sendTitle(NamedEntity entity, String title, String subTitle) {
        Objects.requireNonNull(entity, "entity can not be null");
        Objects.requireNonNull(title, "title can not be null");
        Objects.requireNonNull(subTitle, "subTitle can not be null");

        BukkitAdapter.adapt(entity).ifPresent(sender -> platform.sendTitle(sender, title, subTitle));
    }

    @Override
    public void announceChat(String message, String permission) {
        Objects.requireNonNull(message, "message can not be null");

        platform.announceChat(message, permission);
    }

    @Override
    public void announceChat(String message) {
        Objects.requireNonNull(message, "message can not be null");

        platform.announceChat(message);
    }

    @Override
    public void announceActionBar(String message, String permission) {
        Objects.requireNonNull(message, "message can not be null");

        platform.announceActionBar(permission);
    }

    @Override
    public void announceActionBar(String message) {
        Objects.requireNonNull(message, "message can not be null");

        platform.announceActionBar(message);
    }

    @Override
    public void announceTitle(String title, String subTitle, String permission) {
        Objects.requireNonNull(title, "title can not be null");
        Objects.requireNonNull(subTitle, "subTitle can not be null");

        platform.announceTitle(title, subTitle, permission);
    }

    @Override
    public void announceTitle(String title, String subTitle) {
        Objects.requireNonNull(title, "title can not be null");
        Objects.requireNonNull(subTitle, "subTitle can not be null");

        platform.announceTitle(title, subTitle);
    }
}
