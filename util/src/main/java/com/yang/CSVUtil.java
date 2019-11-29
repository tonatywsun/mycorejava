package com.yang;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.GZIPInputStream;

/**
 * 处理CSV工具类
 * 
 * @author tonasun
 *
 */
public class CSVUtil {

	private static final Logger logger = LoggerFactory.getLogger(CSVUtil.class);

	/**
	 * 解析CSV流
	 * 
	 * @param inputStream
	 * @return
	 */
	public List<CSVRecord> analysisAndDealZipInputStream(InputStream inputStream) {
		GZIPInputStream gzip = null;
		CSVParser csvFileParser = null;
		try {
			// 解压 不是解压文件则不需要解压
			gzip = new GZIPInputStream(inputStream);
			// CSV格式
			CSVFormat csvFileFormat = CSVFormat.DEFAULT.withEscape('\\').withQuote('"');
			csvFileParser = new CSVParser(new InputStreamReader(gzip), csvFileFormat);
			return csvFileParser.getRecords();
		} catch (IOException e) {
			logger.error("analysisAndDealZipInputStream exception[{}]", e.getMessage());
			e.printStackTrace();
		} finally {
			if (csvFileParser != null) {
				try {
					csvFileParser.close();
				} catch (IOException e) {
					logger.error("csvFileParser close exception[{}]", e.getMessage());
					e.printStackTrace();
				}
			}
			if (gzip != null) {
				try {
					gzip.close();
				} catch (IOException e) {
					logger.error("gzip close exception[{}]", e.getMessage());
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
