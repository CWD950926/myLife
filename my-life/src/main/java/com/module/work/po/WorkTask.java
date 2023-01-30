package com.module.work.po;

import java.util.Date;
import java.time.LocalDate;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 工作任务
 * @author generator
 * @since 2023-01-29
 */
@Data
@ApiModel(value="WorkTask对象", description="工作任务")
public class WorkTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "任务时间")
    private LocalDate time;

    @ApiModelProperty(value = "工作内容")
    private String taskDesc;

    @ApiModelProperty(value = "优先级")
    private String priority;

    @ApiModelProperty(value = "工作进度")
    private String workProcess;

    @ApiModelProperty(value = "版本")
    private String version;

    @ApiModelProperty(value = "创建时间")
    private Date createdTime;


}
