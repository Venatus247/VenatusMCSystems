package de.venatus247.vutils.utils.worldborder.api;

@FunctionalInterface
public interface FunctionDoubleDoubleLong {
    void lerp(double oldSize, double newSize, long time);
}
