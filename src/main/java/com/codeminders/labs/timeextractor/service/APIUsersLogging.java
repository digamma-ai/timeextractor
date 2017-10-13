package com.codeminders.labs.timeextractor.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import org.apache.log4j.Logger;

import com.codeminders.labs.timeextractor.entities.LogData;
import com.codeminders.labs.timeextractor.exceptions.ExceptionMessages;
import com.codeminders.labs.timeextractor.service.key.IORegistrationService;

/**
 * <h1>APIUsers Logging Service Class</h1> is developed for storing information
 * about service usage by users
 *
 * @author Anastasiia Mishchuk
 * @version 1.0
 * @since 2014-10-28
 */
public class APIUsersLogging {

    private String logFile = "/logs/log.data";
    private static final Logger logger = Logger.getLogger(APIUsersLogging.class);

    public void log(LogData log) {

        PrintWriter writer = null;
        BufferedWriter bufferedWr = null;
        FileWriter fileWr = null;
        try {

            URL url = IORegistrationService.class.getResource(logFile);
            String file = url.getPath();
            fileWr = new FileWriter(file, true);
            bufferedWr = new BufferedWriter(fileWr);
            writer = new PrintWriter(bufferedWr);
            writer.println(log.getUtcDate() + "," + log.getApiKey() + "," + log.getService() + "," + log.getUserEmail() + "," + log.getIp());
            writer.close();
        } catch (NullPointerException ex) {
            logger.error(ExceptionMessages.FILE_NOT_FOUND + " " + logFile);
        } catch (IOException e) {
            logger.error(ExceptionMessages.IO_EXCEPTION + " " + logFile);
        }

        finally {
            if (fileWr != null) {
                try {
                    fileWr.close();
                } catch (IOException e) {
                    logger.error(ExceptionMessages.FILE_NOT_FOUND + " " + logFile);
                }
            }
            if (bufferedWr != null) {
                try {
                    bufferedWr.close();
                } catch (IOException e) {
                    logger.error(ExceptionMessages.FILE_NOT_FOUND + " " + logFile);
                }
            }
            if (writer != null) {
                writer.close();
            }

        }

    }

}
