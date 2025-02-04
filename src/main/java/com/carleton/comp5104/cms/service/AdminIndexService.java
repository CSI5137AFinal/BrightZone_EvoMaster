package com.carleton.comp5104.cms.service;

import com.carleton.comp5104.cms.entity.AdminTodoList;
import net.minidev.json.JSONObject;

import java.util.List;


public interface AdminIndexService {
    Integer getAccountTableSize() throws Exception;

    Integer getCourseTableSize();

    Integer getClazzTableSize();

    Integer getClazzRoomTableSize();

    AdminTodoList getTodoListById(int todoId);

    List<AdminTodoList> getAdminTodoList(int adminId);

    Integer addAdminToDoList(AdminTodoList addForm);

    Integer changeToDoStatus(int todoListId) throws Exception;

    Integer modifyAdminTodoList(AdminTodoList editForm);
}
