package daybreak.nerfedshield.compat.v1_9_R2;

import daybreak.nerfedshield.compat.INMS;
import net.minecraft.server.v1_9_R2.Item;
import net.minecraft.server.v1_9_R2.ItemCooldown;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_9_R2.util.CraftMagicNumbers;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class NMSImpl implements INMS {

	@Override
	public void setCooldown(Player player, Material material, int ticks) {
		((CraftPlayer) player).getHandle().db().a(CraftMagicNumbers.getItem(material), ticks);
	}

	@Override
	public boolean hasCooldown(Player player, Material material) {
		return ((CraftPlayer) player).getHandle().db().a(CraftMagicNumbers.getItem(material));
	}

	@Override
	public int getCooldown(Player player, Material material) {
		final ItemCooldown cooldownTracker = ((CraftPlayer) player).getHandle().db();
		final Item item = CraftMagicNumbers.getItem(material);
		final float left = cooldownTracker.a(item, 0.0f);
		return (int) (left * (1.0f / (left - cooldownTracker.a(item, 1.0f))));
	}

	@Override
	public void clearActiveItem(LivingEntity livingEntity) {
		((CraftLivingEntity) livingEntity).getHandle().clearActiveItem();
	}

	@Override
	public void broadcastEntityEffect(Entity entity, byte status) {
		final net.minecraft.server.v1_9_R2.Entity nmsEntity = ((CraftEntity) entity).getHandle();
		nmsEntity.getWorld().broadcastEntityEffect(nmsEntity, status);
	}

}
