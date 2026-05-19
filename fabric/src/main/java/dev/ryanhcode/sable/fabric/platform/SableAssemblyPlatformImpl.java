package dev.ryanhcode.sable.fabric.platform;

import dev.ryanhcode.sable.fabric.mixinterface.LevelExtension;
import dev.ryanhcode.sable.platform.SableAssemblyPlatform;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.ApiStatus;
import java.util.ArrayList;
import java.util.List;

@ApiStatus.Internal
public class SableAssemblyPlatformImpl implements SableAssemblyPlatform {

    @Override
    public void setIgnoreOnPlace(final Level level, final boolean ignore) {
        ((LevelExtension) level).sable$setIgnoreOnPlace(ignore);
    }

    @Override
    public void clearNonClearableContainerItems(final BlockEntity blockEntity) {
        if (blockEntity == null) {
            return;
        }
        try {
            final Level level = blockEntity.getLevel();
            if (level == null) {
                return;
            }
            final CompoundTag tag = blockEntity.saveWithFullMetadata(level.registryAccess());
            if (tag.isEmpty()) {
                return;
            }
            final List<String> keysToRemove = new ArrayList<>();
            for (final String key : tag.getAllKeys()) {
                if (!key.equals("id")) {
                    keysToRemove.add(key);
                }
            }
            for (final String key : keysToRemove) {
                tag.remove(key);
            }
            blockEntity.loadWithComponents(tag, level.registryAccess());
        } catch (final Exception ignored) {

        }
    }
}
