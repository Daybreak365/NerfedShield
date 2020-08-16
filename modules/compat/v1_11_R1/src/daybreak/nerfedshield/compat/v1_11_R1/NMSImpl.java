package daybreak.nerfedshield.compat.v1_11_R1;

import daybreak.nerfedshield.compat.INMS;
import net.minecraft.server.v1_11_R1.ItemCooldown;
import net.minecraft.server.v1_11_R1.ItemCooldown.Info;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_11_R1.util.CraftMagicNumbers;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class NMSImpl implements INMS {

	@Override
	public void setCooldown(Player player, Material material, int ticks) {
		((CraftPlayer) player).getHandle().di().a(CraftMagicNumbers.getItem(material), ticks);
	}

	@Override
	public boolean hasCooldown(Player player, Material material) {
		return ((CraftPlayer) player).getHandle().di().a(CraftMagicNumbers.getItem(material));
	}

	@Override
	public int getCooldown(Player player, Material material) {
		final ItemCooldown cooldownTracker = ((CraftPlayer) player).getHandle().di();
		final Info cooldown = cooldownTracker.a.get(CraftMagicNumbers.getItem(material));
		return cooldown == null ? 0 : Math.max(0, cooldown.b - cooldownTracker.b);
	}

	@Override
	public void clearActiveItem(LivingEntity livingEntity) {
		((CraftLivingEntity) livingEntity).getHandle().clearActiveItem();
	}

	@Override
	public void broadcastEntityEffect(Entity entity, byte status) {
		final net.minecraft.server.v1_11_R1.Entity nmsEntity = ((CraftEntity) entity).getHandle();
		nmsEntity.getWorld().broadcastEntityEffect(nmsEntity, status);
	}

}
