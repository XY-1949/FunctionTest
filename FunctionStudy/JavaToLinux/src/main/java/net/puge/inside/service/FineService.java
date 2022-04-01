package net.puge.inside.service;

import net.puge.inside.entity.Fine;
import com.baomidou.mybatisplus.extension.service.IService;
import net.puge.inside.entity.User;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yinsd
 * @since 2022-03-29
 */
public interface FineService extends IService<Fine> {

    /**
     * 清除内网缓存
     * @return
     * @throws
     */
    void clearIntranet();

    /**
     * 清除部署缓存
     * @return
     * @throws IOException
     */
    void clearDeploy();

    /**
     * 在Linux服务器上创建一个文件a
     * @return
     */
    void addOneFile();

    /**
     * 获取所有的用户信息
     * @return
     */
    List<User> getAllUserInfo();
}
