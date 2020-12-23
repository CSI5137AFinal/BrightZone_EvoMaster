package com.carleton.comp5104.cms.controller.admin;

import com.carleton.comp5104.cms.entity.Request;
import com.carleton.comp5104.cms.service.AdminRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/request")
public class AdminRequestController {

    @Autowired
    private AdminRequestService adminRequestService;

    @DeleteMapping("/deleteRequestByUserId")
    public ResponseEntity<Boolean> deleteRequestByUserId(@RequestParam("userId") Integer userId) {
        boolean b = adminRequestService.deleteAllByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(b);
    }

    @PutMapping("/updateRequest")
    public ResponseEntity<Boolean> updateRequestStatus(@RequestParam("id") Integer requestId,
                                                       @RequestParam("status") String newStatus) {
        boolean b = adminRequestService.updateRequestStatus(requestId, newStatus);
        return ResponseEntity.status(HttpStatus.OK).body(b);
    }

    @GetMapping("/getAllOpenRequest")
    public ResponseEntity<List<Request>> getAllOpenRequest() {
        List<Request> allOpenRequest = adminRequestService.getAllOpenRequest();
        return ResponseEntity.status(HttpStatus.OK).body(allOpenRequest);
    }

}
