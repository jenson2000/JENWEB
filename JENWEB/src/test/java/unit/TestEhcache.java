package unit;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;

import com.jen.sen.persistence.core.dao.impl.FrameCenterDaoImpl;
import com.jen.sen.persistence.dao.system.IUserDao;
import com.jen.sen.persistence.pojo.system.TUser;

public class TestEhcache extends FrameCenterDaoImpl<TUser> {
	
	@Resource
	private IUserDao iUserDao;
	
	 //这里
    @SuppressWarnings("unchecked")
	@Cacheable(value = "baseCache")
    public TUser getUserByDeviceId(String deviceId) {
        if (deviceId != null) {
            List<TUser> list = (List<TUser>) getHibernateTemplate()
                    .find("From TUser u where u.id='" + deviceId
                            + "'");
            if (list != null && list.size() > 0) {
                return list.get(0);
            }
        }
        return null;
    }

}
