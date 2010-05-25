/**
 * 
 */
package net.cafeto.queryserialize;

import java.io.Serializable;

/**
 * @author fospitia
 *
 */
public class Join implements Serializable {

	private static final long serialVersionUID = -3517519725932222768L;

	private String type = "INNER";
	private boolean fetch = false;
	private String path;
	private String alias;

	/**
	 * 
	 */
	public Join() {
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the fetch
	 */
	public boolean isFetch() {
		return fetch;
	}

	/**
	 * @param fetch the fetch to set
	 */
	public void setFetch(boolean fetch) {
		this.fetch = fetch;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * @param alias the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * @return
	 */
	public String toJPQL() {
		StringBuffer buffer = new StringBuffer();
		if ("INNER".equals(type))
			buffer.append("INNER JOIN ");
		else
			buffer.append("LEFT OUTER JOIN ");
		if (fetch)
			buffer.append("FETCH ");
		buffer.append(path);
		if (alias != null)
			buffer.append(" AS " + alias);
		return buffer.toString().trim();
	}
}
