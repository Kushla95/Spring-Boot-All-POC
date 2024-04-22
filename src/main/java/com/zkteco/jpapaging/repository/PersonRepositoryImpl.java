package com.zkteco.jpapaging.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Repository;

import com.zkteco.jpapaging.model.Person;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;


@Repository
@Transactional
public class PersonRepositoryImpl implements PersonRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Iterable<Person> findAll(Sort sort) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> cq = cb.createQuery(Person.class);
        Root<Person> root = cq.from(Person.class);
        if (sort != null) {
            cq.orderBy(QueryUtils.toOrders(sort, root, cb));
        }
        TypedQuery<Person> query = entityManager.createQuery(cq);
        return query.getResultList();
	}

	@Override
	public Page<Person> findAll(Pageable pageable) {
		 CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
	        countQuery.select(cb.count(countQuery.from(Person.class)));
	        Long total = entityManager.createQuery(countQuery).getSingleResult();

	        CriteriaQuery<Person> dataQuery = cb.createQuery(Person.class);
	        Root<Person> root = dataQuery.from(Person.class);
	        dataQuery.select(root);
	        if (pageable.getSort() != null) {
	            dataQuery.orderBy(QueryUtils.toOrders(pageable.getSort(), root, cb));
	        }

	        TypedQuery<Person> typedQuery = entityManager.createQuery(dataQuery);
	        typedQuery.setFirstResult((int) pageable.getOffset());
	        typedQuery.setMaxResults(pageable.getPageSize());

	        List<Person> content = typedQuery.getResultList();

	        return new PageImpl<>(content, pageable, total);
	}
	
	

	@Override
	public <S extends Person> List<S> saveAll(List<Person> people) {
		List<S> savedPeople = new ArrayList<>();
        for (Person person : people) {
            entityManager.persist(person);
            savedPeople.add((S) person); // Cast each saved person to the generic type S
        }
        return savedPeople;
	}

}
