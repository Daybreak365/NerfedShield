package daybreak.nerfedshield.compat.v1_19_R1;

import daybreak.nerfedshield.compat.INMS;
import net.minecraft.world.item.ItemCooldown;
import net.minecraft.world.item.ItemCooldown.Info;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_19_R1.util.CraftMagicNumbers;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class NMSImpl implements INMS {

	@Override
	public void setCooldown(Player player, Material material, int ticks) {
		((CraftHumanEntity) player).getHandle().getCooldowns().addCooldown(CraftMagicNumbers.getItem(material), ticks);
	}

	@Override
	public boolean hasCooldown(Player player, Material material) {
		return ((CraftHumanEntity) player).getHandle().getCooldowns().isOnCooldown(CraftMagicNumbers.getItem(material));
	}

	@Override
	public int getCooldown(Player player, Material material) {
		final ItemCooldown cooldownTracker = ((CraftHumanEntity) player).getHandle().getCooldowns();
		final Info cooldown = cooldownTracker.cooldowns.get(CraftMagicNumbers.getItem(material));
		return cooldown == null ? 0 : Math.max(0, cooldown.endTime - cooldownTracker.tickCount);
	}

	@Override
	public void clearActiveItem(LivingEntity livingEntity) {
		((CraftLivingEntity) livingEntity).getHandle().stopUsingItem();
	}

	@Override
	public void broadcastEntityEffect(Entity entity, byte status) {
		final net.minecraft.world.entity.Entity nmsEntity = ((CraftEntity) entity).getHandle();
		nmsEntity.getLevel().broadcastEntityEvent(nmsEntity, status);
	}

}
