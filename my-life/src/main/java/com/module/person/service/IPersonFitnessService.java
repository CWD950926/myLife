package com.module.person.service;

import com.module.person.po.PersonFitness;
import com.baomidou.mybatisplus.extension.service.IService;
import com.result.EPage;
import com.module.person.dto.personfitness.*;
import com.result.ResponseResult;

import java.util.List;

/**
 * 健身计划 服务类
 * @author generator
 * @since 2023-01-31
 */
public interface IPersonFitnessService extends IService<PersonFitness> {


    /**
     * 添加健身计划
     * @param userId 用户id
     * @param req 健身计划
     * @return int
     */
    ResponseResult<PersonFitness> add(Long userId, AddPersonFitnessReq req);


    /**
     * 删除健身计划
     * @param id 主键
     * @return int
     */
    ResponseResult delete(Long id);


    /**
    * 修改健身计划
    * @param userId 用户id
    * @param req
    * @return int
    */
    ResponseResult updateData( Long userId, UpdatePersonFitnessReq req);


    /**
     * id查询数据
     * @param id id
     * @return PersonFitness
     */
    ResponseResult<PersonFitnessRsp> findById(Long id);

    ResponseResult<List<PersonFitness>> findList();

    PersonFitnessRsp getByPo(PersonFitness po);


}
