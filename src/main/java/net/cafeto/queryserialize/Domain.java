/**
 * 
 */
package net.cafeto.queryserialize;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

/**
 * @author fospitia
 *
 */
public class Domain implements Serializable {

	private static final long serialVersionUID = -4421151619972646232L;

	private String name;
	private String alias;
	private boolean collection = false;
	private List<Join> joins = new ArrayList<Join>();

	/**
	 * 
	 */
	public Domain() {
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the collection
	 */
	public boolean isCollection() {
		return collection;
	}

	/**
	 * @param collection the collection to set
	 */
	public void setCollection(boolean collection) {
		this.collection = collection;
	}

	/**
	 * @return the joins
	 */
	public List<Join> getJoins() {
		return joins;
	}

	/**
	 * @param joins the joins to set
	 */
	public void setJoins(List<Join> joins) {
		this.joins = joins;
	}

	/**
	 * @param clazz
	 */
	public void entity(Class<?> clazz) {
		if (!clazz.isAnnotationPresent(Entity.class)) {
			return;
		}
		String name = clazz.getAnnotation(Entity.class).name();
		if (name != null && !name.trim().isEmpty()) {
			System.out.println("name: " + name);
			this.name = clazz.getAnnotation(Entity.class).name();
		} else {
			System.out.println("simple name: " + clazz.getSimpleName());
			this.name = clazz.getSimpleName();
		}
	}

	/**
	 * @return
	 */
	public String toJPQL() {
		StringBuffer buffer = new StringBuffer();
		if (collection) {
			buffer.append("IN (" + name + ")");
		} else {
			buffer.append(name);
		}
		for (Join join : joins) {
			buffer.append(" " + join.toJPQL());
		}
		if (alias != null)
			buffer.append(" AS " + alias);

		return buffer.toString().trim();
	}
}
