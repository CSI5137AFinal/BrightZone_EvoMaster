package com.carleton.comp5104.cms.service;

import java.util.Map;

public interface AccountService {
    Map<String, Object> registerAccount(String email);

    Map<String, Object> login(String email, String password);

    Map<String, Object> createRequest(int accountId, String requestMessage, String requestType);

    Map<String, Object> passwordRecovery(String email, String verificationCode, String newPassword);

    Map<String, Object> sendVerificationCode(String email);

    Map<String, Object> updateEmail(int accountId, String email);

    Map<String, Object> updatePassword(int accountId, String password);
}
