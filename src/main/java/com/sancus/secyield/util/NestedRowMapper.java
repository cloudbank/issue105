/*
 * Copyright (c) 2016 TopCoder, Inc. All rights reserved.
 */
package com.sancus.secyield.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.NotWritablePropertyException;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.JdbcUtils;

/**
 * The Custom Row Mapper for Jdbc Templates.
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
public class NestedRowMapper<T> implements RowMapper<T> {

	  private Class<T> mappedClass;

	  public NestedRowMapper(Class<T> mappedClass) {
	    this.mappedClass = mappedClass;
	  }

	  @Override
	  public T mapRow(ResultSet rs, int rowNum) throws SQLException {

	    T mappedObject = BeanUtils.instantiate(this.mappedClass);
	    BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(mappedObject);

	    bw.setAutoGrowNestedPaths(true);

	    ResultSetMetaData meta_data = rs.getMetaData();
	    int columnCount = meta_data.getColumnCount();
	    for (int index = 1; index <= columnCount; index++) {

	      try {

	        String column = JdbcUtils.lookupColumnName(meta_data, index);
	        Object value = JdbcUtils.getResultSetValue(rs, index, Class.forName(meta_data.getColumnClassName(index)));
	        bw.setPropertyValue(column, value);

	      } catch (TypeMismatchException | NotWritablePropertyException | ClassNotFoundException e) {
	         // Ignore
	      }
	    }

	    return mappedObject;
	  }
	}