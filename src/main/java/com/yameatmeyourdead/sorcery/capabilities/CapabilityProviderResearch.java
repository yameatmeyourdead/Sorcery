package com.yameatmeyourdead.sorcery.capabilities;

import javax.annotation.Nonnull;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

/**
 * This class provides all the capabilities that Player Research needs
 * 1) CapabilityPlayerResearch -> PlayerResearchInterfaceInstance
 */
public class CapabilityProviderResearch implements ICapabilitySerializable<INBT> {
    private Research research = new Research();
    private final Direction NO_SPECIFIC_SIDE = null;

    /**
     * Asks the Provider if it has the given capability
     * @param capability<T> capability to be checked for
     * @param facing the side of the provider being checked (null = no particular side)
     * @param <T> The interface instance that is used
     * @return a lazy-initialisation supplier of the interface instance that is used to access this capability
     *         In this case, we don't actually use lazy initialisation because the instance is very quick to create.
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if(cap == CapabilityPlayerResearch.CAPABILITY_PLAYER_RESEARCH) {
            return (LazyOptional<T>)LazyOptional.of(() -> research);
        }
        return LazyOptional.empty();
    }

    /**
     * Write capability state information to NBT
     */
    @Override
    public INBT serializeNBT() {
        return CapabilityPlayerResearch.CAPABILITY_PLAYER_RESEARCH.writeNBT(research, NO_SPECIFIC_SIDE);
    }

    /**
     * Read capability state information out of NBT
     */
    @Override
    public void deserializeNBT(INBT nbt) {
        CapabilityPlayerResearch.CAPABILITY_PLAYER_RESEARCH.readNBT(research, NO_SPECIFIC_SIDE, nbt);
    }
    
}
