package com.laboratory.infrastructure.sequence;


import com.google.common.base.Preconditions;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



@Repository
@Transactional(propagation = Propagation.REQUIRES_NEW)
class JpaSequenceGenerator implements SequenceGenerator {

	@PersistenceContext
	@Qualifier(value = "entityManagerFactory")
	private EntityManager entityManager;

	@Override
	public long next(String name) {
		Preconditions.checkNotNull(name);

		TypedQuery<Sequence> query = entityManager.createQuery(
				"SELECT s FROM Sequence s WHERE  s.name = :name", Sequence.class);
		query.setParameter("name", name);
		Sequence sequence = query.getSingleResult();

		entityManager.refresh(sequence, LockModeType.PESSIMISTIC_WRITE);
		long next = sequence.next();
		entityManager.flush();

		return next;
	}
}
