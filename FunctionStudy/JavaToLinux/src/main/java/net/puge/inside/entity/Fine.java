package net.puge.inside.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author yinsd
 * @since 2022-03-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("puge_fine")
@ApiModel(value="Fine对象", description="")
public class Fine implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "处罚金额")
    private BigDecimal finePrize;

    @ApiModelProperty(value = "处罚人id")
    private String finePersonId;

    @ApiModelProperty(value = "处罚人姓名")
    private String finePersonName;

    @ApiModelProperty(value = "被处罚人id")
    private String fineById;

    @ApiModelProperty(value = "被处罚人姓名")
    private String fineByName;

    @ApiModelProperty(value = "出具处罚单部门")
    private String fineDepartment;

    @ApiModelProperty(value = "处罚原因")
    private String fineReason;

    @ApiModelProperty(value = "支付状态（0初始状态;1 支付完成;2支付失败）")
    private String payStatus;

    @ApiModelProperty(value = "支付时间")
    private Date payTime;

    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    private Date gmtModified;


}
