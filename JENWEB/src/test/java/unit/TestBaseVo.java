package unit;
import java.sql.Date;

import com.jen.sen.persistence.pojo.system.TUser;
import com.jen.sen.web.vo.system.UserVo;

public class TestBaseVo {

	public static void main(String[] args) {
		UserVo vo =new UserVo();
		vo.setUserId(1111L);

		vo.setUserEmail("jesnon@sjen.com");
		vo.setUpdateTime(Date.valueOf("2008-12-12"));
		
		TUser tu = vo.toPojo();
		
		System.out.println("aaaaaa:::"+tu.getUserId());
		System.out.println("bbbbbb:::"+tu.getUserEmail());
		System.out.println("cccccc:::"+tu.getUpdateTime());
		System.out.println("dddddd:::"+tu.getCreateTime());
		
		/** ------------------------  ***/
		TUser ta = new TUser();
		UserVo va =new UserVo();
		
		ta.setUserId(1111L);

		ta.setUserEmail("jesnon@sjen.com");
		ta.setUpdateTime(Date.valueOf("2008-12-12"));
		
		va.toVo(ta);
		
		System.out.println("zzzzzz:::"+va.getUserId());
		System.out.println("xxxxxxx:::"+va.getUserEmail());
		System.out.println("vvvvv:::"+va.getUpdateTime());
		System.out.println("bbbbb:::"+va.getCreateTime());
		
		
		
		
		
		
		
		

	}

	

}
