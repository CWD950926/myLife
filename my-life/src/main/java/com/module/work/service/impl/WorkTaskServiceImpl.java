package com.module.work.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.module.work.dao.WorkTaskMapper;
import com.module.work.dto.worktask.AddWorkTaskReq;
import com.module.work.dto.worktask.UpdateWorkTaskReq;
import com.module.work.dto.worktask.WorkTaskRsp;
import com.module.work.po.WorkTask;
import com.module.work.service.IWorkTaskService;
import com.result.ResponseResult;
import com.util.DateUtil;
import com.util.idmaker.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * 服务实现类
 *
 * @author generator
 * @since 2023-01-10
 */
@Slf4j
@Service
public class WorkTaskServiceImpl extends ServiceImpl<WorkTaskMapper, WorkTask> implements IWorkTaskService {

    @Override
    public ResponseResult add( Long userId, AddWorkTaskReq req) {
        Date currentDate = DateUtil.getCurrentDate();
        if (CollectionUtils.isEmpty(req.getVersions())) {
            return ResponseResult.buildFail("未输入版本");
        }
        for (String version : req.getVersions()) {
            WorkTask po = new WorkTask();
            BeanUtils.copyProperties(req, po);
            if (req.getTime() == null) {
                po.setTime(DateUtil.date2LocalDate(currentDate));
            } else {
                po.setTime(req.getTime());
            }
            po.setId(IdUtil.genId());
            po.setCreatedTime(currentDate);
            po.setVersion(version);
            baseMapper.insert(po);
        }
        return ResponseResult.buildSuccess();

    }


    @Override
    public ResponseResult delete( Long id) {
        WorkTask po = baseMapper.selectById(id);
        if (po == null) {
            return ResponseResult.buildFail("数据不存在");
        }
        Integer effectRow = baseMapper.deleteById(id);
        return ResponseResult.buildSuccess(effectRow);
    }


    @Override
    public ResponseResult updateData( Long userId, UpdateWorkTaskReq req) {
        WorkTask po = baseMapper.selectById(req.getId());
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
    public ResponseResult<WorkTaskRsp> findById( Long id) {
        WorkTask po = baseMapper.selectById(id);
        WorkTaskRsp rsp = getByPo(po);
        return ResponseResult.buildSuccess(rsp);
    }

    @Override
    public ResponseResult<List<WorkTask>> findList() {
        LambdaQueryWrapper<WorkTask> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(WorkTask::getPriority).orderByAsc(WorkTask::getVersion);
        return ResponseResult.buildSuccess(baseMapper.selectList(queryWrapper));
    }


    @Override
    public WorkTaskRsp getByPo(WorkTask po) {
        if (po == null) {
            return null;
        }
        WorkTaskRsp rsp = new WorkTaskRsp();
        BeanUtils.copyProperties(po, rsp);
        return rsp;
    }
}
