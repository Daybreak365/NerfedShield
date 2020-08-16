package daybreak.nerfedshield.util.io;

import daybreak.nerfedshield.NerfedShield;
import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;

/**
 * File 유틸
 *
 * @author Daybreak 새벽
 */
public class Files {

	private static final File mainDirectory = new File("plugins/NerfedShield");

	static {
		if (!mainDirectory.exists()) {
			mainDirectory.mkdirs();
		}
	}

	private Files() {
	}

	public static File newFile(String path) {
		final File file = new File(mainDirectory.getPath() + "/" + path);
		try {
			if (!file.exists()) {
				if (file.getParentFile() != null && !file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}
				file.createNewFile();
			}
			return file;
		} catch (IOException e) {
			Bukkit.getConsoleSender().sendMessage(NerfedShield.prefix + file.getPath() + " 파일을 생성하지 못했습니다.");
			return file;
		}
	}

}
