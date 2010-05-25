/**
 * 
 */
package net.cafeto.queryserialize;

/**
 * @author fospitia
 * @param <T>
 *
 */
public class CriteriaBuilder {

	public static enum TrimType {
		LEADING, TRAILING, BOTH
	}
	
	/**
	 * 
	 */
	public CriteriaBuilder() {
	}

	/**
	 * @param expr0
	 * @param expr1
	 * @return
	 */
	public Expression and(Expression expr0, Expression expr1) {
		if (expr0.getLogical() != null && expr0.getValues().isEmpty()) {
			expr0.getValues().add(expr1);
			return expr0;
		}
		if (expr1.getLogical() != null && expr1.getValues().isEmpty()) {
			expr1.getValues().add(expr0);
			return expr1;
		}

		Expression obj = new Expression();
		obj.setLogical("AND");
		obj.getValues().add(expr0);
		obj.getValues().add(expr1);
		return obj;
	}

	/**
	 * @param expr0
	 * @param expr1
	 * @return
	 */
	public Expression or(Expression expr0, Expression expr1) {
		if (expr0.getLogical() != null && expr0.getValues().isEmpty()) {
			expr0.getValues().add(expr1);
			return expr0;
		}
		if (expr1.getLogical() != null && expr1.getValues().isEmpty()) {
			expr1.getValues().add(expr0);
			return expr1;
		}

		Expression obj = new Expression();
		obj.setLogical("OR");
		obj.getValues().add(expr0);
		obj.getValues().add(expr1);
		return obj;
	}

	/**
	 * @return
	 */
	public Expression disjunction() {
		Expression obj = new Expression();
		obj.setLogical("OR");
		return obj;
	}

	/**
	 * @return
	 */
	public Expression conjunction() {
		Expression obj = new Expression();
		obj.setLogical("AND");
		return obj;
	}

	/**
	 * @param expr
	 * @return
	 */
	public Expression not(Expression expr) {
		expr.setNegated(true);
		return expr;
	}

	/**
	 * @param expr0
	 * @param expr1
	 * @return
	 */
	public Expression sum(Expression expr0, Expression expr1) {
		Expression expression = new Expression();
		expression.setOperator("SUM");
		expression.getValues().add(expr0);
		expression.getValues().add(expr1);
		return expression;
	}

	/**
	 * @param expr0
	 * @param expr1
	 * @return
	 */
	public Expression diff(Expression expr0, Expression expr1) {
		Expression expression = new Expression();
		expression.setOperator("DIFF");
		expression.getValues().add(expr0);
		expression.getValues().add(expr1);
		return expression;
	}

	/**
	 * @param expr0
	 * @param expr1
	 * @return
	 */
	public Expression prod(Expression expr0, Expression expr1) {
		Expression expression = new Expression();
		expression.setOperator("PROD");
		expression.getValues().add(expr0);
		expression.getValues().add(expr1);
		return expression;
	}

	/**
	 * @param expr0
	 * @param expr1
	 * @return
	 */
	public Expression qout(Expression expr0, Expression expr1) {
		Expression expression = new Expression();
		expression.setOperator("QUOT");
		expression.getValues().add(expr0);
		expression.getValues().add(expr1);
		return expression;
	}

	/**
	 * @param expr0
	 * @param expr1
	 * @return
	 */
	public Expression eq(Expression expr0, Expression expr1) {
		Expression expression = new Expression();
		expression.setOperator("EQ");
		expression.getValues().add(expr0);
		expression.getValues().add(expr1);
		return expression;
	}

	/**
	 * @param expr0
	 * @param expr1
	 * @return
	 */
	public Expression ne(Expression expr0, Expression expr1) {
		Expression expression = new Expression();
		expression.setOperator("NE");
		expression.getValues().add(expr0);
		expression.getValues().add(expr1);
		return expression;
	}

	/**
	 * @param expr0
	 * @param expr1
	 * @return
	 */
	public Expression gt(Expression expr0, Expression expr1) {
		Expression expression = new Expression();
		expression.setOperator("GT");
		expression.getValues().add(expr0);
		expression.getValues().add(expr1);
		return expression;
	}

	/**
	 * @param expr0
	 * @param expr1
	 * @return
	 */
	public Expression lt(Expression expr0, Expression expr1) {
		Expression expression = new Expression();
		expression.setOperator("LT");
		expression.getValues().add(expr0);
		expression.getValues().add(expr1);
		return expression;
	}

	/**
	 * @param expr0
	 * @param expr1
	 * @return
	 */
	public Expression ge(Expression expr0, Expression expr1) {
		Expression expression = new Expression();
		expression.setOperator("GE");
		expression.getValues().add(expr0);
		expression.getValues().add(expr1);
		return expression;
	}

	/**
	 * @param expr0
	 * @param expr1
	 * @return
	 */
	public Expression le(Expression expr0, Expression expr1) {
		Expression expression = new Expression();
		expression.setOperator("LE");
		expression.getValues().add(expr0);
		expression.getValues().add(expr1);
		return expression;
	}

	/**
	 * @param expr0
	 * @param expr1
	 * @return
	 */
	public Expression like(Expression expr0, Expression expr1) {
		Expression expression = new Expression();
		expression.setOperator("LIKE");
		expression.getValues().add(expr0);
		expression.getValues().add(expr1);
		return expression;
	}

	/**
	 * @param expr0
	 * @param expr1
	 * @return
	 */
	public Expression notLike(Expression expr0, Expression expr1) {
		Expression expression = new Expression();
		expression.setOperator("NOT_LIKE");
		expression.getValues().add(expr0);
		expression.getValues().add(expr1);
		return expression;
	}

	/**
	 * @param expr0
	 * @param expr1
	 * @param expr2
	 * @return
	 */
	public Expression between(Expression expr0, Expression expr1, Expression expr2) {
		Expression expression = new Expression();
		expression.setOperator("BETWEEN");
		expression.getValues().add(expr0);
		expression.getValues().add(expr1);
		expression.getValues().add(expr2);
		return expression;
	}

	/**
	 * @param expr0
	 * @param expr1
	 * @param expr2
	 * @return
	 */
	public Expression notBetween(Expression expr0, Expression expr1, Expression expr2) {
		Expression expression = new Expression();
		expression.setOperator("NOT_BETWEEN");
		expression.getValues().add(expr0);
		expression.getValues().add(expr1);
		expression.getValues().add(expr2);
		return expression;
	}

	/**
	 * @param expr0
	 * @return
	 */
	public Expression isNull(Expression expr0) {
		Expression expression = new Expression();
		expression.setOperator("IS_NULL");
		expression.getValues().add(expr0);
		return expression;
	}

	/**
	 * @param expr0
	 * @return
	 */
	public Expression isNotNull(Expression expr0) {
		Expression expression = new Expression();
		expression.setOperator("IS_NOT_NULL");
		expression.getValues().add(expr0);
		return expression;
	}

	/**
	 * @param expr0
	 * @return
	 */
	public Expression isEmpty(Expression expr0) {
		Expression expression = new Expression();
		expression.setOperator("IS_EMPTY");
		expression.getValues().add(expr0);
		return expression;
	}

	/**
	 * @param expr0
	 * @return
	 */
	public Expression isNotEmpty(Expression expr0) {
		Expression expression = new Expression();
		expression.setOperator("IS_NOT_EMPTY");
		expression.getValues().add(expr0);
		return expression;
	}

	/**
	 * @param expr0
	 * @param expr1
	 * @return
	 */
	public Expression isMember(Expression expr0, Expression expr1) {
		Expression expression = new Expression();
		expression.setOperator("MEMBER");
		expression.getValues().add(expr0);
		expression.getValues().add(expr1);
		return expression;
	}

	/**
	 * @param expr0
	 * @param expr1
	 * @return
	 */
	public Expression isNotMember(Expression expr0, Expression expr1) {
		Expression expression = new Expression();
		expression.setOperator("NOT_MEMBER");
		expression.getValues().add(expr0);
		expression.getValues().add(expr1);
		return expression;
	}

	/**
	 * @param expr0
	 * @return
	 */
	public Expression isTrue(Expression expr0) {
		Expression expression = new Expression();
		expression.setOperator("IS_TRUE");
		expression.getValues().add(expr0);
		return expression;
	}

	/**
	 * @param expr0
	 * @return
	 */
	public Expression isNotTrue(Expression expr0) {
		Expression expression = new Expression();
		expression.setOperator("IS_NOT_TRUE");
		expression.getValues().add(expr0);
		return expression;
	}

	/**
	 * @param expr0
	 * @return
	 */
	public Expression isFalse(Expression expr0) {
		Expression expression = new Expression();
		expression.setOperator("IS_FALSE");
		expression.getValues().add(expr0);
		return expression;
	}

	/**
	 * @param expr0
	 * @return
	 */
	public Expression isNotFalse(Expression expr0) {
		Expression expression = new Expression();
		expression.setOperator("IS_NOT_FALSE");
		expression.getValues().add(expr0);
		return expression;
	}
	/**
	 * @param expr0
	 * @return
	 */
	public Expression in(Expression... expr0) {
		Expression expression = new Expression();
		expression.setOperator("IN");
		for (Expression expr : expr0) {
			expression.getValues().add(expr);
		}
		return expression;
	}

	/**
	 * @param criteria
	 * @return
	 */
	public Expression in(CriteriaQuery criteria) {
		Expression expression = new Expression();
		expression.setOperator("IN");
		expression.setSubquery(criteria);
		return expression;
	}

	/**
	 * @param criteria
	 * @return
	 */
	public Expression notIn(CriteriaQuery criteria) {
		Expression expression = new Expression();
		expression.setOperator("NOT_IN");
		expression.setSubquery(criteria);
		return expression;
	}

	/**
	 * @param criteria
	 * @return
	 */
	public Expression exists(CriteriaQuery criteria) {
		Expression expression = new Expression();
		expression.setOperator("EXISTS");
		expression.setSubquery(criteria);
		return expression;
	}

	/**
	 * @param criteria
	 * @return
	 */
	public Expression notExists(CriteriaQuery criteria) {
		Expression expression = new Expression();
		expression.setOperator("NOT_EXISTS");
		expression.setSubquery(criteria);
		return expression;
	}

	/**
	 * @param criteria
	 * @return
	 */
	public Expression all(CriteriaQuery criteria) {
		Expression expression = new Expression();
		expression.setOperator("ALL");
		expression.setSubquery(criteria);
		return expression;
	}

	/**
	 * @param criteria
	 * @return
	 */
	public Expression any(CriteriaQuery criteria) {
		Expression expression = new Expression();
		expression.setOperator("ANY");
		expression.setSubquery(criteria);
		return expression;
	}

	/**
	 * @param criteria
	 * @return
	 */
	public Expression some(CriteriaQuery criteria) {
		Expression expression = new Expression();
		expression.setOperator("SOME");
		expression.setSubquery(criteria);
		return expression;
	}

	/**
	 * @param expr
	 * @return
	 */
	public Expression count(Expression expr) {
		Expression expression = new Expression();
		expression.setFunction("COUNT");
		expression.getValues().add(expr);
		return expression;
	}

	/**
	 * @param expr
	 * @return
	 */
	public Expression countDistinct(Expression expr) {
		Expression expression = new Expression();
		expression.setFunction("COUNT_DISTINCT");
		expression.getValues().add(expr);
		return expression;
	}

	/**
	 * @param expr
	 * @return
	 */
	public Expression max(Expression expr) {
		Expression expression = new Expression();
		expression.setFunction("MAX");
		expression.getValues().add(expr);
		return expression;
	}

	/**
	 * @param expr
	 * @return
	 */
	public Expression min(Expression expr) {
		Expression expression = new Expression();
		expression.setFunction("MIN");
		expression.getValues().add(expr);
		return expression;
	}

	/**
	 * @param expr
	 * @return
	 */
	public Expression avg(Expression expr) {
		Expression expression = new Expression();
		expression.setFunction("AVG");
		expression.getValues().add(expr);
		return expression;
	}

	/**
	 * @param expr
	 * @return
	 */
	public Expression sum(Expression expr) {
		Expression expression = new Expression();
		expression.setFunction("SUM");
		expression.getValues().add(expr);
		return expression;
	}

	/**
	 * @param expr0
	 * @param expr1
	 * @return
	 */
	public Expression concat(Expression expr0, Expression expr1) {
		Expression expression = new Expression();
		expression.setFunction("CONCAT");
		expression.getValues().add(expr0);
		expression.getValues().add(expr1);
		return expression;
	}

	/**
	 * @param expr0
	 * @param expr1
	 * @param expr2
	 * @return
	 */
	public Expression substring(Expression expr0, Expression expr1, Expression expr2) {
		Expression expression = new Expression();
		expression.setFunction("SUBSTRING");
		expression.getValues().add(expr0);
		expression.getValues().add(expr1);
		expression.getValues().add(expr2);
		return expression;
	}

	/**
	 * @param type
	 * @param expr0
	 * @param expr1
	 * @return
	 */
	public Expression trim(TrimType type, Expression expr0, Expression expr1) {
		Expression expression = new Expression();
		expression.setFunction("TRIM");
		expression.setTrim(type.name());
		expression.getValues().add(expr0);
		expression.getValues().add(expr1);
		return expression;
	}

	/**
	 * @param type
	 * @param expr
	 * @return
	 */
	public Expression trim(TrimType type, Expression expr) {
		Expression expression = new Expression();
		expression.setFunction("TRIM");
		expression.setTrim(type.name());
		expression.getValues().add(this.literal(Character.valueOf(' ')));
		expression.getValues().add(expr);
		return expression;
	}

	/**
	 * @param expr0
	 * @param expr1
	 * @return
	 */
	public Expression trim(Expression expr0, Expression expr1) {
		Expression expression = new Expression();
		expression.setFunction("TRIM");
		expression.setTrim(TrimType.BOTH.name());
		expression.getValues().add(expr0);
		expression.getValues().add(expr1);
		return expression;
	}

	/**
	 * @param expr
	 * @return
	 */
	public Expression trim(Expression expr) {
		Expression expression = new Expression();
		expression.setFunction("TRIM");
		expression.setTrim(TrimType.BOTH.name());
		expression.getValues().add(this.literal(Character.valueOf(' ')));
		expression.getValues().add(expr);
		return expression;
	}

	/**
	 * @param expr
	 * @return
	 */
	public Expression lower(Expression expr) {
		Expression expression = new Expression();
		expression.setFunction("LOWER");
		expression.getValues().add(expr);
		return expression;
	}

	/**
	 * @param expr
	 * @return
	 */
	public Expression upper(Expression expr) {
		Expression expression = new Expression();
		expression.setFunction("UPPER");
		expression.getValues().add(expr);
		return expression;
	}

	/**
	 * @param expr
	 * @return
	 */
	public Expression length(Expression expr) {
		Expression expression = new Expression();
		expression.setFunction("LENGTH");
		expression.getValues().add(expr);
		return expression;
	}

	/**
	 * @param expr0
	 * @param expr1
	 * @return
	 */
	public Expression locate(Expression expr0, Expression expr1) {
		Expression expression = new Expression();
		expression.setFunction("LOCATE");
		expression.getValues().add(expr0);
		expression.getValues().add(expr1);
		return expression;
	}

	/**
	 * @param expr0
	 * @param expr1
	 * @param expr2
	 * @return
	 */
	public Expression locate(Expression expr0, Expression expr1, Expression expr2) {
		Expression expression = new Expression();
		expression.setFunction("LOCATE");
		expression.getValues().add(expr0);
		expression.getValues().add(expr1);
		expression.getValues().add(expr2);
		return expression;
	}

	/**
	 * @param expr
	 * @return
	 */
	public Expression abs(Expression expr) {
		Expression expression = new Expression();
		expression.setFunction("ABS");
		expression.getValues().add(expr);
		return expression;
	}

	/**
	 * @param expr
	 * @return
	 */
	public Expression sqrt(Expression expr) {
		Expression expression = new Expression();
		expression.setFunction("SQRT");
		expression.getValues().add(expr);
		return expression;
	}

	/**
	 * @param expr0
	 * @param expr1
	 * @return
	 */
	public Expression mod(Expression expr0, Expression expr1) {
		Expression expression = new Expression();
		expression.setFunction("MOD");
		expression.getValues().add(expr0);
		expression.getValues().add(expr1);
		return expression;
	}

	/**
	 * @param expr
	 * @return
	 */
	public Expression size(Expression expr) {
		Expression expression = new Expression();
		expression.setFunction("SIZE");
		expression.getValues().add(expr);
		return expression;
	}

	/**
	 * @return
	 */
	public Expression currentDate() {
		Expression expression = new Expression();
		expression.setFunction("CURRENT_DATE");
		return expression;
	}

	/**
	 * @return
	 */
	public Expression currentTime() {
		Expression expression = new Expression();
		expression.setFunction("CURRENT_TIME");
		return expression;
	}

	/**
	 * @return
	 */
	public Expression currentTimestamp() {
		Expression expression = new Expression();
		expression.setFunction("CURRENT_TIMESTAMP");
		return expression;
	}

	/**
	 * @param value
	 * @return
	 */
	public Expression literal(Object literal) {
		Expression expression = new Expression();
		expression.setLiteral(literal);
		return expression;
	}

	/**
	 * @param path
	 * @return
	 */
	public Expression path(String path) {
		Expression expression = new Expression();
		expression.setPath(path);
		return expression;
	}

	/**
	 * @param parameter
	 * @return
	 */
	public Expression parameter(String parameter) {
		Expression expression = new Expression();
		expression.setParameter(parameter);
		return expression;
	}

	/**
	 * @param expression
	 * @return
	 */
	public Order asc(Expression expression) {
		Order order = new Order();
		order.setExpression(expression);
		order.setAscending(true);
		return order;
	}

	/**
	 * @param expression
	 * @return
	 */
	public Order desc(Expression expression) {
		Order order = new Order();
		order.setExpression(expression);
		order.setAscending(false);
		return order;
	}

	/**
	 * @return
	 */
	public CriteriaQuery createQuery() {
		CriteriaQuery query = new CriteriaQuery();
		return query;
	}

	/**
	 * @param domain
	 * @return
	 */
	public CriteriaQuery createQuery(Domain root) {
		CriteriaQuery query = new CriteriaQuery();
		query.setRoot(root);
		return query;
	}

	/**
	 * @param name
	 * @param alias
	 * @return
	 */
	public Domain domain(String name, String alias) {
		Domain domain = new Domain();
		domain.setName(name);
		domain.setAlias(alias);
		return domain;
	}

	/**
	 * @param clazz
	 * @param alias
	 * @return
	 */
	public Domain domain(Class<?> clazz, String alias) {
		Domain domain = new Domain();
		domain.setName(clazz.getName());
		domain.setAlias(alias);
		return domain;
	}
	
	/**
	 * @param path
	 * @param alias
	 * @param collection
	 * @return
	 */
	public Domain domainCollection(String path, String alias) {
		Domain domain = new Domain();
		domain.setName(path);
		domain.setAlias(alias);
		domain.setCollection(true);
		return domain;
	}

	/**
	 * @param path
	 * @param alias
	 * @return
	 */
	public Join inner(String path, String alias) {
		Join join = new Join();
		join.setPath(path);
		join.setAlias(alias);
		return join;
	}

	/**
	 * @param path
	 * @param alias
	 * @return
	 */
	public Join left(String path, String alias) {
		Join join = new Join();
		join.setPath(path);
		join.setAlias(alias);
		join.setType("LEFT");
		return join;
	}

	/**
	 * @param path
	 * @return
	 */
	public Join fetch(String path) {
		Join join = new Join();
		join.setFetch(true);
		join.setPath(path);
		return join;
	}

	/**
	 * @param path
	 * @return
	 */
	public Join leftFetch(String path) {
		Join join = new Join();
		join.setFetch(true);
		join.setPath(path);
		join.setType("LEFT");
		return join;
	}
}
