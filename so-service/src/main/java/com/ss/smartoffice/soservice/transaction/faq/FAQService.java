package com.ss.smartoffice.soservice.transaction.faq;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.faq.FAQ;
import com.ss.smartoffice.shared.model.faq.FAQComment;
import com.ss.smartoffice.shared.model.faq.FAQCommentRepo;
import com.ss.smartoffice.shared.model.faq.FAQLike;
import com.ss.smartoffice.shared.model.faq.FAQLikeRepo;
import com.ss.smartoffice.shared.model.faq.FAQRefLinkRepo;
import com.ss.smartoffice.shared.model.faq.FAQRepo;
import com.ss.smartoffice.shared.model.faq.FAQTag;
import com.ss.smartoffice.shared.model.faq.FAQTagRepo;
import com.ss.smartoffice.soservice.master.tag.MTag;
import com.ss.smartoffice.soservice.master.tag.TagRepo;

@RestController
@RequestMapping("/transaction/faq")
public class FAQService {
	
	@Autowired
	FAQRepo faqRepo;
	
	@Autowired
	FAQLikeRepo faqLikeRepo;
	
	@Autowired
	FAQCommentRepo faqCommentRepo;
	
	@Autowired
	FAQRefLinkRepo faqRefLinkRepo;
	
	@Autowired
	TagRepo tagRepo;
	
	@Autowired
	FAQTagRepo faqTagRepo;
	
	@Autowired
	CommonUtils commonUtils;
	
	@GetMapping
	public Iterable<FAQ> findBySummary() throws SmartOfficeException{
		return faqRepo.findBySummaries();
	}
	
	@GetMapping("/{id}")
	public Optional<FAQ> findFAQById(@PathVariable(value = "id")Integer id) throws SmartOfficeException{
		return faqRepo.findById(id);
	}
	
	@PostMapping
	public FAQ createFAQ(@RequestBody FAQ faq)throws SmartOfficeException{
		if(!faq.getFaqTags().isEmpty()){
			faq.setFaqTags(createTags(faq.getFaqTags()));
		}				
		return faqRepo.save(faq);
	}
	
	public List<FAQTag> createTags(@RequestBody List<FAQTag> faqTags) throws SmartOfficeException{		
		for(FAQTag tag:faqTags) {
			if(tagRepo.findByName(tag.getTagName())==null) {
				MTag mTag = new MTag();
				mTag.setName(tag.getTagName());
				tagRepo.save(mTag);
				tag.setMTagId(mTag.getId());
			}
		}		
		return faqTags;
	}
	
	
	@PatchMapping("/{id}")
	public FAQ updateFAQ(@PathVariable(value = "id")Integer id,@RequestBody FAQ faq)throws SmartOfficeException{
		FAQ faqFromDb = faqRepo.findById(id).get();		
		faqFromDb.setHeader(faq.getHeader());
		faqFromDb.setPost(faq.getPost());
		faq.setFaqTags(createTags(faq.getFaqTags()));
		faqFromDb.setFaqTags(faq.getFaqTags());		
		faqFromDb.setFaqRefLinks(faq.getFaqRefLinks());
		return faqRepo.save(faqFromDb);
	}
	
	@PatchMapping("/fqa-comments/{id}")
	public FAQComment createOrUpdateFAQComment(@PathVariable(value = "id")Integer id,@RequestBody FAQComment faqComment)throws SmartOfficeException{
		
		if(faqComment.getId()==null) {
			faqComment.setTFaqId(id.toString());
			faqComment.setCreatedBy(commonUtils.getLoggedinEmployeeId());
			faqComment.setCreatedDt(LocalDateTime.now());
			faqCommentRepo.save(faqComment);
		}else {
			if(faqComment.getCreatedBy().equals(commonUtils.getLoggedinEmployeeId())) {
				faqComment.setTFaqId(id.toString());
				faqComment.setModifiedBy(commonUtils.getLoggedinEmployeeId());
				faqComment.setModifiedDt(LocalDateTime.now());
				faqCommentRepo.save(faqComment);
			}else {
				throw new SmartOfficeException("Sorry only owner of this Comment can update");
			}
		}				
		return faqComment;
	}
	
	@PatchMapping("/fqa-like/{id}/{isLiked}")
	public FAQLike createOrUpdateFAQLike(@PathVariable(value = "id")Integer id,@PathVariable(value = "isLiked")String isLiked )throws SmartOfficeException{
		FAQLike faqLike = new FAQLike();
		List<String> isLikeContains = new ArrayList<String>(Arrays.asList("Y","N"));
		if(isLikeContains.contains(isLiked)) {
			faqLike = faqLikeRepo.findByTFaqIdAndCreatedBy(id.toString(), commonUtils.getLoggedinEmployeeId());			
		if(faqLike==null) {
			faqLike = new FAQLike();
			faqLike.setTFaqId(id.toString());
			faqLike.setCreatedBy(commonUtils.getLoggedinEmployeeId());
			faqLike.setCreatedDt(LocalDateTime.now());
			faqLike.setIsLiked(isLiked);
			faqLikeRepo.save(faqLike);
		}else {
			faqLike.setTFaqId(id.toString());
			faqLike.setModifiedBy(commonUtils.getLoggedinEmployeeId());
			faqLike.setModifiedDt(LocalDateTime.now());
			faqLike.setIsLiked(isLiked);
			faqLikeRepo.save(faqLike);
		}
		}else {
			throw new SmartOfficeException("Unkown URL ");
		}
						
		return faqLike;
	}
	
	@DeleteMapping("/{id}")
	public void deleteFAQ(@PathVariable(value = "id")Integer id)throws SmartOfficeException{
		faqRepo.deleteById(id);
	}
	
	@DeleteMapping("/faq-comment/{id}")
	public void deleteFAQComment(@PathVariable(value = "id")Integer id)throws SmartOfficeException{
		faqCommentRepo.deleteById(id);
	}
	@DeleteMapping("/faq-reflink/{id}")
	public void deleteFAQRefLink(@PathVariable(value = "id")Integer id)throws SmartOfficeException{
		faqRefLinkRepo.deleteById(id);
	}
	
	@DeleteMapping("/faq-tag/{id}")
	public void deleteFAQTag(@PathVariable(value = "id")Integer id)throws SmartOfficeException{
		faqTagRepo.deleteById(id);
	}
	
	
}
