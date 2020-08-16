package daybreak.nerfedshield.compat.version;

import com.google.common.base.Enums;
import org.bukkit.Bukkit;

public class ServerVersion {

	public static final NMSVersion INSTANCE;

	static {
		final String[] version = Bukkit.getServer().getClass().getName().replace('.', ',').split(",");
		if (version.length >= 4) {
			INSTANCE = Enums.getIfPresent(NMSVersion.class, version[3]).or(NMSVersion.UNSUPPORTED);
		} else INSTANCE = NMSVersion.UNSUPPORTED;
	}

	private ServerVersion() {
	}

	public static int getVersion() {
		return INSTANCE.version;
	}

}
