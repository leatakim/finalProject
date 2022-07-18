package edu.kh.bangbanggokgok.service.notice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.bangbanggokgok.dao.notice.NoticeDAO;
import edu.kh.bangbanggokgok.vo.notice.Notice;
import edu.kh.bangbanggokgok.vo.notice.NoticeDetail;
import edu.kh.bangbanggokgok.vo.notice.Pagination;

@Service
public class NoticeServiceImpl implements NoticeService{

	@Autowired
	private NoticeDAO dao;

	// 공지사항 목록 조회 서비스
	@Override
	public Map<String, Object> selectNoticeList(int cp, String list) {
		
		int listCount = dao.getListCount();
		Pagination pagination = new Pagination(cp, listCount);
		
		List<Notice> noticeList = dao.selectNoticeList(pagination, list);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("noticeList", noticeList);
		
		return map;
	}

	// 게시글 상세 조회 서비스 구현
	@Override
	public NoticeDetail selectNoticeDetail(int boardNo) {
		
		
		return dao.selectnoticeDetail(boardNo);
	}
	
	
}
