package ru.lairon.service.message.impl;

import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.audience.Audience;
import ru.lairon.service.namedentity.NamedEntity;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class VelocityAdapter {

    private final ProxyServer proxyServer;

    public VelocityAdapter(ProxyServer proxyServer) {
        Objects.requireNonNull(proxyServer, "proxyServer can not be null");
        this.proxyServer = proxyServer;
    }

    public Optional<Audience> adapt(NamedEntity entity) {
        Objects.requireNonNull(entity, "entity can not be null");
        return Optional.of(proxyServer.getPlayer(entity.getUUID()).get());
    }


}
