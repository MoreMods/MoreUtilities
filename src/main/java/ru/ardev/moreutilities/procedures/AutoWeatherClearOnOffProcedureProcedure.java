package ru.ardev.moreutilities.procedures;

import ru.ardev.moreutilities.init.MoreutilitiesModGameRules;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

public class AutoWeatherClearOnOffProcedureProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (world.getLevelData().getGameRules().getBoolean(MoreutilitiesModGameRules.AUTOWEATHERCLEAR) == false) {
			if (world instanceof Level _level)
				_level.getGameRules().getRule(MoreutilitiesModGameRules.AUTOWEATHERCLEAR).set((true), _level.getServer());
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(Component.literal("Auto weather clear is off"), (true));
		} else if (world.getLevelData().getGameRules().getBoolean(MoreutilitiesModGameRules.AUTOWEATHERCLEAR) == true) {
			if (world instanceof Level _level)
				_level.getGameRules().getRule(MoreutilitiesModGameRules.AUTOWEATHERCLEAR).set((false), _level.getServer());
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(Component.literal("Auto weather clear is on"), (true));
		}
	}
}
