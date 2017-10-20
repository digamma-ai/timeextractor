package ai.digamma.service;

import ai.digamma.entities.ExtractionRule;
import org.apache.log4j.Logger;

/**
 * <h1>Rules Factory Class</h1> is designed to return concrete abstract ExtractionRule
 * class implementation by specified name
 *
 * @author Anastasiia Mishchuk
 * @version 1.0
 * @since 2014-10-28
 */
public class RulesFactory {
    private static final Logger logger = Logger.getLogger(TemporalExtractionService.class);

    public ExtractionRule createRule(String className) {
        try {
            Class<?> concreteClass = Class.forName(className);
            return (ExtractionRule) concreteClass.newInstance();
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
