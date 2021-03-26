package com.finalPj.testpj.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.finalPj.testpj.dao.CategoryDAO;
import com.finalPj.testpj.dto.ProductDTO;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Inject
	private CategoryDAO cdao;

	@Override
	public List<ProductDTO> dms_dramalist() throws Exception {
		
		return cdao.dms_dramalist();
	}

	@Override
	public List<ProductDTO> abr_dramalist() throws Exception {
		
		return cdao.abr_dramalist();
	}

	@Override
	public List<ProductDTO> dms_filmlist() throws Exception {
		
		return cdao.dms_filmlist();
	}

	@Override
	public List<ProductDTO> abr_filmlist() throws Exception {
		
		return cdao.abr_filmlist();
	}

	@Override
	public List<ProductDTO> listsearch(String pName, String pImg, String keyword) throws Exception {

		return cdao.listsearch(pName, pImg, keyword);
	}

}
