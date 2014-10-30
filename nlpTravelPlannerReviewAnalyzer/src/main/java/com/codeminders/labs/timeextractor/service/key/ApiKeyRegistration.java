package com.codeminders.labs.timeextractor.service.key;

import com.codeminders.labs.timeextractor.entities.UserInfo;

public class ApiKeyRegistration {

    private ApiKeyService apiKeyService = new ApiKeyService();
    private IORegistrationService regService = new IORegistrationService();

    public String registerAndGetAPIKey(UserInfo userInfo) throws Exception {
        String email = userInfo.getEmail();
        regService.writeToFile(userInfo);
        return apiKeyService.generateAPIKey(email);
    }

}
