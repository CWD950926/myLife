package com.module.work.dto.worktask;

import java.time.LocalDate;
import java.util.Date;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
*
* 
* @author generator
* @since 2023-01-10
*/
@Data
@ApiModel(value="WorkTask新增对象", description="")
public class AddWorkTaskReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "工作内容")
    private String taskDesc;

    @ApiModelProperty(value = "优先级")
    private String priority;

    @ApiModelProperty(value = "工作进度")
    private String workProcess;

    @ApiModelProperty(value = "版本")
    private List<String> versions;
    @ApiModelProperty(value = "任务时间")
    private LocalDate time;

}

