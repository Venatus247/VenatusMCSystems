package de.venatus247.vutils.utils.handlers;

import org.bukkit.event.Event;

public interface IHandlerListener<T extends Event> {

    void handle(T event);

}
