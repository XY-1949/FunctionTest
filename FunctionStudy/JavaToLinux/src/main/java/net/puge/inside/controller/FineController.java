package net.puge.inside.controller;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.puge.inside.entity.User;
import net.puge.inside.entity.vo.LinuxInfo;
import net.puge.inside.service.FineService;
import net.puge.inside.utils.R.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yinsd
 * @since 2022-03-29
 */
@Api(description = "清除系统缓存")
@RestController
@RequestMapping("/inside/fine")
public class FineController {

    @Autowired
    private FineService fineService;

    @Autowired
    private LinuxInfo linuxInfo;

    @GetMapping()
    public String getUserName() {
        String username = linuxInfo.getUsername();
        System.out.println(username);
        return username;
    }

    /**
     * 清除内网缓存
     * @return
     */
    @ApiOperation(value = "清除内网缓存")
    @GetMapping("/clearIntranet")
    public R clearIntranet(){
        fineService.clearIntranet();
        return R.ok();
    }

    /**
     * 清除部署缓存
     * @return
     */
    @ApiOperation(value = "清除部署缓存")
    @GetMapping("/clearDeploy")
    public R clearDedloy(){
        fineService.clearDeploy();
        return R.ok();
    }

    /**
     * 在Linux服务器上创建一个文件
     * @return
     */
    @ApiOperation(value = "添加一个新文件")
    @GetMapping("/addOneFile")
    public R addOneFile(){
        fineService.addOneFile();
        return R.ok();
    }

    /**
     * 获取所有的用户信息
     * @return
     */
    @ApiOperation(value = "")
    @GetMapping("/getAllUserInfo")
    public R getAllUserInfo(){
        List<User> list =fineService.getAllUserInfo();
        return R.ok().data("list",list);
    }

}


