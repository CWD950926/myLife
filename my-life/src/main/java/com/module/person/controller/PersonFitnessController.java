package com.module.person.controller;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import com.module.person.service.IPersonFitnessService;
import com.module.person.po.PersonFitness;
import com.result.EPage;
import com.result.ResponseResult;
import com.module.person.dto.personfitness.*;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 健身计划 http api 入口
 *
 * @author generator
 * @since 2023-01-10
 */
@Api(tags = {"健身计划"})
@CrossOrigin(allowCredentials = "true")
@RestController
@RequestMapping("/scrm/v1/person-fitness")
public class PersonFitnessController {

    @Autowired
    private IPersonFitnessService personFitnessService;



    @ApiOperation(value = "新增健身计划")
    @PostMapping("/m/add")
    public ResponseResult<PersonFitness> add(@RequestHeader(name = "userId", required = false) Long userId,
                                         @RequestHeader(name = "tenancyId", required = true) Long tenancyId,
                                         @RequestBody AddPersonFitnessReq req){
        return personFitnessService.add(tenancyId,userId,req);
    }


    @ApiOperation(value = "删除健身计划")
    //@GetMapping("/m/del")
    public ResponseResult delete(@RequestHeader(name = "userId", required = false) Long userId,
                                 @RequestHeader(name = "tenancyId", required = true) Long tenancyId,
                                 @ApiParam("健身计划ID") @RequestParam Long id){
        return personFitnessService.delete(tenancyId,id);
    }


    @ApiOperation(value = "更新健身计划")
    @PostMapping("/m/update")
    public ResponseResult update(@RequestHeader(name = "userId", required = false) Long userId,
                                 @RequestHeader(name = "tenancyId", required = true) Long tenancyId,
                                 @RequestBody UpdatePersonFitnessReq req){
        return personFitnessService.updateData(tenancyId,userId,req);
    }

    @ApiOperation(value = "查询健身计划分页数据")
    @PostMapping("/m/findListByPage")
    public ResponseResult<EPage<PersonFitnessRsp>> findListByPage(@RequestHeader(name = "userId", required = false) Long userId,
                                                           @RequestHeader(name = "tenancyId", required = true) Long tenancyId,
                                                           @RequestParam Integer page,
                                                           @RequestParam Integer pageCount,
                                                           @RequestBody PersonFitnessReq req){
         return personFitnessService.findListByPage(tenancyId,page, pageCount,req);
    }

    @ApiOperation(value = "id查询健身计划")
    @GetMapping("/m/findById")
    public ResponseResult<PersonFitnessRsp> findById(@RequestHeader(name = "userId", required = false) Long userId,
                                              @RequestHeader(name = "tenancyId", required = true) Long tenancyId,
                                              @ApiParam("健身计划ID") @RequestParam Long id){
         return personFitnessService.findById(tenancyId,id);
    }

}
