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
public class CapabilityProviderResearcher implements ICapabilitySerializable<INBT> {
    private Researcher researcher = new Researcher();
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
        if(cap == CapabilityPlayerResearcher.CAPABILITY_PLAYER_RESEARCHER) {
            return (LazyOptional<T>)LazyOptional.of(() -> researcher);
        }
        return LazyOptional.empty();
    }

    /**
     * Write capability state information to NBT
     */
    @Override
    public INBT serializeNBT() {
        return CapabilityPlayerResearcher.CAPABILITY_PLAYER_RESEARCHER.writeNBT(researcher, NO_SPECIFIC_SIDE);
    }

    /**
     * Read capability state information out of NBT
     */
    @Override
    public void deserializeNBT(INBT nbt) {
        CapabilityPlayerResearcher.CAPABILITY_PLAYER_RESEARCHER.readNBT(researcher, NO_SPECIFIC_SIDE, nbt);
    }
    
}
