package com.module.work.controller;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import com.module.work.service.IWorkTodoService;
import com.module.work.po.WorkTodo;
import com.result.EPage;
import com.result.ResponseResult;
import com.module.work.dto.worktodo.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 工作任务 http api 入口
 *
 * @author generator
 * @since 2023-03-01
 */
@Api(tags = {"待办事项"})
@CrossOrigin(allowCredentials = "true")
@RestController
@RequestMapping("/life/v1/work-todo")
public class WorkTodoController {

    @Autowired
    private IWorkTodoService workTodoService;


    @ApiOperation(value = "新增待办")
    @PostMapping("/m/addOrUpdate")
    public ResponseResult<WorkTodo> addOrUpdate(@RequestHeader(name = "userId", required = false) Long userId,
                                                @RequestBody AddWorkTodoReq req) {
        return workTodoService.addOrUpdate(userId, req);
    }


    @ApiOperation(value = "查询待办")
    @GetMapping("/m/findByType")
    public ResponseResult<WorkTodo> findList() {
        return ResponseResult.buildSuccess(workTodoService.findByType(0));
    }
}
