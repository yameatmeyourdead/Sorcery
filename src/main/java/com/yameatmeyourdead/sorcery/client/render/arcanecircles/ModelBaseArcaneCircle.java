package com.yameatmeyourdead.sorcery.client.render.arcanecircles;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ModelBaseArcaneCircle extends Model {
    public ModelRenderer circle;

    public ModelBaseArcaneCircle() {
        super(RenderType::entityCutout);

        circle = new ModelRenderer(this);
        circle.setPos(0, 24, 0);
        circle.setTexSize(32, 32);
        circle.addBox(0, 1, 0, 32, 1, 32);
        
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder renderBuffer, int combinedLight, int combinedOverlay, float r, float g, float b, float a) {
        circle.render(matrixStack, renderBuffer, combinedLight, combinedOverlay, r, g, b, a);
    }

}
