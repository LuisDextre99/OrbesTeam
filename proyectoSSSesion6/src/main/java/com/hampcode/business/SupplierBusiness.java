package com.hampcode.business;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.hampcode.model.entity.Supplier;
import com.hampcode.model.repository.SupplierRepository;

@Named
public class SupplierBusiness implements Serializable{
	private static final long serialVersionUID=1L;
	
	@Inject
	private SupplierRepository supplierRepository;
	
	public List<Supplier> getAll() throws Exception {
		return supplierRepository.findAll();
	}
	

}
