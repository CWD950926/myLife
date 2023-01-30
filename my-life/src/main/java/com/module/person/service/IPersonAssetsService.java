package com.module.person.service;

import com.module.person.po.PersonAssets;
import com.baomidou.mybatisplus.extension.service.IService;
import com.result.EPage;
import com.module.person.dto.personassets.*;
import com.result.ResponseResult;

import java.util.List;


/**
 * 个人资产 服务类
 * @author generator
 * @since 2023-01-30
 */
public interface IPersonAssetsService extends IService<PersonAssets> {


    /**
     * 添加个人资产
     * @param userId 用户id
     * @param req 个人资产
     * @return int
     */
    ResponseResult<PersonAssets> add( Long userId, AddPersonAssetsReq req);


    /**
     * 删除个人资产

     * @param id 主键
     * @return int
     */
    ResponseResult delete( Long id);


    /**
    * 修改个人资产
    * @param userId 用户id
    * @param req
    * @return int
    */
    ResponseResult updateData( Long userId, UpdatePersonAssetsReq req);


    /**
     * id查询数据
     * @param id id
     * @return PersonAssets
     */
    ResponseResult<PersonAssetsRsp> findById(Long id);

    ResponseResult<List<PersonAssets>> findList();
    PersonAssetsRsp getByPo(PersonAssets po);
}
