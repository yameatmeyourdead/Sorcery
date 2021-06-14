package com.yameatmeyourdead.sorcery.research;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ResearchIntro extends ResearchBase {
    public ResearchIntro() {
        setName("Introduction to Sorcery's Research System");
        setInsanity(0);
        setPrerequisites(null);
    }

    public static ItemStack createResearchNote(World world, PlayerEntity player) {
        ItemStack note = null;
        return note;
    }
}