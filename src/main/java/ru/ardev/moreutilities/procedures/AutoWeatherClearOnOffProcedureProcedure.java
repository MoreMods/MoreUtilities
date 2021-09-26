package ru.ardev.moreutilities.procedures;

import ru.ardev.moreutilities.world.AutoWeatherClearGameRule;
import ru.ardev.moreutilities.MoreutilitiesModElements;
import ru.ardev.moreutilities.MoreutilitiesMod;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

@MoreutilitiesModElements.ModElement.Tag
public class AutoWeatherClearOnOffProcedureProcedure extends MoreutilitiesModElements.ModElement {
	public AutoWeatherClearOnOffProcedureProcedure(MoreutilitiesModElements instance) {
		super(instance, 13);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MoreutilitiesMod.LOGGER.warn("Failed to load dependency entity for procedure AutoWeatherClearOnOffProcedure!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MoreutilitiesMod.LOGGER.warn("Failed to load dependency world for procedure AutoWeatherClearOnOffProcedure!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		if (((world.getWorldInfo().getGameRulesInstance().getBoolean(AutoWeatherClearGameRule.gamerule)) == (false))) {
			if (world instanceof World) {
				((World) world).getGameRules().get(AutoWeatherClearGameRule.gamerule).set((true), ((World) world).getServer());
			}
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("Auto weather clear is off"), (true));
			}
		} else if (((world.getWorldInfo().getGameRulesInstance().getBoolean(AutoWeatherClearGameRule.gamerule)) == (true))) {
			if (world instanceof World) {
				((World) world).getGameRules().get(AutoWeatherClearGameRule.gamerule).set((false), ((World) world).getServer());
			}
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("Auto weather clear is on"), (true));
			}
		}
	}
}
