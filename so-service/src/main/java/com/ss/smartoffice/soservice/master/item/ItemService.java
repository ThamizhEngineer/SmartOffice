package com.ss.smartoffice.soservice.master.item;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.model.SmartOfficeException;

@RestController
@RequestMapping("/master/item")
@Scope("prototype")
public class ItemService {

	@Autowired
	ItemRepository itemRepository;
	
	@GetMapping
	public Iterable<Item> getItem() throws SmartOfficeException{
		return itemRepository.findAll();
		
	}


@GetMapping("/{id}")
	public Optional<Item> getItemById(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		return itemRepository.findById(id);
		
	}

@PostMapping
	public Item addItem(@RequestBody Item item)throws SmartOfficeException{
		return itemRepository.save(item);
		
	}

@PatchMapping("/{id}")
	public Item updateItem(@RequestBody Item item)throws SmartOfficeException{
		return itemRepository.save(item);
		
	}


@DeleteMapping("/{id}")
	public void deleteItem(@PathVariable(value="id")Integer id)throws SmartOfficeException{
		itemRepository.deleteById(id);
	}

@GetMapping("/advance-filters")
public List<Item> advanceFetchQuery(
		@RequestParam (value="itemCode",required=false)String itemCode,
		@RequestParam (value="referenceNumber",required=false)String referenceNumber,
		@RequestParam (value="forPurchase",required=false)String forPurchase,
		@RequestParam (value="hsnSacCode",required=false)String hsnSacCode,
		@RequestParam (value="itemName",required=false)String itemName){
	return itemRepository.fetchByAdvanceFilter(itemCode, referenceNumber,forPurchase,hsnSacCode,itemName);
}

}