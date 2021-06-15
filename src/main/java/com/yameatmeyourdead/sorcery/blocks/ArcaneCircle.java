package com.yameatmeyourdead.sorcery.blocks;

import com.mojang.realmsclient.gui.RealmsServerSlotButton.Action;
import com.yameatmeyourdead.sorcery.setup.Registration;
import com.yameatmeyourdead.sorcery.te.ArcaneCircleTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class ArcaneCircle extends Block {
    private static final VoxelShape SHAPE = Block.box(-8, 0, -8, 24, 1, 24);

    public ArcaneCircle() {
        super(Properties.of(Material.WOOL).strength(1f, 0f).noCollission().noOcclusion().noDrops());
    }
    
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return Registration.ARCANE_CIRCLE_ENTITY_TYPE.get().create();
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult raytraceResult) {
        if(world.getBlockEntity(pos) instanceof ArcaneCircleTileEntity) {
            ((ArcaneCircleTileEntity) world.getBlockEntity(pos)).toggleActive();
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.FAIL;
    }

    @Override
    public BlockRenderType getRenderShape(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }
}
