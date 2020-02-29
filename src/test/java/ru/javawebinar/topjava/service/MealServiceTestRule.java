package ru.javawebinar.topjava.service;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class MealServiceTestRule implements TestRule {
    private static final Logger log = LoggerFactory.getLogger(MealServiceTestRule.class);
    private static HashMap<String, Long> resultMap = new HashMap<>();

    @Override
    public Statement apply(Statement base, Description description) {
        if (description.isTest()) {
            return new Statement() {
                @Override
                public void evaluate() throws Throwable {
                    long startTest = System.currentTimeMillis();
                    base.evaluate();
                    long endTest = System.currentTimeMillis();
                    long result = endTest - startTest;
                    log.debug("Name: " + description.getMethodName() + "; " + "time: " + result + "ms.");
                    resultMap.put(description.getMethodName(), result);
                }
            };
        }
        if (description.isSuite()) {
            return new Statement() {
                @Override
                public void evaluate() throws Throwable {
                    base.evaluate();
                    resultMap.forEach((s, aLong) -> System.out.println("Name: " + s + "; " + "time: " + aLong + "ms."));
                }
            };
        }
        return base;
    }
}
