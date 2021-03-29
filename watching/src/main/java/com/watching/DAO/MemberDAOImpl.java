package com.watching.DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.watching.DTO.MemberDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//회원목록 구현
//현재 클래스를 DAO bean으로 등록시킴
@Repository
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	SqlSession sqlSession;
	
	private static final String nameSpace = "com.watching.mappers.memberMapper";

	@Override
	public boolean memberLogin(MemberDTO dto) throws Exception {
		String mname = sqlSession.selectOne(nameSpace + ".memberLogin", dto);
		return (mname == null) ? false : true;
	}

	@Override
	public void memberJoin(MemberDTO dto) throws Exception {
		sqlSession.insert(nameSpace + ".memberJoin", dto);
	}

	@Override
	public MemberDTO memberDetail(String mid) throws Exception {
		return sqlSession.selectOne(nameSpace + ".memberDetail", mid);
	}

	@Override
	public void memberEdit(MemberDTO dto) throws Exception {
		sqlSession.update(nameSpace + ".memberEdit", dto);
	}

	@Override
	public void memberDelete(String mid) throws Exception {
		sqlSession.delete(nameSpace + ".memberDelete", mid);
	}

	@Override
	public List<MemberDTO> memberFindId(MemberDTO dto) throws Exception {
		return sqlSession.selectList(nameSpace + ".memberFindId", dto);
	}

	@Override
	public String memberFindPw(MemberDTO dto) throws Exception {
		return sqlSession.selectOne(nameSpace + ".memberFindPw", dto);
	}

	@Override
	public int idCheck(String mid) throws Exception {
		return sqlSession.selectOne(nameSpace + ".idCheck", mid);
	}

}