package com.yameatmeyourdead.sorcery.items;

import com.yameatmeyourdead.sorcery.Sorcery;

import net.minecraft.item.Item;

public class RollOfParchment extends Item {
    public RollOfParchment() {
        super(new Item.Properties().stacksTo(64).tab(Sorcery.ITEM_GROUP));
    }
}
