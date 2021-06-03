package com.yameatmeyourdead.sorcery.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class FirstBlock extends Block {
    
    public FirstBlock() {
        super(Properties.of(Material.METAL).sound(SoundType.METAL));
    }
}
