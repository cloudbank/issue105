package com.csa.apex.fundyield.api.services.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;

import com.csa.apex.fundyield.utility.Constants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.csa.apex.fundyield.Application;
import com.csa.apex.fundyield.seccommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.seccommons.entities.Instrument;
import com.csa.apex.fundyield.seccommons.entities.InstrumentTypeCode;
import com.csa.apex.fundyield.seccommons.entities.PortfolioHoldingSnapshot;
import com.csa.apex.fundyield.seccommons.entities.SECConfiguration;
import com.csa.apex.fundyield.seccommons.entities.TradableEntitySnapshot;
import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.TestUtility;

/**
 * Test class for the SecuritySECYieldServiceImpl.
 *
 * @see SecuritySECYieldServiceImpl
 * @author [es],TCSDEVELOPER
 * @version 1.1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class SecuritySECYieldServiceImplTest {

	/**
	 * The test export file name.
	 */
	private static final String TEST_EXPORT_CSV_FILE_NAME = "test-export.csv";

	/**
	 * The export file type.
	 */
	private static final String EXPORT_FILE_TYPE = "application/zip";

	/**
	 * Utility class.
	 */
	@Autowired
	private TestUtility utility;

	/**
	 * SecuritySECYieldServiceImpl object.
	 */
	@Autowired
	private SecuritySECYieldServiceImpl securitySECYieldServiceImpl;

	/**
	 * Mock rest template.
	 */
	@Mock
	private RestTemplate restTemplate;

	/**
	 * Mock Setup.
	 * 
	 * @throws ParseException
	 * @throws RestClientException
	 */
	@Before
	public void setUp() throws RestClientException, ParseException {

		FundAccountingYieldData data = utility.constructFAYAData();
		Instrument instrument = data.getInstruments().get(0);
		TradableEntitySnapshot tes = instrument.getTradableEntities().get(0).getTradableEntitySnapshots().get(0);
		PortfolioHoldingSnapshot holding = data.getPortfolios().get(0).getPortfolioHoldings().get(0);

		SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_MASK_MM_DD_YYYY);
		data.setReportDate(formatter.parse("06/03/2016"));
		instrument.setFinalMaturityDate(formatter.parse("02/15/2044"));
		instrument.setMaturityPrc(utility.getBigDecimalWithScale7(new BigDecimal(100)));
		instrument.setInstrumentTypeCode(InstrumentTypeCode.VPS);

		tes.setMarketPrice(utility.getBigDecimalWithScale7(new BigDecimal(114.7389035)));
		tes.setCurrentIncomeRate(utility.getBigDecimalWithScale7(new BigDecimal(0.01375)));
		tes.setFdrTipsInflationaryRatio(utility.getBigDecimalWithScale7(new BigDecimal(1.022250268)));

		holding.setInflationAdjShareCnt(utility.getBigDecimalWithScale7(new BigDecimal(42.23)));
		holding.setSettleShareCnt(utility.getBigDecimalWithScale7(new BigDecimal(142.23)));
		holding.setFxRt(utility.getBigDecimalWithScale7(new BigDecimal(1)));
		holding.setMarketValueBaseAmt(utility.getBigDecimalWithScale7(new BigDecimal(70135342.4)));
		holding.setAccruedIncomeAmt(utility.getBigDecimalWithScale7(new BigDecimal(257693.72)));
		holding.setEarnedInflCmpsBaseAmt(utility.getBigDecimalWithScale7(new BigDecimal(-4956.56)));

		// Create mocks
		MockitoAnnotations.initMocks(this);
		when(restTemplate.getForObject(any(String.class), eq(SECConfiguration.class)))
				.thenReturn(new SECConfiguration());
		when(restTemplate.getForObject(any(URI.class), eq(FundAccountingYieldData.class))).thenReturn(data);
		when(restTemplate.exchange(any(String.class), eq(HttpMethod.PUT), any(HttpEntity.class), eq(Boolean.class)))
				.thenReturn(new ResponseEntity<Boolean>(true, new HttpHeaders(), HttpStatus.CREATED));

		securitySECYieldServiceImpl.setRestTemplate(restTemplate);
	}

	/**
	 * Test processSecuritySECData Yield and income is asserted to check
	 * calculation has been done.
	 *
	 * @throws Exception
	 */
	@Test
	public void processSecuritySECDataTest() throws Exception {
		FundAccountingYieldData data = securitySECYieldServiceImpl.processSecuritySECData(new Date());

		assertEquals(
				CommonUtility.getTradableEntitySnapshot(data.getInstruments().get(0)).getDerOneDaySecurityYield()
						.setScale(2, BigDecimal.ROUND_HALF_DOWN),
				new BigDecimal(-0.02).setScale(2, BigDecimal.ROUND_HALF_DOWN));
		assertEquals(data.getPortfolios().get(0).getPortfolioHoldings().get(0).getDerSecYield1DayIncomeAmt().setScale(2,
				BigDecimal.ROUND_HALF_DOWN), new BigDecimal(-8867.28).setScale(2, BigDecimal.ROUND_HALF_DOWN));

	}

	/**
	 * Test getCalculatedSecuritySECData.
	 * 
	 * @throws Exception
	 */
	@Test
	public void getCalculatedSecuritySECDataMockTest() throws Exception {
		FundAccountingYieldData data = securitySECYieldServiceImpl.getCalculatedSecuritySECData(new Date());
		assertEquals(data.getInstruments().size(), 1);
		assertEquals(data.getPortfolios().size(), 1);
	}

	/**
	 * Test exportCalculatedSecuritySECData.
	 * 
	 * @throws Exception
	 *             if any exception occurs
	 */
	@Test
	public void exportCalculatedSecuritySECDataTest() throws Exception {
		HttpServletResponse response = mock(HttpServletResponse.class);
		MockServletOutputStream servletOutputStream = new MockServletOutputStream();
		when(response.getOutputStream()).thenReturn(servletOutputStream);
		securitySECYieldServiceImpl.exportCalculatedSecuritySECData(new Date(), response);
		verify(response).setContentType(EXPORT_FILE_TYPE);
		final File csvFile = convertServletOutputStreamToFile(servletOutputStream);
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File expectedCsvFile = new File(classLoader.getResource(TEST_EXPORT_CSV_FILE_NAME).getFile());
			assertEquals(getCSVFileContentsAsString(csvFile), getCSVFileContentsAsString(expectedCsvFile));
		} finally {
			csvFile.delete();
		}
	}

	/**
	 * Mocked <ServletOutputStream> to check the contents of the zip file after
	 * it is written.
	 */
	private final class MockServletOutputStream extends ServletOutputStream {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		public void write(int i) throws IOException {
			byteArrayOutputStream.write(i);
		}

		public ZipInputStream getZipInputStream() {
			return new ZipInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
		}

		@Override
		public boolean isReady() {
			return true;
		}

		@Override
		public void setWriteListener(WriteListener listener) {
		}
	}

	/**
	 * Converts the servlet output stream to a file.
	 * 
	 * @param servletOutputStream
	 *            the servlet output stream
	 * @return the file
	 * @throws IOException
	 *             if any I/O exception occurs or if the test export file is not
	 *             found
	 */
	private File convertServletOutputStreamToFile(MockServletOutputStream servletOutputStream) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ZipInputStream zipInputStream = servletOutputStream.getZipInputStream();
		while (zipInputStream.getNextEntry() != null) {
			for (int b = zipInputStream.read(); b != -1; b = zipInputStream.read()) {
				baos.write(b);
			}
			zipInputStream.closeEntry();
		}
		baos.close();
		zipInputStream.close();

		final File csvFile = new File(TEST_EXPORT_CSV_FILE_NAME);
		FileOutputStream fos = new FileOutputStream(csvFile);
		baos.writeTo(fos);
		fos.close();
		return csvFile;
	}

	/**
	 * Transforms the contents of a CSV file to string.
	 * 
	 * @param csvFile
	 *            the CSV file
	 * @return the
	 * @throws IOException
	 */
	private String getCSVFileContentsAsString(File csvFile) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(csvFile.getAbsolutePath()));
		StringBuilder sb = new StringBuilder();
		String line = br.readLine();
		while (line != null) {
			sb.append(line);
			sb.append(System.lineSeparator());
			line = br.readLine();
		}
		br.close();
		return sb.toString();
	}
}