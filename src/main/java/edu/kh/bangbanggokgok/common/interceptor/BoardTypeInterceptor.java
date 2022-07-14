package edu.kh.bangbanggokgok.common.interceptor;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import edu.kh.bangbanggokgok.service.board.LandMarkService;
import edu.kh.bangbanggokgok.vo.board.BoardType;

public class BoardTypeInterceptor implements HandlerInterceptor{
	
	private Logger logger = LoggerFactory.getLogger(BoardTypeInterceptor.class);
	
	@Autowired
	private LandMarkService service;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	throws Exception{
		// 전처리
		
		ServletContext application = request.getServletContext();
		
		if(application.getAttribute("boardTypeList") == null) {
			List<BoardType> boardTypeList = service.selectBoardType();
		}
			
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	

}