package com.codeminders.labs.timeextractor.service;

import com.codeminders.labs.timeextractor.entities.Rule;

public class RulesFactory {

    public Rule createRule(String className) {
        try {
            Class<?> concreteClass = Class.forName(className);
            return (Rule) concreteClass.newInstance();
        } catch (NullPointerException e) {
            System.out.println("No class found: " + className);
            e.printStackTrace();
        } catch (InstantiationException e) {
            System.out.println("No class found" + className);
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("No class found: " + className);
            e.printStackTrace();
        }
        return null;
    }

}
