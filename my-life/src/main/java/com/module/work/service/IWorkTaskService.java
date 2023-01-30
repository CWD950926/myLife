package com.module.work.service;

import com.module.work.po.WorkTask;
import com.baomidou.mybatisplus.extension.service.IService;
import com.result.EPage;
import com.module.work.dto.worktask.*;
import com.result.ResponseResult;

import java.util.List;


/**
 *  服务类
 * @author generator
 * @since 2023-01-10
 */
public interface IWorkTaskService extends IService<WorkTask> {


    /**
     * 添加

     * @param userId 用户id
     * @param req 
     * @return int
     */
    ResponseResult add(Long userId, AddWorkTaskReq req);


    /**
     * @param id 主键
     * @return int
     */
    ResponseResult delete( Long id);


    /**
    * 修改

    * @param userId 用户id
    * @param req
    * @return int
    */
    ResponseResult updateData( Long userId, UpdateWorkTaskReq req);


    /**
     * id查询数据

     * @param id id
     * @return WorkTask
     */
    ResponseResult<WorkTaskRsp> findById(Long id);


    ResponseResult<List<WorkTask>> findList();

    WorkTaskRsp getByPo(WorkTask po);
}
