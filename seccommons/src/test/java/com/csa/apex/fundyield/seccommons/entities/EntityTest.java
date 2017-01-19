/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.seccommons.entities;

import org.junit.Test;

import com.openpojo.reflection.filters.FilterPackageInfo;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;

/**
 * Test class for the entities.
 *
 * @see SECConfigurationTest
 * @author [es],TCSDEVELOPER
 * @version 1.0
 */
public class EntityTest {

    /**
     * Unit test for the entities.
     */
    @Test
    public void testEntities() {
        Validator validator = ValidatorBuilder.create().with(new SetterTester()).with(new GetterTester()).build();

        validator.validate(EntityTest.class.getPackage().getName(), new FilterPackageInfo());
    }

}
