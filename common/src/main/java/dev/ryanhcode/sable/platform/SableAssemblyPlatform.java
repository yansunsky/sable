package dev.ryanhcode.sable.platform;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public interface SableAssemblyPlatform {
    SableAssemblyPlatform INSTANCE = SablePlatformUtil.load(SableAssemblyPlatform.class);

    void setIgnoreOnPlace(final Level level, final boolean ignore);

    void clearNonClearableContainerItems(final BlockEntity blockEntity);
}
