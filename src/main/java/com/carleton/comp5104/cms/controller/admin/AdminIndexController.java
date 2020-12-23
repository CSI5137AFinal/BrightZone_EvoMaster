package com.carleton.comp5104.cms.controller.admin;

import com.carleton.comp5104.cms.entity.AdminTodoList;
import com.carleton.comp5104.cms.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/admin/index")
public class AdminIndexController {

    @Autowired
    private AdminIndexService adminIndexService;

    @GetMapping("/getAccountsNum")
    public ResponseEntity<Integer> getAccountsNum() {
        Integer accountTableSize = null;
        try {
            accountTableSize = adminIndexService.getAccountTableSize();
            return ResponseEntity.status(HttpStatus.OK).body(accountTableSize);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @GetMapping("/getCoursesNum")
    public ResponseEntity<Integer> getCoursesNum() {
        Integer courseTableSize = adminIndexService.getCourseTableSize();
        return ResponseEntity.status(HttpStatus.OK).body(courseTableSize);
    }

    @GetMapping("/getClazzNum")
    public ResponseEntity<Integer> getClazzNum() {
        Integer clazzTableSize = adminIndexService.getClazzTableSize();
        return ResponseEntity.status(HttpStatus.OK).body(clazzTableSize);
    }

    @GetMapping("/getClazzRoomNum")
    public ResponseEntity<Integer> getClazzRoomNum() {
        Integer clazzRoomTableSize = adminIndexService.getClazzRoomTableSize();
        return ResponseEntity.status(HttpStatus.OK).body(clazzRoomTableSize);
    }

    @GetMapping("/getTodoListById/{todoId}")
    public ResponseEntity<AdminTodoList> getTodoListById(@PathVariable int todoId) {
        AdminTodoList todoListById = adminIndexService.getTodoListById(todoId);
        return ResponseEntity.status(HttpStatus.OK).body(todoListById);
    }

    @GetMapping("/getAdminToDoList/{adminId}")
    public ResponseEntity<List<AdminTodoList>> getAdminToDoList(@PathVariable int adminId) {
        return ResponseEntity.status(HttpStatus.OK).body(adminIndexService.getAdminTodoList(adminId));
    }

    @PostMapping("/addAdminToDoList")
    public ResponseEntity<String> addAdminToDoList(@RequestBody AdminTodoList addForm) {
        Integer status = adminIndexService.addAdminToDoList(addForm);
        if (status == 0) {
            return ResponseEntity.status(HttpStatus.OK).body("success");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
        }
    }

    @PostMapping("/modifyAdminToDoList")
    public ResponseEntity<String> modifyAdminToDoList(@RequestBody AdminTodoList addForm) {
        Integer status = adminIndexService.modifyAdminTodoList(addForm);
        System.out.println(status);
        if (status == 0) {
            return ResponseEntity.status(HttpStatus.OK).body("success");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
        }
    }

    @GetMapping("/changeAdminTodoListStatus/{todoListId}")
    public ResponseEntity<String> changeAdminTodoListStatus(@PathVariable int todoListId) {
        Integer status = null;
        try {
            status = adminIndexService.changeToDoStatus(todoListId);
            if (status == 0) {
                return ResponseEntity.status(HttpStatus.OK).body("success");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
            }
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }
}
