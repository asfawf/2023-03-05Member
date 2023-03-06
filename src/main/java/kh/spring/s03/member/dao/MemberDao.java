package kh.spring.s03.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kh.spring.s03.member.vo.MemberVo;

@Repository
public class MemberDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	// 해당 try catch 의 경우 회원가입시 중복된 값으로 실패할 경우 오류 페이지로 보내지 않고 -1 을 반환하게 한다
	public int insert(MemberVo vo) throws Exception{
		/*
		 * int result = -1;
		 * 
		 * try { return sqlSession.insert("memberMapper.insertId", vo); } catch
		 * (Exception e) { e.printStackTrace(); }
		 * 
		 * return result;
		 */
		int result = -1;
		
		result= sqlSession.insert("memberMapper.insertId", vo);
		
		return result;
	}
	public int update(MemberVo vo) {
		return sqlSession.update("memberMapper.updateId", vo);
	}
	public int delete(String id) {
		return sqlSession.delete("memberMapper.deleteId", id);
	}
	public MemberVo selectOne(String id) {
		return sqlSession.selectOne("memberMapper.selectOneId", id);
	}
	public List<MemberVo> selectList() {
		return sqlSession.selectList("memberMapper.selectListId");
	}
}
