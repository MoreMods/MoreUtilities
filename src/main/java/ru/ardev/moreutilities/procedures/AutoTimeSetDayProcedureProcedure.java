package ru.ardev.moreutilities.procedures;

import ru.ardev.moreutilities.world.AutoTimeSetDayGameRule;
import ru.ardev.moreutilities.MoreutilitiesModElements;
import ru.ardev.moreutilities.MoreutilitiesMod;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;

import java.util.Map;
import java.util.HashMap;

@MoreutilitiesModElements.ModElement.Tag
public class AutoTimeSetDayProcedureProcedure extends MoreutilitiesModElements.ModElement {
	public AutoTimeSetDayProcedureProcedure(MoreutilitiesModElements instance) {
		super(instance, 3);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MoreutilitiesMod.LOGGER.warn("Failed to load dependency world for procedure AutoTimeSetDayProcedure!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		if ((((world.getWorldInfo().getGameRulesInstance().getBoolean(AutoTimeSetDayGameRule.gamerule)) == (true))
				&& ((world.getWorldInfo().getDayTime()) != 1000))) {
			if (world instanceof ServerWorld)
				((ServerWorld) world).setDayTime((int) 1000);
		}
	}

	@SubscribeEvent
	public void onWorldTick(TickEvent.WorldTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			IWorld world = event.world;
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("world", world);
			dependencies.put("event", event);
			this.executeProcedure(dependencies);
		}
	}
}
