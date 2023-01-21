
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package ru.ardev.moreutilities.init;

import ru.ardev.moreutilities.network.WeatherClearKeyMessage;
import ru.ardev.moreutilities.network.TimeSetDayKeyMessage;
import ru.ardev.moreutilities.network.ClearDropKeyMessage;
import ru.ardev.moreutilities.MoreutilitiesMod;

import org.lwjgl.glfw.GLFW;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class MoreutilitiesModKeyMappings {
	public static final KeyMapping TIME_SET_DAY_KEY = new KeyMapping("key.moreutilities.time_set_day_key", GLFW.GLFW_KEY_BACKSLASH,
			"key.categories.misc") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				MoreutilitiesMod.PACKET_HANDLER.sendToServer(new TimeSetDayKeyMessage(0, 0));
				TimeSetDayKeyMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};
	public static final KeyMapping CLEAR_DROP_KEY = new KeyMapping("key.moreutilities.clear_drop_key", GLFW.GLFW_KEY_KP_ADD, "key.categories.misc") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				MoreutilitiesMod.PACKET_HANDLER.sendToServer(new ClearDropKeyMessage(0, 0));
				ClearDropKeyMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};
	public static final KeyMapping WEATHER_CLEAR_KEY = new KeyMapping("key.moreutilities.weather_clear_key", GLFW.GLFW_KEY_MINUS,
			"key.categories.misc") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				MoreutilitiesMod.PACKET_HANDLER.sendToServer(new WeatherClearKeyMessage(0, 0));
				WeatherClearKeyMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};

	@SubscribeEvent
	public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
		event.register(TIME_SET_DAY_KEY);
		event.register(CLEAR_DROP_KEY);
		event.register(WEATHER_CLEAR_KEY);
	}

	@Mod.EventBusSubscriber({Dist.CLIENT})
	public static class KeyEventListener {
		@SubscribeEvent
		public static void onClientTick(TickEvent.ClientTickEvent event) {
			if (Minecraft.getInstance().screen == null) {
				TIME_SET_DAY_KEY.consumeClick();
				CLEAR_DROP_KEY.consumeClick();
				WEATHER_CLEAR_KEY.consumeClick();
			}
		}
	}
}
