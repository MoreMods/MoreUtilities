package ru.ardev.moreutilities.procedures;

import ru.ardev.moreutilities.init.MoreutilitiesModGameRules;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

public class AutoTimeSetDayOnOffProcedureProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (world.getLevelData().getGameRules().getBoolean(MoreutilitiesModGameRules.AUTOTIMESETDAY) == true) {
			if (world instanceof Level _level)
				_level.getGameRules().getRule(MoreutilitiesModGameRules.AUTOTIMESETDAY).set((false), _level.getServer());
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(Component.literal("Auto time set day is off"), (true));
		} else if (world.getLevelData().getGameRules().getBoolean(MoreutilitiesModGameRules.AUTOTIMESETDAY) == false) {
			if (world instanceof Level _level)
				_level.getGameRules().getRule(MoreutilitiesModGameRules.AUTOTIMESETDAY).set((true), _level.getServer());
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(Component.literal("Auto time set day is on"), (true));
		}
	}
}
