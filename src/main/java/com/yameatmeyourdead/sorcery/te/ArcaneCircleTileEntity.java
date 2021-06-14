package com.yameatmeyourdead.sorcery.te;

import com.yameatmeyourdead.sorcery.recipe.ArcaneCircleRecipe;
import com.yameatmeyourdead.sorcery.setup.Registration;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;

public class ArcaneCircleTileEntity extends TileInventory implements ITickableTileEntity {
    public boolean isActive = false;
    public int activeCounter = 0;
    public Direction rotation = Direction.from2DDataValue(0);

    public ArcaneCircleTileEntity(TileEntityType<?> type) {
        super(type, 16, "arcanecircle");
    }
    
    public ArcaneCircleTileEntity() {
        this(Registration.ARCANE_CIRCLE_ENTITY_TYPE.get());
    }

    public Direction getRotation() {
        return rotation;
    }

    @Override
    public void tick() {
        for (IRecipe<?> recipe : Registration.getRecipes(Registration.ARCANE_CIRCLE_RECIPE, getLevel()).values()) {
            if(recipe == null)
                continue;
            if(!(recipe instanceof ArcaneCircleRecipe)) continue;
            final ArcaneCircleRecipe STR = (ArcaneCircleRecipe) recipe;
            if(STR.matches(this, getLevel())) {
                ItemStack[] itemsToAdd = STR.getResultItems();
                for(ItemStack item : itemsToAdd) {
                    inventory.add(item);
                }
                break;
            }
        }
    }
}
