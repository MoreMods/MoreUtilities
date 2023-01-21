package ru.ardev.moreutilities.procedures;

import ru.ardev.moreutilities.MoreutilitiesMod;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;

import java.util.Map;

public class TimeSetDayProcedureProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MoreutilitiesMod.LOGGER.warn("Failed to load dependency world for procedure TimeSetDayProcedure!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		if (world instanceof ServerWorld)
			((ServerWorld) world).setDayTime((int) 1000);
	}
}
