package com.ss.smartoffice.soservice.master.vendor;



import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.ss.smartoffice.soservice.master.model.Vendor;

public interface VendorRepository extends CrudRepository<Vendor, Integer> {
List<Vendor> findByCustomerCodeAndOrgName(String customerCode,String orgName);
List<Vendor> findByCustomerCode(String customerCode);
List<Vendor> findByOrgName(String orgName);
}
