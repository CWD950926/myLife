package com.module.work.service;

import com.module.work.po.WorkTodo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.result.EPage;
import com.module.work.dto.worktodo.*;
import com.result.ResponseResult;

import java.util.List;

/**
 * 工作任务 服务类
 *
 * @author generator
 * @since 2023-03-01
 */
public interface IWorkTodoService extends IService<WorkTodo> {


    /**
     * 添加工作任务
     *
     * @param userId 用户id
     * @param req    工作任务
     * @return int
     */
    ResponseResult<WorkTodo> addOrUpdate(Long userId, AddWorkTodoReq req);


    WorkTodo findByType(Integer type);
}
