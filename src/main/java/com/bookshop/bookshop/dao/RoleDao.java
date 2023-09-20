package com.bookshop.bookshop.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookshop.bookshop.entity.Role;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class RoleDao implements RoleDaoInterface {
    
    @Autowired
	private EntityManager entityManager;

	public RoleDao(EntityManager theEntityManager) {

		entityManager = theEntityManager;
		
	}

	@Override
	public Role findRoleByName(String theRoleName) {

		TypedQuery<Role> theQuery = entityManager.createQuery("from Role where name=:roleName", Role.class);
		theQuery.setParameter("roleName", theRoleName);
		
		Role theRole = null;
		
		try {
			theRole = theQuery.getSingleResult();
		} catch (Exception e) {
			theRole = null;
		}
		
		return theRole;
	}
}
