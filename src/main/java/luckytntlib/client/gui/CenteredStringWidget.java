package luckytntlib.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.network.chat.Component;

/**
 * Renders a vertically center-aligned String in a Layout.
 * Is used in the config Screen
 */
public class CenteredStringWidget extends StringWidget {

	public CenteredStringWidget(Component component, Font font) {
		super(component, font);
	}

	@Override
	public void renderWidget(PoseStack pose, int i1, int i2, float f) {
		pose.pushPose();
		pose.translate(0f, 6f, 0f);
		super.renderWidget(pose, i1, i2, f);
		pose.popPose();
	}
}