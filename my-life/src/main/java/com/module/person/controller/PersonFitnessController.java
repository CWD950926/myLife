package com.module.person.controller;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import com.module.person.service.IPersonFitnessService;
import com.module.person.po.PersonFitness;
import com.result.EPage;
import com.result.ResponseResult;
import com.module.person.dto.personfitness.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 健身计划 http api 入口
 *
 * @author generator
 * @since 2023-01-31
 */
@Api(tags = {"健身计划"})
@CrossOrigin(allowCredentials = "true")
@RestController
@RequestMapping("/life/v1/person-fitness")
public class PersonFitnessController {

    @Autowired
    private IPersonFitnessService personFitnessService;


    @ApiOperation(value = "新增健身计划")
    @PostMapping("/m/add")
    public ResponseResult<PersonFitness> add(@RequestHeader(name = "userId", required = false) Long userId,
                                             @RequestBody AddPersonFitnessReq req) {
        return personFitnessService.add(userId, req);
    }


    @ApiOperation(value = "删除健身计划")
    //@GetMapping("/m/del")
    public ResponseResult delete(@RequestHeader(name = "userId", required = false) Long userId,
                                 @ApiParam("健身计划ID") @RequestParam Long id) {
        return personFitnessService.delete(id);
    }


    @ApiOperation(value = "更新健身计划")
    @PostMapping("/m/update")
    public ResponseResult update(@RequestHeader(name = "userId", required = false) Long userId,
                                 @RequestBody UpdatePersonFitnessReq req) {
        return personFitnessService.updateData(userId, req);
    }


    @ApiOperation(value = "id查询健身计划")
    @GetMapping("/m/findById")
    public ResponseResult<PersonFitnessRsp> findById(@RequestHeader(name = "userId", required = false) Long userId,
                                                     @ApiParam("健身计划ID") @RequestParam Long id) {
        return personFitnessService.findById(id);
    }


    @ApiOperation(value = "查询列表")
    @GetMapping("/m/findList")
    public ResponseResult<List<PersonFitness>> findList(@RequestHeader(name = "userId", required = false) Long userId) {
        return personFitnessService.findList();
    }
}
