package daybreak.nerfedshield.compat.v1_16_R1;

import daybreak.nerfedshield.compat.INMS;
import net.minecraft.server.v1_16_R1.ItemCooldown;
import net.minecraft.server.v1_16_R1.ItemCooldown.Info;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_16_R1.util.CraftMagicNumbers;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class NMSImpl implements INMS {

	@Override
	public void setCooldown(Player player, Material material, int ticks) {
		((CraftPlayer) player).getHandle().getCooldownTracker().setCooldown(CraftMagicNumbers.getItem(material), ticks);
	}

	@Override
	public boolean hasCooldown(Player player, Material material) {
		return ((CraftPlayer) player).getHandle().getCooldownTracker().hasCooldown(CraftMagicNumbers.getItem(material));
	}

	@Override
	public int getCooldown(Player player, Material material) {
		final ItemCooldown cooldownTracker = ((CraftPlayer) player).getHandle().getCooldownTracker();
		final Info cooldown = cooldownTracker.cooldowns.get(CraftMagicNumbers.getItem(material));
		return cooldown == null ? 0 : Math.max(0, cooldown.endTick - cooldownTracker.currentTick);
	}

	@Override
	public void clearActiveItem(LivingEntity livingEntity) {
		((CraftLivingEntity) livingEntity).getHandle().clearActiveItem();
	}

	@Override
	public void broadcastEntityEffect(Entity entity, byte status) {
		final net.minecraft.server.v1_16_R1.Entity nmsEntity = ((CraftEntity) entity).getHandle();
		nmsEntity.getWorld().broadcastEntityEffect(nmsEntity, status);
	}

}
