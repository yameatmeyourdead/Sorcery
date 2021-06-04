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
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IWorld;

public class Crucible extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE_N = Stream.of(
        VoxelShapes.combineAndSimplify(Block.box(5.862962962962963, 3, 5.751851851851853, 9.862962962962964, 4, 9.751851851851853), VoxelShapes.combineAndSimplify(Block.box(5.862962962962963, 4, 9.751851851851853, 9.862962962962964, 5, 11.751851851851853), VoxelShapes.combineAndSimplify(Block.box(5.862962962962963, 4, 3.7518518518518533, 9.862962962962964, 5, 5.751851851851853), VoxelShapes.combineAndSimplify(Block.box(4.862962962962963, 4, 4.751851851851853, 5.862962962962963, 5, 10.751851851851853), VoxelShapes.combineAndSimplify(Block.box(9.862962962962964, 4, 4.751851851851853, 10.862962962962964, 5, 10.751851851851853), VoxelShapes.combineAndSimplify(Block.box(10.862962962962964, 4, 5.751851851851853, 11.862962962962964, 6, 9.751851851851853), VoxelShapes.combineAndSimplify(Block.box(3.862962962962963, 4, 5.751851851851853, 4.862962962962963, 6, 9.751851851851853), VoxelShapes.combineAndSimplify(Block.box(3.862962962962963, 5, 9.751851851851853, 5.862962962962963, 7, 11.751851851851853), VoxelShapes.combineAndSimplify(Block.box(3.862962962962963, 5, 3.7518518518518533, 5.862962962962963, 7, 5.751851851851853), VoxelShapes.combineAndSimplify(Block.box(9.862962962962964, 5, 3.7518518518518533, 11.862962962962964, 7, 5.751851851851853), VoxelShapes.combineAndSimplify(Block.box(9.862962962962964, 5, 9.751851851851853, 11.862962962962964, 7, 11.751851851851853), VoxelShapes.combineAndSimplify(Block.box(5.862962962962963, 5, 10.751851851851853, 9.862962962962964, 6, 12.751851851851853), VoxelShapes.combineAndSimplify(Block.box(5.862962962962963, 5, 2.7518518518518533, 9.862962962962964, 6, 4.751851851851853), VoxelShapes.combineAndSimplify(Block.box(3.862962962962963, 6, 2.7518518518518533, 11.862962962962964, 9, 3.7518518518518533), VoxelShapes.combineAndSimplify(Block.box(3.862962962962963, 6, 11.751851851851853, 11.862962962962964, 9, 12.751851851851853), VoxelShapes.combineAndSimplify(Block.box(2.862962962962963, 6, 3.7518518518518533, 3.862962962962963, 9, 11.751851851851853), Block.box(11.862962962962964, 6, 3.7518518518518533, 12.862962962962964, 9, 11.751851851851853), IBooleanFunction.CAULDRON), IBooleanFunction.CAULDRON), IBooleanFunction.CAULDRON), IBooleanFunction.CAULDRON), IBooleanFunction.CAULDRON), IBooleanFunction.CAULDRON), IBooleanFunction.CAULDRON), IBooleanFunction.CAULDRON), IBooleanFunction.CAULDRON), IBooleanFunction.CAULDRON), IBooleanFunction.CAULDRON), IBooleanFunction.CAULDRON), IBooleanFunction.CAULDRON), IBooleanFunction.CAULDRON), IBooleanFunction.CAULDRON), IBooleanFunction.CAULDRON),
        VoxelShapes.combineAndSimplify(Block.box(10.862962962962964, 0, 3.7518518518518533, 11.862962962962964, 3, 4.751851851851853), VoxelShapes.combineAndSimplify(Block.box(3.862962962962963, 0, 3.7518518518518533, 4.862962962962963, 3, 4.751851851851853), VoxelShapes.combineAndSimplify(Block.box(9.862962962962964, 2, 4.751851851851853, 10.862962962962964, 4, 5.751851851851853), VoxelShapes.combineAndSimplify(Block.box(3.862962962962963, 0, 10.751851851851853, 4.862962962962963, 3, 11.751851851851853), VoxelShapes.combineAndSimplify(Block.box(4.862962962962963, 2, 4.751851851851853, 5.862962962962963, 4, 5.751851851851853), VoxelShapes.combineAndSimplify(Block.box(9.862962962962964, 2, 9.751851851851853, 10.862962962962964, 4, 10.751851851851853), VoxelShapes.combineAndSimplify(Block.box(10.862962962962964, 0, 10.751851851851853, 11.862962962962964, 3, 11.751851851851853), Block.box(4.862962962962963, 2, 9.751851851851853, 5.862962962962963, 4, 10.751851851851853), IBooleanFunction.CA), IBooleanFunction.LEGS), IBooleanFunction.LEGS), IBooleanFunction.LEGS), IBooleanFunction.LEGS), IBooleanFunction.LEGS), IBooleanFunction.LEGS),
        Block.box(3.862962962962963, 7, 3.7518518518518533, 11.862962962962964, 8, 11.751851851851853),
        Block.box(5.862962962962963, 8, 9.451851851851853, 6.862962962962963, 12, 10.451851851851853)
        ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);});;
    
    private static final VoxelShape SHAPE_E = Stream.of(
        VoxelShapes.combineAndSimplify(Block.box(5.862962962962963, 3, 5.751851851851853, 9.862962962962964, 4, 9.751851851851853), VoxelShapes.combineAndSimplify(Block.box(5.862962962962963, 4, 9.751851851851853, 9.862962962962964, 5, 11.751851851851853), VoxelShapes.combineAndSimplify(Block.box(5.862962962962963, 4, 3.7518518518518533, 9.862962962962964, 5, 5.751851851851853), VoxelShapes.combineAndSimplify(Block.box(4.862962962962963, 4, 4.751851851851853, 5.862962962962963, 5, 10.751851851851853), VoxelShapes.combineAndSimplify(Block.box(9.862962962962964, 4, 4.751851851851853, 10.862962962962964, 5, 10.751851851851853), VoxelShapes.combineAndSimplify(Block.box(10.862962962962964, 4, 5.751851851851853, 11.862962962962964, 6, 9.751851851851853), VoxelShapes.combineAndSimplify(Block.box(3.862962962962963, 4, 5.751851851851853, 4.862962962962963, 6, 9.751851851851853), VoxelShapes.combineAndSimplify(Block.box(3.862962962962963, 5, 9.751851851851853, 5.862962962962963, 7, 11.751851851851853), VoxelShapes.combineAndSimplify(Block.box(3.862962962962963, 5, 3.7518518518518533, 5.862962962962963, 7, 5.751851851851853), VoxelShapes.combineAndSimplify(Block.box(9.862962962962964, 5, 3.7518518518518533, 11.862962962962964, 7, 5.751851851851853), VoxelShapes.combineAndSimplify(Block.box(9.862962962962964, 5, 9.751851851851853, 11.862962962962964, 7, 11.751851851851853), VoxelShapes.combineAndSimplify(Block.box(5.862962962962963, 5, 10.751851851851853, 9.862962962962964, 6, 12.751851851851853), VoxelShapes.combineAndSimplify(Block.box(5.862962962962963, 5, 2.7518518518518533, 9.862962962962964, 6, 4.751851851851853), VoxelShapes.combineAndSimplify(Block.box(3.862962962962963, 6, 2.7518518518518533, 11.862962962962964, 9, 3.7518518518518533), VoxelShapes.combineAndSimplify(Block.box(3.862962962962963, 6, 11.751851851851853, 11.862962962962964, 9, 12.751851851851853), VoxelShapes.combineAndSimplify(Block.box(2.862962962962963, 6, 3.7518518518518533, 3.862962962962963, 9, 11.751851851851853), Block.box(11.862962962962964, 6, 3.7518518518518533, 12.862962962962964, 9, 11.751851851851853), IBooleanFunction.CAULDRON), IBooleanFunction.CAULDRON), IBooleanFunction.CAULDRON), IBooleanFunction.CAULDRON), IBooleanFunction.CAULDRON), IBooleanFunction.CAULDRON), IBooleanFunction.CAULDRON), IBooleanFunction.CAULDRON), IBooleanFunction.CAULDRON), IBooleanFunction.CAULDRON), IBooleanFunction.CAULDRON), IBooleanFunction.CAULDRON), IBooleanFunction.CAULDRON), IBooleanFunction.CAULDRON), IBooleanFunction.CAULDRON), IBooleanFunction.CAULDRON),
        VoxelShapes.combineAndSimplify(Block.box(10.862962962962964, 0, 3.7518518518518533, 11.862962962962964, 3, 4.751851851851853), VoxelShapes.combineAndSimplify(Block.box(3.862962962962963, 0, 3.7518518518518533, 4.862962962962963, 3, 4.751851851851853), VoxelShapes.combineAndSimplify(Block.box(9.862962962962964, 2, 4.751851851851853, 10.862962962962964, 4, 5.751851851851853), VoxelShapes.combineAndSimplify(Block.box(3.862962962962963, 0, 10.751851851851853, 4.862962962962963, 3, 11.751851851851853), VoxelShapes.combineAndSimplify(Block.box(4.862962962962963, 2, 4.751851851851853, 5.862962962962963, 4, 5.751851851851853), VoxelShapes.combineAndSimplify(Block.box(9.862962962962964, 2, 9.751851851851853, 10.862962962962964, 4, 10.751851851851853), VoxelShapes.combineAndSimplify(Block.box(10.862962962962964, 0, 10.751851851851853, 11.862962962962964, 3, 11.751851851851853), Block.box(4.862962962962963, 2, 9.751851851851853, 5.862962962962963, 4, 10.751851851851853), IBooleanFunction.LEGS), IBooleanFunction.LEGS), IBooleanFunction.LEGS), IBooleanFunction.LEGS), IBooleanFunction.LEGS), IBooleanFunction.LEGS), IBooleanFunction.LEGS),
        Block.box(3.862962962962963, 7, 3.7518518518518533, 11.862962962962964, 8, 11.751851851851853),
        Block.box(4.862962962962963, 8, 8.451851851851853, 5.862962962962963, 12, 9.451851851851853)
        ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);});;
    private static final VoxelShape SHAPE_S = ;
    private static final VoxelShape SHAPE_W = ;
    public Crucible(Properties p_i48440_1_) {
        super(Properties.of(Material.METAL).sound(SoundType.METAL));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
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
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }
}
