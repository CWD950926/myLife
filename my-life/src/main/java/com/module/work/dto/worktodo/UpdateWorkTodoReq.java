package com.module.work.dto.worktodo;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 工作任务
 *
 * @author generator
 * @since 2023-03-01
 */
@Data
@ApiModel(value="WorkTodo更新对象", description="工作任务")
public class UpdateWorkTodoReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "待办事项")
    private String toDo;


    @ApiModelProperty(value = "待办类型")
    private Integer type;


}
