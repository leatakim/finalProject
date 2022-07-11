<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원가입</title>

<link rel="stylesheet" href="${contextPath}/resources/css/signUp.css">

<link
	href="https://hangeul.pstatic.net/hangeul_static/css/nanum-barun-gothic.css"
	rel="stylesheet">
</head>
<body>
	<main>
		헤더

		<section class="signUp-content">
			<h1 class="page-title">회원가입</h1>

			<form action="#">

				<div class="signUp-row">
					<label for="memberEmail">아이디(이메일)</label> <input type="text"
						id="memberEmail" placeholder="ex) user@xx.co.kr" required>
					<button>인증번호 전송</button>
				</div>
				<span class="signUp-message">사용 가능한 이메일 주소를 입력해주세요.</span>

				<div class="signUp-row">
					<label for="emailCheck">인증번호</label> <input type="text"
						id="emailCheck" placeholder="6자리" required>
					<button>확인</button>
				</div>
				<span class="signUp-message">인증 번호를 입력해주세요.</span>

				<div class="signUp-row">
					<label for="memberName">이름</label> <input type="text"
						id="memberName" placeholder="ex) 유저일" required>
				</div>
				<span class="signUp-message">한글, 영어만 입력해주세요.</span>

				<div class="signUp-row">
					<label for="memberPw">비밀번호</label> <input type="password"
						id="memberPw" required>
				</div>
				<span class="signUp-message">영어, 숫자, 특수문자(!,@,#,-,_) 6~20글자
					사이로 입력해주세요.</span>

				<div class="signUp-row">
					<label for="memberPwConfirm">비밀번호 확인</label> <input type="password"
						id="memberPwConfirm" required>
				</div>
				<span class="signUp-message">비밀번호가 일치하지 않습니다.</span>

				<div class="signUp-row">
					<label for="memberTel">전화번호</label> <input type="text"
						id="memberTel" placeholder="ex) 01012345678" required>
				</div>
				<span class="signUp-message">전화번호를 입력해주세요.(- 제외)</span>

				<div class="signUp-row">
					<label for="memberAddress">주소</label> <input type="text"
						id="sample4_postcode" name="memberAddress" placeholder="우편번호"
						maxlength="6" required>

					<button type="button" onclick="sample4_execDaumPostcode()">우편번호
						찾기</button>
				</div>

				<div class="signUp-row">
					<label for=""></label> <input type="text" id="sample4_roadAddress"
						name="memberAddress" placeholder="도로명주소" required>
				</div>

				<div class="signUp-row">
					<label for=""></label> <input type="text"
						id="sample4_detailAddress" name="memberAddress" placeholder="상세주소"
						required>
				</div>

				<div class="signUp-row">
					<button id="signUp-btn">가 입 하 기</button>
				</div>
			</form>
		</section>
	</main>
</body>
</html>