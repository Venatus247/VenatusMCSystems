package de.venatus247.vutils.utils.worldborder.v1_17_r1;

import static de.venatus247.vutils.utils.worldborder.api.ConsumerSupplierTupel.of;

import de.venatus247.vutils.utils.worldborder.api.AbstractWorldBorder;
import de.venatus247.vutils.utils.worldborder.api.Position;
import de.venatus247.vutils.utils.worldborder.api.WorldBorderAction;
import net.minecraft.network.protocol.game.*;
import net.minecraft.world.level.ChunkCoordIntPair;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class VWorldBorder extends AbstractWorldBorder {

    private final net.minecraft.world.level.border.WorldBorder handle;

    public VWorldBorder(Player player) {
        this(new net.minecraft.world.level.border.WorldBorder());
        this.handle.world = ((CraftWorld)player.getWorld()).getHandle();
    }

    public VWorldBorder(World world) {
        this(((CraftWorld) world).getHandle().getWorldBorder());
    }

    public VWorldBorder(net.minecraft.world.level.border.WorldBorder worldBorder) {
        super(
                of(
                        position -> worldBorder.setCenter(position.x(), position.z()),
                        () -> new Position(worldBorder.getCenterX(), worldBorder.getCenterZ())
                ),
                () -> new Position(worldBorder.e(), worldBorder.f()),
                () -> new Position(worldBorder.g(), worldBorder.h()),
                of(worldBorder::setSize, worldBorder::getSize),
                of(worldBorder::setDamageBuffer, worldBorder::getDamageBuffer),
                of(worldBorder::setDamageAmount, worldBorder::getDamageAmount),
                of(worldBorder::setWarningTime, worldBorder::getWarningTime),
                of(worldBorder::setWarningDistance, worldBorder::getWarningDistance),
                (Location location) -> worldBorder.isInBounds(new ChunkCoordIntPair(location.getBlockX(), location.getBlockZ())),
                worldBorder::transitionSizeBetween
        );
        this.handle = worldBorder;
    }

    @Override
    public void send(Player player, WorldBorderAction worldBorderAction) {
        var packet =  switch (worldBorderAction){
            case INITIALIZE -> new ClientboundInitializeBorderPacket(handle);
            case LERP_SIZE -> new ClientboundSetBorderLerpSizePacket(handle);
            case SET_CENTER -> new ClientboundSetBorderCenterPacket(handle);
            case SET_SIZE -> new ClientboundSetBorderSizePacket(handle);
            case SET_WARNING_BLOCKS -> new ClientboundSetBorderWarningDistancePacket(handle);
            case SET_WARNING_TIME -> new ClientboundSetBorderWarningDelayPacket(handle);
        };

        ((CraftPlayer) player).getHandle().b.sendPacket(packet);
    }
}
