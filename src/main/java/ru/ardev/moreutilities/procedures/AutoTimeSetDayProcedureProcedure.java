package ru.ardev.moreutilities.procedures;

import ru.ardev.moreutilities.init.MoreutilitiesModGameRules;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class AutoTimeSetDayProcedureProcedure {
	@SubscribeEvent
	public static void onWorldTick(TickEvent.LevelTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.level);
		}
	}

	public static void execute(LevelAccessor world) {
		execute(null, world);
	}

	private static void execute(@Nullable Event event, LevelAccessor world) {
		if (world.getLevelData().getGameRules().getBoolean(MoreutilitiesModGameRules.AUTOTIMESETDAY) == true) {
			if (world instanceof ServerLevel _level)
				_level.setDayTime(1000);
		}
	}
}
