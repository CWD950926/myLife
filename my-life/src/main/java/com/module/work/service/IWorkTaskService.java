package com.module.work.service;

import com.module.work.po.WorkTask;
import com.baomidou.mybatisplus.extension.service.IService;
import com.result.EPage;
import com.module.work.dto.worktask.*;
import com.result.ResponseResult;



/**
 *  服务类
 * @author generator
 * @since 2023-01-10
 */
public interface IWorkTaskService extends IService<WorkTask> {


    /**
     * 添加
     * @param tenancyId 租户id
     * @param userId 用户id
     * @param req 
     * @return int
     */
    ResponseResult<WorkTask> add(Long tenancyId, Long userId, AddWorkTaskReq req);


    /**
     * 删除
     * @param tenancyId 租户id
     * @param id 主键
     * @return int
     */
    ResponseResult delete(Long tenancyId, Long id);


    /**
    * 修改
    * @param tenancyId 租户id
    * @param userId 用户id
    * @param req
    * @return int
    */
    ResponseResult updateData(Long tenancyId, Long userId, UpdateWorkTaskReq req);


    /**
     * id查询数据
     * @param tenancyId id
     * @param id id
     * @return WorkTask
     */
    ResponseResult<WorkTaskRsp> findById(Long tenancyId,Long id);

    /**
    * 查询分页数据
    * @param tenancyId id
    * @param page      页码
    * @param pageCount 每页条数
    * @return IPage<WorkTask>
    */
    ResponseResult<EPage<WorkTaskRsp>> findListByPage(Long tenancyId,Integer page, Integer pageCount,WorkTaskReq req);

    WorkTaskRsp getByPo(WorkTask po);
}
