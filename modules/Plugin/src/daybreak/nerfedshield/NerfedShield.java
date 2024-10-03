package daybreak.nerfedshield;

import daybreak.nerfedshield.compat.INMS;
import daybreak.nerfedshield.compat.version.NMSVersion;
import daybreak.nerfedshield.compat.version.ServerVersion;
import daybreak.nerfedshield.config.Configuration;
import daybreak.nerfedshield.config.Configuration.Settings;
import daybreak.nerfedshield.config.wizard.ConfigWizard;
import daybreak.nerfedshield.event.ShieldCooldownEvent;
import daybreak.nerfedshield.event.ShieldCooldownEvent.CooldownType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageModifier;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public class NerfedShield extends JavaPlugin implements Listener {

	public static final String prefix = "§aNerfedShield §2》§f";
	private static NerfedShield plugin;
	private static INMS nms;

	public NerfedShield() {
		plugin = this;
		INMS nms;
		try {
			nms = Class.forName("daybreak.nerfedshield.compat." + ServerVersion.INSTANCE.name() + ".NMSImpl").asSubclass(INMS.class).getConstructor().newInstance();
		} catch (Exception e) {
            e.printStackTrace();
			nms = null;
		}
		NerfedShield.nms = nms;
	}

	public static NerfedShield getPlugin() throws IllegalStateException {
		if (plugin == null) throw new IllegalStateException();
		return plugin;
	}

	public static INMS getNMS() throws IllegalStateException {
		if (nms == null) throw new IllegalStateException();
		return nms;
	}

	@Nullable
	private static Entity getDamager(final Entity damager) {
		if (damager instanceof Projectile) {
			final ProjectileSource shooter = ((Projectile) damager).getShooter();
			return shooter instanceof Entity ? (Entity) shooter : null;
		} else return damager;
	}

	@Override
	public void onEnable() {
		if (ServerVersion.INSTANCE != NMSVersion.UNSUPPORTED && nms != null) {
			Bukkit.getPluginManager().registerEvents(this, this);
			try {
				Configuration.getInstance().update();
			} catch (IOException | InvalidConfigurationException e) {
				Bukkit.getConsoleSender().sendMessage(NerfedShield.prefix + "콘피그 파일 업데이트 중 오류가 발생하였습니다.");
			}
			getCommand("nerfedshield").setExecutor(new CommandExecutor() {
				@Override
				public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
					if (sender instanceof Player) {
						if (sender.isOp()) {
							new ConfigWizard((Player) sender, NerfedShield.this).show();
						} else sender.sendMessage("§c관리자 권한이 필요합니다.");
					} else sender.sendMessage("§c콘솔에서 사용할 수 없는 명령어입니다.");
					return true;
				}
			});
			Bukkit.getConsoleSender().sendMessage(prefix + "플러그인이 활성화되었습니다. §8(§7" + ServerVersion.INSTANCE.name() + "§8)");
		} else {
			Bukkit.getConsoleSender().sendMessage(prefix + "플러그인이 지원하지 않는 버전입니다.");
			Bukkit.getPluginManager().disablePlugin(this);
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	private void onEntityDamageByEntity(final EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player) {
			final Player entity = (Player) e.getEntity();
			if (e.getDamage(DamageModifier.BLOCKING) < 0 && !nms.hasCooldown(entity, Material.SHIELD)) {
				if (e.getDamager() instanceof Projectile) {
					if (!Settings.isProjectileCooldownEnabled()) return;
					final ShieldCooldownEvent event = new ShieldCooldownEvent(entity, e, CooldownType.PROJECTILE, Settings.getProjectileCooldownTime());
					Bukkit.getPluginManager().callEvent(event);
					if (!event.isCancelled()) {
						nms.setCooldown(entity, Material.SHIELD, event.getCooldownTicks());
						new BukkitRunnable() {
							@Override
							public void run() {
								nms.clearActiveItem(entity);
							}
						}.runTaskLater(this, 1L);
					}
					if (Settings.isProjectileMaxBlockEnabled()) {
						e.setDamage(DamageModifier.BLOCKING, Math.max(-Settings.getProjectileMaxBlockAmount(), e.getDamage(DamageModifier.BLOCKING)));
						nms.broadcastEntityEffect(entity, (byte) 30);
					}
				} else {
					if (!Settings.isCooldownEnabled()) return;
					final ShieldCooldownEvent event = new ShieldCooldownEvent(entity, e, CooldownType.BASE, Settings.getCooldownTime());
					Bukkit.getPluginManager().callEvent(event);
					if (!event.isCancelled()) {
						nms.setCooldown(entity, Material.SHIELD, event.getCooldownTicks());
						new BukkitRunnable() {
							@Override
							public void run() {
								nms.clearActiveItem(entity);
							}
						}.runTaskLater(this, 1L);
					}
					if (Settings.isMaxBlockEnabled()) {
						e.setDamage(DamageModifier.BLOCKING, Math.max(-Settings.getMaxBlockAmount(), e.getDamage(DamageModifier.BLOCKING)));
						nms.broadcastEntityEffect(entity, (byte) 30);
					}
				}
			}
		}
		if (Settings.isGroggyEnabled()) {
			final Entity damager = getDamager(e.getDamager());
			if (damager instanceof Player) {
				final Player entity = (Player) damager;
				final int groggyTime = Settings.getGroggyTime();
				if (nms.getCooldown(entity, Material.SHIELD) > groggyTime) return;
				final ShieldCooldownEvent event = new ShieldCooldownEvent(entity, e, CooldownType.GROGGY, groggyTime);
				Bukkit.getPluginManager().callEvent(event);
				if (!event.isCancelled()) {
					nms.setCooldown(entity, Material.SHIELD, event.getCooldownTicks());
					new BukkitRunnable() {
						@Override
						public void run() {
							nms.clearActiveItem(entity);
						}
					}.runTaskLater(this, 1L);
				}
			}
		}
	}

	@Override
	public void onDisable() {
		try {
			Configuration.getInstance().update();
		} catch (IOException | InvalidConfigurationException e) {
			Bukkit.getConsoleSender().sendMessage(NerfedShield.prefix + "콘피그 파일 업데이트 중 오류가 발생하였습니다.");
		}
		Bukkit.getConsoleSender().sendMessage(prefix + "플러그인이 비활성화되었습니다.");
	}

}
