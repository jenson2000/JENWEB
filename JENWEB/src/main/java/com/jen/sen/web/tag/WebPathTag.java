package com.jen.sen.web.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 
 * filename WebPathTag.java
 * company jen
 * @author jenson
 * @email jenson2000@sina.com
 */
public class WebPathTag extends TagSupport {

	private static final long serialVersionUID = 3092155688159820077L;
	/**
	 * 上下文中之下的相对路径地址
	 * */
	private String pathAddress;

	public String getPathAddress() {
		return pathAddress;
	}

	public void setPathAddress(String pathAddress) {
		this.pathAddress = pathAddress;
	}

	@Override
	public int doEndTag() throws JspException {
		return super.doEndTag();
	}

	@Override
	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		String fullPath = null;
		String ctxPath = request.getContextPath();
		if (!ctxPath.startsWith("/")) {
			ctxPath = "/" + ctxPath;
		}
		if (!ctxPath.endsWith("/")) {
			ctxPath += "/";
		}

		if ("".equals(this.getPathAddress().trim())) {
			fullPath = ctxPath;
		} else {
			fullPath = ctxPath + this.getPathAddress() + "/";
		}

		try {
			pageContext.getOut().print(fullPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
}
