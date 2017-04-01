package unit;

import com.jen.sen.shiro.ShiroUtils;

public class TestMD5 {

	public static void main(String[] args) {
		System.out.println("aaaaa:"+ShiroUtils.getpwdShiro("123456"));
		System.out.println("aaaaa:"+ShiroUtils.getpwdShiro("234567"));

	}

}
