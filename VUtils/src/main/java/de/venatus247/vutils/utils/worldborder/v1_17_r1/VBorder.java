package de.venatus247.vutils.utils.worldborder.v1_17_r1;

import de.venatus247.vutils.utils.worldborder.api.WorldBorderApiImpl;

public class VBorder extends WorldBorderApiImpl {
    public VBorder() {
        super(VWorldBorder::new, VWorldBorder::new);
    }
}
