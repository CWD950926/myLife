package com.module.person.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.module.person.dao.PersonAssetsMapper;
import com.module.person.dto.personassets.AddPersonAssetsReq;
import com.module.person.dto.personassets.PersonAssetsRsp;
import com.module.person.dto.personassets.UpdatePersonAssetsReq;
import com.module.person.po.PersonAssets;
import com.module.person.service.IPersonAssetsService;
import com.result.ResponseResult;
import com.util.DateUtil;
import com.util.idmaker.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * 个人资产 服务实现类
 *
 * @author generator
 * @since 2023-01-30
 */
@Slf4j
@Service
public class PersonAssetsServiceImpl extends ServiceImpl<PersonAssetsMapper, PersonAssets> implements IPersonAssetsService {

    @Override
    public ResponseResult<PersonAssets> add(Long userId, AddPersonAssetsReq req) {
        Date currentDate = DateUtil.getCurrentDate();
        PersonAssets po = new PersonAssets();
        BeanUtils.copyProperties(req, po);
        po.setId(IdUtil.genId());
        po.setCreatedTime(currentDate);

        //计算总和
        BigDecimal total = req.getWx().add(req.getAlipay()).add(req.getConstruction())
                .add(req.getIndustrialAndCommercial()).add(req.getMerchants())
                .add(req.getChina()).add(req.getEastMoney()).add(req.getCitics());
        po.setTotal(total);

        int effectRow = baseMapper.insert(po);
        if (effectRow > 0) {
            return ResponseResult.buildSuccess(po);
        } else {
            return ResponseResult.buildFail("新增失败");
        }
    }


    @Override
    public ResponseResult delete(Long id) {
        PersonAssets po = baseMapper.selectById(id);
        if (po == null) {
            return ResponseResult.buildFail("数据不存在");
        }
        Integer effectRow = baseMapper.deleteById(id);
        return ResponseResult.buildSuccess(effectRow);
    }


    @Override
    public ResponseResult updateData(Long userId, UpdatePersonAssetsReq req) {
        PersonAssets po = baseMapper.selectById(req.getId());
        if (po == null) {
            return ResponseResult.buildFail("数据不存在");
        }

        BeanUtils.copyProperties(req, po);
        Integer effectRow = baseMapper.updateById(po);
        if (effectRow > 0) {
            return ResponseResult.buildSuccess(effectRow);
        } else {
            return ResponseResult.buildFail("更新失败");
        }
    }


    @Override
    public ResponseResult<PersonAssetsRsp> findById(Long id) {
        PersonAssets po = baseMapper.selectById(id);
        PersonAssetsRsp rsp = getByPo(po);
        return ResponseResult.buildSuccess(rsp);
    }

    @Override
    public ResponseResult<List<PersonAssets>> findList() {
        LambdaQueryWrapper<PersonAssets> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(PersonAssets::getTime);
        return ResponseResult.buildSuccess(baseMapper.selectList(queryWrapper));
    }


    @Override
    public PersonAssetsRsp getByPo(PersonAssets po) {
        if (po == null) {
            return null;
        }
        PersonAssetsRsp rsp = new PersonAssetsRsp();
        BeanUtils.copyProperties(po, rsp);
        return rsp;
    }
}
