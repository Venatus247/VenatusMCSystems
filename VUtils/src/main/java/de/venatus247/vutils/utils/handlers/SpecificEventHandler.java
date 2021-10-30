package de.venatus247.vutils.utils.handlers;

import org.bukkit.event.Event;

import java.util.ArrayList;
import java.util.List;

public abstract class SpecificEventHandler<T extends Event> {

    private final List<IHandlerListener<T>> listeners = new ArrayList<>();

    public void registerListener(IHandlerListener<T> listener) {
        listeners.add(listener);
    }

    public void unregisterListener(IHandlerListener<T> listener) {
        listeners.remove(listener);
    }

    public void onEvent(T event) {
        listeners.forEach(l -> l.handle(event));
    }

}
