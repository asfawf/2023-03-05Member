package kh.spring.s03.member.service;

import java.util.List;

import kh.spring.s03.member.vo.MemberVo;

public interface MemberService {

	public int insert(MemberVo vo) ;
	public int update(MemberVo vo) ;
	public int delete(String id);
	public MemberVo selectOne(String id);
	public List<MemberVo> selectList();
}
