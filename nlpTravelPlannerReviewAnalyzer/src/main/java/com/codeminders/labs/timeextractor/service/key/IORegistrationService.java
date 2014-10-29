package com.codeminders.labs.timeextractor.service.key;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import org.apache.log4j.Logger;
import com.codeminders.labs.timeextractor.entities.UserInfo;
import com.codeminders.labs.timeextractor.exceptions.ExceptionMessages;

public class IORegistrationService {
    private String registrationFile = "registration.data";
    private static final Logger logger = Logger.getLogger(IORegistrationService.class);

    public IORegistrationService() {
    }

    public IORegistrationService(String registrationFile) {
        this.registrationFile = registrationFile;
    }

    public void writeToFile(UserInfo userInfo) throws Exception {
        String email = userInfo.getEmail();
        HashSet<String> allEmails = getAllAvailableEmails();
        if (!allEmails.contains(email)) {
            try {
                PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(registrationFile, true)));
                writer.println(userInfo.getEmail() + "," + userInfo.getName() + "," + userInfo.getCountry());
                writer.close();
            } catch (Exception ex) {
                logger.error(ex);
            }
        } else {
            throw new Exception(ExceptionMessages.REGISTRATION_ERROR);
        }
    }

    public boolean checkIfEmailExists(String email) {
        HashSet<String> allEmails = getAllAvailableEmails();
        return allEmails.contains(email);

    }

    public HashSet<String> getAllAvailableEmails() {
        HashSet<UserInfo> users = getAllUserInfo();
        HashSet<String> emails = new HashSet<String>();
        for (UserInfo user : users) {
            emails.add(user.getEmail());
        }
        return emails;
    }

    private HashSet<UserInfo> getAllUserInfo() {
        HashSet<UserInfo> usersInfo = new HashSet<UserInfo>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(registrationFile));
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String email = data[0];
                String name = data[1];
                String country = data[2];
                UserInfo userInfo = new UserInfo(name, email, country);
                usersInfo.add(userInfo);
            }
            br.close();
        } catch (Exception ex) {
            logger.error(ex);
        }
        return usersInfo;
    }

}
