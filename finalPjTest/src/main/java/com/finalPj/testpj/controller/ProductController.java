package com.finalPj.testpj.controller;

import java.io.File;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.finalPj.testpj.dto.ProductDTO;
import com.finalPj.testpj.service.ProductService;


@Controller
@RequestMapping("/admin/*")
public class ProductController {
	
	@Inject
	ProductService productService;
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	//기능 upload(insert),list,delete,update(modify),uphit, detailView
	
	//리스트 화면 출력
	@RequestMapping("list")
	public String list(Model model) throws Exception{

		model.addAttribute("dtos", productService.list());
		
		return "/admin/list2";
	}
	
	//업로드 화면 출력, 리스트에서 업로드 버튼을 누르면 나오는 화면
	@RequestMapping("uploadView")
	public String uploadView(Model model) throws Exception{
		return "/admin/uploadView";
	}
	
	//uploadView에서 작성후 업로드 버튼을 누르면 실제로 db에 올라가는 서비스
	@RequestMapping(value="upload", method=RequestMethod.POST)
	public String upload(@ModelAttribute ProductDTO dto, MultipartFile file) throws Exception{
	
		
		String pImg = file.getOriginalFilename();
		
		//임시 디렉토리에 사진을 저장
		File target = new File(uploadPath, pImg);
		//위의 파일을 지정된 디렉토리로 복사
		FileCopyUtils.copy(file.getBytes(), target);
		//파일이름 지정
		dto.setpImg(pImg);
		
		productService.upload(dto);
		
		return "redirect:/admin/list"; //또는 다른 화면
	}
	
	//수정을 위한 detailView
	@RequestMapping("view")
	public String view(Model model, @RequestParam("pCode")int pCode) throws Exception{

		model.addAttribute("view", productService.view(pCode));
		return "/admin/view";
	}
	//modifyView
	@RequestMapping("modifyView")
	public String modifyView(Model model, @RequestParam("pCode")int pCode) throws Exception{
		model.addAttribute("view", productService.view(pCode));
		return "/admin/modifyView";
	}
	
	//modifyView에서 수정버튼 누르면 update되는 서비스
	@RequestMapping("modify")
	public String modify(@ModelAttribute ProductDTO dto, MultipartFile file) throws Exception {		
		String pImg = file.getOriginalFilename();
		
		//임시 디렉토리에 사진을 저장
		File target = new File(uploadPath, pImg);
		//위의 파일을 지정된 디렉토리로 복사
		FileCopyUtils.copy(file.getBytes(), target);
		//파일이름 지정
		dto.setpImg(pImg);
		
		productService.modify(dto);
		
		return "redirect:/admin/view/{pCode}";
	}
	
	//삭제는 list에서 삭제버튼 누르면 바로 실행
	@RequestMapping("delete")
	public String delete(HttpServletRequest request) throws Exception{
		String[] pCodes = request.getParameterValues("pCodes");
		//선택확인
		for(String i : pCodes)System.out.println(Integer.parseInt(i));
		for(String i : pCodes) {
			//파일삭제
			ProductDTO delDto = productService.view(Integer.parseInt(i));
			String filePath = uploadPath+delDto.getpImg();
			System.out.println(filePath);
			
			File file = new File(filePath);			
			if(file.exists()) {	
				if(file.delete()) {
					System.out.println("이미지 파일 삭제 완료");
				}else {
					System.out.println("이미지 파일 삭제 실패");
				}
			}else {
				System.out.println("파일이 존재하지 않음");
			}
			productService.delete(Integer.parseInt(i));			
		}
		return "redirect:/admin/list";	
	}
	
	
}
		
