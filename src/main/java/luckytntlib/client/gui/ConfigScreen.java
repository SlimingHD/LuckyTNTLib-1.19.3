package luckytntlib.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;

import luckytntlib.config.LuckyTNTLibConfigValues;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.LiteralContents;
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
		addRenderableWidget(new Button((width - 100) / 2, height - 30, 100, 20, CommonComponents.GUI_DONE, button -> onClose()));
		addRenderableWidget(performant_explosion = new Button(20, 40, 100, 20, LuckyTNTLibConfigValues.PERFORMANT_EXPLOSION.get().booleanValue() ? CommonComponents.OPTION_ON : CommonComponents.OPTION_OFF, button -> nextBooleanValue(LuckyTNTLibConfigValues.PERFORMANT_EXPLOSION, performant_explosion)));
		addRenderableWidget(new Button(width - 120, 40, 100, 20, Component.translatable("config.reset"), button -> resetBooleanValue(LuckyTNTLibConfigValues.PERFORMANT_EXPLOSION, performant_explosion)));
		addRenderableWidget(explosion_performance_factor_slider = new ForgeSlider(20, 60, 100, 20, MutableComponent.create(new LiteralContents("")), MutableComponent.create(new LiteralContents("")), 30d, 60d, LuckyTNTLibConfigValues.EXPLOSION_PERFORMANCE_FACTOR.get() * 100, true));
		addRenderableWidget(new Button(width - 120, 60, 100, 20, Component.translatable("config.reset"), button -> resetDoubleValue(LuckyTNTLibConfigValues.EXPLOSION_PERFORMANCE_FACTOR, explosion_performance_factor_slider)));
	}
	
	@Override
	public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
		renderBackground(stack);
		drawCenteredString(stack, font, title, width / 2, 8, 0xFFFFFF);
		drawCenteredString(stack, font, Component.translatable("config.performant_explosion"), width / 2, 46, 0xFFFFFF);
		drawCenteredString(stack, font, Component.translatable("config.explosion_performance_factor"), width / 2, 66, 0xFFFFFF);
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
