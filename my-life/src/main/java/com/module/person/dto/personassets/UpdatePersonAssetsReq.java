package com.module.person.dto.personassets;

import java.math.BigDecimal;
import java.util.Date;
import java.time.LocalDate;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 个人资产
 *
 * @author generator
 * @since 2023-01-30
 */
@Data
@ApiModel(value="PersonAssets更新对象", description="个人资产")
public class UpdatePersonAssetsReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "记录时间")
    private LocalDate time;

    @ApiModelProperty(value = "微信余额")
    private BigDecimal wx;

    @ApiModelProperty(value = "支付宝")
    private BigDecimal alipay;

    @ApiModelProperty(value = "建设银行")
    private BigDecimal construction;

    @ApiModelProperty(value = "工商银行")
    private BigDecimal industrialAndCommercial;

    @ApiModelProperty(value = "招商银行")
    private BigDecimal merchants;

    @ApiModelProperty(value = "中国银行")
    private BigDecimal china;

    @ApiModelProperty(value = "借别人的钱总和")
    private BigDecimal borrow;

    @ApiModelProperty(value = "借款描述")
    private String borrowDesc;



}
