package edu.kh.bangbanggokgok.service.board;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import edu.kh.bangbanggokgok.common.Util;
import edu.kh.bangbanggokgok.dao.board.LandMarkDAO;
import edu.kh.bangbanggokgok.exception.InsertFailException;
import edu.kh.bangbanggokgok.vo.board.LandMark;
import edu.kh.bangbanggokgok.vo.board.LandMarkDetail;
import edu.kh.bangbanggokgok.vo.board.LandMarkIMG;

@Service
public class LandMarkServiceImpl implements LandMarkService {

	@Autowired
	private LandMarkDAO dao;


	// 랜드마크 전체 목록 조회
	@Override
	public Map<String, Object> selectAllLandMarkList(int num) {

//		int ListCount = dao.getListCount();
//		List<LandMark> landMarkList = dao.selectAllLandMarkList();

		List<LandMarkIMG> landMakrImage = dao.selectLandmarkImageList();
		List<LandMark> landMarkList = dao.selectLandMarkList(num);
		Map<String, Object> map = new HashMap<String, Object>();
		for (LandMark e : landMarkList) {
			e.setLandMarkContent(Util.newLineClear(e.getLandMarkContent()));
			e.setLandMarkContent(Util.XSSClear(e.getLandMarkContent()));
			e.setLandMarkName(Util.XSSClear(e.getLandMarkName()));
		}

		map.put("landMakrImage", landMakrImage);
		map.put("landmarkList", landMarkList);

//		map.put("ListCount", ListCount);
//		map.put("landMarkList", landMarkList);

		return map;
	}

	// 랜드마크 상세 조회
	@Override
	public LandMarkDetail selectLandmarkDetail(int landMarkNo) {
		return dao.selectLandmarkDetail(landMarkNo);
	}

	// 게시글,이미지 삽입
	@Override
	public int insertLandMark(LandMarkDetail detail, List<MultipartFile> imageList, String webPath, String folderPath)
			throws IOException {

		int landMarkNo = dao.insertLandMark(detail);

		if (landMarkNo > 0) {
			List<LandMarkIMG> landMarkImageList = new ArrayList<LandMarkIMG>();
			List<String> reNameList = new ArrayList<String>();

			for (int i = 0; i < imageList.size(); i++) {

				if (imageList.get(i).getSize() > 0) {

					// 변경된 파일명 저장
					String reName = Util.fileRename(imageList.get(i).getOriginalFilename());
					reNameList.add(reName);

					LandMarkIMG img = new LandMarkIMG();
					img.setLandMarkNo(landMarkNo); // 게시글 번호
					img.setLandMarkImageLV(i); // 이미지 순서(파일 레벨)
					img.setLandMarkReName(webPath + reName); // 웹 접근 경로 + 변경된 파일명

					landMarkImageList.add(img);
				}
			} // for 종료

			if (!landMarkImageList.isEmpty()) {

				int result = dao.insertLandMarkImageList(landMarkImageList);

				if (result == landMarkImageList.size()) { // 삽입된 행의 개수와 업로드 이미지 수가 같을 경우

					// 서버에 이미지 저장

					for (int i = 0; i < landMarkImageList.size(); i++) {
						int index = landMarkImageList.get(i).getLandMarkImageLV();

						imageList.get(index).transferTo(new File(folderPath + reNameList.get(i)));
					}

				} else { // 이미지 삽입 실패 시

					// 강제로 예외를 발생 시켜 rollback을 수행하게 함
					// -> 사용자 정의 예외
					throw new InsertFailException();
				}
			}
		}

		return landMarkNo;
	}

	@Transactional(rollbackFor = { Exception.class })
	@Override
	public int updateLandMark(LandMarkDetail detail, List<MultipartFile> imageList, String webPath, String folderPath,
			String deleteList) throws IOException {

		int result = dao.updateLandmark(detail);

		if (result > 0) {

			List<LandMarkIMG> ladnMarkImageList = new ArrayList<LandMarkIMG>();
			List<String> reNameList = new ArrayList<String>();

			for (int i = 0; i < imageList.size(); i++) {

				if (imageList.get(i).getSize() > 0) { // i번째 요소에 업로드된 이미지가 있을 경우

					String reName = Util.fileRename(imageList.get(i).getOriginalFilename());
					reNameList.add(reName);

					LandMarkIMG img = new LandMarkIMG();
					img.setLandMarkNo(detail.getLandMarkNo());
					img.setLandMarkImageLV(i);
					img.setLandMarkReName(webPath + reName);

					ladnMarkImageList.add(img);
				}
			}

			if (deleteList.equals("ggomsu")) {
				deleteList = "";
			}

			// 삭제된 이미지
			if (!deleteList.equals("")) {
				Map<String, Object> map = new HashMap<>();

				map.put("landMarkNo", detail.getLandMarkNo());
				map.put("deleteList", deleteList);

				result = dao.deleteLandmarkImage(map);
			}

			if (result > 0) {

				for (LandMarkIMG img : ladnMarkImageList) {
					result = dao.updateLandmarkImage(img);

					if (result == 0) {

						result = dao.insertLandmarkImage(img);
					}
				}

				if (!ladnMarkImageList.isEmpty() && result != 0) {

					for (int i = 0; i < ladnMarkImageList.size(); i++) {

						int index = ladnMarkImageList.get(i).getLandMarkImageLV();
						imageList.get(index).transferTo(new File(folderPath + reNameList.get(i)));
					}
				}
			}
		}
		return result;
	}

	@Override
	public int landmarkBookmark(String loginNo, String landmarkNo) {

//		인트형 배열로 실험해봐야함
//		int[] infoA = {loginNo,landmarkNo};

		Map<String, String> infoB = new HashMap<String, String>();
		infoB.put("loginNo", loginNo);
		infoB.put("landmarkNo", landmarkNo);

		return dao.landmarkBookmark(infoB);
	}

	@Override
	public int landmarkBookmarkInsert(String loginNo, String landmarkNo) {
		Map<String, String> infoB = new HashMap<String, String>();
		infoB.put("loginNo", loginNo);
		infoB.put("landmarkNo", landmarkNo);
		return dao.insertLandBookmark(infoB);
	}

	@Override
	public int landmarkBookmarkDelete(String loginNo, String landmarkNo) {
		Map<String, String> infoA = new HashMap<String, String>();
		infoA.put("loginNo", loginNo);
		infoA.put("landmarkNo", landmarkNo);
		return dao.landmarkBookmarkDelete(infoA);
	}

	@Override
	public double rankLandmark(int landmarkNo) {
		String result = dao.rankLandmark(landmarkNo);
		if (result == null)
			result = "0";
		return Double.parseDouble(result);
	}

	@Override
	public int addPointCheck(int landmarkNo, int userNo) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("landmarkNo", landmarkNo);
		map.put("userNo", userNo);
		return dao.addPointChcek(map);
	}

	@Override
	public double insertRankPoint(String rankPoint, String userNo, int landmarkNo) {
		Map<String, String> map = new HashMap<String, String>();
		String sLandmarkNo = Integer.toString(landmarkNo);
		map.put("sLandmarkNo", sLandmarkNo);
		map.put("userNo", userNo);
		map.put("rankPoint", rankPoint);

		if (dao.insertRankPoint(map) > 0) {
			return rankLandmark(landmarkNo);
		}

		return -100;
	}

	@Override
	public double deleteRankPoint(String userNo, int landmarkNo) {
		Map<String, String> map = new HashMap<String, String>();
		String sLandmarkNo = Integer.toString(landmarkNo);
		map.put("sLandmarkNo", sLandmarkNo);
		map.put("userNo", userNo);

		if (dao.deleteRankPoint(map) > 0) {
			return rankLandmark(landmarkNo);
		}

		return -100;
	}


	@Override
	public int getListCount(int locationType) {
		return dao.getListCount(locationType);
	}

	@Override
	public List<LandMark> selectLandMarkList(int locationType) {
		
		return dao.selectLandMarkPagination(locationType);
	}

}
