package com.carleton.comp5104.cms.controller.admin;

import com.carleton.comp5104.cms.entity.Account;
import com.carleton.comp5104.cms.entity.Classroom;
import com.carleton.comp5104.cms.entity.ClassroomSchedule;
import com.carleton.comp5104.cms.entity.Clazz;
import com.carleton.comp5104.cms.repository.ClassroomRepository;
import com.carleton.comp5104.cms.service.AdminClazzService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.text.ParseException;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/api/admin/class")
public class AdminClazzController {
    @Autowired
    AdminClazzService adminClazzService;

    @GetMapping("/getClassInfoByCourseId/{courseId}")
    public ResponseEntity<ArrayList<Clazz>> getClassInfoByCourseId(@PathVariable String courseId) {
        ArrayList<Clazz> classInfoByCourseId = adminClazzService.getClassInfoByCourseId(Integer.parseInt(courseId));
        if (!classInfoByCourseId.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(classInfoByCourseId);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(classInfoByCourseId);
        }
    }

    @GetMapping("/getClassSchedulesByClassId/{classId}")
    public ResponseEntity<ArrayList<ClassroomSchedule>> getClassSchedulesByClassId(@PathVariable String classId) {
        ArrayList<ClassroomSchedule> classSchedulesByClassId = adminClazzService.getClassSchedulesByClassId(Integer.parseInt(classId));
        if (!classSchedulesByClassId.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(classSchedulesByClassId);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }


    @GetMapping("/getProfessorById/{userId}")
    public ResponseEntity<Account> getProfessorById(@PathVariable int userId) {
        Account professorById = adminClazzService.getProfessorById(userId);
        if (professorById != null) {
            return ResponseEntity.status(HttpStatus.OK).body(professorById);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/getProfessorByEmail/{email}")
    public ResponseEntity<Account> getProfessorByEmail(@PathVariable String email) {
        Account professorByEmail = adminClazzService.getProfessorByEmail(email);
        if (professorByEmail != null) {
            return ResponseEntity.status(HttpStatus.OK).body(professorByEmail);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/classroomSchedule/")
    public ResponseEntity<ArrayList<Classroom>> classroomSchedule(@RequestParam HashMap<String, String> checkMap) throws ParseException {
        ArrayList<Classroom> classrooms = adminClazzService.classroomSchedule(checkMap);
        return ResponseEntity.status(HttpStatus.OK).body(classrooms);
    }

    @GetMapping("/getProfessorList")
    public ResponseEntity<ArrayList<Account>> getProfessorList() {
        ArrayList<Account> professorList = adminClazzService.getProfessorList();
        if (!professorList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(professorList);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/getClassroomSizeList")
    public ResponseEntity<TreeSet<Integer>> getClassroomSizeList() {
        TreeSet<Integer> classroomSizeList = adminClazzService.getClassroomSizeList();
        if (!classroomSizeList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(classroomSizeList);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/addNewClassInfo")
    public ResponseEntity<Clazz> addNewClassInfo(@RequestBody Clazz newClazz) {
        System.out.println(newClazz.toString());
        Clazz clazz = adminClazzService.addNewClassInfo(newClazz);
        if (clazz != null) {
            return ResponseEntity.status(HttpStatus.OK).body(clazz);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/updateClassInfo")
    public ResponseEntity<Clazz> updateClassInfo(@RequestBody Clazz newEditClazz) {
        System.out.println(newEditClazz.toString());
        Clazz clazz = adminClazzService.updateClassInfo(newEditClazz);
        if (clazz != null) {
            return ResponseEntity.status(HttpStatus.OK).body(clazz);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    @PostMapping("/addNewClassSchedules")
    public ResponseEntity<String> addNewClassSchedules(@RequestBody ArrayList<HashMap<String, String>> newClassroomSchedules) {
        System.out.println(newClassroomSchedules.toString());
        Integer status = adminClazzService.addNewClassSchedules(newClassroomSchedules);
        if (status == 0) {
            return ResponseEntity.status(HttpStatus.OK).body("success");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
        }
    }

    @PostMapping("/updateClassSchedules")
    public ResponseEntity<String> updateClassSchedules(@RequestBody ArrayList<HashMap<String, String>> newEditClassroomSchedules) {
        System.out.println(newEditClassroomSchedules.toString());
        Integer status = adminClazzService.updateClassSchedules(newEditClassroomSchedules);
        if (status == 0) {
            return ResponseEntity.status(HttpStatus.OK).body("success");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
        }
    }

    @DeleteMapping("/deleteClassByClassId/{classId}")
    public ResponseEntity<String> deleteClassByClassId(@PathVariable String classId) {
        Integer status = adminClazzService.deleteClassByClassId(Integer.parseInt(classId));
        if (status == 0) {
            return ResponseEntity.status(HttpStatus.OK).body("success");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
        }
    }

}
