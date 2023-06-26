package com.ss.smartoffice.shared.dm;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.common.DocUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.dm.DocInfo;
import com.ss.smartoffice.shared.model.dm.DocMetadata;
import com.ss.smartoffice.shared.model.dm.DocumentType;

@Component
public class DocumentManagementHelper {

	@Value("${docs.base.location}")
	private String docsLocation;

	@Value("${docs.uploadfolder.name}")
	private String uploadFolderName;

	@Value("${docs.folder.delimitter}")
	private String folderDelimitter;

	@Autowired
	DocTypeHelper docTypeHelper;
	@Autowired
	DocInfoHelper docInfoHelper;
	@Autowired
	DocUtils docUtils;
	@Autowired
	CommonUtils commonUtils;

	public Map<String, String> checkDocBinary(String docTypeCode, MultipartFile[] filesToUpload) throws SmartOfficeException {
		try {
			Map<String, String> checkUpload = new HashMap<String, String>();
			if (docTypeCode == null || docTypeCode.isEmpty()) {
				new SmartOfficeException("docTypeCode is mandatory");
			} else if (filesToUpload == null || filesToUpload.length <= 0) {
				new SmartOfficeException("filesToUpload is mandatory");
			}
			List<DocumentType> docTypes = docTypeHelper.findByDocTypeCode(docTypeCode);
			String isUpload = "";
			String docTypeId = "";
			String docTypeExtension = "";

			if (docTypes.isEmpty()) {
				System.out.println("Unknown document-type -" + docTypeCode);
				throw new SmartOfficeException("Unknown document-type specified in the url");
			} else {
				docTypeId = docTypes.get(0).getId() + "";
				docTypeExtension = docTypes.get(0).getDocExtension();

			}

			for (MultipartFile uploadedFile : filesToUpload) {
				System.out.println("file start");
				try {
					if (uploadedFile == null || uploadedFile.isEmpty()) {
						throw new SmartOfficeException("Uploading file cannot be Empty");
					}
					ArrayList<String> extensions = new ArrayList<>(Arrays.asList(docTypeExtension.split(",")));
					String extension = commonUtils.getFileExtension(uploadedFile.getOriginalFilename());

					if (extensions.contains(extension)) {
						isUpload="Y";
					} else {

						throw new SmartOfficeException("Not a Allowed File Format");
					}

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Suppressing the error, so that next file can be processed");
				}
			}
			if(isUpload=="Y") {
				checkUpload.put("isUpload", "Y");
				return checkUpload;
			}else {
				throw new SmartOfficeException("Not a Allowed File Format");
			}
			
		} catch (SmartOfficeException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException(e.getMessage());
		}

	}

	public List<DocInfo> uploadDocsAsBinary(String docTypeCode, MultipartFile[] filesToUpload)
			throws SmartOfficeException {
		try {
			System.out.println("i/p - docTypeCode-" + docTypeCode + "-");
			System.out.println("i/p - filesToUpload" + filesToUpload);
			System.out.println("i/p - filesToUpload" + filesToUpload.length);
			if (docTypeCode == null || docTypeCode.isEmpty()) {
				new SmartOfficeException("docTypeCode is mandatory");
			} else if (filesToUpload == null || filesToUpload.length <= 0) {
				new SmartOfficeException("filesToUpload is mandatory");
			}

			System.out.println("before creating object");
			// Get file name maximum file size can be 10Mb when exceeds throw error
			List<DocInfo> docInfos = new ArrayList<DocInfo>();
			SmartOfficeException soe = null;
			String docId = "";
			String docTypeId = "";
			String uploadFilePathWithName = "";
			String uploadFileName = "";
			String uploadFolder = docsLocation + folderDelimitter + uploadFolderName + folderDelimitter + docTypeCode;
			String docTypeExtension = "";
			List<DocumentType> docTypes = docTypeHelper.findByDocTypeCode(docTypeCode);

			System.out.println("docTypes-" + docTypes);
			if (docTypes.isEmpty()) {
				System.out.println("Unknown document-type -" + docTypeCode);
				throw new SmartOfficeException("Unknown document-type specified in the url");
			} else {
				docTypeId = docTypes.get(0).getId() + "";
				docTypeExtension = docTypes.get(0).getDocExtension();

			}

			System.out.println("docTypeId-" + docTypeId);
			for (MultipartFile uploadedFile : filesToUpload) {
				System.out.println("file start");
				try {
					if (uploadedFile == null || uploadedFile.isEmpty()) {
						System.out.println("Uploading file cannot be Empty");
						throw new SmartOfficeException("Uploading file cannot be Empty");
					}
					ArrayList<String> extensions = new ArrayList<>(Arrays.asList(docTypeExtension.split(",")));
					System.out.println("extensions..!!"+extensions);
					String extension = commonUtils.getFileExtension(uploadedFile.getOriginalFilename());
					System.out.println(uploadedFile.getName());
					if (extensions.contains(extension)) {

						System.out.println(extension);
						System.out.println("before create file");
						// create file
						docId = commonUtils.generateId();
						System.out.println(docId);
						uploadFileName = LocalDateTime.now() + "-" + docId;
						uploadFilePathWithName = uploadFolder + folderDelimitter + uploadFileName + '.' + extension;
						docUtils.getOrCreateFolder(uploadFolder);
						File file = new File(uploadFilePathWithName);
						uploadedFile.transferTo(file);

						// create doc information
						DocInfo docInfo = new DocInfo();
						docInfo.setDocTypeId(docTypeId);
						docInfo.setDocId(docId);
						docInfo.setDocNameFromUser(uploadedFile.getOriginalFilename());
						docInfo.setDocName(file.getName());
						docInfo.setDocExtension(docUtils.getFileExtension(uploadedFile));
						docInfo.setDocLocation(uploadFolder);
						docInfo.setDocSize(file.length() + "");
						docInfos.add(docInfo);

					} else {

						throw new SmartOfficeException("Not a Allowed File Format");
					}

				} catch (Exception e) {
					e.printStackTrace();
					soe = new SmartOfficeException(e.getMessage());
					System.out.println("Suppressing the error, so that next file can be processed");
				}
			}

			// throw error if all docs were not saved
			if (docInfos.isEmpty() && soe != null) {
				throw soe;
			}
			docInfos = docInfoHelper.saveAll(docInfos);
			System.out.println("o/p - docInfos - " + docInfos);
			return docInfos;
		} catch (SmartOfficeException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException(e.getMessage());
		}

	}

	public List<DocInfo> uploadAndCheckIn(String docTypeCode, MultipartFile[] filesToUpload) {
		List<DocInfo> docInfos = uploadDocsAsBinary(docTypeCode, filesToUpload);
		if (docInfos != null && !docInfos.isEmpty()) {
			checkInDocs(docInfos);
		} else {
			throw new SmartOfficeException("No docInfo for checkIn");
		}
		return docInfos;
	}

	public List<DocInfo> checkInDocs(List<DocInfo> docInfos) throws SmartOfficeException {
		try {
			List<DocInfo> movedDocInfos = new ArrayList<DocInfo>();
			List<String> busKeys = null;
			String finalFolderPath = "";
			SmartOfficeException soe = null;
			File oldFileWE; // with Extension
			File newFileWE; // with Extension

			File oldFile;
			File newFile;
			Map<String, String> busKeyWithValues = null;
			if (docInfos.isEmpty()) {
				throw new SmartOfficeException("No Documents' information specified to checkin");
			}
			for (DocInfo docInfo : docInfos) {
				try {
					System.out.println("189000-Start check in" + docInfo);
					// update docInfo with db info except Metadata
					DocInfo docInfoRefreshed = docInfoHelper.findByDocId(docInfo.getDocId()).get(0);
					docInfoRefreshed.setMetadataList(docInfo.getMetadataList());
					docInfo = docInfoRefreshed;

					// get docType object based on docTypeId
					DocumentType docType = docTypeHelper.findById(new Integer(docInfo.getDocTypeId()));

					// find the businessKeys needed for DocType
					busKeys = findBusKeysNeeded(docType);

					// check if the businessKeys are set in the docInfo
					busKeyWithValues = getBusKeyValues(docInfo, busKeys);
					boolean isMoved;

					// construct destination folder
					finalFolderPath = ensureDestinationFolderExists(docType, busKeyWithValues);
					newFile = new File(finalFolderPath + folderDelimitter + docInfo.getDocName());
					// move the file
					oldFile = new File(docInfo.getDocLocation() + folderDelimitter + docInfo.getDocName());
					isMoved = oldFile.renameTo(newFile);
					if (!isMoved) {
						throw new SmartOfficeException("Unable to create file(" + finalFolderPath + folderDelimitter
								+ docInfo.getDocName() + ") - Please check access rights");
					}
					// update doc info
					docInfo.setDocExtension("pdf");
					docInfo.setDocLocation(finalFolderPath);
					movedDocInfos.add(docInfo);
				} catch (Exception e) {
					e.printStackTrace();
					soe = new SmartOfficeException(e.getMessage());
					System.out.println("Suppressing the error");
				}
			}

			if (!movedDocInfos.isEmpty()) {
				movedDocInfos = docInfoHelper.saveAll(movedDocInfos);
			} else if (soe != null) {
				// throw error if all docs were not moved
				throw soe;
			}

			return movedDocInfos;
		} catch (SmartOfficeException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException(e.getMessage());
		}

	}

	public List<DocInfo> getDocInfoByDocId(String docId) {
		return docInfoHelper.findByDocId(docId);
	}

	public List<DocInfo> getDocInfoByDocName(String docName) {
		return docInfoHelper.findByDocName(docName);
	}

	public File getDocByDocId(String docId) {

		try {
			DocInfo docInfo = docInfoHelper.findByDocId(docId).get(0);
			File file = new File(docInfo.getDocLocation() + folderDelimitter + docInfo.getDocName());
			return file;
		} catch (SmartOfficeException soe) {
			throw soe;
		} catch (Exception e) {
			throw new SmartOfficeException(e.getMessage());
		}
	}

	public List<DocInfo> moveDocs(List<DocInfo> docInfos) {
		try {
			List<DocInfo> movedDocInfos = new ArrayList<DocInfo>();
			String newLocation;
			SmartOfficeException soe = null;
			File newFile = null;
			File oldFile = null;
			for (DocInfo docInfo : docInfos) {
				try {
					newLocation = null;
					oldFile = null;
					newFile = null;
					newLocation = docInfo.getNewDocLocation();
					docInfo = docInfoHelper.findByDocId(docInfo.getDocId()).get(0);
					newFile = new File(
							newLocation + folderDelimitter + docInfo.getDocName() + "." + docInfo.getDocExtension());
					oldFile = new File(docInfo.getDocLocation() + folderDelimitter + docInfo.getDocName() + "."
							+ docInfo.getDocExtension());
					// move the file
					boolean isMoved = oldFile.renameTo(newFile);
					if (!isMoved) {
						throw new SmartOfficeException("Unable to create file(" + newLocation + folderDelimitter
								+ docInfo.getDocName() + ") - Please check access rights");
					}
					docInfo.setDocLocation(newLocation);
					movedDocInfos.add(docInfo);
				} catch (Exception e) {
					e.printStackTrace();
					soe = new SmartOfficeException(e.getMessage());
					System.out.println("Suppressing the error");
				}
			}

			if (!movedDocInfos.isEmpty()) {
				movedDocInfos = docInfoHelper.saveAll(movedDocInfos);
			} else if (soe != null) {
				// throw error if all docs were not moved
				throw soe;
			}

			return movedDocInfos;
		} catch (SmartOfficeException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException(e.getMessage());
		}
	}

	private List<String> findBusKeysNeeded(DocumentType documentType) {
		List<String> busKeys = new ArrayList<String>();
		String busKey = "";
		busKey = extractBusKey(documentType.getFirstFolderName());
		if (!busKey.isEmpty()) {
			busKeys.add(busKey);
		}
		busKey = extractBusKey(documentType.getSecondFolderName());
		if (!busKey.isEmpty()) {
			busKeys.add(busKey);
		}
		busKey = extractBusKey(documentType.getThirdFolderName());
		if (!busKey.isEmpty()) {
			busKeys.add(busKey);
		}
		busKey = extractBusKey(documentType.getFourthFolderName());
		if (!busKey.isEmpty()) {
			busKeys.add(busKey);
		}
		return busKeys;
	}

	private String extractBusKey(String folderName) {
		try {
			if (folderName != null && folderName.startsWith("<") && folderName.endsWith(">")) {
				return folderName.substring(1, folderName.length() - 1);
			} else
				return "";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

	private Map<String, String> getBusKeyValues(DocInfo docInfo, List<String> busKeys) {
		Map<String, String> busKeyValues = new HashMap<String, String>();
		String busValue = null;
		for (String busKey : busKeys) {
			busValue = null;
			if (busKey.equalsIgnoreCase("month-year")) {
				busValue = docUtils.getCurrentMonthYear();
			} else {
				if (docInfo.getMetadataList() == null) {
					throw new SmartOfficeException("No Metadata available for docInfo(" + docInfo.getId() + ")");
				}
				for (DocMetadata docMetadata : docInfo.getMetadataList()) {
					if (docMetadata.getMdCode().equalsIgnoreCase(busKey)) {
						busValue = docMetadata.getMdValue();
					}
				}
			}
			if (busValue == null) {
				throw new SmartOfficeException("BusKey(" + busKey + ") not found to create a folder");
			} else
				busKeyValues.put(busKey, busValue);
		}
		return busKeyValues;
	}

	private String ensureDestinationFolderExists(DocumentType documentType, Map<String, String> busKeyWithValues) {
		// for each folderName
		// check if folderName is null or empty
		// no - return the destinationFolderPath
		// yes - check if its static
		// yes- append to destinationFolderPath
		// no - append busKey to destinationFolderPath
		// getOrCreateFolder

		String destinationFolder = docsLocation;
		try {
			String[] folderNames = { documentType.getFirstFolderName(), documentType.getSecondFolderName(),
					documentType.getThirdFolderName(), documentType.getFourthFolderName() };
			for (String folderName : folderNames) {
				if (folderName == null || folderName.isEmpty())
					break;
				else {
					if (extractBusKey(folderName).isEmpty()) {
						destinationFolder = destinationFolder + folderDelimitter + folderName;
					} else {
						destinationFolder = destinationFolder + folderDelimitter
								+ busKeyWithValues.get(extractBusKey(folderName));
					}
					docUtils.getOrCreateFolder(destinationFolder);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return destinationFolder;
	}

	public void deleteByDocId(String docId) {
		try {
			DocInfo d = docInfoHelper.findByDocId(docId).get(0);
			docInfoHelper.deleteByDocInfoId(d.getId());
			docInfoHelper.deleteByDocId(docId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Iterable<DocInfo> findAllDocInfo() {
		return docInfoHelper.getAllDocInfo();
	}

}
