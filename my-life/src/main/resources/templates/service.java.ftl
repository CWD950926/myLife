package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import com.result.EPage;
import com.module.${cfg.module}.dto.${entity?lower_case}.*;
import com.result.ResponseResult;



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
     * @param tenancyId 租户id
     * @param userId 用户id
     * @param req ${table.comment!}
     * @return int
     */
    ResponseResult<${entity}> add(Long tenancyId, Long userId, Add${entity}Req req);


    /**
     * 删除${table.comment!}
     * @param tenancyId 租户id
     * @param id 主键
     * @return int
     */
    ResponseResult delete(Long tenancyId, Long id);


    /**
    * 修改${table.comment!}
    * @param tenancyId 租户id
    * @param userId 用户id
    * @param req
    * @return int
    */
    ResponseResult updateData(Long tenancyId, Long userId, Update${entity}Req req);


    /**
     * id查询数据
     * @param tenancyId id
     * @param id id
     * @return ${entity}
     */
    ResponseResult<${entity}Rsp> findById(Long tenancyId,Long id);

    /**
    * 查询${table.comment!}分页数据
    * @param tenancyId id
    * @param page      页码
    * @param pageCount 每页条数
    * @return IPage<${entity}>
    */
    ResponseResult<EPage<${entity}Rsp>> findListByPage(Long tenancyId,Integer page, Integer pageCount,${entity}Req req);

    ${entity}Rsp getByPo(${entity} po);
}
</#if>
