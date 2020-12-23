package com.carleton.comp5104.cms.controller.admin;

import com.carleton.comp5104.cms.entity.Account;
import com.carleton.comp5104.cms.entity.Faculty;
import com.carleton.comp5104.cms.service.AdminAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/admin/account")
public class AdminAccountController {

    @Autowired
    private AdminAccountService adminAccountService;

    @GetMapping("/getAll/{pageNum}/{pageSize}")
    public ResponseEntity<Page<Account>> getAllAccount(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) {
        try {
            Page<Account> allAccount = adminAccountService.getAllAccount(pageNum, pageSize);
            return ResponseEntity.status(HttpStatus.OK).body(allAccount);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/getAllByType/{type}/{pageNum}/{pageSize}")
    public ResponseEntity<Page<Account>> getAllAccountByType(@PathVariable("type") String type,
                                                             @PathVariable("pageNum") Integer pageNum,
                                                             @PathVariable("pageSize") Integer pageSize) {
        try {
            Page<Account> allAccountByType = adminAccountService.getAllAccountByType(type, pageNum, pageSize);
            return ResponseEntity.status(HttpStatus.OK).body(allAccountByType);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @GetMapping("/getAllByName/{name}/{pageNum}/{pageSize}")
    public ResponseEntity<Page<Account>> getAllAccountByName(@PathVariable("name") String name,
                                                             @PathVariable("pageNum") Integer pageNum,
                                                             @PathVariable("pageSize") Integer pageSize) {
        return ResponseEntity.status(HttpStatus.OK).body(adminAccountService.getAllAccountByName(name, pageNum, pageSize));
    }

    @GetMapping("/getAllByTypeAndName/{type}/{name}/{pageNum}/{pageSize}")
    public ResponseEntity<Page<Account>> getAllAccountByTypeAndName(@PathVariable("type") String type,
                                                                    @PathVariable("name") String name,
                                                                    @PathVariable("pageNum") Integer pageNum,
                                                                    @PathVariable("pageSize") Integer pageSize) {
        return ResponseEntity.status(HttpStatus.OK).body(adminAccountService.getAllAccountByTypeAndName(type, name, pageNum, pageSize));
    }

    @GetMapping("/getAllFaculties")
    public ResponseEntity<List<Faculty>> getAllFaculties() {
        List<Faculty> allFaculties = adminAccountService.getAllFaculties();
        if (allFaculties.size() != 0) {
            return ResponseEntity.status(HttpStatus.OK).body(allFaculties);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    @GetMapping("/getAccount/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable("id") Integer id) {
        Account accountById = adminAccountService.getAccountById(id);
        if (accountById != null) {
            return ResponseEntity.status(HttpStatus.OK).body(accountById);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    @PostMapping("/addNewAccount")
    public ResponseEntity<String> addNewAccount(@RequestBody Account newAccount) {
        int status = adminAccountService.addNewAccount(newAccount);
        if (status == 1) {
            return ResponseEntity.status(HttpStatus.OK).body("success");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
        }
    }

    @DeleteMapping("/deleteAccount/{id}")
    public ResponseEntity<String> deleteAccountById(@PathVariable("id") Integer id) {
        int status = adminAccountService.deleteAccountById(id);
        if (status == 0) {
            return ResponseEntity.status(HttpStatus.OK).body("success");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
        }
    }

    @PutMapping("/updateAccount")
    public ResponseEntity<String> updateAccount(@RequestBody Account updateAccount) {
        int status = adminAccountService.updateAccount(updateAccount);
        if (status == 0) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("success");
//            return "success";
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("error");
//            return "error";
        }
    }

    @GetMapping("/check/email/{Email}")
    public ResponseEntity<String> newAccountEmailValidCheck(@PathVariable("Email") String newEmailAddress) {
        String s = adminAccountService.newAccountEmailValidCheck(newEmailAddress);
        if (s.equals("Repeat")) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(s);
        } else {
            return new ResponseEntity<>(s, HttpStatus.ACCEPTED);
        }
//        return adminAccountService.newAccountEmailValidCheck(newEmailAddress);
    }

}
