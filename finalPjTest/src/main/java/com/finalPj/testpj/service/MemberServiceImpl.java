package com.finalPj.testpj.service;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalPj.testpj.dao.MemberDAO;
import com.finalPj.testpj.dto.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDAO dao;
	
	@Autowired
	private static Hashtable<String, String> loginMembers = new Hashtable<String, String>();

	@Override
	public boolean memberLogin(MemberDTO dto, HttpSession session) throws Exception {
		boolean isLogin = isLogin(dto.getMid());
		if (!isLogin) {
			boolean result = dao.memberLogin(dto);
			if (result) {
				setSession(session, dto);
			}
			return result;
		}
		return !isLogin;
	}
	
	public boolean isLogin(String mid) {
		boolean isLogin = false;
		Enumeration<String> e = loginMembers.keys();		
		String key = "";
		while (e.hasMoreElements()) {
			key = (String) e.nextElement();
			if (mid.equals(loginMembers.get(key)))
				isLogin = true;
		}
		return isLogin;
	}
	
	public void setSession(HttpSession session, MemberDTO dto) {
		loginMembers.put(session.getId(), dto.getMid());
		session.setAttribute("mid", dto.getMid());
	}

	@Override
	public void memberJoin(MemberDTO dto) throws Exception {
		dto.setMcode(dto.getMcode());
		dto.setMid(dto.getMid());
		dto.setMpw(dto.getMpw());
		dto.setMname(dto.getMname());
		dto.setMmemsdate(dto.getMmemsdate());
		dto.setMscode(dto.getMscode());
		dto.setMstate(dto.getMstate());
		dao.memberJoin(dto);
	}

	@Override
	public MemberDTO memberDetail(String mid) throws Exception {
		return dao.memberDetail(mid);
	}

	@Override
	public void memberEdit(MemberDTO dto) throws Exception {
		dao.memberEdit(dto);

	}

	@Override
	public void memberDelete(String mid, HttpSession session) throws Exception {
		session.invalidate();
		dao.memberDelete(mid);
	}

	@Override
	public void memberLogout(HttpSession session) throws Exception {
		loginMembers.remove(session.getId());
		session.invalidate();
	}

	@Override
	public List<MemberDTO> memberFindId(MemberDTO dto) throws Exception {
		return dao.memberFindId(dto);
	}

	@Override
	public String memberFindPw(MemberDTO dto) throws Exception {
		return dao.memberFindPw(dto);
	}

	@Override
	public int idCheck(String mid) throws Exception {
		return dao.idCheck(mid);
	}

}
