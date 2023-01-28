package ${package.ServiceImpl};

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ${package.Mapper}.${table.mapperName};
import com.module.${cfg.module}.dto.${entity?lower_case}.Add${entity}Req;
import com.module.${cfg.module}.dto.${entity?lower_case}.${entity}Req;
import com.module.${cfg.module}.dto.${entity?lower_case}.${entity}Rsp;
import com.module.${cfg.module}.dto.${entity?lower_case}.Update${entity}Req;
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
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
 * ${table.comment!} 服务实现类
 *
 * @author ${author}
 * @since ${date}
 */
@Slf4j
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    @Override
    public ResponseResult<${entity}> add(Long tenancyId, Long userId, Add${entity}Req req){
        Date currentDate = DateUtil.getCurrentDate();
        ${entity} po = new ${entity}();
        BeanUtils.copyProperties( req,po);
        po.setId(IdUtil.genId());
        po.setCreatedBy(userId);
        po.setUpdatedBy(userId);
        po.setCreatedTime(currentDate);
        po.setUpdatedTime(currentDate);
        int effectRow = baseMapper.insert(po);
        if (effectRow > 0) {
            return ResponseResult.buildSuccess(po);
        } else {
            return ResponseResult.buildFail("新增失败");
        }
    }


    @Override
    public ResponseResult delete(Long tenancyId, Long id) {
        ${entity} po = baseMapper.selectById(id);
        if (po == null) {
            return ResponseResult.buildFail("数据不存在");
        }
        Integer effectRow = baseMapper.deleteById(id);
        return ResponseResult.buildSuccess(effectRow);
    }


    @Override
    public ResponseResult updateData(Long tenancyId, Long userId, Update${entity}Req req){
        ${entity} po = baseMapper.selectById(req.getId());
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
    public ResponseResult<${entity}Rsp> findById(Long tenancyId,Long id){
        ${entity} po = baseMapper.selectById(id);
        ${entity}Rsp rsp = getByPo(po);
        return ResponseResult.buildSuccess(rsp);
    }

    @Override
    public  ResponseResult<EPage<${entity}Rsp>> findListByPage(Long tenancyId,Integer page, Integer pageCount,${entity}Req req){
        LambdaQueryWrapper<${entity}> queryWrapper = new LambdaQueryWrapper<>(req);
        IPage<${entity}> wherePage = new Page<>(page, pageCount);
        IPage<${entity}> poList = baseMapper.selectPage(wherePage, queryWrapper);
        List<${entity}Rsp> rspList = new ArrayList<>();
        if (poList != null) {
            List<${entity}> list = poList.getRecords();
            if (list!=null&&list.size()>0){
                for (${entity} po:list){
                    ${entity}Rsp rsp = getByPo(po);
                    rspList.add(rsp);
                }
            }
        }
        EPage ePage = new EPage((int) wherePage.getTotal(), (int) wherePage.getPages(), (int) wherePage.getCurrent(), (int) wherePage.getSize(), rspList);
        return ResponseResult.buildSuccess(ePage);
    }


    @Override
    public ${entity}Rsp getByPo(${entity} po) {
        if (po == null) {
            return null;
        }
        ${entity}Rsp rsp = new ${entity}Rsp();
        BeanUtils.copyProperties(po, rsp);
        return rsp;
    }
}
</#if>