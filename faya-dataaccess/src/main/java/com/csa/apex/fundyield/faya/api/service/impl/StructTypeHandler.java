package com.csa.apex.fundyield.faya.api.service.impl;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.data.jdbc.support.oracle.SqlStructValue;

import java.sql.*;

/**
 * Mybatis handler to support STRUCT data type.
 * This handler only supports SQL Objects (STRUCT) as an parameter
 *
 * Refer to the Mybatis mapper file for usage example (e.g. RowMapper.xml, StoredProcedures.xml).
 */
public class StructTypeHandler implements TypeHandler {

    /**
     * This will be invoked by Mybatis to when it sees a parameter STRUCT. It will then translate the model object
     * to the equivalent STRUCT (Type Object in Oracle)
     *
     * @param preparedStatement
     * @param i
     * @param o
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Object o, JdbcType jdbcType) throws SQLException {
        SqlStructValue value = (SqlStructValue) o;
        value.setTypeValue(preparedStatement, i, jdbcType.TYPE_CODE, null);
    }

    /**
     * Not implemented since it is not required.
     *
     * @param resultSet
     * @param s
     * @return
     * @throws SQLException
     */
    @Override
    public Object getResult(ResultSet resultSet, String s) throws SQLException {
        return null;
    }

    /**
     * Not implemented since it is not required
     * @param resultSet
     * @param i
     * @return
     * @throws SQLException
     */
    @Override
    public Object getResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    /**
     * Not implemented since it is not required
     *
     * @param callableStatement
     * @param i
     * @return
     * @throws SQLException
     */
    @Override
    public Object getResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }

}
