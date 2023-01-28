package ${package.Controller};

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
import com.result.EPage;
import com.result.ResponseResult;
import com.module.${cfg.module}.dto.${entity?lower_case}.*;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * ${table.comment!} http api 入口
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Api(tags = {"${table.comment!}"})
@CrossOrigin(allowCredentials = "true")
@RestController
</#if>
@RequestMapping("/scrm/v1/<#if package.ModuleName??>${package.ModuleName}</#if><#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??>:${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>public class ${table.controllerName} extends ${superControllerClass}{
<#else>public class ${table.controllerName} {
</#if>

    @Autowired
    private ${table.serviceName} ${(table.serviceName?substring(1))?uncap_first};



    @ApiOperation(value = "新增${table.comment!}")
    @PostMapping("/m/add")
    public ResponseResult<${entity}> add(@RequestHeader(name = "userId", required = false) Long userId,
                                         @RequestHeader(name = "tenancyId", required = true) Long tenancyId,
                                         @RequestBody Add${entity}Req req){
        return ${(table.serviceName?substring(1))?uncap_first}.add(tenancyId,userId,req);
    }


    @ApiOperation(value = "删除${table.comment!}")
    //@GetMapping("/m/del")
    public ResponseResult delete(@RequestHeader(name = "userId", required = false) Long userId,
                                 @RequestHeader(name = "tenancyId", required = true) Long tenancyId,
                                 @ApiParam("${table.comment!}ID") @RequestParam Long id){
        return ${(table.serviceName?substring(1))?uncap_first}.delete(tenancyId,id);
    }


    @ApiOperation(value = "更新${table.comment!}")
    @PostMapping("/m/update")
    public ResponseResult update(@RequestHeader(name = "userId", required = false) Long userId,
                                 @RequestHeader(name = "tenancyId", required = true) Long tenancyId,
                                 @RequestBody Update${entity}Req req){
        return ${(table.serviceName?substring(1))?uncap_first}.updateData(tenancyId,userId,req);
    }

    @ApiOperation(value = "查询${table.comment!}分页数据")
    @PostMapping("/m/findListByPage")
    public ResponseResult<EPage<${entity}Rsp>> findListByPage(@RequestHeader(name = "userId", required = false) Long userId,
                                                           @RequestHeader(name = "tenancyId", required = true) Long tenancyId,
                                                           @RequestParam Integer page,
                                                           @RequestParam Integer pageCount,
                                                           @RequestBody ${entity}Req req){
         return ${(table.serviceName?substring(1))?uncap_first}.findListByPage(tenancyId,page, pageCount,req);
    }

    @ApiOperation(value = "id查询${table.comment!}")
    @GetMapping("/m/findById")
    public ResponseResult<${entity}Rsp> findById(@RequestHeader(name = "userId", required = false) Long userId,
                                                 @RequestHeader(name = "tenancyId", required = true) Long tenancyId,
                                                 @ApiParam("${table.comment!}ID") @RequestParam Long id){
         return ${(table.serviceName?substring(1))?uncap_first}.findById(tenancyId,id);
    }

}
</#if>