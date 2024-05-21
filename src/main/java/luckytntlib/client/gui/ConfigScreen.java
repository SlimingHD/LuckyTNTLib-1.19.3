package luckytntlib.client.gui;

import luckytntlib.config.LuckyTNTLibConfigValues;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.layouts.GridLayout.RowHelper;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.layouts.LayoutSettings;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraftforge.client.gui.widget.ForgeSlider;
import net.minecraftforge.common.ForgeConfigSpec;

/**
 * The config screen of Lucky TNT Lib.
 * Extending this is not advised. :)
 */
public class ConfigScreen extends Screen{

	Button performant_explosion = null;
	
	ForgeSlider explosion_performance_factor_slider = null;
	
	HeaderAndFooterLayout layout = new HeaderAndFooterLayout(this, 20, 40);
	
	public ConfigScreen() {
		super(Component.translatable("config.title"));
	}

	@Override
	public void init() {
		LinearLayout linear = layout.addToHeader(LinearLayout.vertical());
		linear.addChild(new StringWidget(Component.translatable("config.title"), font), LayoutSettings::alignHorizontallyCenter);
		GridLayout grid = new GridLayout();
		
		grid.defaultCellSetting().paddingHorizontal(4).paddingBottom(4).alignHorizontallyCenter();
		RowHelper rows = grid.createRowHelper(3);
		rows.addChild(performant_explosion = new Button.Builder(LuckyTNTLibConfigValues.PERFORMANT_EXPLOSION.get().booleanValue() ? CommonComponents.OPTION_ON : CommonComponents.OPTION_OFF, button -> nextBooleanValue(LuckyTNTLibConfigValues.PERFORMANT_EXPLOSION, button)).width(100).build());
		performant_explosion.setTooltip(Tooltip.create(Component.translatable("config.performant_explosion_tooltip")));
		rows.addChild(new CenteredStringWidget(Component.translatable("config.performant_explosion"), font));
		rows.addChild(new Button.Builder(Component.translatable("config.reset"), button -> resetBooleanValue(LuckyTNTLibConfigValues.PERFORMANT_EXPLOSION, performant_explosion)).width(100).build());
		rows.addChild(explosion_performance_factor_slider = new ForgeSlider(0, 0, 100, 20, Component.empty(), Component.empty(), 30d, 60d, LuckyTNTLibConfigValues.EXPLOSION_PERFORMANCE_FACTOR.get() * 100, true));
		explosion_performance_factor_slider.setTooltip(Tooltip.create(Component.translatable("config.explosion_performance_factor_tooltip")));
		rows.addChild(new CenteredStringWidget(Component.translatable("config.explosion_performance_factor"), font));
		rows.addChild(new Button.Builder(Component.translatable("config.reset"), button -> resetDoubleValue(LuckyTNTLibConfigValues.EXPLOSION_PERFORMANCE_FACTOR, explosion_performance_factor_slider)).width(100).build());
		
		layout.addToContents(grid);
		layout.addToFooter(new Button.Builder(CommonComponents.GUI_DONE, button -> onClose()).width(100).build());
		layout.visitWidgets(this::addRenderableWidget);
		repositionElements();
	}
	
    @Override
    public void repositionElements() {
        layout.arrangeElements();
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
