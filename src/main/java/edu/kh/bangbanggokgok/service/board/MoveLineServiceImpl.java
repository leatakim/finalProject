//package edu.kh.bangbanggokgok.service.board;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import edu.kh.bangbanggokgok.dao.board.MoveLineDAO;
//import edu.kh.bangbanggokgok.vo.board.MoveLine;
//
//
//public class MoveLineServiceImpl implements MoveLineService{
//
//	@Autowired
//	private MoveLineDAO dao;
//	
//	
//	// 지역 타입,이름 조회 서비스 구현
//	@Override
//	public List<Locations> selectMLTypeList() {
//		return dao.selectMLTypeList();
//	}
//	
//	
//	// 지역별 코스 목록 조회 서비스 구현
//	@Override
//	public Map<String, Object> selectMoveLineList(int cp, int locationType) {
//		
//		
//		// 특정 지역의 전체 게시글 수 조회 + 
//		// 2) 페이지네이션 객체 생성(listCount)
//		int listCount = dao.getListCount(locationType);
//		Pagination pagination = new Pagination(cp, listCount);
//				
//		// 3) 코스 목록 조회
//		List<MoveLine> boardList = dao.selectMoveLineList(pagination, locationType);
//		
//		// map만들어 담기
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("pagination", pagination);
//		map.put("boardList", locationType);
//		
//		return map;
//	}
//
//	
//	
////	// 검색 게시글 목록 조회 서비스 구현
////	@Override
////	public Map<String, Object> searchBoardList(Map<String, Object> paramMap) {
////		
////		// 검색 조건에 맞는 게시글 목록의 전체 개수 조회
////		int listCount = dao.searchListCount( paramMap  );
////		
////		// 페이지네이션 객체 생성
////		Pagination pagination = new Pagination( (int)paramMap.get("cp") , listCount);
////		
////		// 검색 조건에 맞는 게시글 목록 조회(페이징 처리 적용)
////		List<MoveLine> moveLineList = dao.searchMoveLineList(paramMap, pagination);
////		
////		// map만들어 담기
////		Map<String, Object> map = new HashMap<String, Object>();
////		map.put("pagination", pagination);
////		map.put("moveLineList", moveLineList);
////		
////		return map;
////	}
//
//	
//	
//}
