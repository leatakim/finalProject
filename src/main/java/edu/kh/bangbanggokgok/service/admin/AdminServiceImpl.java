package edu.kh.bangbanggokgok.service.admin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.kh.bangbanggokgok.common.Util;
import edu.kh.bangbanggokgok.dao.admin.AdminDAO;
import edu.kh.bangbanggokgok.vo.notice.NoticeDetail;
import edu.kh.bangbanggokgok.vo.notice.NoticeImage;
import edu.kh.bangbanggokgok.vo.notice.Pagination;
import edu.kh.bangbanggokgok.vo.question.Question;
import edu.kh.bangbanggokgok.vo.user.User;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminDAO dao;
	
	// 공지 삽입
	@Override
	public int insertNotice(NoticeDetail detail, List<MultipartFile> imageList, String webPath, String folderPath) throws IOException{
		
		detail.setNoticeTitle(Util.XSSHandling(detail.getNoticeTitle()));
		detail.setNoticeContent(Util.XSSHandling(detail.getNoticeContent()));
		detail.setNoticeContent(Util.newLineHandling(detail.getNoticeContent()));
		
		int noticeNo = dao.insertNotice(detail);
		
		if(noticeNo > 0) {
			List<NoticeImage> noticeImageList = new ArrayList<NoticeImage>();
			List<String> reNameList = new ArrayList<String>();
			
			for(int i=0; i<imageList.size(); i++) {
				if(imageList.get(i).getSize() > 0) {
					String reName = Util.fileRename(imageList.get(i).getOriginalFilename());
					reNameList.add(reName);
					
					NoticeImage img = new NoticeImage();
					img.setNoticeNo(noticeNo);
					img.setImageLevel(i);
					img.setImageReName(webPath + reName);
				}
			}
			
			if(!noticeImageList.isEmpty()) {
				int result = dao.insertNoticeImageList(noticeImageList);
				
				if(result == noticeImageList.size()) {
					
					for(int i=0; i < noticeImageList.size(); i++) {
						int index = noticeImageList.get(i).getImageLevel();
						imageList.get(index).transferTo(new File(folderPath + reNameList.get(i)));
						
					}
					
				}
			}
		}
		
		return noticeNo;
	}

	// 회원 조회
	@Override
	public Map<String, Object> selectUserList(int cp, String list) {
		int userListCount = dao.getUserListCount(list);
		Pagination pagination = new Pagination(cp, userListCount);
		
		List<User> userList = dao.selectUserList(pagination, list);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("userList", userList);
		
		return map;
	}

	// 문의 조회
	@Override
	public Map<String, Object> selectInquiryList(int cp, String list) {
		int inquiryListCount = dao.getInquiryListCount(list);
		
		Pagination pagination = new Pagination(cp, inquiryListCount);
		List<Question> questionList = dao.selectInquiryList(pagination, list);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("questionList", questionList);
		
		return map;
	}

	// 신고 조회
	@Override
	public Map<String, Object> selectreportList(int cp, String list) {
		int reportListCount = dao.getReportListCount(list);
		
		Pagination pagination = new Pagination(cp, reportListCount);
		List<Question> reportList = dao.selectReportList(pagination, list);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("reportList", reportList);
		
		return map;
	}


	
	
	
	
	
	
	
	
}