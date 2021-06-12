package com.yameatmeyourdead.sorcery.client.render.arcanecircles;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yameatmeyourdead.sorcery.Sorcery;
import com.yameatmeyourdead.sorcery.te.ArcaneCircleTileEntity;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;

public class ArcaneCircleRenderer extends TileEntityRenderer<ArcaneCircleTileEntity> {
    public static final ResourceLocation texture = new ResourceLocation(Sorcery.MODID + ":textures/arcanecircles/arcane_circle_top.png");
    final static ModelBaseArcaneCircle model = new ModelBaseArcaneCircle();

    public ArcaneCircleRenderer(TileEntityRendererDispatcher tileEntityRendererDispatcher) {
        super(tileEntityRendererDispatcher);
    }

    @Override
    public void render(ArcaneCircleTileEntity arcaneCircleTileEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer renderBuffers, int combinedLight, int combinedOverlay) {
        matrixStack.pushPose();
        matrixStack.translate(0, .125 + getHeightOffset(partialTicks) * 5, 0);

        matrixStack.scale(-1, -1, 1);
        matrixStack.translate(0d, (double) -1.501f, 0d);
        
        // matrixStack.mulPose(new Quaternion(0, getRotationAngle(partialTicks), 0, 0));
        IVertexBuilder renderBuffer = renderBuffers.getBuffer(model.renderType(texture));
        model.renderToBuffer(matrixStack, renderBuffer, combinedLight, combinedOverlay, 255, 255, 255, 255);

        matrixStack.popPose();
    }

    public float getHeightOffset(float pTick) {
        return (float) Math.sin(pTick / 10000) * 10;
    }

    public float getRotationAngle(float pTick) {
        return (float) (Math.sin(pTick / 100) * 2 * Math.PI);
    }

    @Override
    public boolean shouldRenderOffScreen(ArcaneCircleTileEntity te) {
        return false;
    }    
}
