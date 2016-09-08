package com.csa.apex.secyield.api.services.impl;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import com.csa.apex.secyield.Application;
import com.csa.apex.secyield.entities.SECConfiguration;
import com.csa.apex.secyield.entities.SecuritySECData;
import com.csa.apex.secyield.utility.TestUtility;

/**
 * Test class for the SECYieldServiceImpl.
 *
 * @see SECYieldServiceImpl
 * @author [es],TCSDEVELOPER
 * @version 1.1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class SECYieldServiceImplTest {

	/**
	 * The test export file name
	 */
	private static final String TEST_EXPORT_CSV_FILE_NAME = "test-export.csv";

	/**
	 * The export file type
	 */
	private static final String EXPORT_FILE_TYPE = "application/zip";

	/**
	 * Utility class
	 */
	@Autowired
	private TestUtility utility;

	// Create Mock
	@Mock
	private RestTemplate restTemplate;

	/**
	 * Mock Setup
	 * 
	 * @throws ParseException
	 * @throws RestClientException
	 */
	@Before
	public void setUp() throws RestClientException, ParseException {
		MockitoAnnotations.initMocks(this);
		when(restTemplate.getForObject(any(URI.class), eq(SECConfiguration.class))).thenReturn(new SECConfiguration());
		when(restTemplate.getForObject(any(URI.class), eq(SecuritySECData[].class)))
				.thenReturn(utility.getSecuritySECDataArray());
		when(restTemplate.exchange(any(String.class), eq(HttpMethod.PUT), any(HttpEntity.class), eq(Boolean.class)))
				.thenReturn(new ResponseEntity<Boolean>(true, new HttpHeaders(), HttpStatus.CREATED));
	}

	/**
	 * SECYieldServiceImpl object
	 */
	@InjectMocks
	@Autowired
	@Qualifier("secYieldServiceImpl")
	SECYieldServiceImpl secyYieldServiceImpl;

	/**
	 * Test processSecuritySECData Yield and income is asserted to check calculation has been done
	 * 
	 * @throws Exception
	 */
	@Test
	public void processSecuritySECDataTest() throws Exception {
		List<SecuritySECData> data = secyYieldServiceImpl.processSecuritySECData(new Date());
		SecuritySECData securitySECData = data.get(0);
		assertEquals(securitySECData.getDerOneDaySecurityYield().setScale(7, BigDecimal.ROUND_HALF_DOWN),
				securitySECData.getSecurityReferenceData().getInterestRt().setScale(7, BigDecimal.ROUND_HALF_DOWN));
		assertEquals(securitySECData.getPositionData()[0].getDerOneDaySecurityIncome().setScale(2,
				BigDecimal.ROUND_HALF_DOWN), new BigDecimal(26690.42).setScale(2, BigDecimal.ROUND_HALF_DOWN));

	}

	/**
	 * Test getCalculatedSecuritySECData
	 * 
	 * @throws Exception
	 */
	@Test
	public void getCalculatedSecuritySECDataMockTest() throws Exception {
		List<SecuritySECData> data = secyYieldServiceImpl.getCalculatedSecuritySECData(new Date());
		assertEquals(data.size(), 2);
	}

 	/**
 	 * Test performRedemptionDateAndMaturityDateEqualityTest()
 	 *
 	 * @throws Exception
 	 */
 	@Test
 	public void performRedemptionDateAndMaturityDateEqualityTest() throws Exception {
 		List<SecuritySECData> data = secyYieldServiceImpl.getCalculatedSecuritySECData(new Date());
 		for(SecuritySECData securitySECData :data)
 		{
 			assertEquals(securitySECData.getDerRedemptionDate(), securitySECData.getSecurityReferenceData().getFinalMaturityDate());
 		}
 	}

	/**
	 * Test exportCalculatedSecuritySECData
	 * 
	 * @throws Exception
	 *             if any exception occurs
	 */
	@Test
	public void exportCalculatedSecuritySECDataTest() throws Exception {
		HttpServletResponse response = mock(HttpServletResponse.class);
		MockServletOutputStream servletOutputStream = new MockServletOutputStream();
		when(response.getOutputStream()).thenReturn(servletOutputStream);
		secyYieldServiceImpl.exportCalculatedSecuritySECData(new Date(), response);
		verify(response).setContentType(EXPORT_FILE_TYPE);
		final File csvFile = convertServletOutputStreamToFile(servletOutputStream);
		ClassLoader classLoader = getClass().getClassLoader();
		File expectedCsvFile = new File(classLoader.getResource(TEST_EXPORT_CSV_FILE_NAME).getFile());
		assertEquals(getCSVFileContentsAsString(csvFile), getCSVFileContentsAsString(expectedCsvFile));
		csvFile.delete();
	}

	/**
	 * Mocked <ServletOutputStream> to check the contents of the zip file after it is written
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
	 * Converts the servlet output stream to a file
	 * 
	 * @param servletOutputStream
	 *            the servlet output stream
	 * @return the file
	 * @throws IOException
	 *             if any I/O exception occurs
	 * @throws FileNotFoundException
	 *             if the test export file is not found
	 */
	private File convertServletOutputStreamToFile(MockServletOutputStream servletOutputStream)
			throws IOException, FileNotFoundException {
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
	 * Transforms the contents of a CSV file to string
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