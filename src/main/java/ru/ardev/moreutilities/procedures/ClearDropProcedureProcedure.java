package ru.ardev.moreutilities.procedures;

import ru.ardev.moreutilities.world.ClearDropLightningEffectGameRule;
import ru.ardev.moreutilities.MoreutilitiesMod;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Entity;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class ClearDropProcedureProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				MoreutilitiesMod.LOGGER.warn("Failed to load dependency world for procedure ClearDropProcedure!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				MoreutilitiesMod.LOGGER.warn("Failed to load dependency entity for procedure ClearDropProcedure!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		Entity entity = (Entity) dependencies.get("entity");
		if (world instanceof ServerWorld) {
			((World) world).getServer().getCommandManager().handleCommand(
					new CommandSource(ICommandSource.DUMMY, new Vector3d((entity.getPosX()), (entity.getPosY()), (entity.getPosZ())), Vector2f.ZERO,
							(ServerWorld) world, 4, "", new StringTextComponent(""), ((World) world).getServer(), null).withFeedbackDisabled(),
					"kill @e[type=item]");
		}
		{
			List<? extends PlayerEntity> _players = new ArrayList<>(world.getPlayers());
			for (Entity entityiterator : _players) {
				if (world.getWorldInfo().getGameRulesInstance().getBoolean(ClearDropLightningEffectGameRule.gamerule) == true) {
					if (world instanceof ServerWorld) {
						LightningBoltEntity _ent = EntityType.LIGHTNING_BOLT.create((World) world);
						_ent.moveForced(Vector3d.copyCenteredHorizontally(
								new BlockPos(entityiterator.getPosX(), entityiterator.getPosY(), entityiterator.getPosZ())));
						_ent.setEffectOnly(true);
						((World) world).addEntity(_ent);
					}
				}
			}
		}
	}
}
