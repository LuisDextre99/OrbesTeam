package com.hampcode.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import com.hampcode.business.ProductBusiness;
import com.hampcode.business.SupplierBusiness;
import com.hampcode.model.entity.Product;
import com.hampcode.model.entity.Supplier;
import com.hampcode.util.Message;

@Named
@SessionScoped
public class ProductController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProductBusiness productBusiness;
	
	@Inject
	private SupplierBusiness supplierBusiness;

	
	//Producto
	private Product product;
	private List<Product> products;
	private Product productSelect;
	private String filterName;

	//Proveedor
	
	private Supplier supplier;
	private List<Supplier> suppliers;
	
	
	@PostConstruct
	public void init() {
		product = new Product();
		products = new ArrayList<Product>();
		suppliers=new ArrayList<Supplier>();
		getAllProducts();
	}

	public void getAllProducts() {
		try {
			products = productBusiness.getAll();
		} catch (Exception e) {
			Message.messageError("Error Carga de Productos");
		}
	}
	

	public void getAllSupplier() {
		try {
			suppliers = supplierBusiness.getAll();
		} catch (Exception e) {
			Message.messageError("Error Carga de Productos");
		}
	}

	public String newProduct() {
		resetForm();
		getAllSupplier();
		return "/product/insert.xhtml";
	}

	public String goListProduct() {
		return "/product/list.xhtml";
	}
	
	
	public String saveProduct() {

		String view="";
		try {
			 if(product.getId()!=null)
			   { 
				 product.setSupplier(supplier);

				 productBusiness.update(product);
			   Message.messageInfo("Registro actualizado exitosamente");
			   }
			 else {
				 
				 product.setSupplier(supplier);
				 productBusiness.insert(product);
				 Message.messageInfo("Registro guardado exitosamente");
			}
			 this.getAllProducts();
			resetForm();
			view="list";
		} catch (Exception e) {
			Message.messageError("Error Product: "+ e.getStackTrace());
		}
		return view;
	  
		
	}

	public String editProduct() {
		String view = "";
		try {
			if(this.productSelect !=null)
			{
				this.product=productSelect;
				
				view="update"; //Vista
			}else {
				Message.messageInfo("Debe seleccionar un producto");
			}
			
		} catch (Exception e) {
			Message.messageError("Error Product: " + e.getMessage());
		}
		

		return view;
	}

	public void searchProductByName() {
		
	}

	public void selectProduct(SelectEvent e) {
		this.productSelect = (Product) e.getObject();
	}

	public void resetForm() {
		this.filterName="";
		this.product = new Product();
	}

	
	
	/*Metodos Get - Set*/

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Product getProductSelect() {
		return productSelect;
	}

	public void setProductSelect(Product productSelect) {
		this.productSelect = productSelect;
	}

	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

	public List<Supplier> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(List<Supplier> suppliers) {
		this.suppliers = suppliers;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	
	
	
}
