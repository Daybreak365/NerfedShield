package daybreak.nerfedshield.compat.version;

public enum NMSVersion {

	UNSUPPORTED(-1, -1),
	v1_9_R2(9, 2),
	v1_10_R1(10, 1),
	v1_11_R1(11, 1),
	v1_12_R1(12, 1),
	v1_13_R1(13, 1),
	v1_13_R2(13, 2),
	v1_14_R1(14, 1),
	v1_15_R1(15, 1),
	v1_16_R1(16, 1),
	v1_16_R2(16, 2),
	v1_18_R2(18, 2),
	v1_19_R1(19, 1);

	public final int version;
	public final int release;

	NMSVersion(final int version, final int release) {
		this.version = version;
		this.release = release;
	}

}
