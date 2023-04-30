package ru.lairon.service.message;

import ru.lairon.service.namedentity.NamedEntity;

public interface MessageService {

    void sendChat(NamedEntity entity, String message);
    void sendActionBar(NamedEntity entity, String message);
    void sendTitle(NamedEntity entity, String title, String subTitle);
    void announceChat(String message, String permission);
    void announceChat(String message);
    void announceActionBar(String message, String permission);
    void announceActionBar(String message);
    void announceTitle(String title, String subTitle, String permission);
    void announceTitle(String title, String subTitle);

}
