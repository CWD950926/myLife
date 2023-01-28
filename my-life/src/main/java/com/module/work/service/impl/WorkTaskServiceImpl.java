package com.module.work.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.module.work.dao.WorkTaskMapper;
import com.module.work.dto.worktask.AddWorkTaskReq;
import com.module.work.dto.worktask.WorkTaskReq;
import com.module.work.dto.worktask.WorkTaskRsp;
import com.module.work.dto.worktask.UpdateWorkTaskReq;
import com.module.work.po.WorkTask;
import com.module.work.service.IWorkTaskService;
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
 *  服务实现类
 *
 * @author generator
 * @since 2023-01-10
 */
@Slf4j
@Service
public class WorkTaskServiceImpl extends ServiceImpl<WorkTaskMapper, WorkTask> implements IWorkTaskService {

    @Override
    public ResponseResult<WorkTask> add(Long tenancyId, Long userId, AddWorkTaskReq req){
        Date currentDate = DateUtil.getCurrentDate();
        WorkTask po = new WorkTask();
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
        WorkTask po = baseMapper.selectById(id);
        if (po == null) {
            return ResponseResult.buildFail("数据不存在");
        }
        Integer effectRow = baseMapper.deleteById(id);
        return ResponseResult.buildSuccess(effectRow);
    }


    @Override
    public ResponseResult updateData(Long tenancyId, Long userId, UpdateWorkTaskReq req){
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
    public ResponseResult<WorkTaskRsp> findById(Long tenancyId,Long id){
        WorkTask po = baseMapper.selectById(id);
        WorkTaskRsp rsp = getByPo(po);
        return ResponseResult.buildSuccess(rsp);
    }

    @Override
    public  ResponseResult<EPage<WorkTaskRsp>> findListByPage(Long tenancyId,Integer page, Integer pageCount,WorkTaskReq req){
        QueryWrapper<WorkTask> queryWrapper = new QueryWrapper<>(req);
        IPage<WorkTask> wherePage = new Page<>(page, pageCount);
        IPage<WorkTask> poList = baseMapper.selectPage(wherePage, queryWrapper);
        List<WorkTaskRsp> rspList = new ArrayList<>();
        if (poList != null) {
            List<WorkTask> list = poList.getRecords();
            if (list!=null&&list.size()>0){
                for (WorkTask po:list){
                    WorkTaskRsp rsp = getByPo(po);
                    rspList.add(rsp);
                }
            }
        }
        EPage ePage = new EPage((int) wherePage.getTotal(), (int) wherePage.getPages(), (int) wherePage.getCurrent(), (int) wherePage.getSize(), rspList);
        return ResponseResult.buildSuccess(ePage);
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
