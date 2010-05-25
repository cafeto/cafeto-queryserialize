/**
 * 
 */
package net.cafeto.queryserialize;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author fospitia
 * 
 */
public class Expression implements Serializable {

	private static final long serialVersionUID = -8581378668443176744L;

	private boolean negated = false;
	private String logical;
	private String operator;
	private String function;
	private String trim;
	private List<Expression> values = new ArrayList<Expression>();
	private String path;
	private String parameter;
	private Object literal;
	private CriteriaQuery subquery;
	private String alias;

	/**
	 * 
	 */
	public Expression() {
	}

	/**
	 * @return the negated
	 */
	public boolean isNegated() {
		return negated;
	}

	/**
	 * @param negated
	 *            the negated to set
	 */
	public void setNegated(boolean negated) {
		this.negated = negated;
	}

	/**
	 * @return the logical
	 */
	public String getLogical() {
		return logical;
	}

	/**
	 * @param logical
	 *            the logical to set
	 */
	public void setLogical(String logical) {
		this.logical = logical;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the parameter
	 */
	public String getParameter() {
		return parameter;
	}

	/**
	 * @param parameter
	 *            the parameter to set
	 */
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	/**
	 * @return the literal
	 */
	public Object getLiteral() {
		return literal;
	}

	/**
	 * @param literal
	 *            the literal to set
	 */
	public void setLiteral(Object literal) {
		this.literal = literal;
	}

	/**
	 * @return the subquery
	 */
	public CriteriaQuery getSubquery() {
		return subquery;
	}

	/**
	 * @param subquery
	 *            the subquery to set
	 */
	public void setSubquery(CriteriaQuery subquery) {
		this.subquery = subquery;
	}

	/**
	 * @return the operator
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * @param operator
	 *            the operator to set
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * @return the trim
	 */
	public String getTrim() {
		return trim;
	}

	/**
	 * @param trim
	 *            the trim to set
	 */
	public void setTrim(String trim) {
		this.trim = trim;
	}

	/**
	 * @return the values
	 */
	public List<Expression> getValues() {
		return values;
	}

	/**
	 * @param values
	 *            the values to set
	 */
	public void setValues(List<Expression> values) {
		this.values = values;
	}

	/**
	 * @return the function
	 */
	public String getFunction() {
		return function;
	}

	/**
	 * @param function
	 *            the function to set
	 */
	public void setFunction(String function) {
		this.function = function;
	}

	/**
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * @param alias
	 *            the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * @param alias
	 * @return
	 */
	public Expression alias(String alias) {
		this.alias = alias;
		return this;
	}

	/**
	 * @return
	 */
	public String toJPQL() {
		StringBuffer buffer = new StringBuffer();
		int size = values.size();
		if (negated)
			buffer.append(" NOT (");

		if (logical != null) {
			if (size == 1)
				buffer.append(" (");
			for (int i = 0; i < size; i++) {
				buffer.append(" " + values.get(i).toJPQL());
				if (i != size - 1) {
					buffer.append(" " + logical);
				}
			}
			if (size == 1)
				buffer.append(")");
		} else if (function != null) {
			if ("COUNT".equals(function)) {
				buffer.append(" COUNT(" + values.get(0).toJPQL() + ")");
			} else if ("COUNT_DISTINCT".equals(function)) {
				buffer.append(" COUNT(DISTINCT " + values.get(0).toJPQL() + ")");
			} else if ("MIN".equals(function)) {
				buffer.append(" MIN(" + values.get(0).toJPQL() + ")");
			} else if ("MAX".equals(function)) {
				buffer.append(" MAX(" + values.get(0).toJPQL() + ")");
			} else if ("AVG".equals(function)) {
				buffer.append(" AVG(" + values.get(0).toJPQL() + ")");
			} else if ("SUM".equals(function)) {
				buffer.append(" SUM(" + values.get(0).toJPQL() + ")");
			} else if ("CONCAT".equals(function)) {
				buffer.append(" CONCAT(" + values.get(0).toJPQL() + "," + values.get(1).toJPQL() + ")");
			} else if ("SUBSTRING".equals(function)) {
				buffer.append(" SUBSTRING(");
				for (int i = 0; i < values.size(); i++) {
					buffer.append(values.get(i).toJPQL());
				}
				buffer.append(")");
			} else if ("TRIM".equals(function)) {
				buffer.append(" TRIM(" + trim + "," + values.get(0).toJPQL() + "," + values.get(1).toJPQL()
						+ ")");
			} else if ("LOWER".equals(function)) {
				buffer.append(" LOWER(" + values.get(0).toJPQL() + ")");
			} else if ("UPPER".equals(function)) {
				buffer.append(" UPPER(" + values.get(0).toJPQL() + ")");
			} else if ("LENGTH".equals(function)) {
				buffer.append(" LENGTH(" + values.get(0).toJPQL() + ")");
			} else if ("LOCATE".equals(function)) {
				buffer.append(" LOCATE(");
			} else if ("ABS".equals(function)) {
				buffer.append(" ABS(" + values.get(0).toJPQL() + ")");
			} else if ("SQRT".equals(function)) {
				buffer.append(" SQRT(" + values.get(0).toJPQL() + ")");
			} else if ("MOD".equals(function)) {
				buffer.append(" MOD(" + values.get(0).toJPQL() + ")");
			} else if ("SIZE".equals(function)) {
				buffer.append(" SIZE(" + values.get(0).toJPQL() + ")");
			} else if ("CURRENT_DATE".equals(function)) {
				buffer.append(" CURRENT_DATE");
			} else if ("CURRENT_TIME".equals(function)) {
				buffer.append(" CURRENT_TIME");
			} else if ("CURRENT_TIMESTAMP".equals(function)) {
				buffer.append(" CURRENT_TIMESTAMP");
			}
		} else if (operator != null) {
			if ("SUM".equals(operator)) {
				buffer.append(values.get(0).toJPQL());
				buffer.append(" + ");
				buffer.append(values.get(1).toJPQL());
			} else if ("DIFF".equals(operator)) {
				buffer.append(values.get(0).toJPQL());
				buffer.append(" - ");
				buffer.append(values.get(1).toJPQL());
			} else if ("PROD".equals(operator)) {
				buffer.append(values.get(0).toJPQL());
				buffer.append(" * ");
				buffer.append(values.get(1).toJPQL());
			} else if ("QUOT".equals(operator)) {
				buffer.append(values.get(0).toJPQL());
				buffer.append(" / ");
				buffer.append(values.get(1).toJPQL());
			} else if ("EQ".equals(operator)) {
				buffer.append(values.get(0).toJPQL());
				buffer.append(" = ");
				buffer.append(values.get(1).toJPQL());
			} else if ("NE".equals(operator)) {
				buffer.append(values.get(0).toJPQL());
				buffer.append(" != ");
				buffer.append(values.get(1).toJPQL());
			} else if ("GT".equals(operator)) {
				buffer.append(values.get(0).toJPQL());
				buffer.append(" > ");
				buffer.append(values.get(1).toJPQL());
			} else if ("LT".equals(operator)) {
				buffer.append(values.get(0).toJPQL());
				buffer.append(" < ");
				buffer.append(values.get(1).toJPQL());
			} else if ("GE".equals(operator)) {
				buffer.append(values.get(0).toJPQL());
				buffer.append(" >= ");
				buffer.append(values.get(1).toJPQL());
			} else if ("LE".equals(operator)) {
				buffer.append(values.get(0).toJPQL());
				buffer.append(" <= ");
				buffer.append(values.get(1).toJPQL());
			} else if ("LIKE".equals(operator)) {
				buffer.append(values.get(0).toJPQL());
				buffer.append(" LIKE ");
				buffer.append(values.get(1).toJPQL());
			} else if ("NOT_LIKE".equals(operator)) {
				buffer.append(values.get(0).toJPQL());
				buffer.append(" NOT LIKE ");
				buffer.append(values.get(1).toJPQL());
			} else if ("BETWEEN".equals(operator)) {
				buffer.append(values.get(0).toJPQL());
				buffer.append(" BETWEEN ");
				buffer.append(values.get(1).toJPQL());
				buffer.append(" AND ");
				buffer.append(values.get(2).toJPQL());
			} else if ("NOT_BETWEEN".equals(operator)) {
				buffer.append(values.get(0).toJPQL());
				buffer.append(" NOT BETWEEN ");
				buffer.append(values.get(1).toJPQL());
				buffer.append(" AND ");
				buffer.append(values.get(2).toJPQL());
			} else if ("IS_NULL".equals(operator)) {
				buffer.append(values.get(0).toJPQL());
				buffer.append(" IS NULL ");
			} else if ("IS_NOT_NULL".equals(operator)) {
				buffer.append(values.get(0).toJPQL());
				buffer.append(" IS NOT NULL ");
			} else if ("IS_EMPTY".equals(operator)) {
				buffer.append(values.get(0).toJPQL());
				buffer.append(" IS EMPTY ");
			} else if ("IS_NOT_EMPTY".equals(operator)) {
				buffer.append(values.get(0).toJPQL());
				buffer.append(" IS NOT EMPTY ");
			} else if ("MEMBER".equals(operator)) {
				buffer.append(values.get(0).toJPQL());
				buffer.append(" MEMBER OF ");
				buffer.append(values.get(1).toJPQL());
			} else if ("NOT_MEMBER".equals(operator)) {
				buffer.append(values.get(0).toJPQL());
				buffer.append(" NOT MEMBER OF ");
				buffer.append(values.get(1).toJPQL());
			} else if ("IS_TRUE".equals(operator)) {
				buffer.append(values.get(0).toJPQL());
				buffer.append(" IS TRUE");
			} else if ("IS_NOT_TRUE".equals(operator)) {
				buffer.append(values.get(0).toJPQL());
				buffer.append(" IS NOT TRUE");
			} else if ("IS_FALSE".equals(operator)) {
				buffer.append(values.get(0).toJPQL());
				buffer.append(" IS FALSE");
			} else if ("IS_NOT_FALSE".equals(operator)) {
				buffer.append(values.get(0).toJPQL());
				buffer.append(" IS NOT FALSE");
			} else if ("IN".equals(operator)) {
				buffer.append(values.get(0).toJPQL());
				buffer.append(" IN (");
				for (int i = 0; i < values.size(); i++) {
					if (i > 0)
						buffer.append(", ");
					buffer.append(values.get(1).toJPQL());
				}
				buffer.append(")");
			} else if ("NOT_IN".equals(operator)) {
				buffer.append(values.get(0).toJPQL());
				buffer.append(" NOT IN (");
				for (int i = 0; i < values.size(); i++) {
					if (i > 0)
						buffer.append(", ");
					buffer.append(values.get(1).toJPQL());
				}
				buffer.append(")");
			} else if ("EXISTS".equals(operator)) {
				buffer.append(" EXISTS");
				buffer.append(values.get(0).toJPQL());
			} else if ("NOT_EXISTS".equals(operator)) {
				buffer.append(" NOT EXISTS");
				buffer.append(values.get(0).toJPQL());
			} else if ("ALL".equals(operator)) {
				buffer.append(" ALL");
				buffer.append(values.get(0).toJPQL());
			} else if ("SOME".equals(operator)) {
				buffer.append(" SOME");
				buffer.append(values.get(0).toJPQL());
			} else if ("ANY".equals(operator)) {
				buffer.append(" ANY");
				buffer.append(values.get(0).toJPQL());
			}
		} else if (path != null) {
			buffer.append(" " + path);
		} else if (parameter != null) {
			buffer.append(" :" + parameter);
		} else if (literal != null) {
			if (literal instanceof String || literal instanceof Character)
				buffer.append("'" + literal.toString() + "'");
			else if (literal instanceof Date)
				buffer.append("'" + literal.toString() + "'");
			else
				buffer.append(" " + literal.toString());
		} else if (subquery != null) {
			buffer.append(" (" + subquery.toJPQL() + ")");
		}
		if (negated) {
			buffer.append(")");
		}
		if (alias != null) {
			buffer.append(" AS " + alias);
		}

		return buffer.toString().trim();
	}
}
