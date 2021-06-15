package com.yameatmeyourdead.sorcery.items;

import java.util.ArrayList;
import java.util.List;

import com.yameatmeyourdead.sorcery.research.ResearchBase;
import com.yameatmeyourdead.sorcery.research.ResearchManager;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class ResearchNotes extends Item {
    ResearchBase research;
    List<ITextComponent> hoverText = new ArrayList<>();

    public ResearchNotes() {
        super(new Item.Properties().stacksTo(64).rarity(Rarity.RARE));
    }
    
    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        if(research == null) return ActionResultType.FAIL;
        if(ActionResultType.PASS == ResearchManager.completeResearch(context.getPlayer(), research)) {
            stack.setCount(0);
            return ActionResultType.PASS;
        }
        else {
            context.getPlayer().sendMessage(new TranslationTextComponent("You have already obtained this knowledge."), context.getPlayer().getUUID());
            return ActionResultType.FAIL;
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, World world, List<ITextComponent> _hoverText, ITooltipFlag flag) {
        _hoverText.addAll(hoverText);
    }
    
    public void setHoverText(List<ITextComponent> hoverText) {
        this.hoverText = hoverText;
    }
}
