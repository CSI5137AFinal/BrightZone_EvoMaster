package com.carleton.comp5104.cms.service.impl;

import com.carleton.comp5104.cms.entity.AdminTodoList;
import com.carleton.comp5104.cms.enums.AdminTodoLevel;
import com.carleton.comp5104.cms.enums.WeekDay;
import com.carleton.comp5104.cms.repository.*;
import com.carleton.comp5104.cms.service.AdminIndexService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.Optional;

@Service
public class AdminIndexServiceImpl implements AdminIndexService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ClazzRepository clazzRepository;
    @Autowired
    private ClassroomRepository classroomRepository;
    @Autowired
    private AdminTodoListRepository adminTodoListRepository;

    @Override
    public Integer getAccountTableSize() throws Exception {
        return accountRepository.findAll().size();
    }

    @Override
    public Integer getCourseTableSize() {
        return courseRepository.findAll().size();
    }

    @Override
    public Integer getClazzTableSize() {
        return clazzRepository.findAll().size();
    }

    @Override
    public Integer getClazzRoomTableSize() {
        return classroomRepository.findAll().size();
    }

    @Override
    public AdminTodoList getTodoListById(int todoId) {
        Optional<AdminTodoList> byId = adminTodoListRepository.findById(todoId);
        return byId.orElse(null);
    }

    @Override
    public List<AdminTodoList> getAdminTodoList(int adminId) {
        return adminTodoListRepository.findAllByAdminIdAndStatusEquals(adminId, 0);
    }

//    @Override
//    public Integer addAdminToDoList(JSONObject addForm) {
//        int status = -1;
//        int adminId = Integer.parseInt(addForm.get("adminId").toString());
//        AdminTodoLevel level = AdminTodoLevel.valueOf(addForm.get("level").toString());
//        String notes = addForm.getAsString("notes");
//        List<String> timeList = (List<String>) addForm.get("time");
//        System.out.println(timeList.get(0).split("T")[0]);
//        System.out.println(timeList.get(1).split("T")[0]);
//        Date startTime = formatString2Time(timeList.get(0).split("T")[0]);
//        Date endTime = formatString2Time(timeList.get(1).split("T")[0]);
//        System.out.println(startTime.toString());
//        System.out.println(endTime.toString());
//        try {
//            AdminTodoList adminTodoList = new AdminTodoList();
//            adminTodoList.setAdminId(adminId);
//            adminTodoList.setNotes(notes);
//            adminTodoList.setLevel(level);
//            adminTodoList.setStatus(false);
//            adminTodoList.setStartTime(startTime);
//            adminTodoList.setEndTime(endTime);
//            adminTodoListRepository.save(adminTodoList);
//            status = 0;
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
//        return status;
//    }

    @Override
    public Integer addAdminToDoList(AdminTodoList addForm) {
        int status = -1;
        try {
            adminTodoListRepository.save(addForm);
            status = 0;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return status;
    }

    @Override
    public Integer changeToDoStatus(int todoListId) throws Exception{
        int status = -1;
        Optional<AdminTodoList> byId = adminTodoListRepository.findById(todoListId);
        if (byId.isPresent()) {
            AdminTodoList adminTodoList = byId.get();
            if (adminTodoList.getStatus() == 1) {
                adminTodoList.setStatus(0);
            } else {
                adminTodoList.setStatus(1);
            }
            adminTodoListRepository.save(adminTodoList);
            status = 0;
        }
        return status;
    }

    @Override
    public Integer modifyAdminTodoList(AdminTodoList editForm) {
        int status = -1;
        try {
            Optional<AdminTodoList> byId = adminTodoListRepository.findById(editForm.getId());
            if (byId.isPresent()) {
                adminTodoListRepository.save(editForm);
                status = 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return status;
    }
}
