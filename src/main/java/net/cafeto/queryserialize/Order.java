/**
 * 
 */
package net.cafeto.queryserialize;

import java.io.Serializable;

/**
 * @author fospitia
 *
 */
public class Order implements Serializable {

	private static final long serialVersionUID = -5069070011656095153L;

	private Expression expression;
	private boolean ascending = true;

	/**
	 * @param property
	 * @param ascending
	 */
	public Order() {
	}

	/**
	 * @return
	 */
	public Expression getExpression() {
		return expression;
	}

	/**
	 * @param property
	 */
	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	/**
	 * @return
	 */
	public boolean isAscending() {
		return ascending;
	}

	/**
	 * @param ascending
	 */
	public void setAscending(boolean ascending) {
		this.ascending = ascending;
	}

	/**
	 * @return
	 */
	public String toJPQL() {
		StringBuffer buffer = new StringBuffer(expression.toJPQL());
		if (ascending)
			buffer.append(" ASC");
		else
			buffer.append(" DESC");
		return buffer.toString().trim();
	}
}
