package luckytntlib.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;

import luckytntlib.config.LuckyTNTLibConfigValues;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.FrameWidget;
import net.minecraft.client.gui.components.GridWidget;
import net.minecraft.client.gui.components.GridWidget.RowHelper;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraftforge.client.gui.widget.ForgeSlider;
import net.minecraftforge.common.ForgeConfigSpec;

/**
 * The config screen of Lucky TNT Lib.
 * Extending this is not advised.
 */
public class ConfigScreen extends Screen{

	Button performant_explosion = null;
	
	ForgeSlider explosion_performance_factor_slider = null;
	
	public ConfigScreen() {
		super(Component.translatable("config.title"));
	}
	
	@Override
	public void init() {
		GridWidget grid = new GridWidget();
		grid.defaultCellSetting().paddingHorizontal(4).paddingBottom(4).alignHorizontallyCenter();
		RowHelper rows = grid.createRowHelper(3);
		rows.addChild(performant_explosion = new Button.Builder(LuckyTNTLibConfigValues.PERFORMANT_EXPLOSION.get().booleanValue() ? CommonComponents.OPTION_ON : CommonComponents.OPTION_OFF, button -> nextBooleanValue(LuckyTNTLibConfigValues.PERFORMANT_EXPLOSION, button)).width(100).build());
		rows.addChild(new CenteredStringWidget(Component.translatable("config.performant_explosion"), font));
		rows.addChild(new Button.Builder(Component.translatable("config.reset"), button -> resetBooleanValue(LuckyTNTLibConfigValues.PERFORMANT_EXPLOSION, performant_explosion)).width(100).build());
		rows.addChild(explosion_performance_factor_slider = new ForgeSlider(0, 0, 100, 20, Component.empty(), Component.empty(), 30d, 60d, LuckyTNTLibConfigValues.EXPLOSION_PERFORMANCE_FACTOR.get() * 100, true));
		rows.addChild(new CenteredStringWidget(Component.translatable("config.explosion_performance_factor"), font));
		rows.addChild(new Button.Builder(Component.translatable("config.reset"), button -> resetDoubleValue(LuckyTNTLibConfigValues.EXPLOSION_PERFORMANCE_FACTOR, explosion_performance_factor_slider)).width(100).build());

		grid.pack();
		FrameWidget.alignInRectangle(grid, 0, 40, width, height, 0.5f, 0f);
		addRenderableWidget(grid);
		addRenderableWidget(new Button.Builder(CommonComponents.GUI_DONE, button -> onClose()).bounds((width - 100) / 2, height - 30, 100, 20).build());
	}
	
	@Override
	public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
		renderBackground(stack);
		drawCenteredString(stack, font, title, width / 2, 8, 0xFFFFFF);
		super.render(stack, mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void onClose() {
		if(explosion_performance_factor_slider != null) {
			LuckyTNTLibConfigValues.EXPLOSION_PERFORMANCE_FACTOR.set(explosion_performance_factor_slider.getValue() / 100d);
		}
		super.onClose();
	}
	
	public void resetDoubleValue(ForgeConfigSpec.DoubleValue config, ForgeSlider slider) {
		config.set(config.getDefault());
		slider.setValue(config.getDefault() * 100);
	}
	
	public void resetBooleanValue(ForgeConfigSpec.BooleanValue config, Button button) {
		config.set(config.getDefault());
		button.setMessage(config.getDefault() ? CommonComponents.OPTION_ON : CommonComponents.OPTION_OFF);
	}
	
	public void nextBooleanValue(ForgeConfigSpec.BooleanValue config, Button button) {
		boolean value = config.get().booleanValue();
		if(value) {
			value = false;
		} else {
			value = true;
		}
		config.set(value);
		button.setMessage(value ? CommonComponents.OPTION_ON : CommonComponents.OPTION_OFF);
	}
}
