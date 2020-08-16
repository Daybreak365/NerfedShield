package daybreak.nerfedshield.config;

import daybreak.nerfedshield.config.interfaces.Cacher;
import daybreak.nerfedshield.config.interfaces.Node;

public enum ConfigNodes implements Node {

	COOLDOWN("쿨타임.기본", null, "# 근접 공격 방어시 방패 쿨타임"),
	COOLDOWN_ENABLE("쿨타임.기본.활성화", true, "# 쿨타임 활성화 여부"),
	COOLDOWN_TIME("쿨타임.기본.시간", 40, "# 쿨타임 시간 (단위: 틱)"),
	PROJECTILE_COOLDOWN("쿨타임.원거리", null, "# 투사체 방어시 방패 쿨타임"),
	PROJECTILE_COOLDOWN_ENABLE("쿨타임.원거리.활성화", true, "# 쿨타임 활성화 여부"),
	PROJECTILE_COOLDOWN_TIME("쿨타임.원거리.시간", 40, "# 쿨타임 시간 (단위: 틱)"),
	MAX_BLOCK("최대방어량.기본", null, "# 방패로 막을 수 있는 최대 근접 대미지"),
	MAX_BLOCK_ENABLE("최대방어량.기본.활성화", true, "# 활성화 여부"),
	MAX_BLOCK_AMOUNT("최대방어량.기본.양", 14, "# 막을 수 있는 최대 대미지"),
	PROJECTILE_MAX_BLOCK("최대방어량.원거리", null, "# 방패로 막을 수 있는 최대 투사체 대미지"),
	PROJECTILE_MAX_BLOCK_ENABLE("최대방어량.원거리.활성화", true, "# 활성화 여부"),
	PROJECTILE_MAX_BLOCK_AMOUNT("최대방어량.원거리.양", 14, "# 막을 수 있는 최대 대미지"),
	GROGGY("그로기", null,
			"# 생명체를 공격했을 때 방패에 일정 시간 쿨타임이 생깁니다.",
			"# 공격 후 바로 방패로 방어하는 행위를 방지할 수 있습니다."
	),
	GROGGY_ENABLE("그로기.활성화", true, "# 그로기 활성화 여부"),
	GROGGY_TIME("그로기.시간", 15, "# 그로기 시간 (단위: 틱)");

	private final String path;
	private final Object defaultValue;
	private final Cacher nodeHandler;
	private final String[] comments;

	ConfigNodes(String path, Object defaultValue, Cacher nodeHandler, String... comments) {
		this.path = path;
		this.defaultValue = defaultValue;
		this.nodeHandler = nodeHandler;
		this.comments = comments;
	}

	ConfigNodes(String path, Object defaultValue, String... comments) {
		this(path, defaultValue, null, comments);
	}

	@Override
	public String getPath() {
		return path;
	}

	@Override
	public Object getDefault() {
		return defaultValue;
	}

	@Override
	public boolean hasCacher() {
		return nodeHandler != null;
	}

	@Override
	public Cacher getCacher() {
		return nodeHandler;
	}

	@Override
	public String[] getComments() {
		return comments;
	}

}
