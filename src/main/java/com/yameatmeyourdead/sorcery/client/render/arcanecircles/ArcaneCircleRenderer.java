package com.yameatmeyourdead.sorcery.client.render.arcanecircles;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yameatmeyourdead.sorcery.Sorcery;
import com.yameatmeyourdead.sorcery.te.ArcaneCircleTileEntity;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

public class ArcaneCircleRenderer extends TileEntityRenderer<ArcaneCircleTileEntity> {
    private ResourceLocation TEXTURE; 
    private final ResourceLocation TEXTURE_INACTIVE = new ResourceLocation(Sorcery.MODID, "textures/arcanecircles/arcane_circle_base.png");
    private final ResourceLocation TEXTURE_ACTIVE = TEXTURE_INACTIVE; //new ResourceLocation(Sorcery.MODID, "textures/arcanecircles/arcane_circle_base_active.png");
    private final ModelBaseArcaneCircle model = new ModelBaseArcaneCircle();

    public ArcaneCircleRenderer(TileEntityRendererDispatcher tileEntityRendererDispatcher) {
        super(tileEntityRendererDispatcher);
    }

    @Override
    public void render(ArcaneCircleTileEntity arcaneCircleTileEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer renderBuffer, int combinedLight, int combinedOverlay) {
        matrixStack.pushPose();
        
        matrixStack.translate(.5, -1.5, .5);

        TEXTURE = arcaneCircleTileEntity.isActive ? TEXTURE_ACTIVE : TEXTURE_INACTIVE;
        
        // move up and down sinusoidally
        // matrixStack.translate(0, getHeightOffset(partialTicks), 0);
        
        // rotate about y axis
        matrixStack.mulPose(Vector3f.YN.rotationDegrees(arcaneCircleTileEntity.advanceRotation()));
        
        model.renderToBuffer(matrixStack, renderBuffer.getBuffer(model.renderType(TEXTURE)), combinedLight, combinedOverlay, 1, 1, 1, (float)(Math.sin(arcaneCircleTileEntity.advanceRotation() / 100)/3 + .6));

        matrixStack.popPose();
    }

    @Override
    public boolean shouldRenderOffScreen(ArcaneCircleTileEntity te) {
        return false;
    }    
}
