package com.ss.smartoffice.sodocumentservice.csvupload;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.dm.DocInfoRepository;
import com.ss.smartoffice.shared.dm.DocumentManagementHelper;
import com.ss.smartoffice.shared.model.InvoiceForUpload;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.dm.DocInfo;

@RestController
@RequestMapping("csv/invoice")
public class InvoiceForUploadService {

	@Value("${int-invoice.url}")
	private String invoiceUrl;

	@Autowired
	CommonUtils commonUtils;
	@Autowired
	DocumentManagementHelper documentManagementHelper;
	@Autowired
	DocInfoRepository docInfoRepo;

	@PostMapping("/upload/{docTypeCode}")
	public List<DocInfo> upload(@PathVariable(value = "docTypeCode") String docTypeCode,
			@RequestParam("uploadingFiles") MultipartFile[] filesToUpload) throws SmartOfficeException {
		List<DocInfo> docInfo = documentManagementHelper.uploadDocsAsBinary(docTypeCode, filesToUpload);
		for (DocInfo d : docInfo) {
			System.out.println(d.getDocId());
			if (isNullOrEmpty(d.getDocId()) == false) {
				String path = d.getDocLocation() + "/" + d.getDocName();
				try {
					extractDataFromCsv(path, d.getDocId());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return docInfo;
	}

	private void extractDataFromCsv(String path, String docId) throws IOException {
		File input = new File(path);
		List<InvoiceForUpload> extractedData = readObjectsFromCsv(input);
		System.out.println(extractedData);
		if (extractedData != null && !extractedData.isEmpty()) {
			extractedData.get(0).setDocId(docId);
			postExtractedDataForProcessing(extractedData);
		} else {
			throw new SmartOfficeException("No data has been extrcted");
		}
	}

	public List<InvoiceForUpload> readObjectsFromCsv(File file) throws IOException {
		CsvMapper csvMapper = new CsvMapper();
		CsvSchema bootstrap = csvMapper.schemaFor(InvoiceForUpload.class).withHeader();
		MappingIterator<InvoiceForUpload> mappingIterator = csvMapper.readerFor(InvoiceForUpload.class).with(bootstrap)
				.readValues(file);
		return mappingIterator.readAll();
	}

	public static boolean isNullOrEmpty(String str) {
		if (str != null && !str.isEmpty())
			return false;
		return true;
	}

	private List<InvoiceForUpload> postExtractedDataForProcessing(List<InvoiceForUpload> extrackedData)
			throws SmartOfficeException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", commonUtils.getLoggedinAppToken());
		HttpEntity<List<InvoiceForUpload>> request = new HttpEntity<List<InvoiceForUpload>>(extrackedData, headers);

		ResponseEntity<List<InvoiceForUpload>> responseObj = commonUtils.getRestTemplate().exchange(
				invoiceUrl + "/start", HttpMethod.POST, request,
				new ParameterizedTypeReference<List<InvoiceForUpload>>() {
				});
		return responseObj.getBody();
	}
}
