package net.puge.inside.utils.R;

import io.swagger.annotations.ApiModelProperty;

/**
 * 返回状态码
 */
public interface ResultCode {


    @ApiModelProperty("成功")
    public static Integer SUCCESS = 20000;

    @ApiModelProperty("失败")
    public static Integer ERROR = 20001;

    @ApiModelProperty("未登录，无效token")
    public static Integer TOKEN = 50014;


    @ApiModelProperty("登录过期")
    public static Integer TOKEN_OVERDUE = 50015;

    @ApiModelProperty("非法token")
    public static Integer TOKEN_ILLEGAL = 50016;

}
