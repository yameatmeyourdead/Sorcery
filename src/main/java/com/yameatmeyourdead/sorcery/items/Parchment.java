package com.yameatmeyourdead.sorcery.items;

import com.yameatmeyourdead.sorcery.Sorcery;

import net.minecraft.item.Item;

public class Parchment extends Item {

    public Parchment() {
        super(new Item.Properties().stacksTo(64).tab(Sorcery.ITEM_GROUP));
    }
}
