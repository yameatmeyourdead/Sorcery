package com.yameatmeyourdead.sorcery.client.render.arcanecircles;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ModelBaseArcaneCircle extends Model {
    public ModelRenderer circle;

    public ModelBaseArcaneCircle() {
        super(RenderType::entityTranslucent);
        texWidth = 160;
        texHeight = 160;

        circle = new ModelRenderer(this);
        circle.setTexSize(160, 160);
        circle.texOffs(0, 0);
        circle.addBox(-8, 0, -8, 24, 1, 24);
        circle.setPos(-8, 24, -8);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder renderBuffer, int combinedLight, int combinedOverlay, float r, float g, float b, float a) {
        circle.render(matrixStack, renderBuffer, combinedLight, combinedOverlay, r, g, b, a);
    }

}
