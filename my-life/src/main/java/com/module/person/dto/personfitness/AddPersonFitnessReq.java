package com.module.person.dto.personfitness;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
*
* 健身计划
* @author generator
* @since 2023-01-10
*/
@Data
@ApiModel(value="PersonFitness新增对象", description="健身计划")
public class AddPersonFitnessReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "时间")
    private Date time;

    @ApiModelProperty(value = "饮食")
    private String diet;

    @ApiModelProperty(value = "运动")
    private String exercise;

    @ApiModelProperty(value = "版本")
    private String version;


}

