package com.module.work.controller;

import com.module.work.dto.worktask.AddWorkTaskReq;
import com.module.work.dto.worktask.UpdateWorkTaskReq;
import com.module.work.dto.worktask.WorkTaskRsp;
import com.module.work.po.WorkTask;
import com.module.work.service.IWorkTaskService;
import com.result.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * http api 入口
 *
 * @author generator
 * @since 2023-01-10
 */
@Api(tags = {"工作任务"})
@CrossOrigin(allowCredentials = "true")
@RestController
@RequestMapping("/life/v1/work-task")
public class WorkTaskController {

    @Autowired
    private IWorkTaskService workTaskService;


    @ApiOperation(value = "新增工作任务")
    @PostMapping("/m/add")
    public ResponseResult add(@RequestHeader(name = "userId", required = false) Long userId,
                              @RequestBody AddWorkTaskReq req) {
        return workTaskService.add(userId, req);
    }


    @ApiOperation(value = "删除工作任务")
    //@GetMapping("/m/del")
    public ResponseResult delete(@RequestHeader(name = "userId", required = false) Long userId,
                                 @ApiParam("ID") @RequestParam Long id) {
        return workTaskService.delete(id);
    }

    @ApiOperation(value = "更新工作任务")
    @PostMapping("/m/update")
    public ResponseResult update(@RequestHeader(name = "userId", required = false) Long userId,
                                 @RequestBody UpdateWorkTaskReq req) {
        return workTaskService.updateData(userId, req);
    }

    @ApiOperation(value = "查询工作任务")
    @GetMapping("/m/findList")
    public ResponseResult<List<WorkTask>> findList(@RequestHeader(name = "userId", required = false) Long userId) {
        return workTaskService.findList();
    }

    @ApiOperation(value = "id查询工作任务")
    @GetMapping("/m/findById")
    public ResponseResult<WorkTaskRsp> findById(@RequestHeader(name = "userId", required = false) Long userId,
                                                @ApiParam("ID") @RequestParam Long id) {
        return workTaskService.findById(id);
    }

}
