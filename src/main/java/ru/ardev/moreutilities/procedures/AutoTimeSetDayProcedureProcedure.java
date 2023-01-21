package ru.ardev.moreutilities.procedures;

import ru.ardev.moreutilities.world.AutoTimeSetDayGameRule;
import ru.ardev.moreutilities.MoreutilitiesMod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;

import java.util.Map;
import java.util.HashMap;

public class AutoTimeSetDayProcedureProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onWorldTick(TickEvent.WorldTickEvent event) {
			if (event.phase == TickEvent.Phase.END) {
				IWorld world = event.world;
				Map<String, Object> dependencies = new HashMap<>();
				dependencies.put("world", world);
				dependencies.put("event", event);
				executeProcedure(dependencies);
			}
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MoreutilitiesMod.LOGGER.warn("Failed to load dependency world for procedure AutoTimeSetDayProcedure!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		if (world.getWorldInfo().getGameRulesInstance().getBoolean(AutoTimeSetDayGameRule.gamerule) == true) {
			if (world instanceof ServerWorld)
				((ServerWorld) world).setDayTime((int) 1000);
		}
	}
}
