package daybreak.nerfedshield.config;

import java.io.IOException;
import org.bukkit.configuration.InvalidConfigurationException;

public class Configuration extends CachedConfig<ConfigNodes> {

	private static final Configuration INSTANCE;

	static {
		try {
			INSTANCE = new Configuration();
		} catch (IOException | InvalidConfigurationException e) {
			throw new RuntimeException(e);
		}
	}

	private Configuration() throws IOException, InvalidConfigurationException {
		super(ConfigNodes.class, "config.yml");
	}

	public static Configuration getInstance() {
		return INSTANCE;
	}

	public static class Settings {

		private Settings() {
		}

		public static boolean isCooldownEnabled() {
			return INSTANCE.get(ConfigNodes.COOLDOWN_ENABLE);
		}

		public static int getCooldownTime() {
			return INSTANCE.get(ConfigNodes.COOLDOWN_TIME);
		}

		public static boolean isProjectileCooldownEnabled() {
			return INSTANCE.get(ConfigNodes.PROJECTILE_COOLDOWN_ENABLE);
		}

		public static int getProjectileCooldownTime() {
			return INSTANCE.get(ConfigNodes.PROJECTILE_COOLDOWN_TIME);
		}

		public static boolean isMaxBlockEnabled() {
			return INSTANCE.get(ConfigNodes.MAX_BLOCK_ENABLE);
		}

		public static int getMaxBlockAmount() {
			return INSTANCE.get(ConfigNodes.MAX_BLOCK_AMOUNT);
		}

		public static boolean isProjectileMaxBlockEnabled() {
			return INSTANCE.get(ConfigNodes.PROJECTILE_MAX_BLOCK_ENABLE);
		}

		public static int getProjectileMaxBlockAmount() {
			return INSTANCE.get(ConfigNodes.PROJECTILE_MAX_BLOCK_AMOUNT);
		}

		public static boolean isGroggyEnabled() {
			return INSTANCE.get(ConfigNodes.GROGGY_ENABLE);
		}

		public static int getGroggyTime() {
			return INSTANCE.get(ConfigNodes.GROGGY_TIME);
		}

	}

}
