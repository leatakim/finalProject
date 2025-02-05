package edu.kh.bangbanggokgok.service.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import edu.kh.bangbanggokgok.vo.admin.ReportMoveLine;
import edu.kh.bangbanggokgok.vo.notice.NoticeDetail;
import edu.kh.bangbanggokgok.vo.question.QuestionDetail;

public interface AdminService {

	/** 공지 삽입
	 * @param detail
	 * @param imageList
	 * @param webPath
	 * @param folderPath
	 * @return noticeNo
	 * @throws IOExeption
	 */
	int insertNotice(NoticeDetail detail, List<MultipartFile> imageList, String webPath, String folderPath) throws IOException;

	/** 회원 조회
	 * @param cp
	 * @param loginUser
	 * @return Map
	 */
	Map<String, Object> selectUserList(int cp, String list);


	/** 문의 조회
	 * @param cp
	 * @param list
	 * @return map
	 */
	Map<String, Object> selectInquiryList(int cp, String list);

	/** 코스 신고 조회
	 * @param cp
	 * @param list
	 * @return map
	 */
	Map<String, Object> selectreportList(int cp, String list);

	/** 문의 상세 조회
	 * @param questionNo
	 * @return result
	 */
	QuestionDetail selectQuestionDetail(int questionNo);

	/** 문의 답변 등록
	 * @param detail
	 * @return result
	 */
	int insertAnswer(QuestionDetail detail);

	/** 코스 신고처리
	 * @param report
	 * @return result
	 */
	int updateReport(ReportMoveLine report);

	/** 댓글 신고 조회
	 * @param cp
	 * @param list
	 * @return map
	 */
	Map<String, Object> selectReplyReport(int cp, String list);

	/** 활동회원 조회
	 * @param cp
	 * @param list
	 * @return
	 */	
	Map<String, Object> selectSignUpUser(int cp, String list);

	/** 탈퇴회원 조회
	 * @param cp
	 * @param list
	 * @return
	 */
	Map<String, Object> selectSecession(int cp, String list);

	/** 공지 수정
	 * @param detail
	 * @param imageList
	 * @param webPath
	 * @param folderPath
	 * @param deleteList
	 * @return result
	 * @throws IOException
	 */
	int updateNotice(NoticeDetail detail, List<MultipartFile> imageList, String webPath, String folderPath,
			String deleteList) throws IOException;


}
