package dev.ryanhcode.sable.neoforge.platform;

import dev.ryanhcode.sable.platform.SableAssemblyPlatform;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public class SableAssemblyPlatformImpl implements SableAssemblyPlatform {

    @Override
    public void setIgnoreOnPlace(final Level level, final boolean ignore) {
        level.captureBlockSnapshots = ignore;
    }

    @Override
    public void clearNonClearableContainerItems(final BlockEntity blockEntity) {
        try {
            final Level level = blockEntity.getLevel();
            if (level == null) return;
            final CompoundTag oldTag = blockEntity.saveWithFullMetadata(level.registryAccess());
            final String id = oldTag.getString("id");
            final CompoundTag newTag = new CompoundTag();
            newTag.putString("id", id);
            blockEntity.loadWithComponents(newTag, level.registryAccess());
        } catch (final Exception ignored) {}
    }
}
