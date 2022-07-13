package net.fabricmc.orienteering.client.gui.screen;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.NarratorManager;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class AbstractControlBlockScreen extends Screen {
    protected TextFieldWidget controlCodeTextField;
    protected ButtonWidget doneButton;
    protected ButtonWidget cancelButton;

    public AbstractControlBlockScreen() {
        super(NarratorManager.EMPTY);
    }

    @Override
    public void tick() {
        this.controlCodeTextField.tick();
    }

    protected void init() {
        this.client.keyboard.setRepeatEvents(true);
        this.doneButton = this.addDrawableChild(new ButtonWidget(this.width / 2 - 4 - 150, this.height / 4 + 120 + 12,
                150, 20, ScreenTexts.DONE, button -> this.commitAndClose()));
        this.cancelButton = this.addDrawableChild(new ButtonWidget(this.width / 2 + 4, this.height / 4 + 120 + 12, 150,
                20, ScreenTexts.CANCEL, button -> this.onClose()));
        this.controlCodeTextField = new TextFieldWidget(this.textRenderer, this.width / 2 - 150, 50, 300, 20,
                (Text) new TranslatableText("advMode.command"));
        this.controlCodeTextField.setMaxLength(3);
        this.addSelectableChild(this.controlCodeTextField);
        this.setInitialFocus(this.controlCodeTextField);
        this.controlCodeTextField.setTextFieldFocused(true);
    }

    @Override
    public void resize(MinecraftClient client, int width, int height) {
        String string = this.controlCodeTextField.getText();
        this.init(client, width, height);
        this.controlCodeTextField.setText(string);
    }

    protected void commitAndClose() {
        // CommandBlockExecutor commandBlockExecutor = this.getCommandExecutor();
        // this.syncSettingsToServer(commandBlockExecutor);
        // if (!commandBlockExecutor.isTrackingOutput()) {
        // commandBlockExecutor.setLastOutput(null);
        // }
        // this.client.setScreen(null);
    }
}
