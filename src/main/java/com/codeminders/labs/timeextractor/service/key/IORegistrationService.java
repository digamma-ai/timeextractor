package com.codeminders.labs.timeextractor.service.key;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.HashSet;

import org.apache.log4j.Logger;

import com.codeminders.labs.timeextractor.entities.UserInfo;
import com.codeminders.labs.timeextractor.exceptions.ExceptionMessages;

// /var/lib/tomcat7/webapps/timeextractor-2/WEB-INF/classes/registration/registration.data

/**
 * <h1>IORegistration Service Class</h1> is used for user registration, and
 * validation of already registered users
 *
 * @author Anastasiia Mishchuk
 * @version 1.0
 * @since 2014-10-30
 */

public class IORegistrationService {
    private String registrationFile = "/registration/registration.data";
    private static final Logger logger = Logger.getLogger(IORegistrationService.class);

    public IORegistrationService() {
    }

    public IORegistrationService(String registrationFile) {
        this.registrationFile = registrationFile;
    }

    public void writeToFile(UserInfo userInfo) throws Exception {
        String email = userInfo.getEmail();
        if (!checkIfEmailExists(email)) {
            PrintWriter writer = null;
            BufferedWriter bufferedWr = null;
            FileWriter fileWr = null;
            try {

                URL url = IORegistrationService.class.getResource(registrationFile);
                String file = url.getPath();

                fileWr = new FileWriter(file, true);
                bufferedWr = new BufferedWriter(fileWr);
                writer = new PrintWriter(bufferedWr);
                writer.println(userInfo.getEmail() + "," + userInfo.getName() + "," + userInfo.getCountry());
                writer.close();
            } catch (NullPointerException ex) {
                throw new Exception(ExceptionMessages.FILE_NOT_FOUND);
            }

            finally {
                if (fileWr != null) {
                    fileWr.close();
                }
                if (bufferedWr != null) {
                    bufferedWr.close();
                }
                if (writer != null) {
                    writer.close();
                }

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
            URL url = IORegistrationService.class.getResource(registrationFile);
            String file = url.getPath();
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String email = null;
                String name = null;
                String country = null;
                try {
                    email = data[0];
                } catch (Exception ex) {

                }
                try {
                    name = data[1];
                } catch (Exception ex) {

                }
                try {
                    country = data[2];
                } catch (Exception ex) {

                }
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
