package daybreak.nerfedshield.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerEvent;

public class ShieldCooldownEvent extends PlayerEvent implements Cancellable {

	private static final HandlerList handlers = new HandlerList();
	private final EntityDamageByEntityEvent damageEvent;
	private final CooldownType cooldownType;
	private int cooldownTicks;
	private boolean isCancelled = false;

	public ShieldCooldownEvent(final Player player, final EntityDamageByEntityEvent damageEvent, final CooldownType cooldownType, final int cooldownTicks) {
		super(player);
		this.damageEvent = damageEvent;
		this.cooldownType = cooldownType;
		this.cooldownTicks = cooldownTicks;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public EntityDamageByEntityEvent getDamageEvent() {
		return damageEvent;
	}

	public CooldownType getCooldownType() {
		return cooldownType;
	}

	public int getCooldownTicks() {
		return cooldownTicks;
	}

	public void setCooldownTicks(int cooldownTicks) {
		if (cooldownTicks < 0) throw new IllegalArgumentException("cooldownTicks cannot be negative");
		this.cooldownTicks = cooldownTicks;
	}

	@Override
	public boolean isCancelled() {
		return isCancelled;
	}

	@Override
	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}

	public enum CooldownType {
		BASE, PROJECTILE, GROGGY
	}

}