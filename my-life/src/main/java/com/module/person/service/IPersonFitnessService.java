package com.module.person.service;

import com.module.person.po.PersonFitness;
import com.baomidou.mybatisplus.extension.service.IService;
import com.result.EPage;
import com.module.person.dto.personfitness.*;
import com.result.ResponseResult;



/**
 * 健身计划 服务类
 * @author generator
 * @since 2023-01-10
 */
public interface IPersonFitnessService extends IService<PersonFitness> {


    /**
     * 添加健身计划
     * @param tenancyId 租户id
     * @param userId 用户id
     * @param req 健身计划
     * @return int
     */
    ResponseResult<PersonFitness> add(Long tenancyId, Long userId, AddPersonFitnessReq req);


    /**
     * 删除健身计划
     * @param tenancyId 租户id
     * @param id 主键
     * @return int
     */
    ResponseResult delete(Long tenancyId, Long id);


    /**
    * 修改健身计划
    * @param tenancyId 租户id
    * @param userId 用户id
    * @param req
    * @return int
    */
    ResponseResult updateData(Long tenancyId, Long userId, UpdatePersonFitnessReq req);


    /**
     * id查询数据
     * @param tenancyId id
     * @param id id
     * @return PersonFitness
     */
    ResponseResult<PersonFitnessRsp> findById(Long tenancyId,Long id);

    /**
    * 查询健身计划分页数据
    * @param tenancyId id
    * @param page      页码
    * @param pageCount 每页条数
    * @return IPage<PersonFitness>
    */
    ResponseResult<EPage<PersonFitnessRsp>> findListByPage(Long tenancyId,Integer page, Integer pageCount,PersonFitnessReq req);

    PersonFitnessRsp getByPo(PersonFitness po);
}
