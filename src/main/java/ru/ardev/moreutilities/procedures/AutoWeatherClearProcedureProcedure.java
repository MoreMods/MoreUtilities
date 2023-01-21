package ru.ardev.moreutilities.procedures;

import ru.ardev.moreutilities.world.AutoWeatherClearGameRule;
import ru.ardev.moreutilities.MoreutilitiesMod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;

import java.util.Map;
import java.util.HashMap;

public class AutoWeatherClearProcedureProcedure {
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
				MoreutilitiesMod.LOGGER.warn("Failed to load dependency world for procedure AutoWeatherClearProcedure!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		if (world.getWorldInfo().getGameRulesInstance().getBoolean(AutoWeatherClearGameRule.gamerule) == true) {
			if (world instanceof ServerWorld) {
				((World) world).getServer().getCommandManager()
						.handleCommand(new CommandSource(ICommandSource.DUMMY, new Vector3d(0, 0, 0), Vector2f.ZERO, (ServerWorld) world, 4, "",
								new StringTextComponent(""), ((World) world).getServer(), null).withFeedbackDisabled(), "weather clear");
			}
		}
	}
}
