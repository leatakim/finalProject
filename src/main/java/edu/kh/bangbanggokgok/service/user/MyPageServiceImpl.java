package edu.kh.bangbanggokgok.service.user;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.kh.bangbanggokgok.common.Util;
import edu.kh.bangbanggokgok.dao.user.MyPageDAO;
import edu.kh.bangbanggokgok.vo.board.LandMark;
import edu.kh.bangbanggokgok.vo.board.MoveLine;
import edu.kh.bangbanggokgok.vo.user.MyMoveline;
import edu.kh.bangbanggokgok.vo.user.MyReply;
import edu.kh.bangbanggokgok.vo.user.User;

@Service
public class MyPageServiceImpl implements MyPageService {

	@Autowired
	private MyPageDAO dao;

	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	
	// 프로필 이미지 변경
	@Override
	public int updateProfile(Map<String, Object> map) throws IOException {

		MultipartFile uploadImage = (MultipartFile) map.get("uploadImage");
		String delete = (String)map.get("delete");
		
		String renameImage = null;
		
		if(delete.equals("0")) {
			renameImage = Util.fileRename(uploadImage.getOriginalFilename());
			map.put("profileImage", map.get("webPath") + renameImage);
		} else {
			map.put("profileImage", null);
		}
		
		int result = dao.updateProfile(map);
		
		if(result > 0 && map.get("profileImage")!=null) {
			uploadImage.transferTo(new File( map.get("folderPath") + renameImage ));
		}
		return result;
	}

	// 회원정보 수정
	@Override
	public int updateInfo(Map<String, Object> map) {
		return dao.updateInfo(map);
	}

	// 비밀번호 변경
	@Override
	public int changePw(Map<String, Object> map) {
		
		String encPw = dao.selectEncPw((int)map.get("userNo"));
		
		if(bcrypt.matches((String)map.get("userPw"), encPw)) {
			String bcryptPw = bcrypt.encode((String)map.get("changePw"));
			map.put("changePw", bcryptPw);
			
			return dao.changePw(map);
		}
		return 0;
	}

	// 회원 탈퇴
	@Override
	public int secession(Map<String, Object> map) {
		String encPw = dao.selectEncPw((int)map.get("userNo"));
		
		if(bcrypt.matches((String)map.get("userPw"), encPw)) {
			return dao.secession((int)map.get("userNo"));
		}
		return 0;
	}

	//즐겨찾는 친구들
	@Override
	public List<LandMark> favoriteLandmark(int userNo) {
		return dao.favoriteLandmark(userNo);
	}
	@Override
	public List<MoveLine> favoriteMoveline(int userNo) {
		return dao.favoriteMoveline(userNo);
	}

	//내댓글
	@Override
	public List<MyReply> selectMyReplyList(int userNo) {
		return dao.selectMyReplyList(userNo);
	}

	//내코스
	@Override
	public List<MyMoveline> selectMyMovelineList(int userNo) {
		return dao.selectMyMovelineList(userNo);
	}
	
}