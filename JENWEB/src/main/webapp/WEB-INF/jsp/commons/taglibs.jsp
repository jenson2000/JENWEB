<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="webTagTools" uri="/WEB-INF/tld/WebPath.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="com.jen.sen.commons.Constant"%>

<% 
//去除JSP缓存
	response.setHeader("Pragma", "No-cache");   
	response.setHeader("Cache-Control", "no-cache");   
	response.setDateHeader("Expires", 0); 
%>
