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
    public ResponseResult<${entity}> add( Long userId, Add${entity}Req req){
        Date currentDate = DateUtil.getCurrentDate();
        ${entity} po = new ${entity}();
        BeanUtils.copyProperties( req,po);
        po.setId(IdUtil.genId());
        po.setCreatedTime(currentDate);
        int effectRow = baseMapper.insert(po);
        if (effectRow > 0) {
            return ResponseResult.buildSuccess(po);
        } else {
            return ResponseResult.buildFail("新增失败");
        }
    }


    @Override
    public ResponseResult delete( Long id) {
        ${entity} po = baseMapper.selectById(id);
        if (po == null) {
            return ResponseResult.buildFail("数据不存在");
        }
        Integer effectRow = baseMapper.deleteById(id);
        return ResponseResult.buildSuccess(effectRow);
    }


    @Override
    public ResponseResult updateData( Long userId, Update${entity}Req req){
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
    public ResponseResult<${entity}Rsp> findById(Long id){
        ${entity} po = baseMapper.selectById(id);
        ${entity}Rsp rsp = getByPo(po);
        return ResponseResult.buildSuccess(rsp);
    }

    @Override
    public ResponseResult<List<${entity}>> findList() {
        LambdaQueryWrapper<${entity}> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.orderByDesc(${entity}::getTime);
            return ResponseResult.buildSuccess(baseMapper.selectList(queryWrapper));
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