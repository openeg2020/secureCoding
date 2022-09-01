package kr.co.openeg.lab.board.dao;

import java.util.HashMap;
import java.util.List;

import kr.co.openeg.lab.board.model.BoardCommentModel;
import kr.co.openeg.lab.board.model.BoardModel;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDao{

	private HashMap<String, Object> valueMap = new HashMap<String, Object>();
	
	@Autowired
	private SqlSession sqlSession;

	private static final String mapper = "kr.co.openeg.lab.board.dao.BoardDao";
	 
	public List<BoardModel> selectBoardList(int startArticleNum, int endArticleNum) {
		valueMap.put("startArticleNum", startArticleNum);
		valueMap.put("endArticleNum", endArticleNum);
		return sqlSession.selectList(mapper+".getBoardList", valueMap);
	}

	 
	public BoardModel selectOneArticle(int idx) {
		return (BoardModel) sqlSession.selectOne(mapper+".getOneArticle", idx);
	}

	 
	public List<BoardModel> selectArticle(String type, String keyword, int startArticleNum, int endArticleNum) {
		valueMap.put("type", type);
		valueMap.put("keyword", keyword);
		valueMap.put("startArticleNum", startArticleNum);
		valueMap.put("endArticleNum", endArticleNum);
		return sqlSession.selectList(mapper+".searchArticle", valueMap);
	}

	 
	public List<BoardCommentModel> selectCommentList(int idx) {
		return sqlSession.selectList(mapper+".getCommentList", idx);
	}

	 
	public void insertArticle(BoardModel board) {
		    sqlSession.insert(mapper+".writeArticle", board);	
	}

	 
	public void insertComment(BoardCommentModel comment) {
		   sqlSession.insert(mapper+".writeComment", comment);
	}

	 
	public void updateHitcount(int hitcount, int idx) {
		valueMap.put("hitcount", hitcount);
		valueMap.put("idx", idx);
		sqlSession.update(mapper+".updateHitcount", valueMap);		
	}

	 
	public void updateRecommendCount(int recommendcount, int idx) {
		valueMap.put("recommendcount", recommendcount);
		valueMap.put("idx", idx);
		sqlSession.update(mapper+".updateRecommendcount", valueMap);
		
	}
	 
	public int selectTotalNum() {
		return (Integer) sqlSession.selectOne(mapper+".getTotalNum");
	}

	 
	public int selectSearchTotalNum(String type, String keyword) {
		valueMap.put("type", type);
		valueMap.put("keyword", keyword);
		return (Integer) sqlSession.selectOne(mapper+".getSearchTotalNum", valueMap);
	}

	 
	public void deleteComment(int idx) {
		sqlSession.delete(mapper+".deleteComment", idx);
	}
	
	 
	public void deleteArticle(int idx) {
		sqlSession.delete(mapper+".deleteArticle", idx);	
	}

	 
	public BoardCommentModel selectOneComment(int idx) {
		return (BoardCommentModel) sqlSession.selectOne(mapper+".getOneComment", idx);		
	}

	 
	public void updateArticle(BoardModel board) {
		   sqlSession.update(mapper+".modifyArticle", board);
	}	

}
