package com.hampcode.model.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.hampcode.model.entity.Supplier;

@Named
public class SupplierRepository implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName="pwPU")
	private EntityManager em;
	
	public List<Supplier> findAll() throws Exception{
		List<Supplier> suppliers=new ArrayList<>();
		
		TypedQuery<Supplier> query=em.createQuery("FROM Supplier s"
				,Supplier.class);
		suppliers=query.getResultList();
		
		return suppliers;
	}
	
	

}
