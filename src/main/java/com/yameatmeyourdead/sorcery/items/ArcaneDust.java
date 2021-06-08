package com.yameatmeyourdead.sorcery.items;

import com.yameatmeyourdead.sorcery.Sorcery;

import net.minecraft.item.Item;
import net.minecraft.item.Rarity;

public class ArcaneDust extends Item {

    public ArcaneDust() {
        super(new Item.Properties().rarity(Rarity.UNCOMMON).durability(10).tab(Sorcery.ITEM_GROUP));
    }
    
}
