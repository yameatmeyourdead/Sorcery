package com.yameatmeyourdead.sorcery.items;

import com.yameatmeyourdead.sorcery.Sorcery;

import net.minecraft.item.Item;
import net.minecraft.item.Rarity;

public class ArcaneDust extends Item {
    public ArcaneDust() {
        super(new Item.Properties().stacksTo(64).rarity(Rarity.UNCOMMON).tab(Sorcery.ITEM_GROUP));
    }   
}