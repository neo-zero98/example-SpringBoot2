package com.codmid.orderapi.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codmid.orderapi.converters.ProductConverter;
import com.codmid.orderapi.dtos.ProductDTO;
import com.codmid.orderapi.entity.Product;
import com.codmid.orderapi.services.ProductService;
import com.codmid.orderapi.utils.WrapperResponse;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService producService;
	
	//private ProductConverter productConverter = new ProductConverter();
	@Autowired
	private ProductConverter productConverter;
	
	@GetMapping(value = "/products")
	public ResponseEntity<WrapperResponse<List<ProductDTO>>> finAll(
			@RequestParam(value="pageNumber", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize
			){
		Pageable page = PageRequest.of(pageNumber, pageSize);
		List<Product> lista = this.producService.findAll(page);
		List<ProductDTO> productsDTO = this.productConverter.fromEntity(lista);
		return new WrapperResponse<>(true, "succes", productsDTO)
				 .createResponse(HttpStatus.OK);
		//return new ResponseEntity<List<ProductDTO>>(productsDTO, HttpStatus.OK);
	}
	
	 @GetMapping(value = "/products/{productId}")
	 public ResponseEntity<WrapperResponse<ProductDTO>> findById(@PathVariable(value = "productId") Long productId) {
		 Product product = this.producService.findById(productId);
		 ProductDTO productDTO = this.productConverter.fromEntity(product);
		 //WrapperResponse<ProductDTO> response = new WrapperResponse<ProductDTO>(true, "succes", productDTO);
		 //return new ResponseEntity<WrapperResponse<ProductDTO>>(response, HttpStatus.OK);
		 return new WrapperResponse<ProductDTO>(true, "succes", productDTO)
				 .createResponse(HttpStatus.OK);
	 }
	 
	 @PostMapping(value = "/products")
	 public ResponseEntity<WrapperResponse<ProductDTO>> create(@RequestBody ProductDTO product) {
		 Product updateProduct = this.producService.save(this.productConverter.fromDTO(product));
		 ProductDTO productDTO = this.productConverter.fromEntity(updateProduct);
		 return new WrapperResponse<>(true, "succes", productDTO)
				 .createResponse(HttpStatus.CREATED);
		 //return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);
	 }
	 
	 @PutMapping(value = "/products")
	 public ResponseEntity<WrapperResponse<Product>> update(@RequestBody ProductDTO product) {
		 Product updateProduct = this.producService.save(this.productConverter.fromDTO(product));
		 return new WrapperResponse<>(true, "succes", updateProduct)
				 .createResponse(HttpStatus.OK);
		 //return new ResponseEntity<Product>(updateProduct,HttpStatus.OK);
	 }
	 
	 @DeleteMapping(value = "/products/{productId}")
	 public ResponseEntity<?> delete(@PathVariable("productId") Long productId) {
		 this.producService.delete(productId);
		 return new WrapperResponse<>(true, "succes", null)
				 .createResponse(HttpStatus.OK);
		 //return new ResponseEntity<Void>(HttpStatus.OK);
	 }
	 

}
