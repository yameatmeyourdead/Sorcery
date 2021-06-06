package com.yameatmeyourdead.sorcery.items;

import com.yameatmeyourdead.sorcery.Sorcery;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid=Sorcery.MODID, bus = Bus.MOD)
public class RunicInscriber extends Item {

    public RunicInscriber() {
        super(new Item.Properties().stacksTo(1).tab(Sorcery.ITEM_GROUP));
    }
}
