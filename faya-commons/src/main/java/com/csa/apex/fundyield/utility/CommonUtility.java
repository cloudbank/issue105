/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.utility;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.csa.apex.fundyield.exceptions.ConfigurationException;
import com.csa.apex.fundyield.fayacommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.fayacommons.entities.Instrument;
import com.csa.apex.fundyield.fayacommons.entities.Portfolio;
import com.csa.apex.fundyield.fayacommons.entities.PortfolioHoldingSnapshot;
import com.csa.apex.fundyield.fayacommons.entities.TradableEntity;
import com.csa.apex.fundyield.fayacommons.entities.TradableEntitySnapshot;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * CommonUtility Exposes useful function used through out the code.
 * @author [es],TCSDEVELOPER
 * @version 1.0
 */
public class CommonUtility {

    /**
     * logger class instance.
     */
    private static final Logger LOGGER = Logger.getLogger(CommonUtility.class);

    /**
     * Gson used to convert object to string.
     */
    public static final Gson GSON_FOR_STRING = new Gson();

    /**
     * Gson used to calculate object hash.
     */
    public static final Gson GSON_FOR_HASH;

    static {
        GSON_FOR_HASH = new GsonBuilder().addSerializationExclusionStrategy(new ExclusionStrategy() {

            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                Class<?> fieldType = f.getDeclaredClass();
                return Collection.class.isAssignableFrom(fieldType)
                        || TradableEntity.class == fieldType
                        || Instrument.class == fieldType;
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        }).create();
    }

    /**
     * Private constructor.
     */
    private CommonUtility() {
        // Empty
    }

    /**
     * Convert entity to string.
     * @param entity The object entity
     * @return entity's string representation.
     */
    public static String toString(Object entity) {
        return GSON_FOR_STRING.toJson(entity);
    }

    /**
     * Calculate entity's hash text.
     * @param entity The object entity
     * @return entity's hash text.
     */
    public static String getHashText(Object entity) {
        return DigestUtils.sha384Hex(GSON_FOR_HASH.toJson(entity).getBytes());
    }

    /**
     * Get tradable entity snapshot of instrument.
     * @param instrument The instrument
     * @return tradable entity snapshot
     */
    public static TradableEntitySnapshot getTradableEntitySnapshot(Instrument instrument) {

        List<TradableEntity> tradableEntities = instrument.getTradableEntities();
        if (tradableEntities == null || tradableEntities.isEmpty()) {
            LOGGER.warn("Instrument[" + instrument.getInstrumentSid() + "] does not have tradable entities");
            return null;
        }

        List<TradableEntitySnapshot> tes = tradableEntities.get(0).getTradableEntitySnapshots();

        if (tes == null || tes.isEmpty()) {
            LOGGER.warn(
                    "TradableEntity[" + tradableEntities.get(0).getTradableEntitySid() + "] does not have snapshots");
            return null;
        }

        return tes.get(0);
    }

    /**
     * Get related portfolio holdings for given tradable entity.
     * @param data The FundAccountingYieldData
     * @param tradableEntitySid The tradable entity SID
     * @return related portfolio holdings
     */
    public static List<PortfolioHoldingSnapshot> getRelatedPortfolioHoldings(FundAccountingYieldData data,
            long tradableEntitySid) {
        List<PortfolioHoldingSnapshot> related = new ArrayList<>();
        for (Portfolio portfolio : data.getPortfolios()) {
            for (PortfolioHoldingSnapshot phs : portfolio.getPortfolioHoldings()) {
                if (phs.getTradableEntity() != null
                        && phs.getTradableEntity().getTradableEntitySid() == tradableEntitySid) {
                    related.add(phs);
                }
            }
        }
        return related;
    }

    /**
     * Check object is not null.
     * @param obj The object
     * @param des The description
     * @throws IllegalArgumentException if object is null
     */
    public static void checkNull(Object obj, String des) {
        if (obj == null) {
            throw new IllegalArgumentException(des + " can not be null");
        }
    }

    /**
     * Check string property is not null/empty.
     * @param str The string property
     * @param des The description
     * @throws ConfigurationException if string property is null/empty
     */
    public static void checkString(String str, String des) {
        if (StringUtils.isBlank(str)) {
            throw new IllegalArgumentException(des + " can not be null/empty");
        }
    }

    /**
     * Check number is not negative.
     * @param num The number
     * @param des The description
     * @throws IllegalArgumentException if num is null or negative
     */
    public static void checkNumber(Number num, String des) {
        checkNull(num, des);
        if (num.longValue() < 0) {
            throw new IllegalArgumentException(des + " cannot be negative");
        }
    }

    /**
     * Check object property is configured properly.
     * @param obj The object property
     * @param des The description
     * @throws ConfigurationException if object property is not configured properly
     */
    public static void checkNullConfig(Object obj, String des) {
        if (obj == null) {
            throw new ConfigurationException("Configured properly [" + des + "] can not be null");
        }
    }

    /**
     * Check string property is configured properly.
     * @param str The string property
     * @param des The description
     * @throws ConfigurationException if string property is not configured properly
     */
    public static void checkStringConfig(String str, String des) {
        if (StringUtils.isBlank(str)) {
            throw new ConfigurationException("Configured string properly [" + des + "] can not be null/empty");
        }
    }

    /**
     * Check list property is configured properly.
     * @param list The list property
     * @param des The description
     * @throws ConfigurationException if list property is not configured properly
     */
    public static void checkListConfig(List<?> list, String des) {
        checkNullConfig(list, des);
        if (list.isEmpty()) {
            throw new ConfigurationException("Configured list property [" + des + "] can not be empty");
        }
        for (Object obj : list) {
            checkNullConfig(obj, "Item of " + des);
        }
    }
}
