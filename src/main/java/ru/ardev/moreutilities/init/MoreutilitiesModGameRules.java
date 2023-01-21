
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package ru.ardev.moreutilities.init;

import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.level.GameRules;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MoreutilitiesModGameRules {
	public static final GameRules.Key<GameRules.BooleanValue> AUTOTIMESETDAY = GameRules.register("autoTimeSetDay", GameRules.Category.MISC,
			GameRules.BooleanValue.create(false));
	public static final GameRules.Key<GameRules.BooleanValue> CLEARDROPLIGHTNINGEFFECT = GameRules.register("clearDropLightningEffect",
			GameRules.Category.MISC, GameRules.BooleanValue.create(true));
	public static final GameRules.Key<GameRules.BooleanValue> AUTOWEATHERCLEAR = GameRules.register("autoWeatherClear", GameRules.Category.MISC,
			GameRules.BooleanValue.create(false));
}
