package com.module.work.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.module.work.dao.WorkTodoMapper;
import com.module.work.dto.worktodo.AddWorkTodoReq;
import com.module.work.dto.worktodo.WorkTodoReq;
import com.module.work.dto.worktodo.WorkTodoRsp;
import com.module.work.dto.worktodo.UpdateWorkTodoReq;
import com.module.work.po.WorkTodo;
import com.module.work.service.IWorkTodoService;
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
 * 工作任务 服务实现类
 *
 * @author generator
 * @since 2023-03-01
 */
@Slf4j
@Service
public class WorkTodoServiceImpl extends ServiceImpl<WorkTodoMapper, WorkTodo> implements IWorkTodoService {

    @Override
    public ResponseResult<WorkTodo> addOrUpdate(Long userId, AddWorkTodoReq req) {
        Date currentDate = DateUtil.getCurrentDate();
        WorkTodo po = findByType(0);
        if (po == null) {
            po = new WorkTodo();
            BeanUtils.copyProperties(req, po);
            po.setId(IdUtil.genId());
            po.setCreatedTime(currentDate);
            baseMapper.insert(po);
        } else {
            po.setToDo(req.getToDo());
            baseMapper.updateById(po);
        }
        return ResponseResult.buildSuccess(po);
    }

    @Override
    public WorkTodo findByType(Integer type) {
        LambdaQueryWrapper<WorkTodo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WorkTodo::getType, type).last("limit 1");
        return baseMapper.selectOne(queryWrapper);
    }


}
