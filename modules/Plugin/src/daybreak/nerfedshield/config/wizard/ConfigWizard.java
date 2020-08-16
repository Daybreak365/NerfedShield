package daybreak.nerfedshield.config.wizard;

import daybreak.nerfedshield.config.ConfigNodes;
import daybreak.nerfedshield.config.Configuration;
import daybreak.nerfedshield.config.Configuration.Settings;
import daybreak.nerfedshield.util.minecraft.ItemBuilder;
import daybreak.nerfedshield.util.minecraft.MaterialX;
import java.util.Arrays;
import org.bukkit.DyeColor;
import org.bukkit.block.Banner;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class ConfigWizard extends SettingWizard {

	private final ItemStack COOLDOWN = new ItemBuilder().type(MaterialX.IRON_SWORD).displayName("§b기본 쿨타임").build(),
			PROJECTILE_COOLDOWN = new ItemBuilder().type(MaterialX.BOW).displayName("§b투사체 쿨타임").build(),
			GROGGY = new ItemBuilder().type(MaterialX.CLOCK).displayName("§b그로기").build(),
			MAX_BLOCK = new ItemBuilder().type(MaterialX.SHIELD).displayName("§b기본 최대 방어량").build(),
			PROJECTILE_MAX_BLOCK = new ItemBuilder().type(MaterialX.SHIELD).displayName("§b투사체 최대 방어량").build();

	public ConfigWizard(Player player, Plugin plugin) {
		super(player, 27, "§8§l콘피그", plugin);
	}

	@Override
	void openGUI(Inventory gui) {
		for (int i = 0; i < 27; i++) {
			switch (i) {
				case 10: {
					final ItemMeta meta = COOLDOWN.getItemMeta();
					meta.setLore(Arrays.asList(
							"§a활성화 §f하면 방패로 근접 공격을 막았을 때 쿨타임이 생깁니다.",
							"", "§7상태 : " + (Settings.isCooldownEnabled() ? "§a활성화" : "§c비활성화"),
							"§7SHIFT + 좌클릭§f으로 §a활성화§f/§c비활성화 §f여부를 변경하세요.", "",
							"§f쿨타임§8: §7" + (Settings.getCooldownTime() / 20.0) + "초",
							"§cQ              §6» §e+ 1.00초",
							"§c우클릭         §6» §e+ 0.05초",
							"§cCTRL + Q       §6» §e- 1.00초",
							"§c좌클릭         §6» §e- 0.05초",
							"", "§8(§7Q 키는 버리기 키를 의미합니다. 사용자 설정에 따라 다를 수 있습니다.§8)"));
					COOLDOWN.setItemMeta(meta);
					gui.setItem(i, COOLDOWN);
				}
				break;
				case 11: {
					final ItemMeta meta = PROJECTILE_COOLDOWN.getItemMeta();
					meta.setLore(Arrays.asList(
							"§a활성화 §f하면 방패로 투사체를 막았을 때 쿨타임이 생깁니다.",
							"", "§7상태 : " + (Settings.isProjectileCooldownEnabled() ? "§a활성화" : "§c비활성화"),
							"§7SHIFT + 좌클릭§f으로 §a활성화§f/§c비활성화 §f여부를 변경하세요.", "",
							"§f쿨타임§8: §7" + (Settings.getProjectileCooldownTime() / 20.0) + "초",
							"§cQ              §6» §e+ 1.00초",
							"§c우클릭         §6» §e+ 0.05초",
							"§cCTRL + Q       §6» §e- 1.00초",
							"§c좌클릭         §6» §e- 0.05초",
							"", "§8(§7Q 키는 버리기 키를 의미합니다. 사용자 설정에 따라 다를 수 있습니다.§8)"));
					PROJECTILE_COOLDOWN.setItemMeta(meta);
					gui.setItem(i, PROJECTILE_COOLDOWN);
				}
				break;
				case 13: {
					final ItemMeta meta = GROGGY.getItemMeta();
					meta.setLore(Arrays.asList(
							"§a활성화 §f하면 생명체를 공격했을 때 방패에 일정 시간 쿨타임이 생깁니다.",
							"§f공격 후 바로 방패로 방어하는 행위를 방지할 수 있습니다.",
							"", "§7상태 : " + (Settings.isGroggyEnabled() ? "§a활성화" : "§c비활성화"),
							"§7SHIFT + 좌클릭§f으로 §a활성화§f/§c비활성화 §f여부를 변경하세요.", "",
							"§f그로기 시간§8: §7" + (Settings.getGroggyTime() / 20.0) + "초",
							"§cQ              §6» §e+ 1.00초",
							"§c우클릭         §6» §e+ 0.05초",
							"§cCTRL + Q       §6» §e- 1.00초",
							"§c좌클릭         §6» §e- 0.05초",
							"", "§8(§7Q 키는 버리기 키를 의미합니다. 사용자 설정에 따라 다를 수 있습니다.§8)"));
					GROGGY.setItemMeta(meta);
					gui.setItem(i, GROGGY);
				}
				break;
				case 15: {
					final BlockStateMeta meta = (BlockStateMeta) MAX_BLOCK.getItemMeta();
					meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_UNBREAKABLE);
					final Banner banner = (Banner) meta.getBlockState();
					banner.addPattern(new Pattern(DyeColor.GREEN, PatternType.GRADIENT));
					meta.setBlockState(banner);
					meta.setLore(Arrays.asList(
							"§a활성화 §f하면 방패로 막을 수 있는 근접 공격 대미지에 제한이 생깁니다.",
							"§f막지 못한 대미지는 관통해서 그대로 들어옵니다.",
							"", "§7상태 : " + (Settings.isMaxBlockEnabled() ? "§a활성화" : "§c비활성화"),
							"§7SHIFT + 좌클릭§f으로 §a활성화§f/§c비활성화 §f여부를 변경하세요.", "",
							"§f최대 방어량§8: §7" + Settings.getMaxBlockAmount(),
							"§c우클릭         §6» §e+ 1",
							"§c좌클릭         §6» §e- 1"
					));
					MAX_BLOCK.setItemMeta(meta);
					gui.setItem(i, MAX_BLOCK);
				}
				break;
				case 16: {
					final BlockStateMeta meta = (BlockStateMeta) PROJECTILE_MAX_BLOCK.getItemMeta();
					meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_UNBREAKABLE);
					final Banner banner = (Banner) meta.getBlockState();
					banner.addPattern(new Pattern(DyeColor.LIGHT_BLUE, PatternType.GRADIENT));
					meta.setBlockState(banner);
					meta.setLore(Arrays.asList(
							"§a활성화 §f하면 방패로 막을 수 있는 투사체 공격 대미지에 제한이 생깁니다.",
							"§f막지 못한 대미지는 관통해서 그대로 들어옵니다.",
							"", "§7상태 : " + (Settings.isProjectileMaxBlockEnabled() ? "§a활성화" : "§c비활성화"),
							"§7SHIFT + 좌클릭§f으로 §a활성화§f/§c비활성화 §f여부를 변경하세요.", "",
							"§f최대 방어량§8: §7" + Settings.getProjectileMaxBlockAmount(),
							"§c우클릭         §6» §e+ 1",
							"§c좌클릭         §6» §e- 1"
					));
					PROJECTILE_MAX_BLOCK.setItemMeta(meta);
					gui.setItem(i, PROJECTILE_MAX_BLOCK);
				}
				break;
				default:
					gui.setItem(i, DECO);
					break;
			}
		}

		player.openInventory(gui);
	}

	@Override
	void onClick(InventoryClickEvent e, Inventory gui) {
		e.setCancelled(true);
		final ItemStack currentItem = e.getCurrentItem();
		if (currentItem != null && currentItem.hasItemMeta() && currentItem.getItemMeta().hasDisplayName()) {
			switch (currentItem.getItemMeta().getDisplayName()) {
				case "§b기본 쿨타임":
					switch (e.getClick()) {
						case DROP:
							Configuration.getInstance().modifyProperty(ConfigNodes.COOLDOWN_TIME, Settings.getCooldownTime() + 20);
							show();
							break;
						case RIGHT:
							Configuration.getInstance().modifyProperty(ConfigNodes.COOLDOWN_TIME, Settings.getCooldownTime() + 1);
							show();
							break;
						case CONTROL_DROP:
							Configuration.getInstance().modifyProperty(ConfigNodes.COOLDOWN_TIME, Math.max(1, Settings.getCooldownTime() - 20));
							show();
							break;
						case LEFT:
							Configuration.getInstance().modifyProperty(ConfigNodes.COOLDOWN_TIME, Math.max(1, Settings.getCooldownTime() - 1));
							show();
							break;
						case SHIFT_LEFT:
							Configuration.getInstance().modifyProperty(ConfigNodes.COOLDOWN_ENABLE, !Settings.isCooldownEnabled());
							show();
							break;
					}
					break;
				case "§b투사체 쿨타임":
					switch (e.getClick()) {
						case DROP:
							Configuration.getInstance().modifyProperty(ConfigNodes.PROJECTILE_COOLDOWN_TIME, Settings.getProjectileCooldownTime() + 20);
							show();
							break;
						case RIGHT:
							Configuration.getInstance().modifyProperty(ConfigNodes.PROJECTILE_COOLDOWN_TIME, Settings.getProjectileCooldownTime() + 1);
							show();
							break;
						case CONTROL_DROP:
							Configuration.getInstance().modifyProperty(ConfigNodes.PROJECTILE_COOLDOWN_TIME, Math.max(1, Settings.getProjectileCooldownTime() - 20));
							show();
							break;
						case LEFT:
							Configuration.getInstance().modifyProperty(ConfigNodes.PROJECTILE_COOLDOWN_TIME, Math.max(1, Settings.getProjectileCooldownTime() - 1));
							show();
							break;
						case SHIFT_LEFT:
							Configuration.getInstance().modifyProperty(ConfigNodes.PROJECTILE_COOLDOWN_ENABLE, !Settings.isProjectileCooldownEnabled());
							show();
							break;
					}
					break;
				case "§b그로기":
					switch (e.getClick()) {
						case DROP:
							Configuration.getInstance().modifyProperty(ConfigNodes.GROGGY_TIME, Settings.getGroggyTime() + 20);
							show();
							break;
						case RIGHT:
							Configuration.getInstance().modifyProperty(ConfigNodes.GROGGY_TIME, Settings.getGroggyTime() + 1);
							show();
							break;
						case CONTROL_DROP:
							Configuration.getInstance().modifyProperty(ConfigNodes.GROGGY_TIME, Math.max(1, Settings.getGroggyTime() - 20));
							show();
							break;
						case LEFT:
							Configuration.getInstance().modifyProperty(ConfigNodes.GROGGY_TIME, Math.max(1, Settings.getGroggyTime() - 1));
							show();
							break;
						case SHIFT_LEFT:
							Configuration.getInstance().modifyProperty(ConfigNodes.GROGGY_ENABLE, !Settings.isGroggyEnabled());
							show();
							break;
					}
					break;
				case "§b기본 최대 방어량":
					switch (e.getClick()) {
						case RIGHT:
							Configuration.getInstance().modifyProperty(ConfigNodes.MAX_BLOCK_AMOUNT, Settings.getMaxBlockAmount() + 1);
							show();
							break;
						case LEFT:
							Configuration.getInstance().modifyProperty(ConfigNodes.MAX_BLOCK_AMOUNT, Math.max(1, Settings.getMaxBlockAmount() - 1));
							show();
							break;
						case SHIFT_LEFT:
							Configuration.getInstance().modifyProperty(ConfigNodes.MAX_BLOCK_ENABLE, !Settings.isMaxBlockEnabled());
							show();
							break;
					}
					break;
				case "§b투사체 최대 방어량":
					switch (e.getClick()) {
						case RIGHT:
							Configuration.getInstance().modifyProperty(ConfigNodes.PROJECTILE_MAX_BLOCK_AMOUNT, Settings.getProjectileMaxBlockAmount() + 1);
							show();
							break;
						case LEFT:
							Configuration.getInstance().modifyProperty(ConfigNodes.PROJECTILE_MAX_BLOCK_AMOUNT, Math.max(1, Settings.getProjectileMaxBlockAmount() - 1));
							show();
							break;
						case SHIFT_LEFT:
							Configuration.getInstance().modifyProperty(ConfigNodes.PROJECTILE_MAX_BLOCK_ENABLE, !Settings.isProjectileMaxBlockEnabled());
							show();
							break;
					}
					break;
			}
		}
	}

}
