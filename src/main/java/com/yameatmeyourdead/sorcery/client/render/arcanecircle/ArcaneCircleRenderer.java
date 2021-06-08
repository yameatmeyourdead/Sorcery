package com.yameatmeyourdead.sorcery.client.render.arcanecircle;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yameatmeyourdead.sorcery.Sorcery;
import com.yameatmeyourdead.sorcery.te.ArcaneCircleTileEntity;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;

public class ArcaneCircleRenderer {
    private final double PI = Math.PI;
    public final ResourceLocation resourceArray;

    public ArcaneCircleRenderer() {
        this(new ResourceLocation(Sorcery.MODID, "textures/models/arcane_circle.png"));
    }

    public ArcaneCircleRenderer(ResourceLocation resourceArray) {
        this.resourceArray = resourceArray;
    }

    // how should this be rotated about the center?
    public double getRotation(float tick) {
        return Math.sin(tick % (2*PI));
    }

    // how should this be moved up/down into ground
    public double getHeightOffset(float tick) {
        return Math.sin(tick % (2*PI)) / 6;
    }

    public void renderAt(ArcaneCircleTileEntity arcaneCircleTile, double x, double y, float tick, MatrixStack matrixStack, IRenderTypeBuffer renderer, int combinedLightIn, int combinedOverlayIn) {
        matrixStack.pushPose();
        
        matrixStack.translate(0.5, 0.5, 0.5);

        double rotation = getRotation(tick);
        double heightOffset = getHeightOffset(tick);

        // Direction rotation = arcaneCircleTile.getRotation();
    }
}
