package com.carleton.comp5104.cms.service;

import com.carleton.comp5104.cms.entity.Account;
import com.carleton.comp5104.cms.entity.Faculty;
import com.carleton.comp5104.cms.enums.AccountType;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public interface AdminAccountService {
    Page<Account> getAllAccount(Integer pageNum, Integer pageSize) throws Exception;

    Page<Account> getAllAccountByType(String accountType, Integer pageNum, Integer pageSize) throws Exception;

    Page<Account> getAllAccountByName(String name, Integer pageNum, Integer pageSize) throws Exception;

    Page<Account> getAllAccountByTypeAndName(String accountType, String name, Integer pageNum, Integer pageSize) throws Exception;

    List<Faculty> getAllFaculties();

    Account getAccountById(Integer id) throws Exception;

    Integer addNewAccount(Account newAccount);

    Integer deleteAccountById(Integer id) throws Exception;

    Integer updateAccount(Account updateAccount);

    String newAccountEmailValidCheck(String newEmailAddress);
}
