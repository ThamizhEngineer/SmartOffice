package com.ss.smartoffice.soservice.transaction.clientpo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.dm.DocumentManagementHelper;
import com.ss.smartoffice.shared.model.dm.DocInfo;
import com.ss.smartoffice.shared.model.dm.DocMetadata;

@RestController
@RequestMapping(path = "/transaction/client-pos")
@Scope("prototype")
public class ClientPurchaseOrderService {
	
	@Autowired
	ClientPurchaseOrderRepository clientPurchaseOrderRepository;
		
	@Autowired
	CommonUtils commonUtils;
	@Autowired
	DocumentManagementHelper documentManagementHelper;
	
	// Get ClientPurchaseOrders
	@GetMapping
	public Iterable<ClientPurchaseOrder> getClientPOs(Pageable pageable,Model model,@RequestParam(value="clientId",required=false)String clientId,@RequestParam(value="poDate",required=false)String poDate)throws SmartOfficeException{
		try {
			boolean clientFilter = false, poDateFilter = false;
			clientFilter = (clientId!=null && !clientId.isEmpty());
			poDateFilter = (poDate!=null && !poDate.isEmpty());
			if(clientFilter && poDateFilter) {
				return clientPurchaseOrderRepository.findByClientIdAndPoDate(pageable, clientId, poDate);
			}
			else if (clientFilter) {
				return clientPurchaseOrderRepository.findByClientId(pageable, clientId);
			}
			else if(poDateFilter) {
				return clientPurchaseOrderRepository.findByPoDate(pageable, poDate);
			}
			else {
				return clientPurchaseOrderRepository.findAll();
			}
	
		}catch (Exception e) {
			e.printStackTrace();	
			throw new SmartOfficeException("Exception Occured : ClientPurchaseOrderService - getClientPOs- "+e.getLocalizedMessage());
		}
		
	}
	
	
	// ClientPurchaseOrder byId
	@GetMapping("/{id}")
	public Optional<ClientPurchaseOrder> getTaskById( @PathVariable(value = "id")Integer id) throws SmartOfficeException{
		try {
		return clientPurchaseOrderRepository.findById(id);
		}catch (Exception e) {
			e.printStackTrace();	
			throw new SmartOfficeException("Exception Occured : ClientPurchaseOrderService - getTaskById- "+e.getLocalizedMessage());
		}
	}
	
	// Create or update multiple ClientPurchaseOrders
	@PostMapping("/")
	public ClientPurchaseOrder createorUpdateClientPurchaseOrders(
			@RequestBody ClientPurchaseOrder clientPurchaseOrder) throws SmartOfficeException {
		List<ClientPurchaseOrder> savedClientPurchaseOrders = new ArrayList<ClientPurchaseOrder>();
		ClientPurchaseOrder poFromDB = new ClientPurchaseOrder();
		try {

				clientPurchaseOrder.setPoRefNo(clientPurchaseOrder.getPoRefNo());
				clientPurchaseOrder.setPoRefNo(clientPurchaseOrder.getPoRefNo());
				clientPurchaseOrder.setPoDesc(clientPurchaseOrder.getPoDesc());
				clientPurchaseOrder.setPoDate(clientPurchaseOrder.getPoDate());
				clientPurchaseOrder.setClientId(clientPurchaseOrder.getClientId());
				clientPurchaseOrder.setCreatedBy(commonUtils.getLoggedinUserId());
				clientPurchaseOrder.setIsEnabled("Y");
				clientPurchaseOrderRepository.save(clientPurchaseOrder);
				
				if(clientPurchaseOrder.getId()!=null) {
				ClientPurchaseOrder clientPurchaseOrder2= clientPurchaseOrderRepository.findById(clientPurchaseOrder.getId()).get();
				clientPurchaseOrder2.setPoRefNo(clientPurchaseOrder2.getPoRefNo());
				clientPurchaseOrder2.setPoDesc(clientPurchaseOrder2.getPoDesc());
				clientPurchaseOrder2.setPoDate(clientPurchaseOrder2.getPoDate());
				clientPurchaseOrder2.setClientId(clientPurchaseOrder2.getClientId());
					
				clientPurchaseOrder2.setModifiedBy(commonUtils.getLoggedinUserId());					
				clientPurchaseOrderRepository.save(clientPurchaseOrder2);	
				
				}
				if(clientPurchaseOrder.getDocId()!=null) {							
				DocInfo docInfo = new DocInfo(); 
				docInfo.setDocId(clientPurchaseOrder.getDocId());
				DocMetadata docMetadata = new DocMetadata();
				docMetadata.setMdCode("client-id");
				docMetadata.setMdValue(clientPurchaseOrder.getId().toString());
				List<DocMetadata> docMetadataList = new ArrayList<>();
				docMetadataList.add(docMetadata);
				docInfo.setMetadataList(docMetadataList);
				List<DocInfo> docInfos = new ArrayList<>();
				docInfos.add(docInfo);
				documentManagementHelper.checkInDocs(docInfos);
				}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException("Exception Occured : ClientPurchaseOrderService - createorUpdateClientPurchaseOrders- "
							+ e.getLocalizedMessage());

		}

		return clientPurchaseOrder;

	}

	@DeleteMapping("/{id}")
	public void deleteClientPurchaseOrder(@PathVariable(value="id")Integer id) {
		 clientPurchaseOrderRepository.deleteById(id);
		
	}
}




