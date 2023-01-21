package ru.ardev.moreutilities.world;

import ru.ardev.moreutilities.MoreutilitiesModElements;

import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import net.minecraft.world.GameRules;

import java.lang.reflect.Method;

@MoreutilitiesModElements.ModElement.Tag
public class AutoWeatherClearGameRule extends MoreutilitiesModElements.ModElement {
	public static final GameRules.RuleKey<GameRules.BooleanValue> gamerule = GameRules.register("autoWeatherClear", GameRules.Category.MISC,
			create(false));

	public AutoWeatherClearGameRule(MoreutilitiesModElements instance) {
		super(instance, 9);
	}

	public static GameRules.RuleType<GameRules.BooleanValue> create(boolean defaultValue) {
		try {
			Method createGameruleMethod = ObfuscationReflectionHelper.findMethod(GameRules.BooleanValue.class, "func_223568_b", boolean.class);
			createGameruleMethod.setAccessible(true);
			return (GameRules.RuleType<GameRules.BooleanValue>) createGameruleMethod.invoke(null, defaultValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
