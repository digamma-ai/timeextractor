package ai.digamma.service.key;

import ai.digamma.entities.UserInfo;

/**
 * <h1>Api Key Registration Service Class</h1> is developed for user
 * registration and receiving API Key
 *
 * @author Anastasiia Mishchuk
 * @version 1.0
 * @since 2014-10-30
 */
public class ApiKeyRegistration {

    private ApiKeyService apiKeyService = new ApiKeyService();
    private IORegistrationService regService = new IORegistrationService();

    public String registerAndGetAPIKey(UserInfo userInfo) throws Exception {
        String email = userInfo.getEmail();
        regService.writeToFile(userInfo);
        return apiKeyService.generateAPIKey(email);
    }

}
