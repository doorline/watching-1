package com.finalPj.testpj.service;

import java.util.HashMap;
import java.util.List;

import com.finalPj.testpj.common.SearchVO;
import com.finalPj.testpj.dto.ProductDTO;


public interface ProductService {
	
	public List<ProductDTO> list(SearchVO vo);
	//상세페이지
	public ProductDTO view(int pCode);
	
	public void upload(ProductDTO dto);
	
	public void modify(ProductDTO dto);
	
	public void delete(int pCode);
	
	public void uphit(int pCode);
	
	public int getPcode(String pName);
	
	public int cntList(HashMap<String, String> searchMap);

}