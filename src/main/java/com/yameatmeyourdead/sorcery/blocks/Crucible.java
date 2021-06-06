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
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

public class Crucible extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE_N = Stream.of(
        Block.box(15, 0, 14, 16, 4, 15),
        Block.box(0, 0, 14, 1, 4, 15),
        Block.box(15, 0, 1, 16, 4, 2),
        Block.box(14, 0, 0, 15, 4, 1),
        Block.box(14, 0, 15, 15, 4, 16),
        Block.box(0, 0, 1, 1, 4, 2),
        Block.box(1, 0, 0, 2, 4, 1),
        Block.box(1, 0, 15, 2, 4, 16),
        Block.box(1, 2, 1, 15, 3, 15),
        Block.box(14, 3, 1, 15, 16, 15),
        Block.box(1, 3, 1, 2, 16, 15),
        Block.box(2, 3, 1, 14, 16, 2),
        Block.box(2, 3, 14, 14, 16, 15),
        Block.box(0, 0, 0, 1, 2, 1),
        Block.box(15, 0, 0, 16, 2, 1),
        Block.box(0, 0, 15, 1, 2, 16), Block.box(15, 0, 15, 16, 2, 16)).reduce((v1, v2) -> {return VoxelShapes.join(v1, v2, IBooleanFunction.OR);}).get();;
    private static final VoxelShape SHAPE_E = Stream.of(
        Block.box(1, 0, 15, 2, 4, 16),
        Block.box(1, 0, 0, 2, 4, 1),
        Block.box(14, 0, 15, 15, 4, 16),
        Block.box(15, 0, 14, 16, 4, 15),
        Block.box(0, 0, 14, 1, 4, 15),
        Block.box(14, 0, 0, 15, 4, 1),
        Block.box(15, 0, 1, 16, 4, 2),
        Block.box(0, 0, 1, 1, 4, 2),
        Block.box(1, 2, 1, 15, 3, 15),
        Block.box(1, 3, 14, 15, 16, 15),
        Block.box(1, 3, 1, 15, 16, 2),
        Block.box(14, 3, 2, 15, 16, 14),
        Block.box(1, 3, 2, 2, 16, 14),
        Block.box(15, 0, 0, 16, 2, 1),
        Block.box(15, 0, 15, 16, 2, 16),
        Block.box(0, 0, 0, 1, 2, 1),
        Block.box(0, 0, 15, 1, 2, 16)
        ).reduce((v1, v2) -> {return VoxelShapes.join(v1, v2, IBooleanFunction.OR);}).get();;
    private static final VoxelShape SHAPE_S = Stream.of(
        Block.box(0, 0, 1, 1, 4, 2),
        Block.box(15, 0, 1, 16, 4, 2),
        Block.box(0, 0, 14, 1, 4, 15),
        Block.box(1, 0, 15, 2, 4, 16),
        Block.box(1, 0, 0, 2, 4, 1),
        Block.box(15, 0, 14, 16, 4, 15),
        Block.box(14, 0, 15, 15, 4, 16),
        Block.box(14, 0, 0, 15, 4, 1),
        Block.box(1, 2, 1, 15, 3, 15),
        Block.box(1, 3, 1, 2, 16, 15),
        Block.box(14, 3, 1, 15, 16, 15),
        Block.box(2, 3, 14, 14, 16, 15),
        Block.box(2, 3, 1, 14, 16, 2),
        Block.box(15, 0, 15, 16, 2, 16),
        Block.box(0, 0, 15, 1, 2, 16),
        Block.box(15, 0, 0, 16, 2, 1),
        Block.box(0, 0, 0, 1, 2, 1)
        ).reduce((v1, v2) -> {return VoxelShapes.join(v1, v2, IBooleanFunction.OR);}).get();;
    private static final VoxelShape SHAPE_W = Stream.of(
        Block.box(14, 0, 0, 15, 4, 1),
        Block.box(14, 0, 15, 15, 4, 16),
        Block.box(1, 0, 0, 2, 4, 1),
        Block.box(0, 0, 1, 1, 4, 2),
        Block.box(15, 0, 1, 16, 4, 2),
        Block.box(1, 0, 15, 2, 4, 16),
        Block.box(0, 0, 14, 1, 4, 15),
        Block.box(15, 0, 14, 16, 4, 15),
        Block.box(1, 2, 1, 15, 3, 15),
        Block.box(1, 3, 1, 15, 16, 2),
        Block.box(1, 3, 14, 15, 16, 15),
        Block.box(1, 3, 2, 2, 16, 14),
        Block.box(14, 3, 2, 15, 16, 14),
        Block.box(0, 0, 15, 1, 2, 16),
        Block.box(0, 0, 0, 1, 2, 1),
        Block.box(15, 0, 15, 16, 2, 16),
        Block.box(15, 0, 0, 16, 2, 1)
        ).reduce((v1, v2) -> {return VoxelShapes.join(v1, v2, IBooleanFunction.OR);}).get();;
    
    public Crucible() {
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

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch(state.getValue(FACING)) {
            case EAST:
                return SHAPE_E;
            case NORTH:
                return SHAPE_N;
            case SOUTH:
                return SHAPE_S;
            case WEST:
                return SHAPE_W;
            default:
                return SHAPE_N;
        }
    }
}
