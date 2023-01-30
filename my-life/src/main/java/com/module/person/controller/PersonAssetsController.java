package com.module.person.controller;

import com.module.person.dto.personassets.AddPersonAssetsReq;
import com.module.person.dto.personassets.PersonAssetsRsp;
import com.module.person.dto.personassets.UpdatePersonAssetsReq;
import com.module.person.po.PersonAssets;
import com.module.person.service.IPersonAssetsService;
import com.result.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 个人资产 http api 入口
 *
 * @author generator
 * @since 2023-01-30
 */
@Api(tags = {"个人资产"})
@CrossOrigin(allowCredentials = "true")
@RestController
@RequestMapping("/life/v1/person-assets")
public class PersonAssetsController {

    @Autowired
    private IPersonAssetsService personAssetsService;


    @ApiOperation(value = "新增个人资产")
    @PostMapping("/m/add")
    public ResponseResult<PersonAssets> add(@RequestHeader(name = "userId", required = false) Long userId,
                                            @RequestBody AddPersonAssetsReq req) {
        return personAssetsService.add(userId, req);
    }


    @ApiOperation(value = "删除个人资产")
    //@GetMapping("/m/del")
    public ResponseResult delete(@RequestHeader(name = "userId", required = false) Long userId,
                                 @ApiParam("个人资产ID") @RequestParam Long id) {
        return personAssetsService.delete(id);
    }


    @ApiOperation(value = "更新个人资产")
    @PostMapping("/m/update")
    public ResponseResult update(@RequestHeader(name = "userId", required = false) Long userId,
                                 @RequestBody UpdatePersonAssetsReq req) {
        return personAssetsService.updateData(userId, req);
    }


    @ApiOperation(value = "id查询个人资产")
    @GetMapping("/m/findById")
    public ResponseResult<PersonAssetsRsp> findById(@RequestHeader(name = "userId", required = false) Long userId,
                                                    @ApiParam("个人资产ID") @RequestParam Long id) {
        return personAssetsService.findById(id);
    }

    @ApiOperation(value = "查询列表")
    @GetMapping("/m/findList")
    public ResponseResult<List<PersonAssets>> findList(@RequestHeader(name = "userId", required = false) Long userId) {
        return personAssetsService.findList();
    }
}
