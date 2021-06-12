package com.yameatmeyourdead.sorcery.blocks;

import java.util.stream.Stream;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

public class SorcerersTable extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    protected static final VoxelShape SHAPE = Stream.of(
        Block.box(0, 0, 0, 16, 16, 16),
        Block.box(2, 0, 0, 14, 11, 16)
        // Block.box(0, 0, 0, 2, 14, 1),
        // Block.box(0, 0, 15, 2, 14, 16),
        // Block.box(14, 0, 0, 16, 14, 1),
        // Block.box(14, 0, 15, 16, 14, 16),
        // Block.box(0, 14, 0, 16, 15, 16),
        // Block.box(0, 12, 1, 1, 14, 15),
        // Block.box(15, 12, 1, 16, 14, 15),
        // Block.box(1, 1, 1, 2, 3, 15),
        // Block.box(14, 1, 1, 15, 3, 15),
        // Block.box(0, 15, 0, 16, 16, 1),
        // Block.box(0, 15, 1, 1, 16, 15),
        // Block.box(0, 15, 15, 16, 16, 16),
        // Block.box(15, 15, 1, 16, 16, 15),
        // Block.box(2, 11, 0, 14, 14, 1),
        // Block.box(2, 11, 15, 14, 14, 16),
        // Block.box(0, 11, 1, 16, 12, 15)
        ).reduce((v1, v2) -> {return VoxelShapes.join(v1, v2, IBooleanFunction.ONLY_FIRST);}).get();

    public SorcerersTable() {
        super(Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(2f).noOcclusion());
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState mirror(BlockState state, Mirror _mirror) {
        return state.rotate(_mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    public BlockState rotate(BlockState state, IWorld world, BlockPos pos, Rotation direction) {
        return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }
}
