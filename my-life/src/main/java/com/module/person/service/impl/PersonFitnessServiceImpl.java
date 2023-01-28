package com.module.person.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.module.person.dao.PersonFitnessMapper;
import com.module.person.dto.personfitness.AddPersonFitnessReq;
import com.module.person.dto.personfitness.PersonFitnessReq;
import com.module.person.dto.personfitness.PersonFitnessRsp;
import com.module.person.dto.personfitness.UpdatePersonFitnessReq;
import com.module.person.po.PersonFitness;
import com.module.person.service.IPersonFitnessService;
import com.result.EPage;
import com.result.ResponseResult;
import com.util.DateUtil;
import com.util.idmaker.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 健身计划 服务实现类
 *
 * @author generator
 * @since 2023-01-10
 */
@Slf4j
@Service
public class PersonFitnessServiceImpl extends ServiceImpl<PersonFitnessMapper, PersonFitness> implements IPersonFitnessService {

    @Override
    public ResponseResult<PersonFitness> add(Long tenancyId, Long userId, AddPersonFitnessReq req){
        Date currentDate = DateUtil.getCurrentDate();
        PersonFitness po = new PersonFitness();
        BeanUtils.copyProperties( req,po);
        po.setId(IdUtil.genId());
        int effectRow = baseMapper.insert(po);
        if (effectRow > 0) {
            return ResponseResult.buildSuccess(po);
        } else {
            return ResponseResult.buildFail("新增失败");
        }
    }


    @Override
    public ResponseResult delete(Long tenancyId, Long id) {
        PersonFitness po = baseMapper.selectById(id);
        if (po == null) {
            return ResponseResult.buildFail("数据不存在");
        }
        Integer effectRow = baseMapper.deleteById(id);
        return ResponseResult.buildSuccess(effectRow);
    }


    @Override
    public ResponseResult updateData(Long tenancyId, Long userId, UpdatePersonFitnessReq req){
        PersonFitness po = baseMapper.selectById(req.getId());
        if (po == null) {
            return ResponseResult.buildFail("数据不存在");
        }

        BeanUtils.copyProperties(req, po);;
        Integer effectRow = baseMapper.updateById(po);
        if (effectRow > 0) {
            return ResponseResult.buildSuccess(effectRow);
        } else {
            return ResponseResult.buildFail("更新失败");
        }
    }




    @Override
    public ResponseResult<PersonFitnessRsp> findById(Long tenancyId,Long id){
        PersonFitness po = baseMapper.selectById(id);
        PersonFitnessRsp rsp = getByPo(po);
        return ResponseResult.buildSuccess(rsp);
    }

    @Override
    public  ResponseResult<EPage<PersonFitnessRsp>> findListByPage(Long tenancyId,Integer page, Integer pageCount,PersonFitnessReq req){
        LambdaQueryWrapper<PersonFitness> queryWrapper = new LambdaQueryWrapper<>(req);
        IPage<PersonFitness> wherePage = new Page<>(page, pageCount);
        IPage<PersonFitness> poList = baseMapper.selectPage(wherePage, queryWrapper);
        List<PersonFitnessRsp> rspList = new ArrayList<>();
        if (poList != null) {
            List<PersonFitness> list = poList.getRecords();
            if (list!=null&&list.size()>0){
                for (PersonFitness po:list){
                    PersonFitnessRsp rsp = getByPo(po);
                    rspList.add(rsp);
                }
            }
        }
        EPage ePage = new EPage((int) wherePage.getTotal(), (int) wherePage.getPages(), (int) wherePage.getCurrent(), (int) wherePage.getSize(), rspList);
        return ResponseResult.buildSuccess(ePage);
    }


    @Override
    public PersonFitnessRsp getByPo(PersonFitness po) {
        if (po == null) {
            return null;
        }
        PersonFitnessRsp rsp = new PersonFitnessRsp();
        BeanUtils.copyProperties(po, rsp);
        return rsp;
    }
}
