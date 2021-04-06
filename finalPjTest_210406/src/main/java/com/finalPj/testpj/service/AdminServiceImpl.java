package com.finalPj.testpj.service;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalPj.testpj.dao.AdminDAO;
import com.finalPj.testpj.dto.AdminDTO;
import com.finalPj.testpj.dto.MemberDTO;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Inject    //dao를 호출하기때문에 의존성을 주입한다.
    private AdminDAO admindao;

	@Autowired
	private static Hashtable<String, String> loginAdmins = new Hashtable<String, String>();
	
	@Override
	public boolean loginCheck(AdminDTO dto, HttpSession session) throws Exception {
		boolean result = admindao.loginCheck(dto);
		
		if(result) {
			session.setAttribute("aid", dto.getAid());
			session.setAttribute("spw", dto.getApw());
		}
		
		return result;
	}
	

	@Override
	public void admin_member_forced_evictionCheck(MemberDTO dto) throws Exception {
		

	}

}
