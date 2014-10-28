package com.codeminders.labs.timeextractor.service;

import org.apache.log4j.Logger;

import com.codeminders.labs.timeextractor.entities.Rule;

/**
 * <h1>Rules Factory Class</h1> is designed to return concrete abstract Rule
 * class implementation by specified name
 *
 * @author Anastasiia Mishchuk
 * @version 1.0
 * @since 2014-10-28
 */
public class RulesFactory {
    private static final Logger logger = Logger.getLogger(TemporalExtractionService.class);

    public Rule createRule(String className) {
        try {
            Class<?> concreteClass = Class.forName(className);
            return (Rule) concreteClass.newInstance();
        } catch (NullPointerException e) {
            logger.error("No class found: " + className);
        } catch (InstantiationException e) {
            logger.error("No class found: " + className);
        } catch (IllegalAccessException e) {
            logger.error("No class found: " + className);
        } catch (ClassNotFoundException e) {
            logger.error("No class found: " + className);
        }
        return null;
    }

}
