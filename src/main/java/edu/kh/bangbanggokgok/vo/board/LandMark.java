package edu.kh.bangbanggokgok.vo.board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LandMark {
	private int landMarkNo;
	private int landMarkX;
	private int landMarkY;
	private String landMarkName;
	private String landMarkContent;
	private String landMarkAddress;
	private char landMarkFL;
	private String landMarkCreate;
	private String thumbnail;
	private int userNo;
	private int locationType;
}
