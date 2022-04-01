package net.puge.inside.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Yinsd
 * @version 1.0
 * Only code and time last forever
 * @date 2022/3/23 10:30 PM
 */
@Data
@Component
@ConfigurationProperties(prefix = "ftp.linux")
public class LinuxInfo {

    @ApiModelProperty(value = "服务器IP地址")
    private String ip;

    @ApiModelProperty(value = "服务器端口号")
    private Integer port;

    @ApiModelProperty(value = "服务器用户名")
    private String username;

    @ApiModelProperty(value = "服务器密码")
    private String password;
}
