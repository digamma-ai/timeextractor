package com.codeminders.labs.timeextractor.service.key;

import com.codeminders.labs.timeextractor.entities.UserInfo;

public class ApiKeyRegistration {

    private ApiKeyService apiKeyService = new ApiKeyService();
    private IORegistrationService regService = new IORegistrationService();

    public String registerAndGetAPIKey(UserInfo userInfo) {
        String email = userInfo.getEmail();
        try {
            regService.writeToFile(userInfo);
            return apiKeyService.generateAPIKey(email);
        } catch (Exception e) {
            return null;
        }

    }

}
