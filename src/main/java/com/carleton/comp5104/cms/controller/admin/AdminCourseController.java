package com.carleton.comp5104.cms.controller.admin;

import com.carleton.comp5104.cms.entity.Course;
import com.carleton.comp5104.cms.service.AdminCourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("/admin/course")
public class AdminCourseController {

    @Autowired
    private AdminCourseService adminCourseService;

    @GetMapping("/getAll/{pageNum}/{pageSize}")
    public ResponseEntity<Page<Course>> getAllCourse(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) {
        Page<Course> allCourse = adminCourseService.getAllCourse(pageNum, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(allCourse);
    }

    @GetMapping("/getCourseTableSize")
    public ResponseEntity<Integer> getCourseTableSize() {
        return ResponseEntity.status(HttpStatus.OK).body(adminCourseService.getCourseTableSize());
    }

    @GetMapping("/getCourseById/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable("courseId") Integer courseId) {
        Course courseById = adminCourseService.getCourseById(courseId);
        if (courseById != null) {
            return ResponseEntity.status(HttpStatus.OK).body(courseById);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/getCourseBySubjectAndNumber/{courseSubject}/{courseNumber}")
    public ResponseEntity<Course> getCourseBySubjectAndNumber(@PathVariable String courseSubject, @PathVariable String courseNumber) {
        System.out.println(courseSubject);
        System.out.println(courseNumber);
        Course courseBySubjectAndNumber = adminCourseService.getCourseBySubjectAndNumber(courseSubject, courseNumber);
        if (courseBySubjectAndNumber != null) {
            return ResponseEntity.status(HttpStatus.OK).body(courseBySubjectAndNumber);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/getCourseBySubject/{courseSubject}")
    public ResponseEntity<ArrayList<Course>> getCourseBySubject(@PathVariable String courseSubject) {
        System.out.println(courseSubject);
        ArrayList<Course> courseBySubject = adminCourseService.getCourseBySubject(courseSubject);
        if (!courseBySubject.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(courseBySubject);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/addNewCourse")
    public ResponseEntity<String> addNewCourse(@RequestBody Course newCourse) {
        int status = adminCourseService.addNewCourse(newCourse);
        if (status == 0) {
            return ResponseEntity.status(HttpStatus.OK).body("success");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteACourse(@PathVariable("id") Integer courseId) {
        int status = adminCourseService.deleteACourse(courseId);
        if (status == 0) {
            return ResponseEntity.status(HttpStatus.OK).body("success");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
        }
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateACourse(@RequestBody Course updatingCourse) {
        int status = adminCourseService.updateACourse(updatingCourse);
        if (status == 0) {
            return ResponseEntity.status(HttpStatus.OK).body("success");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
        }
    }

    //font-end check box data source
    @GetMapping("/addPage/getSubject")
    public ResponseEntity<ArrayList<HashMap<String, String>>> getSubjects() {
        ArrayList<HashMap<String, String>> subjects = adminCourseService.getSubjects();
        return ResponseEntity.status(HttpStatus.OK).body(subjects);
    }


    @GetMapping("/addPageCheck/number/{courseSubject}/{courseNumber}")
    public ResponseEntity<String> newCourseNumberValidCheck(@PathVariable String courseSubject, @PathVariable String courseNumber) {
        int status = adminCourseService.newCourseNumberValidCheck(courseSubject, courseNumber);
        if (status == 0) {
            return ResponseEntity.status(HttpStatus.OK).body("valid");
        } else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Repeat");
    }

    @GetMapping("/addPageCheck/name/{courseName}")
    public ResponseEntity<String> newCourseNameValidCheck(@PathVariable String courseName) {
        int status = adminCourseService.newCourseNameValidCheck(courseName);
        if (status == 0) {
            return ResponseEntity.status(HttpStatus.OK).body("valid");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Repeat");
        }
    }

    @PostMapping("/addPage/addPrerequisite")
    public ResponseEntity<String> addCoursePrerequisite(@RequestParam String prerequisiteCourseId, @RequestParam String courseId) {
        ArrayList<Integer> prerequisiteList = new ArrayList<>();
        prerequisiteList.add(Integer.parseInt(prerequisiteCourseId));
        int status = adminCourseService.addCoursePrerequisite(prerequisiteList, Integer.parseInt(courseId));
        if (status == 0) {
            return ResponseEntity.status(HttpStatus.OK).body("success");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
        }
    }

    @GetMapping("/CourseInfo/getCoursePrerequisite/{courseId}")
    public ResponseEntity<List<Course>> getCoursePrerequisite(@PathVariable Integer courseId) {
        List<Course> coursePrerequisite = adminCourseService.getCoursePrerequisite(courseId);
        return ResponseEntity.status(HttpStatus.OK).body(coursePrerequisite);
    }

    @DeleteMapping("/CourseInfo/deleteCoursePrerequisite/{courseId}/{prerequisiteId}")
    public ResponseEntity<String> deleteCoursePrerequisite(@PathVariable String courseId, @PathVariable String prerequisiteId) {
        System.out.println(courseId);
        System.out.println(prerequisiteId);
        int status = adminCourseService.deleteCoursePrerequisite(Integer.parseInt(courseId), Integer.parseInt(prerequisiteId));
        if (status == 0) {
            return ResponseEntity.status(HttpStatus.OK).body("success");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
        }
    }

    @PostMapping("/editPage/updateCoursePrerequisite")
    public ResponseEntity<String> updateCoursePrerequisite(@RequestBody ArrayList<Integer> updatedPrerequisiteList, @RequestParam Integer courseId) {
        int status = 0;
        try {
            status = adminCourseService.updateCoursePrerequisite(updatedPrerequisiteList, courseId);
            if (status == 0) {
                return ResponseEntity.status(HttpStatus.OK).body("success");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
        }
    }

    @PostMapping("/addPage/addPreclusion")
    public ResponseEntity<String> addCoursePreclusion(@RequestParam String preclusionCourseId, @RequestParam String courseId) {
        ArrayList<Integer> preclusionList = new ArrayList<>();
        preclusionList.add(Integer.parseInt(preclusionCourseId));
        int status = adminCourseService.addCoursePreclusion(preclusionList, Integer.parseInt(courseId));
        if (status == 0) {
            return ResponseEntity.status(HttpStatus.OK).body("success");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
        }
    }

    @GetMapping("/CourseInfo/getCoursePreclusion/{courseId}")
    public ResponseEntity<List<Course>> getCoursePreclusion(@PathVariable Integer courseId) {
        List<Course> coursePreclusion = adminCourseService.getCoursePreclusion(courseId);
        return ResponseEntity.status(HttpStatus.OK).body(coursePreclusion);
    }

    @DeleteMapping("/CourseInfo/deleteCoursePreclusion/{courseId}/{preclusionId}")
    public ResponseEntity<String> deleteCoursePreclusion(@PathVariable String courseId, @PathVariable String preclusionId) {
        int status = adminCourseService.deleteCoursePreclusion(Integer.parseInt(courseId), Integer.parseInt(preclusionId));
        if (status == 0) {
            return ResponseEntity.status(HttpStatus.OK).body("success");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
        }
    }

    @PostMapping("/editPage/updateCoursePreclusion")
    public ResponseEntity<String> updateCoursePreclusion(@RequestBody ArrayList<Integer> updatedPreclusionList, @RequestParam Integer courseId) {
        int status = 0;
        try {
            status = adminCourseService.updateCoursePreclusion(updatedPreclusionList, courseId);
            if (status == 0) {
                return ResponseEntity.status(HttpStatus.OK).body("success");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
        }
    }


}
