package net.fabricmc.orienteering.client.gui.screen;

import net.fabricmc.orienteering.block.entity.ControlBlockBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.CyclingButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.NarratorManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class ControlBlockScreen extends Screen {
    private CyclingButtonWidget<Object> modeButton;
    protected TextFieldWidget controlCodeTextField;
    protected ButtonWidget doneButton;
    protected ButtonWidget cancelButton;
    private ControlBlockBlockEntity blockEntity;

    public ControlBlockScreen(ControlBlockBlockEntity blockEntity) {
        super(NarratorManager.EMPTY);
        this.blockEntity = blockEntity;
    }

    @Override
    public void tick() {
        this.controlCodeTextField.tick();
    }

    protected void init() {

        this.client.keyboard.setRepeatEvents(true);
        this.doneButton = this
                .addDrawableChild(new ButtonWidget(this.width / 2 - 4 - 150, this.height / 2 + 12,
                        150, 20, ScreenTexts.DONE, button -> this.commitAndClose()));
        this.cancelButton = this.addDrawableChild(new ButtonWidget(this.width / 2 + 4, this.height / 2 + 12, 150,
                20, ScreenTexts.CANCEL, button -> this.onClose()));
        this.modeButton = this
                .addDrawableChild(CyclingButtonWidget.builder(value -> {
                    return Text.of(value.toString());
                })
                        .values((Object[]) ControlBlockBlockEntity.Type.values())
                        .omitKeyText()
                        .initially(this.blockEntity.getControlType())
                        .build(this.width / 2 - 100 - 4, height / 2 - 60, 100, 20,
                                new TranslatableText("advMode.mode")));
        this.controlCodeTextField = new TextFieldWidget(this.textRenderer, this.width / 2 + 4, this.height / 2 - 40,
                26, 20,
                (Text) new TranslatableText("advMode.command"));
        this.controlCodeTextField.setMaxLength(3);
        this.addSelectableChild(this.controlCodeTextField);
        this.setInitialFocus(this.controlCodeTextField);
        this.controlCodeTextField.setTextFieldFocused(true);
        this.controlCodeTextField.setText(String.valueOf(this.blockEntity.getControlCode()));
    }

    @Override
    public void resize(MinecraftClient client, int width, int height) {
        this.init();
    }

    protected void commitAndClose() {
        this.blockEntity.setControlType(ControlBlockBlockEntity.Type.valueOf(this.modeButton.getValue().toString()));
        this.blockEntity.setControlCode(Integer.parseInt(this.controlCodeTextField.getText()));
        this.client.setScreen(null);
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        ControlBlockScreen.drawTextWithShadow(matrices, this.textRenderer, Text.of("Control Code"),
                this.width / 2 + 4, this.height / 2 - 55, 0xA0A0A0);
        this.controlCodeTextField.render(matrices, mouseX, mouseY, delta);
        super.render(matrices, mouseX, mouseY, delta);
    }
}