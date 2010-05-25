/**
 * 
 */
package net.cafeto.queryserialize;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import net.cafeto.entitycopy.DeepCopy;

/**
 * @author fospitia
 *
 */
public class QuerySerialize implements Serializable {

	private static final long serialVersionUID = -6758243782217905627L;

	private CriteriaQuery criteria;

	private Integer offset = 0;
	private Integer limit = 0;

	private Map<String, Object> parameters = new HashMap<String, Object>();

	private DeepCopy deepCopy;

	/**
	 * 
	 */
	public QuerySerialize() {
	}

	/**
	 * @return the criteria
	 */
	public CriteriaQuery getCriteria() {
		return criteria;
	}

	/**
	 * @param criteria the criteria to set
	 */
	public void setCriteria(CriteriaQuery criteria) {
		this.criteria = criteria;
	}

	/**
	 * @return the offset
	 */
	public Integer getOffset() {
		return offset;
	}

	/**
	 * @param offset the offset to set
	 */
	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	/**
	 * @return the limit
	 */
	public Integer getLimit() {
		return limit;
	}

	/**
	 * @param limit the limit to set
	 */
	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	/**
	 * @return the parameters
	 */
	public Map<String, Object> getParameters() {
		return parameters;
	}

	/**
	 * @param parameters the parameters to set
	 */
	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	/**
	 * @return the deepCopy
	 */
	public DeepCopy getDeepCopy() {
		return deepCopy;
	}

	/**
	 * @param deepCopy the deepCopy to set
	 */
	public void setDeepCopy(DeepCopy deepCopy) {
		this.deepCopy = deepCopy;
	}

	/**
	 * @param entityManager
	 * @return
	 * @throws IllegalStateException
	 */
	public List<?> getResultList(EntityManager entityManager) throws IllegalStateException {
		if (criteria == null)
			throw new NullPointerException("Criteria is null");

		Query query =  entityManager.createQuery(criteria.toJPQL());
		for (Entry<String, Object> param : getParameters().entrySet()) {
			query.setParameter(param.getKey(), param.getValue());
		}
		if (offset != null && offset > 0) {
			query.setFirstResult(offset);
		}
		if (limit != null && limit > 0) {
			query.setMaxResults(limit);
		}

		return query.getResultList();
	}

	/**
	 * @param entityManager
	 * @return
	 * @throws NoResultException
	 * @throws NonUniqueResultException
	 * @throws IllegalStateException
	 */
	public Object getSingleResult(EntityManager entityManager) throws NoResultException, NonUniqueResultException, IllegalStateException { 
		if (criteria == null)
			throw new NullPointerException("Criteria is null");

		Query query =  entityManager.createQuery(criteria.toJPQL());
		for (Entry<String, Object> param : getParameters().entrySet()) {
			query.setParameter(param.getKey(), param.getValue());
		}
		if (offset != null && offset > 0) {
			query.setFirstResult(offset);
		}
		if (limit != null && limit > 0) {
			query.setMaxResults(limit);
		}

		return query.getSingleResult();
	}

	/**
	 * @param entityManager
	 * @return
	 * @throws NoResultException
	 * @throws NonUniqueResultException
	 * @throws IllegalStateException
	 */
	public Long getCountResult(EntityManager entityManager) throws NoResultException, NonUniqueResultException, IllegalStateException { 
		if (criteria == null)
			throw new NullPointerException("Criteria is null");

		Query query =  entityManager.createQuery(criteria.toJPQLCount());
		for (Entry<String, Object> param : getParameters().entrySet()) {
			query.setParameter(param.getKey(), param.getValue());
		}

		return (Long) query.getSingleResult();
	}

	/**
	 * @param name
	 * @param value
	 */
	public void addParameter(String name, Object value) {
		parameters.put(name, value);
	}
}
