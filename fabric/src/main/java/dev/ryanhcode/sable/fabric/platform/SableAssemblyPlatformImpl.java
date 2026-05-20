package dev.ryanhcode.sable.fabric.platform;

import dev.ryanhcode.sable.fabric.mixinterface.LevelExtension;
import dev.ryanhcode.sable.platform.SableAssemblyPlatform;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public class SableAssemblyPlatformImpl implements SableAssemblyPlatform {

    @Override
    public void setIgnoreOnPlace(final Level level, final boolean ignore) {
        ((LevelExtension) level).sable$setIgnoreOnPlace(ignore);
    }

    @Override
    public void clearNonClearableContainerItems(final BlockEntity blockEntity,final CompoundTag tag) {
        try {
            final Level level = blockEntity.getLevel();
            if (level == null) return;
            final String id = tag.getString("id");
            final CompoundTag newTag = new CompoundTag();
            newTag.putString("id", id);
            blockEntity.loadWithComponents(newTag, level.registryAccess());
        } catch (final Exception ignored) {}
    }
}
