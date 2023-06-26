package com.ss.smartoffice.soservice.transaction.minioAPIservice;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xmlpull.v1.XmlPullParserException;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.dm.DocInfoRepository;
import com.ss.smartoffice.shared.dm.DocTypeHelper;
import com.ss.smartoffice.shared.model.dm.DocInfo;
import com.ss.smartoffice.shared.model.dm.DocumentType;
import com.ss.smartoffice.shared.sequence.SequenceGenerationService;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import io.minio.errors.MinioException;

@RestController
@RequestMapping("/photoservice")
public class MinioAPIService {
	// Initialize new album service.
	AlbumDAO albumDao = new AlbumDAO();
	@Autowired
	CommonUtils commonUtils;
	@Autowired
	SequenceGenerationService sequenceGenerationService;

	@Autowired
	DocInfoRepository docInfoRepository;
	@Autowired
	DocTypeHelper docTypeHelper;

	// Define GET method and resource.
	@GetMapping("/list")

//	@Produces({ MediaType.APPLICATION_JSON })
	public List<Album> listAlbums()
			throws InvalidKeyException, NoSuchAlgorithmException, IOException, XmlPullParserException, MinioException {

		// Return list of albums.
		return albumDao.listAlbums();
	}

	@PostMapping
	public List<Album> uploadFile(@RequestBody Album album)
			throws IOException, InvalidKeyException, NoSuchAlgorithmException, XmlPullParserException, MinioException {

		String bucketName = "albums";
		String docId = commonUtils.generateId();
		String keyName = sequenceGenerationService.nextSequence("MINIO-ID").getOutput() + "-" + docId;
		String uploadFileName = album.getLocation();
		AWSCredentials credentials = new BasicAWSCredentials("priya", "priya@123");
		ClientConfiguration clientConfiguration = new ClientConfiguration();
		clientConfiguration.setSignerOverride("AWSS3V4SignerType");

		AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
				.withEndpointConfiguration(
						new AwsClientBuilder.EndpointConfiguration("http://localhost:9000", Regions.US_EAST_1.name()))
				.withPathStyleAccessEnabled(true).withClientConfiguration(clientConfiguration)
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).build();

		try {

			System.out.println("Uploading a new object to S3 from a file\n");
			File file = new File(uploadFileName);
			// Upload file
			s3Client.putObject(new PutObjectRequest(bucketName, keyName, file));
			DocInfo docInfo = new DocInfo();
			docInfo.setDocId(docId);
			docInfo.setDocLocation(bucketName + "/" + "minio");
			docInfo.setCreatedBy(commonUtils.getLoggedinEmployeeId());
			docInfoRepository.save(docInfo);
			// Download file
			GetObjectRequest rangeObjectRequest = new GetObjectRequest(bucketName, keyName);
			S3Object objectPortion = s3Client.getObject(rangeObjectRequest);
			// System.out.println("Printing bytes retrieved:");
			displayTextInputStream(objectPortion.getObjectContent());
		} catch (AmazonServiceException ase) {
			System.out.println(
					"Caught an AmazonServiceException, which means your request made it to Amazon S3, but was rejected with an error response or some reason.");
			System.out.println("Error Message:    " + ase.getMessage());
			System.out.println("HTTP Status Code: " + ase.getStatusCode());
			System.out.println("AWS Error Code:   " + ase.getErrorCode());
			System.out.println("Error Type:       " + ase.getErrorType());
			System.out.println("Request ID:       " + ase.getRequestId());

		} catch (AmazonClientException ace) {
			System.out.println(
					"Caught an AmazonClientException which means the client encountered an internal error while trying to communicate with S3such as not being able to access the network.");
			System.out.println("Error Message: " + ace.getMessage());

		}
		List<Album> list = albumDao.listAlbums();
		List<Album> url = new ArrayList<Album>();
		url.add(list.get(list.size() - 1));
		return url;
	}

	private static void displayTextInputStream(InputStream input) throws IOException {
		// Read one text line at a time and display.
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		while (true) {
			String line = reader.readLine();
			if (line == null)
				break;

			// System.out.println(" " line);
		}
		// System.out.println();
	}

//	@PostMapping("/upload-expense-claims")
//	public DocInfo uploadExpenseClaims(String docTypeCode)
//			throws IOException, InvalidKeyException, NoSuchAlgorithmException, XmlPullParserException, MinioException {
//		 Album album= new Album();
//		DocInfo savedDocInfo = new DocInfo();
//		String bucketName = "albums";
//		String docId = commonUtils.generateId();
//		String keyName = sequenceGenerationService.nextSequence("MINIO-ID").getOutput() + "-" + docId;
//		String uploadFileName = album.getLocation();
//		AWSCredentials credentials = new BasicAWSCredentials("priya", "priya@123");
//		ClientConfiguration clientConfiguration = new ClientConfiguration();
//		clientConfiguration.setSignerOverride("AWSS3V4SignerType");
//
//		AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
//				.withEndpointConfiguration(
//						new AwsClientBuilder.EndpointConfiguration("http://localhost:9000", Regions.US_EAST_1.name()))
//				.withPathStyleAccessEnabled(true).withClientConfiguration(clientConfiguration)
//				.withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
//
//		try {
//
//			System.out.println("Uploading a new object to S3 from a file\n");
//			File file = new File(uploadFileName);
//			// Find docType 
////			List<DocumentType> docTypes = docTypeHelper.findByDocTypeCode(docTypeCode);
////			System.out.println(docTypes);
////			if (docTypes.isEmpty()) {
////				throw new SmartOfficeException("Unknown document-type specified in the url");
////			} else {
////
////				for (DocumentType documentType : docTypes) {
////					DocInfo docInfo = new DocInfo();
////					docInfo.setDocId(docId);
////					docInfo.setDocNameFromUser(file.getName());
////					docInfo.setDocName(file.getName());
////					docInfo.setDocTypeId(documentType.getId().toString());
////					docInfo.setCreatedBy(commonUtils.getLoggedinEmployeeId());
////					savedDocInfo = docInfoRepository.save(docInfo);
////					// Upload file
////					s3Client.putObject(new PutObjectRequest(bucketName, keyName, file));
////					// Download file
////					GetObjectRequest rangeObjectRequest = new GetObjectRequest(bucketName, keyName);
////					S3Object objectPortion = s3Client.getObject(rangeObjectRequest);
////					displayTextInputStream(objectPortion.getObjectContent());
////					List<Album> list = albumDao.listAlbums();
////					List<Album> url = new ArrayList<Album>();
////					url.add(list.get(list.size() - 1));
////					savedDocInfo.setDocLocation(url.get(0).getUrl());
////					savedDocInfo = docInfoRepository.save(savedDocInfo);
////				}
////			}
////		} catch (AmazonServiceException ase) {
////			System.out.println(
////					"Caught an AmazonServiceException, which means your request made it to Amazon S3, but was rejected with an error response or some reason.");
////			System.out.println("Error Message:    " + ase.getMessage());
////			System.out.println("HTTP Status Code: " + ase.getStatusCode());
////			System.out.println("AWS Error Code:   " + ase.getErrorCode());
////			System.out.println("Error Type:       " + ase.getErrorType());
////			System.out.println("Request ID:       " + ase.getRequestId());
////
////		} catch (AmazonClientException ace) {
////			System.out.println(
////					"Caught an AmazonClientException which means the client encountered an internal error while trying to communicate with S3such as not being able to access the network.");
////			System.out.println("Error Message: " + ace.getMessage());
////		}
////		
//		
////		return savedDocInfo;
	

}