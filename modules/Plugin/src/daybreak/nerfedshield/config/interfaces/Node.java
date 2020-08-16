package daybreak.nerfedshield.config.interfaces;

public interface Node {

	String getPath();

	Object getDefault();

	boolean hasCacher();

	Cacher getCacher();

	String[] getComments();

}
