package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import com.result.EPage;
import com.module.${cfg.module}.dto.${entity?lower_case}.*;
import com.result.ResponseResult;

import java.util.List;

/**
 * ${table.comment!} 服务类
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {


    /**
     * 添加${table.comment!}
     * @param userId 用户id
     * @param req ${table.comment!}
     * @return int
     */
    ResponseResult<${entity}> add(Long userId, Add${entity}Req req);


    /**
     * 删除${table.comment!}
     * @param id 主键
     * @return int
     */
    ResponseResult delete(Long id);


    /**
    * 修改${table.comment!}
    * @param userId 用户id
    * @param req
    * @return int
    */
    ResponseResult updateData( Long userId, Update${entity}Req req);


    /**
     * id查询数据
     * @param id id
     * @return ${entity}
     */
    ResponseResult<${entity}Rsp> findById(Long id);

    ResponseResult<List<${entity}>> findList();

    ${entity}Rsp getByPo(${entity} po);


}
</#if>
