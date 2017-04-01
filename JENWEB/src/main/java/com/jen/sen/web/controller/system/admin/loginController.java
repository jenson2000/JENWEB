package com.jen.sen.web.controller.system.admin;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.jen.sen.shiro.ShiroUtils;
import com.jen.sen.untils.Util;
import com.jen.sen.web.component.ajaxComponent;

@Controller
@RequestMapping(value = "system")
public class loginController {
	private final static String JSP_DIR = "system";

	@Resource
	private Producer KaptchaProducer;

	@RequestMapping("captcha.jpg")
	public void captcha(HttpServletResponse response) throws ServletException, IOException {
		response.setDateHeader("Expires", 0);// 禁止服务器端缓存
		// 设置标准的 HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		// 设置IE扩展 HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");// 设置标准 HTTP/1.0 不缓存图片
		response.setContentType("image/jpeg");

		// 生成文字验证码
		String text = KaptchaProducer.createText();
		// 生成图片验证码
		BufferedImage image = KaptchaProducer.createImage(text);
		// 保存到shiro session
		ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
	}

	/**
	 * //清除 shiro授权缓存,废弃,使用redis缓存,以spring缓存注解方式处理
	 * 
	 * @RequestMapping(value = "/clsCache", method = RequestMethod.GET) public
	 *                       void ClsCache(HttpServletRequest request,
	 *                       ModelAndView mv) { UserShiroRealm usr=
	 *                       (UserShiroRealm)SpringBeanTools.getBean("userRealm"
	 *                       ); usr.clearCachedAuthorizationInfo(ShiroUtils.
	 *                       getSubject().getPrincipal()); }
	 */

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLoginPage(HttpServletRequest request, ModelAndView mv) {
		mv.setViewName(JSP_DIR + "/sysLogin");
		return mv;
	}

	@RequestMapping(value = "/main")

	public ModelAndView loginSuccessPage(HttpServletRequest request, ModelAndView mv) {
		mv.setViewName(JSP_DIR + "/sysMain");
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ajaxComponent<?> loginForm(HttpServletRequest request, ModelAndView mv) {
		ajaxComponent<?> ajaxComponent = new ajaxComponent<>();
		String error = null;

		String account = Util.getParameterString(request,"account");
		String pwd = Util.getParameterString(request,"password");
		String captcha = Util.getParameterString(request,"captcha");

		String captchasession = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);

		if (!captcha.equalsIgnoreCase(captchasession)) {
			error = "验证码不正确";
			ajaxComponent.setMsg(error);
			return ajaxComponent;
		}

		// 加密密码
		String password = ShiroUtils.getpwdShiro(pwd);
		Subject subject = ShiroUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(account, password);
		try {
			subject.login(token);

		} catch (UnknownAccountException e) {
			error = e.getMessage();
		} catch (IncorrectCredentialsException e) {
			error = e.getMessage();
		} catch (LockedAccountException e) {
			error = e.getMessage();
		} catch (AuthenticationException e) {
			// 其他错误，比如锁定，如果想单独处理请单独catch处理
			error = "账户验证失败：" + e.getMessage();
		}
		ajaxComponent.setMsg(error);

		return ajaxComponent;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, ModelAndView mv) {
		// 使用权限管理工具进行用户的退出，跳出登录，给出提示信息
		SecurityUtils.getSubject().logout();
		mv.setViewName(JSP_DIR + "/sysLogin");
		return mv;
	}

}
