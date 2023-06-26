package com.ss.smartoffice.soreport.cutomquery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.AuthUserRole;
import com.ss.smartoffice.shared.model.QueryRequest;
import com.ss.smartoffice.shared.model.QueryResponse;
import com.ss.smartoffice.shared.util.QueryHelper;

@RestController
@RequestMapping("/quick-search")
public class QuickSearchService {

	List<String> onlyEmpCanAccess = new ArrayList<String>(Arrays.asList("expense", "travel-advances", "job"));
	List<String> notOnlyEmployee = new ArrayList<String>(Arrays.asList("HR1", "HR2", "N1", "N2", "D", "SU"));

	@Autowired
	QueryHelper queryHelper;

	@Autowired
	CommonUtils commonUtils;

	@PostMapping
	public QueryResponse search(@RequestBody QueryRequest queryRequest) {
		QueryResponse qr = new QueryResponse();
		if (isCurrentUserOnlyEmployee().equals("is-not-only-employee")) {
			return queryHelper.queryDb(queryRequest);
		} else {
			if (onlyEmpCanAccess.contains(queryRequest.getEntity())) {
				return queryHelper.queryDb(queryRequest);
			} else {
				qr.setErrorCode("MANUAL-ERROR");
				qr.setErrorDescription("User does not have access to the requested content");
				return qr;
			}
		}
	}

	public String isCurrentUserOnlyEmployee() {
		String res = "";
		List<String> currentUserRoles = new ArrayList<>();
		AuthUser loggedInUser = (AuthUser) commonUtils.getAuthenticatedUser().getDetails();
		for (AuthUserRole role : loggedInUser.getAuthUserRoles()) {
			currentUserRoles.add(role.getAuthRoleCode());
		}
		int contains = 0;
		int doesNotConain = 0;
		for (int i = 0; i < currentUserRoles.size(); i++) {
			if (notOnlyEmployee.contains(currentUserRoles.get(i))) {
				contains = contains + 1;
			} else {
				doesNotConain = doesNotConain + 1;
			}
		}
		if (contains > 0) {
			res = "is-not-only-employee";
		}
		return res;
	}

}
