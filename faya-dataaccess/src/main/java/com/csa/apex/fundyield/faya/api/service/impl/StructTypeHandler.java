package com.csa.apex.fundyield.faya.api.service.impl;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.data.jdbc.support.oracle.SqlStructValue;

import java.sql.*;

/*
 * This handler only supports SQL Objects (STRUCT) as an parameter
 */
public class StructTypeHandler implements TypeHandler {

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Object o, JdbcType jdbcType) throws SQLException {
        SqlStructValue value = (SqlStructValue) o;
        value.setTypeValue(preparedStatement, i, jdbcType.TYPE_CODE, null);
    }

    @Override
    public Object getResult(ResultSet resultSet, String s) throws SQLException {
        return null;
    }

    @Override
    public Object getResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    @Override
    public Object getResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }

}
