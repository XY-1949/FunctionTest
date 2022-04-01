package net.puge.inside.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Yinsd
 * @version 1.0
 * Only code and time last forever
 * @date 2022/4/1 3:41 PM
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
@ApiModel(value="User对象", description="用户信息表")
public class User implements Serializable {

        private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "用户 ID")
        @TableId(value = "id", type = IdType.ID_WORKER_STR)
        private String id;

        @ApiModelProperty(value = "用户名")
        private String username;

        @ApiModelProperty(value = "密码，加密存储, 返回时排除此字段")
        private String password;

        @ApiModelProperty(value = "帐户是否过期(1 未过期，0已过期)")
        private Integer isAccountNonExpired;

        @ApiModelProperty(value = "帐户是否被锁定(1 未过期，0已过期)")
        private Integer isAccountNonLocked;

        @ApiModelProperty(value = "密码是否过期(1 未过期，0已过期)")
        private Integer isCredentialsNonExpired;

        @ApiModelProperty(value = "帐户是否可用(1 可用，0 删除用户)")
        private Integer isEnabled;

        @ApiModelProperty(value = "姓名")
        private String nickName;

        @ApiModelProperty(value = "密码更新时间")
        private Date pwdUpdateDate;

        @ApiModelProperty(value = "普歌工号")
        private String pugeNumber;

        @ApiModelProperty(value = "普歌网名")
        private String pugeName;

        @ApiModelProperty(value = "普歌头像")
        private String pugeAvater;

        @ApiModelProperty(value = "个性签名")
        private String pugeSignature;

        @ApiModelProperty(value = "年龄")
        private String pugeSex;

        @ApiModelProperty(value = "生日")
        private String pugeBirthday;

        @ApiModelProperty(value = "普歌企业邮箱")
        private String pugeEmail;

        @ApiModelProperty(value = "手机号")
        private String mobile;

        @ApiModelProperty(value = "年级")
        private String schoolAge;

        @ApiModelProperty(value = "逻辑删除")
        private Integer showStatus;

        @ApiModelProperty(value = "普歌级别")
        private String pugeLevel;

        @ApiModelProperty(value = "创建时间")
        @TableField(fill = FieldFill.INSERT)
        private Date gmtCreate;

        @ApiModelProperty(value = "更新时间")
        @TableField(fill = FieldFill.INSERT_UPDATE)
        private Date gmtModified;

    }
