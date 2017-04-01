package com.jen.sen.exception;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.jen.sen.commons.Constant;
import com.jen.sen.untils.DateUtils;

/**
 * 统一异常处理,针对control
 * filename JenExceptionHandler.java
 * company jen
 * @author jenson
 * @email jenson2000@sina.com
 */
@ControllerAdvice(basePackages = "com.jen.sen.web.controller")
public class JenExceptionHandler {
	private static final Logger logger = LogManager.getLogger(JenExceptionHandler.class);

	/**
	 * ajax统一异常处理,返回json字符串,抛出AjaxException
	 */
	@ExceptionHandler(AjaxException.class)
	@ResponseBody
	public void ajaxException(HttpServletResponse response, AjaxException ex) throws IOException {
		logger.error("************* ------ 异常信息记录（ajaxException:" + DateUtils.toDateTime(new Date())
				+ "） ------- ***********");
		logger.error(ex.getMessage(), ex);
		response.getWriter().write(ex.getErrMsg());

	}

	/**
	 * web统一异常处理,返回出错页面(非ajax调用,抛出Exception)
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView WebException(Exception ex, WebRequest request) {
		logger.error("************* ------ 异常信息记录（WebException:" + DateUtils.toDateTime(new Date())
				+ "） ------- ***********");
		logger.error(ex.getMessage(), ex);

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("ex", ex.getMessage());
		if (ex instanceof NumberFormatException) {
			model.put("code", "数据类型转换异常");
		} else if (ex instanceof NullPointerException) {
			model.put("code", "空指针异常");
		} else if (ex instanceof IOException) {
			model.put("code", "IO异常");
		} else if (ex instanceof SocketTimeoutException || ex instanceof ConnectException) {
			model.put("code", "网络异常");
		} else {
			model.put("code", "内部服务器异常");
		}

		ModelAndView mv = new ModelAndView();
		mv.addObject("message", model);
		mv.setViewName("errorPage");

		return mv;
	}
	
	/**
     * 如果抛出UnauthorizedException，将被该异常处理器截获来显示没有权限信息
     */
    @ExceptionHandler({ UnauthorizedException.class })

    public ModelAndView UnauthorizedException(WebRequest request,
        UnauthorizedException ex) {
    	logger.error("========授权异常,没有权限=============");
    	Map<String, Object> model = new HashMap<String, Object>();
		model.put("ex", ex.getMessage());
		
		model.put("code", "您请求的页面未授权");
    	
    ModelAndView mv = new ModelAndView();
    mv.addObject("message", model);
    mv.setViewName("errorPage");
    return mv;
    }
    
    /**
     * 如果抛出UnauthenticatedException，将被该异常处理器截获来未正常登陆
     */
    @ExceptionHandler({ UnauthenticatedException.class})
 
    public ModelAndView UnauthenticatedException(WebRequest request,
        UnauthorizedException ex) {
    	logger.error("========登录异常,没有权限=============");
    	Map<String, Object> model = new HashMap<String, Object>();
		model.put("ex", ex.getMessage());
		
		model.put("code", "请求失败,请登录!");
    	
    ModelAndView mv = new ModelAndView();
    mv.addObject("message", model);
    mv.setViewName("errorPage");
    return mv;
    }
    
    

}
