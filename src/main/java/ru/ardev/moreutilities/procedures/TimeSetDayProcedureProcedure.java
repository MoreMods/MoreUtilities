package ru.ardev.moreutilities.procedures;

import ru.ardev.moreutilities.MoreutilitiesModElements;
import ru.ardev.moreutilities.MoreutilitiesMod;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;

import java.util.Map;

@MoreutilitiesModElements.ModElement.Tag
public class TimeSetDayProcedureProcedure extends MoreutilitiesModElements.ModElement {
	public TimeSetDayProcedureProcedure(MoreutilitiesModElements instance) {
		super(instance, 5);
	}

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
