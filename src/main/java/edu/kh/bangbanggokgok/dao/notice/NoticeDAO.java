package edu.kh.bangbanggokgok.dao.notice;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.bangbanggokgok.vo.notice.Notice;
import edu.kh.bangbanggokgok.vo.notice.NoticeDetail;
import edu.kh.bangbanggokgok.vo.notice.Pagination;

@Repository
public class NoticeDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
	public int getListCount() {
		return sqlSession.selectOne("noticeMapper.getListCount");
	}


	/** 공지사항 목록 조회 DAO
	 * @param pagination
	 * @return NoticeList
	 */
	public List<Notice> selectNoticeList(Pagination pagination, String list) {
		
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		return sqlSession.selectList("noticeMapper.selectNoticeList", list, rowBounds);
	}


	/** 공지 상세조회 DAO
	 * @param boardNo
	 * @return
	 */
	public NoticeDetail selectnoticeDetail(int noticeNo) {
		return sqlSession.selectOne("noticeMapper.selectNoticeDetail", noticeNo);
	}


	/** 공지 전체 수 조회
	 * @return 
	 */
	public int getnoticeListCount() {
		return sqlSession.selectOne("noticeMapper.getNoticeListCount");
	}


	/** 공지 목록 조회 DAO
	 * @param pagination
	 * @param list
	 * @return
	 */
	public List<Notice> selectAllNoticeList(Pagination pagination, String list) {
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		return sqlSession.selectList("noticeMapper.selectAllNoticeList", list, rowBounds);
	}


	/** 이벤트 전체 수 조회
	 * @return result
	 */
	public int getEventListCount() {
		return sqlSession.selectOne("noticeMapper.getEventListCount");
	}


	public List<Notice> selectAllEventList(Pagination pagination, String list) {
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		return sqlSession.selectList("noticeMapper.selectAllEventList", list, rowBounds);
	}
	
	
	
	
	
}
