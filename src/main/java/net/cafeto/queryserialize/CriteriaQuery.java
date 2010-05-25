/**
 * 
 */
package net.cafeto.queryserialize;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fospitia
 * 
 */
public class CriteriaQuery implements Serializable {

	private static final long serialVersionUID = -1825648103557536367L;

	private boolean distict;

	private Domain root;
	private List<Expression> selects = new ArrayList<Expression>();
	private List<Domain> froms = new ArrayList<Domain>();
	private Expression where;
	private Expression having;
	private List<Expression> groupBys = new ArrayList<Expression>();
	private List<Order> orders = new ArrayList<Order>();

	/**
	 * 
	 */
	public CriteriaQuery() {
	}

	/**
	 * @return the distict
	 */
	public boolean isDistict() {
		return distict;
	}

	/**
	 * @param distict
	 *            the distict to set
	 */
	public void setDistict(boolean distict) {
		this.distict = distict;
	}

	/**
	 * @return the root
	 */
	public Domain getRoot() {
		return root;
	}

	/**
	 * @param root
	 *            the root to set
	 */
	public void setRoot(Domain root) {
		this.root = root;
	}

	/**
	 * @return the selects
	 */
	public List<Expression> getSelects() {
		return selects;
	}

	/**
	 * @param selects
	 *            the selects to set
	 */
	public void setSelects(List<Expression> selects) {
		this.selects = selects;
	}

	/**
	 * @return the froms
	 */
	public List<Domain> getFroms() {
		return froms;
	}

	/**
	 * @param froms
	 *            the froms to set
	 */
	public void setFroms(List<Domain> froms) {
		this.froms = froms;
	}

	/**
	 * @return the where
	 */
	public Expression getWhere() {
		return where;
	}

	/**
	 * @param where
	 *            the where to set
	 */
	public void setWhere(Expression where) {
		this.where = where;
	}

	/**
	 * @return the having
	 */
	public Expression getHaving() {
		return having;
	}

	/**
	 * @param having
	 *            the having to set
	 */
	public void setHaving(Expression having) {
		this.having = having;
	}

	/**
	 * @return the groupBys
	 */
	public List<Expression> getGroupBys() {
		return groupBys;
	}

	/**
	 * @param groupBys
	 *            the groupBys to set
	 */
	public void setGroupBys(List<Expression> groupBys) {
		this.groupBys = groupBys;
	}

	/**
	 * @return the orders
	 */
	public List<Order> getOrders() {
		return orders;
	}

	/**
	 * @param orders
	 *            the orders to set
	 */
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	/**
	 * @param distinct
	 * @return
	 */
	public CriteriaQuery distict(boolean distinct) {
		this.distict = distinct;
		return this;
	}

	/**
	 * @param order
	 * @return
	 */
	public CriteriaQuery select(List<Expression> selects) {
		this.selects = selects;
		return this;
	}

	/**
	 * @param expression
	 * @return
	 */
	public CriteriaQuery where(Expression expression) {
		this.where = expression;
		return this;
	}

	/**
	 * @param order
	 * @return
	 */
	public CriteriaQuery groupBy(List<Expression> expressions) {
		groupBys = expressions;
		return this;
	}

	/**
	 * @param expression
	 * @return
	 */
	public CriteriaQuery having(Expression expression) {
		this.where = expression;
		return this;
	}

	/**
	 * @param order
	 * @return
	 */
	public CriteriaQuery orderBy(List<Order> orders) {
		this.orders = orders;
		return this;
	}

	/**
	 * @return
	 */
	public String toJPQL() {
		StringBuffer buffer = new StringBuffer(toJPQLSubquery());
		if (!orders.isEmpty()) {
			buffer.append(" ORDER BY ");
			for (int i = 0; i < orders.size(); i++) {
				if (i > 0)
					buffer.append(", ");
				buffer.append(orders.get(i).toJPQL());
			}
		}
		return buffer.toString().trim();
	}

	/**
	 * @return
	 */
	public String toJPQLSubquery() {
		StringBuffer buffer = new StringBuffer("SELECT ");
		if (distict)
			buffer.append(" DISTINCT");
		if (root != null)
			buffer.append(root.getAlias());
		for (int i = 0; i < selects.size(); i++) {
			if (i > 0 || root != null)
				buffer.append(", ");
			buffer.append(selects.get(i).toJPQL());
		}
		buffer.append(" FROM ");
		if (root != null)
			buffer.append(root.toJPQL());
		for (int i = 0; i < froms.size(); i++) {
			if (i > 0 || root != null)
				buffer.append(", ");
			buffer.append(froms.get(i).toJPQL());
		}
		if (where != null) {
			buffer.append(" WHERE " + where.toJPQL());
		}
		if (!groupBys.isEmpty()) {
			buffer.append(" GROUP BY ");
			for (int i = 0; i < groupBys.size(); i++) {
				if (i > 0)
					buffer.append(", ");
				buffer.append(groupBys.get(i).toJPQL());
			}
		}
		if (having != null) {
			buffer.append(" HAVING " + having.toJPQL());
		}
		return buffer.toString().trim();
	}

	/**
	 * @return
	 */
	public String toJPQLCount() {
		if (root == null)
			return null;
		StringBuffer buffer = new StringBuffer();
		if (selects.size() > 0) {
			buffer.append("SELECT COUNT(countObj) FROM " + root.getName() + " countObj");
			buffer.append(" WHERE EXISTS (" + toJPQLSubquery() + ")");
		} else {
			buffer.append("SELECT COUNT(" + root.getAlias() + ") FROM " + root.toJPQL());
			if (where != null) {
				buffer.append(" WHERE " + where.toJPQL());
			}
			if (!groupBys.isEmpty()) {
				buffer.append(" GROUP BY ");
				for (int i = 0; i < groupBys.size(); i++) {
					if (i > 0)
						buffer.append(", ");
					buffer.append(groupBys.get(i).toJPQL());
				}
			}
			if (having != null) {
				buffer.append(" HAVING " + having.toJPQL());
			}
		}
		return buffer.toString().trim();
	}
}
