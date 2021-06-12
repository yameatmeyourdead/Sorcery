package com.yameatmeyourdead.sorcery.client.render.arcanecircles;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yameatmeyourdead.sorcery.te.ArcaneCircleTileEntity;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;

public class ArcaneCircleRenderer extends TileEntityRenderer<ArcaneCircleTileEntity> {
    public static final ResourceLocation TEXTURE = new ResourceLocation("sorcery:textures/arcanecircles/arcane_circle_top.png");
    final static ModelBaseArcaneCircle model = new ModelBaseArcaneCircle();

    public ArcaneCircleRenderer(TileEntityRendererDispatcher tileEntityRendererDispatcher) {
        super(tileEntityRendererDispatcher);
    }

    @Override
    public void render(ArcaneCircleTileEntity arcaneCircleTileEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer renderBuffer, int combinedLight, int combinedOverlay) {
        matrixStack.pushPose();
        // matrixStack.translate(0, .125 + getHeightOffset(partialTicks) * 5, 0);

        // move up and down sinusoidally
        matrixStack.translate(0, Math.sin(partialTicks) / 16, 0);
        // rotate about y axis
        // matrixStack.mulPose(new Quaternion(0f, (float) (Math.sin(partialTicks / 100) * 2 * Math.PI), 0f, 0f));

        model.renderToBuffer(matrixStack, renderBuffer.getBuffer(model.renderType(TEXTURE)), combinedLight, combinedOverlay, 255, 255, 255, 255);

        matrixStack.popPose();
    }

    @Override
    public boolean shouldRenderOffScreen(ArcaneCircleTileEntity te) {
        return false;
    }    
}
