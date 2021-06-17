package com.yameatmeyourdead.sorcery.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.yameatmeyourdead.sorcery.Sorcery;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GuiSignomicon extends Screen {
    private static ResourceLocation GUI_TEXTURE = new ResourceLocation(Sorcery.MODID, "textures/gui/signomicon.png");
    private int xSize;
    private int ySize;

    public GuiSignomicon() {
        super(new StringTextComponent("Signomicon"));
        this.xSize = 256;
        this.ySize = 256;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    
    @SuppressWarnings("deprecation")
    @Override
    public void renderBackground(MatrixStack matrixStack) {
        // x = 320 middle of screen
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        this.renderBackground(matrixStack, 1);
        this.minecraft.getTextureManager().bind(GUI_TEXTURE);
        this.blit(matrixStack, 192, 32, 0, 0, this.xSize, this.ySize);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
