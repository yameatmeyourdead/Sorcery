package com.yameatmeyourdead.sorcery.items;

import com.yameatmeyourdead.sorcery.capabilities.CapabilityPlayerResearch;
import com.yameatmeyourdead.sorcery.capabilities.Research;
import com.yameatmeyourdead.sorcery.research.ResearchBase;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResultType;

public class ResearchNotes extends Item {
    ResearchBase research = new ResearchBase();

    public ResearchNotes() {
        super(new Item.Properties().stacksTo(64).rarity(Rarity.RARE));
    }
    
    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        PlayerEntity player = context.getPlayer();
        Research researchInterface = player.getCapability(CapabilityPlayerResearch.CAPABILITY_PLAYER_RESEARCH).orElse(null);
        if(researchInterface == null) return ActionResultType.FAIL;
        researchInterface.addResearch(research.getName().toLowerCase());
        
        return ActionResultType.FAIL;
    }
}
