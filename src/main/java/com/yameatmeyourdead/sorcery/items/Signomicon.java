package com.yameatmeyourdead.sorcery.items;

import com.yameatmeyourdead.sorcery.Sorcery;

import net.minecraft.item.Item;

public class Signomicon extends Item {

    public Signomicon() {
        super(new Item.Properties().stacksTo(1).fireResistant().tab(Sorcery.ITEM_GROUP));
    }
}