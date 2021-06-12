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
        
        circle.texOffs(0, 0);
        circle.addBox(0, 0, 0, 16, 0, 16);
        circle.setPos(0, 24, 0);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder renderBuffer, int combinedLight, int combinedOverlay, float r, float g, float b, float a) {
        circle.render(matrixStack, renderBuffer, combinedLight, combinedOverlay, r, g, b, a);
    }

}
