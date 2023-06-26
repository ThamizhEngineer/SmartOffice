package com.ss.smartoffice.soauthservice.auth.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.interceptor.AuthUserRoleRepository;
import com.ss.smartoffice.shared.model.children;
import com.ss.smartoffice.shared.model.menu;
import com.ss.smartoffice.soauthservice.auth.role.AuthRoleFeatureRepository;
import com.ss.smartoffice.soauthservice.model.auth.AuthMenuRepo;
import com.ss.smartoffice.soauthservice.model.auth.AuthRoleFeature;


@RestController
@RequestMapping(path="menu/")
public class AuthMenuService {
	
	@Autowired
	AuthUserRoleRepository authUserRoleRepository;
	
	@Autowired
	AuthRoleFeatureRepository authRoleFeatureRepository;
	
	@Autowired
	AuthSubMenuFeatureRepository authSubMenuFeatureRepository;
	
	@Autowired
	AuthMenuRepo authMenuRepo;
	
	@Autowired
	CommonUtils commonUtils;

	@GetMapping
	public List<menu> getmenu()throws SmartOfficeException{
		System.out.println("In getmenu--> commonUtils.getLoggedinUserId()-"+commonUtils.getLoggedinUserId());
		List<Integer> authUserRolesIds = authUserRoleRepository.authUserRole(commonUtils.getLoggedinUserId());
		List<AuthRoleFeature> authRoleFeatures = new ArrayList<AuthRoleFeature>();
		for(Integer id : authUserRolesIds) {
				authRoleFeatures.addAll(authRoleFeatureRepository.findByAuthRoleId(id));
		}
		List<String> authFeatureIds = authRoleFeatures.stream().map(AuthRoleFeature :: getAuthFeatureId).collect(Collectors.toList());
		Set<String> uniqueAuthFeatureIds = new HashSet<String>(authFeatureIds);
		List<children> subMenus = new ArrayList<children>();
		for(String authFeatureId:uniqueAuthFeatureIds) {
			List<children> subMenu = authSubMenuFeatureRepository.findByAuthFeatureIde(authFeatureId);
			if(subMenu.size()!=0) {
				subMenus.addAll(subMenu);
			}	
		}
		List<String> menuIds = subMenus.stream().map(children :: getAuthMenuId).collect(Collectors.toList());
		Set<String> uniquemenuIds = new HashSet<String>(menuIds);
		List<menu> menus = new ArrayList<menu>();
		for(String menuId:uniquemenuIds) {
			menus.addAll(authMenuRepo.findByState(menuId));
		}
		Collections.sort(menus, menuOrder);
		
		List<children> children = new ArrayList<children>();
		for(menu menu:menus) {
			children = new ArrayList<children>();			
			for(children subMenu:subMenus) {
				if(menu.getId().equals(subMenu.getAuthMenuId())) {				
					children.add(subMenu);
				}
			}
			Collections.sort(children, subMenuOrder);
			menu.setChildren(children);
		}
		System.out.println("In getmenu--> menus-"+menus);
		return menus;
	}
	
	public static Comparator<menu> menuOrder = new Comparator<menu>() {
		public int compare(menu s1, menu s2) {
		   int menu1 = s1.getOrder();
		   int menu2 = s2.getOrder();
		   return menu1-menu2;
	   }};
	
	  public static Comparator<children> subMenuOrder = new Comparator<children>() {
			public int compare(children s1, children s2) {
			   int subMenu1 = s1.getOrder();
			   int subMenu2 = s2.getOrder();
			   return subMenu1-subMenu2;
		   }};

}
