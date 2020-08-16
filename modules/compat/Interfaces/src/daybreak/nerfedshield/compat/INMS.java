package daybreak.nerfedshield.compat;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public interface INMS {

	void setCooldown(final Player player, final Material material, final int ticks);

	boolean hasCooldown(final Player player, final Material material);

	int getCooldown(final Player player, final Material material);

	void clearActiveItem(LivingEntity livingEntity);

	void broadcastEntityEffect(Entity entity, byte status);

}
