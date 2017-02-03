package com.csa.apex.fundyield.api.services.impl;

import com.csa.apex.fundyield.exceptions.CalculationException;
import com.csa.apex.fundyield.fayacommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.fayacommons.entities.Portfolio;
import com.csa.apex.fundyield.fayacommons.entities.SECConfiguration;
import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.Constants;
import com.csa.apex.fundyield.utility.LogMethod;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.concurrent.*;

public abstract class BaseCalculationEngineImpl implements CalculationEngine {

    /**
     * The thread count.
     */
    @Value("${thread.count}")
    private int threadCount;

    /**
     * The SECConfiguration.
     */
    protected SECConfiguration configuration;

    /**
     * Multi-threaded calculation method.
     * @param fundAccountingYieldData the input FundAccountingYieldData;
     * @param configuration the SECConfiguration to be used for config values; if the the config values are provided
     *            they will be used instead of default ones.
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws CalculationException in case any error during calculation.
     */
    @LogMethod
    public void calculate(FundAccountingYieldData fundAccountingYieldData, SECConfiguration configuration)
            throws CalculationException {
        CommonUtility.checkNull(fundAccountingYieldData, this.getClass().getCanonicalName(), Constants.METHOD_CALCULATE, "Parameter fundAccountingYieldData");
        CommonUtility.checkNull(configuration, this.getClass().getCanonicalName(), Constants.METHOD_CALCULATE, "Parameter configuration");

        this.configuration = configuration;

        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        ExecutorCompletionService<Portfolio> completionService = new ExecutorCompletionService<>(executorService);
        Map<Future,Portfolio> jobs = new IdentityHashMap<>();

        for (Portfolio portfolio : fundAccountingYieldData.getPortfolios()) {
            Future<Portfolio> future = completionService.submit(new CalculationThread(portfolio, fundAccountingYieldData.getReportDate()));
            jobs.put(future, portfolio);
        }

        try {
            while(!jobs.isEmpty()) {
                Future<Portfolio> result = completionService.take();
                jobs.remove(result);
            }
        } catch (Exception e) {
            for (Future<Portfolio> future : jobs.keySet())
                future.cancel(true);
            throw new CalculationException(e.getMessage(), e);
        }
    }

    protected abstract Portfolio doCalculate(Portfolio portfolio, Date reportDate) throws Exception;

    private class CalculationThread implements Callable<Portfolio> {

        Portfolio portfolio;
        Date reportDate;

        public CalculationThread(Portfolio portfolio, Date reportDate) {
            this.portfolio = portfolio;
            this.reportDate = reportDate;
        }

        @Override
        public Portfolio call() throws Exception {
            return doCalculate(portfolio, reportDate);
        }
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }
}
