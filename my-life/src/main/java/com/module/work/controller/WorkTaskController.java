package com.module.work.controller;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import com.module.work.service.IWorkTaskService;
import com.module.work.po.WorkTask;
import com.result.EPage;
import com.result.ResponseResult;
import com.module.work.dto.worktask.*;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * http api 入口
 *
 * @author generator
 * @since 2023-01-10
 */
@Api(tags = {"工作任务"})
@CrossOrigin(allowCredentials = "true")
@RestController
@RequestMapping("/scrm/v1/work-task")
public class WorkTaskController {

    @Autowired
    private IWorkTaskService workTaskService;


    @ApiOperation(value = "新增工作任务")
    @PostMapping("/m/add")
    public ResponseResult<WorkTask> add(@RequestHeader(name = "userId", required = false) Long userId,
                                        @RequestHeader(name = "tenancyId", required = true) Long tenancyId,
                                        @RequestBody AddWorkTaskReq req) {
        return workTaskService.add(tenancyId, userId, req);
    }


    @ApiOperation(value = "删除工作任务")
    //@GetMapping("/m/del")
    public ResponseResult delete(@RequestHeader(name = "userId", required = false) Long userId,
                                 @RequestHeader(name = "tenancyId", required = true) Long tenancyId,
                                 @ApiParam("ID") @RequestParam Long id) {
        return workTaskService.delete(tenancyId, id);
    }

    @ApiOperation(value = "更新工作任务")
    @PostMapping("/m/update")
    public ResponseResult update(@RequestHeader(name = "userId", required = false) Long userId,
                                 @RequestHeader(name = "tenancyId", required = true) Long tenancyId,
                                 @RequestBody UpdateWorkTaskReq req) {
        return workTaskService.updateData(tenancyId, userId, req);
    }

    @ApiOperation(value = "查询工作任务分页数据")
    @PostMapping("/m/findListByPage")
    public ResponseResult<EPage<WorkTaskRsp>> findListByPage(@RequestHeader(name = "userId", required = false) Long userId,
                                                             @RequestHeader(name = "tenancyId", required = true) Long tenancyId,
                                                             @RequestParam Integer page,
                                                             @RequestParam Integer pageCount,
                                                             @RequestBody WorkTaskReq req) {
        return workTaskService.findListByPage(tenancyId, page, pageCount, req);
    }

    @ApiOperation(value = "id查询工作任务")
    @GetMapping("/m/findById")
    public ResponseResult<WorkTaskRsp> findById(@RequestHeader(name = "userId", required = false) Long userId,
                                                @RequestHeader(name = "tenancyId", required = true) Long tenancyId,
                                                @ApiParam("ID") @RequestParam Long id) {
        return workTaskService.findById(tenancyId, id);
    }

}
