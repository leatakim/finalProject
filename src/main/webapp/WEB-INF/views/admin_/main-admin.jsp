<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 임시메인</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/admin_/adminNav.css">
</head>
<body>
    <div class="adminNav">
            <ul class="listWrapper">
                <div class="adminList">Admin</div>
                <li id="adminList">
                    <i class="fal fa-clipboard-list-check"></i> 공지사항
                    <div>
                        <a href="${contextPath}/notice/list">공지/이벤트 조회</a><br>
                        <a href="${contextPath}/admin/notice/write">공지/이벤트 작성</a><br>
                    </div>
                </li>
                <li id="adminList">
                    <a href="${contextPath}/admin/user/list"><i class="fal fa-user-friends"></i> 회원관리</a>
                </li>
                <li id="adminList">
                    <a href="${contextPath}/admin/inquiry/list"><i class="fal fa-map-marker-question"></i> 1:1 문의조회</a>
                </li>
                <li id="adminList">
                    <a href="${contextPath}/admin/report/list"><i class="fal fa-siren-on"></i> 신고관리</a>
                </li>
            </ul>

        </div>
</body>
</html>