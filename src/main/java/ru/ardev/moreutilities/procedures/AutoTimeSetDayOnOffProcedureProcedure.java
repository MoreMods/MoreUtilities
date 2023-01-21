package ru.ardev.moreutilities.procedures;

import ru.ardev.moreutilities.world.AutoTimeSetDayGameRule;
import ru.ardev.moreutilities.MoreutilitiesMod;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

public class AutoTimeSetDayOnOffProcedureProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MoreutilitiesMod.LOGGER.warn("Failed to load dependency world for procedure AutoTimeSetDayOnOffProcedure!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MoreutilitiesMod.LOGGER.warn("Failed to load dependency entity for procedure AutoTimeSetDayOnOffProcedure!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		Entity entity = (Entity) dependencies.get("entity");
		if (world.getWorldInfo().getGameRulesInstance().getBoolean(AutoTimeSetDayGameRule.gamerule) == true) {
			if (world instanceof World) {
				((World) world).getGameRules().get(AutoTimeSetDayGameRule.gamerule).set((false), ((World) world).getServer());
			}
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("Auto time set day is off"), (true));
			}
		} else if (world.getWorldInfo().getGameRulesInstance().getBoolean(AutoTimeSetDayGameRule.gamerule) == false) {
			if (world instanceof World) {
				((World) world).getGameRules().get(AutoTimeSetDayGameRule.gamerule).set((true), ((World) world).getServer());
			}
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("Auto time set day is on"), (true));
			}
		}
	}
}
