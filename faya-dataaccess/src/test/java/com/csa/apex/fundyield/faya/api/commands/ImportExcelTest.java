/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.faya.api.commands;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.InputStreamResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.csa.apex.fundyield.faya.Application;
import com.csa.apex.fundyield.faya.commands.ImportExcel;

/**
 * Unit tests for ImportExcel.
 * @author TCSDEVELOPER
 * @version 1.0
 */
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@SqlGroup({@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:clean.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:lookup.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:clean.sql")})
public class ImportExcelTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Test help.
     * @throws Exception if any exception occurs
     */
    @Test
    public void testHelp() throws Exception {
        ImportExcel.main(new String[] {"-h"});
    }

    /**
     * Test import, no args.
     * @throws Exception if any exception occurs
     */
    @Test
    public void testImport_NoArgs() throws Exception {
        ImportExcel.main(new String[] {});
    }

    /**
     * Test import, IO error.
     * @throws Exception if any exception occurs
     */
    @Test
    public void testImport_IOError() throws Exception {
        ImportExcel.main(new String[] {"-m", "NoSuchFile.properties", "-e",
                ImportExcel.class.getClassLoader().getResource("Phase1TestData.xlsx").getPath(), "-x",
                "applicationContext-cli-test.xml"});
    }

    /**
     * Test import.
     * @throws Exception if any exception occurs
     */
    @Test
    public void testImport() throws Exception {
        ImportExcel.main(
                new String[] {"-e", ImportExcel.class.getClassLoader().getResource("Phase1TestData.xlsx").getPath(),
                        "-x", "applicationContext-cli-test.xml"});
        int count = jdbcTemplate.queryForObject("SELECT count(*) FROM Instrument", Integer.class);
        assertEquals(12, count);
        count = jdbcTemplate.queryForObject("SELECT count(*) FROM tradable_entity", Integer.class);
        assertEquals(12, count);
        count = jdbcTemplate.queryForObject("SELECT count(*) FROM tradable_entity_snapshot", Integer.class);
        assertEquals(12, count);
        count = jdbcTemplate.queryForObject("SELECT count(*) FROM Portfolio", Integer.class);
        assertEquals(13, count);
        count = jdbcTemplate.queryForObject("SELECT count(*) FROM Portfolio_holding_snapshot", Integer.class);
        assertEquals(20, count);

        // Import same data again, count should not change
        ImportExcel.main(
                new String[] {"-e", ImportExcel.class.getClassLoader().getResource("Phase1TestData.xlsx").getPath(),
                        "-x", "applicationContext-cli-test.xml"});
        count = jdbcTemplate.queryForObject("SELECT count(*) FROM Instrument", Integer.class);
        assertEquals(12, count);
        count = jdbcTemplate.queryForObject("SELECT count(*) FROM tradable_entity", Integer.class);
        assertEquals(12, count);
        count = jdbcTemplate.queryForObject("SELECT count(*) FROM tradable_entity_snapshot", Integer.class);
        assertEquals(12, count);
        count = jdbcTemplate.queryForObject("SELECT count(*) FROM Portfolio", Integer.class);
        assertEquals(13, count);
        count = jdbcTemplate.queryForObject("SELECT count(*) FROM Portfolio_holding_snapshot", Integer.class);
        assertEquals(20, count);
    }

    /**
     * Test import, clean up calculation results.
     * @throws Exception if any exception occurs
     */
    @Test
    public void testImport_CleanupCalcResult() throws Exception {
        ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(),
                new InputStreamResource(ImportExcel.class.getClassLoader().getResourceAsStream("test_data.sql")));

        // Update some calculation results column
        jdbcTemplate.update("update Portfolio_holding_snapshot set DER_SEC_YIELD_1_DAY_INCOME_AMT = ?",
                new BigDecimal(12.34));

        ImportExcel.main(
                new String[] {"-e", ImportExcel.class.getClassLoader().getResource("Phase1TestData.xlsx").getPath(),
                        "-x", "applicationContext-cli-test.xml", "-c"});

        // The calculation results column should be set to null
        int count = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM Portfolio_holding_snapshot where DER_SEC_YIELD_1_DAY_INCOME_AMT is null",
                Integer.class);
        assertEquals(20, count);
    }
}
