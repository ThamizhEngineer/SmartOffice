package com.ss.smartoffice.soservice.transaction.minioAPIservice;
 
 import java.util.ArrayList;
 import java.util.List;
 import java.util.ArrayList;
 import java.util.Iterator;
 import java.util.List;
 import java.io.ByteArrayInputStream;
 import java.io.IOException;
 import java.security.NoSuchAlgorithmException;
 import java.security.InvalidKeyException;

 import org.json.JSONArray;
 import org.springframework.beans.factory.annotation.Autowired;
import org.xmlpull.v1.XmlPullParserException;
 
 import com.ss.smartoffice.shared.common.CommonUtils;
 import com.ss.smartoffice.shared.model.SmartOfficeException;
 import com.ss.smartoffice.shared.dm.DocInfoRepository;
 import com.ss.smartoffice.shared.model.dm.DocInfo;
 
 import io.minio.MinioClient;
 import io.minio.Result;
 import io.minio.messages.Bucket;
 import io.minio.messages.Item;
 import io.minio.messages.Upload;
 import io.minio.errors.MinioException;
 public class AlbumDAO {
 	
 	@Autowired
 	CommonUtils commonUtils;
 	
 	
 	@Autowired
 	DocInfoRepository docInfoRepository;
 	  public List<Album> listAlbums() throws NoSuchAlgorithmException,
       IOException, InvalidKeyException, XmlPullParserException, MinioException {
 
   List<Album> list = new ArrayList<Album>();
   final String minioBucket = "albums";
 
   // Initialize minio client object.
 //  MinioClient minioClient = new MinioClient("localhost", 9000,
 //                                            "priya",
 //                                            "priya@123");
   MinioClient minioClient = new MinioClient("http://localhost:9000", "priya", "priya@123");
   System.out.println(minioClient.toString());
 
   // List all objects.
   Iterable<Result<Item>> myObjects = minioClient.listObjects(minioBucket);
 
   // Iterate over each elements and set album url.
   for (Result<Item> result : myObjects) {
       Item item = result.get();
       System.out.println(item.lastModified()   +", " +  item.size()  + ", "   +item.objectName());
        
       // Create a new Album Object
       Album album = new Album();
       
       // Set the presigned URL in the album object
       album.setUrl(minioClient.presignedGetObject(minioBucket, item.objectName(), 60 * 60 * 24));
       
       // Add the album object to the list holding Album objects
       list.add(album);
       
   }
 
   // Return list of albums.
   return list;
 }
 	  public void listIncompleteUploads(String bucketName) throws InvalidKeyException, NoSuchAlgorithmException, IOException, XmlPullParserException{
 		  try {
 			  MinioClient minioClient = new MinioClient("http://localhost:9000", "priya", "priya@123");
 			  // Check whether 'mybucket' exist or not.
 			  boolean found = minioClient.bucketExists("albums");
 			  if (found) {
 			    // List all incomplete multipart upload of objects in 'my-bucketname
 			    Iterable<Result<Upload>> myObjects = minioClient.listIncompleteUploads("mybucket");
 			    for (Result<Upload> result : myObjects) {
 			      Upload upload = result.get();
 			      System.out.println(upload.uploadId()   +", " +  upload.objectName());
 			    }
 			  } else {
 			    System.out.println("mybucket does not exist");
 			  }
 			} catch (MinioException e) {
 			  System.out.println("Error occurred: "   +e);
 			}
 		
 	    }
 	  public List<DocInfo> objectUpload(byte[] imageBuffer, String objectName) {
 		  List<DocInfo> docInfos = new ArrayList<>();  
 		  try {
 		    	 
 		        MinioClient minioClient = new MinioClient("http://localhost:9000", "priya", "priya@123");
 		        System.out.println("minioClient");
 		        // Ensure the bucket exists.
 		        if(!minioClient.bucketExists("albums")) {
 		            minioClient.makeBucket("albums");
 		        }
 
 		        // Upload the image to the bucket with putObject
 		        minioClient.putObject("albums", objectName, new ByteArrayInputStream(imageBuffer), imageBuffer.length, "application/octet-stream");
 		      
 				DocInfo docInfo = new DocInfo();
 				docInfo.setId(Integer.valueOf(commonUtils.generateId()));
 				docInfo.setDocLocation("/minio/albums/");
 				docInfo.setCreatedBy(commonUtils.getLoggedinEmployeeId());
 				docInfos.add(docInfo);
 				  System.out.println(docInfo);
 				docInfoRepository.save(docInfo);
 		    
 		    } catch(Exception e) {
 		        System.err.println("Error occurred: "   +e);
 		        e.printStackTrace();
 		    }
 			return docInfos;
 		}
 }