/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.utility;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.csa.apex.fundyield.api.services.impl.moneymarketfundyield.ClassLevel1DayN1AYieldCalculationInput;
import com.csa.apex.fundyield.api.services.impl.moneymarketfundyield.ClassLevel30DayDistributionYieldCalculationInput;
import com.csa.apex.fundyield.api.services.impl.moneymarketfundyield.ClassLevel30DayN1AYieldCalculationInput;
import com.csa.apex.fundyield.api.services.impl.moneymarketfundyield.ClassLevel7DayN1AYieldCalculationInput;
import com.csa.apex.fundyield.api.services.impl.moneymarketfundyield.ClassLevelRestated30DayYieldCalculationInput;
import com.csa.apex.fundyield.api.services.impl.moneymarketfundyield.ClassLevelRestated7DayYieldCalculationInput;
import com.csa.apex.fundyield.api.services.impl.utility.UtilityCustomerAPIClientImpl;
import com.csa.apex.fundyield.api.services.impl.utility.UtilityFAYAAPIClient;
import com.csa.apex.fundyield.fayacommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.fayacommons.entities.Instrument;
import com.csa.apex.fundyield.fayacommons.entities.Portfolio;
import com.csa.apex.fundyield.fayacommons.entities.PortfolioHoldingSnapshot;
import com.csa.apex.fundyield.fayacommons.entities.PortfolioSnapshot;
import com.csa.apex.fundyield.fayacommons.entities.ShareClass;
import com.csa.apex.fundyield.fayacommons.entities.ShareClassSnapshot;
import com.csa.apex.fundyield.fayacommons.entities.TradableEntity;
import com.csa.apex.fundyield.fayacommons.entities.TradableEntitySnapshot;

/**
 * Utility class for the CalculationEngine.
 * @see TestUtility
 * @author [es],TCSDEVELOPER
 * @version 1.1
 */
@Component
public class TestUtility {

    /**
     * Inject field value.
     * @param obj The object
     * @param fieldName The field name
     * @param fieldValue The field value
     * @throws Exception
     */
    public void injectField(Object obj, String fieldName, Object fieldValue) throws Exception {
        Field field = FieldUtils.getDeclaredField(obj.getClass(), fieldName, true);
        field.set(obj, fieldValue);
    }

    /**
     * Construct FundAccountingYieldData for test.
     * @return FundAccountingYieldData
     */
    public FundAccountingYieldData constructFAYAData() {
        FundAccountingYieldData data = new FundAccountingYieldData();
        Instrument instrument = new Instrument();
        TradableEntity te = new TradableEntity();
        TradableEntitySnapshot tes = new TradableEntitySnapshot();
        te.setTradableEntitySnapshots(Arrays.asList(tes));
        instrument.setTradableEntities(Arrays.asList(te));

        Portfolio portfolio = new Portfolio();
        PortfolioHoldingSnapshot holding = new PortfolioHoldingSnapshot();
        holding.setTradableEntity(te);

        portfolio.setPortfolioHoldings(Arrays.asList(holding));

        data.setInstruments(Arrays.asList(instrument));
        data.setPortfolios(Arrays.asList(portfolio));
        return data;
    }

    /**
     * Returns value with scale 7 with ROUND_HALF_DOWN.
     * @param val value passed
     * @return value with scale 7
     */
    public BigDecimal getBigDecimalWithScale7(BigDecimal val) {
        val = val.setScale(7, BigDecimal.ROUND_HALF_DOWN);
        return val;
    }

    public static ClassLevelRestated7DayYieldCalculationInput getClassLevelRestated7DayYieldCalculationInput() {
        ClassLevelRestated7DayYieldCalculationInput rt = new ClassLevelRestated7DayYieldCalculationInput();
        rt.setN1ADistIncomeBreakageAmt(BigDecimal.ONE);
        rt.setN1ADistIncomeAdjAmt(BigDecimal.ONE);
        rt.setdPrevious6Days(BigDecimal.ONE);
        rt.setN1ADistReimbursementAmt(BigDecimal.ONE);
        rt.setNavAmt(BigDecimal.ONE);
        rt.setN1AReimbursementOpct(BigDecimal.ONE);
        rt.setOperationScale(2);
        rt.setN1ADistIncomeAdjRevAmt(BigDecimal.ONE);
        rt.setN1AReimbursementEarnedAmt(BigDecimal.ONE);
        rt.setDistributableCapstockQty(BigDecimal.ONE);
        rt.setN1AReimbursementStr(BigDecimal.ONE);
        rt.setN1ADistIncomeUnmodAmt(BigDecimal.ONE);
        return rt;

    }

    public static ClassLevel1DayN1AYieldCalculationInput getClassLevel1DayN1AYieldCalculationInput() {
        ClassLevel1DayN1AYieldCalculationInput rt = new ClassLevel1DayN1AYieldCalculationInput();
        rt.setN1ADistIncomeBreakageAmt(BigDecimal.ONE);
        rt.setN1ADistIncomeAdjAmt(BigDecimal.ONE);
        rt.setN1ADistReimbursementAmt(BigDecimal.ONE);
        rt.setNavAmount(BigDecimal.ONE);
        rt.setN1ADistIncomeOpct(BigDecimal.ONE);
        rt.setOperationScale(2);
        rt.setN1ADistIncomeAdjRevAmt(BigDecimal.ONE);
        rt.setDistributableCapstockQty(BigDecimal.ONE);
        rt.setN1ADistIncomeStr(BigDecimal.ONE);
        rt.setN1ADistIncomeUnmodAmt(BigDecimal.ONE);
        return rt;
    }

    public static ClassLevel30DayDistributionYieldCalculationInput getClassLevel30DayDistributionYieldCalculationInput() {
        ClassLevel30DayDistributionYieldCalculationInput rt = new ClassLevel30DayDistributionYieldCalculationInput();
        rt.setDaysInYear(1);
        rt.setDistYieldMilRt(BigDecimal.ONE);
        rt.setNavAmt(BigDecimal.ONE);
        rt.setOperationScale(2);
        rt.setReportingDate(1);
        rt.setDistUnmod30DayYieldPct(BigDecimal.ONE);
        return rt;
    }

    public static com.csa.apex.fundyield.api.services.impl.distributionfundyield.ClassLevel30DayDistributionYieldCalculationInput getClassLevel30DayDistributionYieldCalculationInput2() {
        com.csa.apex.fundyield.api.services.impl.distributionfundyield.ClassLevel30DayDistributionYieldCalculationInput rt = new com.csa.apex.fundyield.api.services.impl.distributionfundyield.ClassLevel30DayDistributionYieldCalculationInput();
        rt.setDayOfReportingDate(1);
        rt.setDaysInYear(1);
        rt.setDistUnmod30DayYieldPct(BigDecimal.ONE);
        rt.setOperationScale(2);
        rt.setDistYieldMilRt(BigDecimal.ONE);
        rt.setNavAmt(BigDecimal.ONE);
        return rt;
    }

    public static ClassLevel30DayN1AYieldCalculationInput getClassLevel30DayN1AYieldCalculationInput() {
        ClassLevel30DayN1AYieldCalculationInput rt = new ClassLevel30DayN1AYieldCalculationInput();
        rt.setDerMnyMkt1DayN1AYieldPct(BigDecimal.ONE);
        rt.setSumOfDer1DayYieldN1AMnyMktPctPrevious29Days(BigDecimal.ONE);
        rt.setOperationScale(2);
        return rt;
    }

    public static ClassLevel7DayN1AYieldCalculationInput getClassLevel7DayN1AYieldCalculationInput() {
        ClassLevel7DayN1AYieldCalculationInput rt = new ClassLevel7DayN1AYieldCalculationInput();
        rt.setDerMnyMkt1DayN1AYieldPct(BigDecimal.ONE);
        rt.setSumOfDer1DayYieldN1AMnyMktPctPrevious6Days(BigDecimal.ONE);
        rt.setOperationScale(2);
        return rt;
    }

    public static ClassLevelRestated30DayYieldCalculationInput getClassLevelRestated30DayYieldCalculationInput() {
        ClassLevelRestated30DayYieldCalculationInput rt = new ClassLevelRestated30DayYieldCalculationInput();
        rt.setDerMmRestate1DayYieldPct(BigDecimal.ONE);
        rt.setSumOfDerRestate1DayYieldMnyMktPctPrevious29Days(BigDecimal.ONE);
        rt.setOperationScale(2);
        return rt;
    }

    public static FundAccountingYieldData getFundAccountingYieldData() {
        FundAccountingYieldData rt = new FundAccountingYieldData();
        rt.setPortfolios(new ArrayList<Portfolio>());
        rt.setInstruments(new ArrayList<Instrument>());

        rt.setBusinessDate(new Date(0));
        rt.setReportDate(new Date(0));

        Portfolio portfolio = new Portfolio();
        portfolio.setPortfolioSnapshots(new ArrayList<PortfolioSnapshot>());
        portfolio.setShareClasses(new ArrayList<ShareClass>());
        ShareClass shareClass = new ShareClass();
        PortfolioSnapshot portfolioSnapshot = getPortfolioSnapshot();

        portfolioSnapshot.setFdrPortfolioStateTaxRt(BigDecimal.TEN);
        portfolioSnapshot.setFdrN1AOospGrosDistInc(BigDecimal.TEN);

        portfolio.getShareClasses().add(shareClass);
        portfolio.getPortfolioSnapshots().add(portfolioSnapshot);

        ShareClassSnapshot shareClassSnapshot = getShareClassSnapshot();
        shareClass.setShareClassSnapshots(new ArrayList<ShareClassSnapshot>());
        shareClass.getShareClassSnapshots().add(shareClassSnapshot);

        rt.getPortfolios().add(portfolio);
        return rt;
    }

    public static PortfolioSnapshot getPortfolioSnapshot() {
        PortfolioSnapshot rt = new PortfolioSnapshot();
        rt.setPortfolioSnapshotSid(111);
        rt.setReportDate(new Date(0));
        rt.setDerPortfolioTierNbr(222L);

        rt.setAdjDerSec1DayYieldAmt(BigDecimal.ONE);
        rt.setAdjDerSec30DayYieldAmt(BigDecimal.ONE);
        rt.setDer1DaySecMilRt(BigDecimal.ONE);
        rt.setDer30DaySecMilRt(BigDecimal.ONE);
        rt.setDerDist12MoYieldChgPmePct(BigDecimal.ONE);
        rt.setDerDist1DayYieldC2CCmprPct(BigDecimal.ONE);
        rt.setDerDist1DayYieldPmeChgPct(BigDecimal.ONE);
        rt.setDerDist30DayYieldC2CCmprPct(BigDecimal.ONE);
        rt.setDerDist30DayYieldChgPmePct(BigDecimal.ONE);
        rt.setDerFundSecYieldPmeChgPct(BigDecimal.ONE);
        rt.setDerMnyMkt30DayYieldPmeChgPct(BigDecimal.ONE);
        rt.setDerMnyMkt7DayYieldPmeChgPct(BigDecimal.ONE);
        rt.setDerMnyMktGrossYieldDodChgPct(BigDecimal.ONE);
        rt.setDerMnyMktRst7DayYieldDodChgPct(BigDecimal.ONE);
        rt.setDerRstSecYieldPmeChgPct(BigDecimal.ONE);
        rt.setDerSec1DayYieldAmt(BigDecimal.ONE);
        rt.setDerSec30DayYieldAmt(BigDecimal.ONE);
        rt.setDerSecRestartedYieldAmt(BigDecimal.ONE);
        rt.setDerTotalIncomeAmt(BigDecimal.ONE);
        rt.setExpenseAmt(BigDecimal.ONE);
        rt.setFdrDistributableCapstockQty(BigDecimal.ONE);
        rt.setFdrN1ANetOospDistIncAmt(BigDecimal.ONE);
        rt.setFdrN1AOospDistAmortAmt(BigDecimal.ONE);
        rt.setFdrN1AOospDistIncomeAmt(BigDecimal.ONE);
        rt.setFdrN1AOospGrosDistInc(BigDecimal.ONE);
        rt.setFdrN1ATotGrosIncUnmodAmt(BigDecimal.ONE);
        rt.setFdrPortfolioStateTaxRt(BigDecimal.ONE);
        rt.setFdrSecYieldAdjAmt(BigDecimal.ONE);
        rt.setMnyMkt1DayDistYieldPct(BigDecimal.ONE);
        rt.setMnyMkt1DayGrossYieldPct(BigDecimal.ONE);
        rt.setMnyMktN1AStateTaxRt(BigDecimal.ONE);

        return rt;
    }

    public static ShareClassSnapshot getShareClassSnapshot() {
        ShareClassSnapshot rt = new ShareClassSnapshot();
        rt.setAdjExclShareClassCalcInd("Y");
        rt.setCalendarDate(new Date());
        rt.setReportDate(new Date(0));
        rt.setShareClassSnapshotSid(111);

        rt.setDerDist12MoYieldPct(BigDecimal.ONE);
        rt.setDerDist30DayYieldPct(BigDecimal.ONE);
        rt.setDerMnyMkt1DayDistYieldDodChgPct(BigDecimal.ONE);
        rt.setDerMnyMkt1DayN1AYieldDodChgPct(BigDecimal.ONE);
        rt.setDerMnyMkt1DayN1AYieldPct(BigDecimal.ONE);
        rt.setDerMnyMkt30DayN1AYieldPct(BigDecimal.ONE);
        rt.setDerMnyMkt30DayYieldPct(BigDecimal.ONE);
        rt.setDerMnyMkt7DayDistYieldDodChgPct(BigDecimal.ONE);
        rt.setDerMnyMkt7DayN1AYieldDodChgPct(BigDecimal.ONE);
        rt.setDerMnyMkt7DayN1AYieldPct(BigDecimal.ONE);
        rt.setDerMnyMkt7DayYieldPct(BigDecimal.ONE);
        rt.setDerMnyMktCompound7DayYieldPct(BigDecimal.ONE);
        rt.setDerMnyMktN1ACompound7dayYield(BigDecimal.ONE);
        rt.setDerMnyMktRestate1DayYieldPct(BigDecimal.ONE);
        rt.setDerMnyMktRestatedMilRt(BigDecimal.ONE);
        rt.setDerMnyMktRst30DayYieldPct(BigDecimal.ONE);
        rt.setDerMnyMktRst7DayYieldPct(BigDecimal.ONE);
        rt.setDerN1ADailyMilRt(BigDecimal.ONE);
        rt.setDerN1ADailyYieldPct(BigDecimal.ONE);
        rt.setDerN1ADistIncomeAmt(BigDecimal.ONE);
        rt.setDerSec1DayYieldPct(BigDecimal.ONE);
        rt.setDerSec30DayYieldPct(BigDecimal.ONE);
        rt.setDerSecRestatedYieldPct(BigDecimal.ONE);
        rt.setDerSecYieldPmeChgPct(BigDecimal.ONE);
        rt.setDerTotalExpenseAmt(BigDecimal.ONE);
        rt.setDist12MoMilRt(BigDecimal.ONE);
        rt.setDist1DayYieldPct(BigDecimal.ONE);
        rt.setDist30DayYieldPct(BigDecimal.ONE);
        rt.setDistributableCapstockQty(BigDecimal.ONE);
        rt.setDistUnmod30DayYieldPct(BigDecimal.ONE);
        rt.setDistYieldMilRt(BigDecimal.ONE);
        rt.setExpenseAmt(BigDecimal.ONE);
        rt.setFdrN1AGrossDistIncomeAmt(BigDecimal.ONE);
        rt.setFdrN1ATaxAmt(BigDecimal.ONE);
        rt.setMm1DayMilRt(BigDecimal.ONE);
        rt.setMnyMkt1DayYieldPct(BigDecimal.ONE);
        rt.setN1ADistIncomeAdjAmt(BigDecimal.ONE);
        rt.setN1ADistIncomeAdjRevAmt(BigDecimal.ONE);
        rt.setN1ADistIncomeBreakageAmt(BigDecimal.ONE);
        rt.setN1ADistIncomeUnmodAmt(BigDecimal.ONE);
        rt.setN1ADistReimbursementAmt(BigDecimal.ONE);
        rt.setN1AGrossIncomeUnmodAmt(BigDecimal.ONE);
        rt.setN1AReimbursementEarnedAmt(BigDecimal.ONE);
        rt.setN1AWaiverEarnedAmt(BigDecimal.ONE);
        rt.setNavAmt(BigDecimal.ONE);
        rt.setSecReimbursementEarnedAmt(BigDecimal.ONE);

        return rt;
    }

    public static UtilityFAYAAPIClient getUtilityFAYAAPIClient() throws Exception {
        UtilityCustomerAPIClientImpl rt = new UtilityCustomerAPIClientImpl();
        rt.setGetAvgOfMnyMkt1DayDistYieldPctForPreviousDaysApiPath("mock");
        rt.setGetAvgOfMnyMkt7DayYieldPctForPreviousDaysApiPath("mock");
        rt.setGetSumOfDer1DayYieldN1AMnyMktPctPreviousDaysApiPath("mock");
        rt.setGetSumOfDer7DayYieldN1AMnyMktPctPreviousDaysApiPath("mock");
        rt.setGetSumOfDerRestate1DayYieldMnyMktPctPreviousDaysApiPath("mock");
        RestTemplate restTemplate = mock(RestTemplate.class);
        PowerMockito.mockStatic(UriComponentsBuilder.class);
        URI uri = new URI("");
        UriComponentsBuilder builder = mock(UriComponentsBuilder.class);
        when(UriComponentsBuilder.fromHttpUrl("mock")).thenReturn(builder);
        when(builder.queryParam(any(String.class), any(Date.class))).thenReturn(builder);
        when(builder.queryParam(any(String.class), any(Date.class))).thenReturn(builder);
        when(builder.queryParam(any(String.class), any(Date.class))).thenReturn(builder);
        UriComponents c = mock(UriComponents.class);
        when(builder.build()).thenReturn(c);
        when(c.encode()).thenReturn(c);
        when(c.toUri()).thenReturn(uri);
        when(restTemplate.getForObject(any(URI.class), eq(BigDecimal.class))).thenReturn(BigDecimal.TEN);
        when(restTemplate.exchange(any(String.class), eq(HttpMethod.PUT), any(HttpEntity.class), eq(Boolean.class)))
                .thenReturn(new ResponseEntity<Boolean>(true, new HttpHeaders(), HttpStatus.CREATED));

        rt.setRestTemplate(restTemplate);
        return rt;
    }
}
