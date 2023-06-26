package com.ss.smartoffice.shared.interceptor;

import java.io.BufferedReader;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.smartoffice.shared.apiLogger.ApiLoggerService;
import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException; 

@Component
public class AuthInterceptor implements HandlerInterceptor {

	@Autowired
	CommonUtils commonUtils;

	@Autowired
	ApiLoggerService apiLoggerService;

	@Autowired
	TokenService tokenService;
	@Value("${secure.service}")
	private String secureService;


	private static Logger log = LoggerFactory.getLogger(AuthInterceptor.class);
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3)
			throws Exception {
		// System.out.println("afterCompletion...Request Completed ");

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model)
			throws Exception {

		// to handle CORS issue, this code-block allows all methods from all domains to
		// access services
		String ORIGIN = "Origin";
		try {
			if (request.getHeader(ORIGIN) != null) {
				String origin = request.getHeader(ORIGIN);
				if (!response.getHeaders("Access-Control-Allow-Origin").contains(origin)) {
					response.addHeader("Access-Control-Allow-Origin", origin);
				}
				response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, PATCH, OPTIONS, HEAD");
				response.addHeader("Access-Control-Allow-Credentials", "true");
				response.addHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean skipRequestCheck(HttpServletRequest request) {
		boolean skip = false;

		String uri = request.getRequestURI();
		if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
			skip = true;
		} else if (uri.contains("/_internal")) {
			skip = true;
		} else if (uri.contains("/so-auth-service") && uri.contains("/tokens")) {

			skip = true;
		} else if (uri.contains("/so-service") && uri.contains("/transaction/vacancy-openings")) {

			skip = true;
		} else if (uri.contains("/so-service") && uri.contains("/transaction/recruiment-service")) {

			skip = true;
		}
		else if (uri.contains("/so-auth-service") && uri.contains("/error")) {

			skip = true;
		}
		return skip;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		SmartOfficeException exception = null;
		try {

			if (skipRequestCheck(request)) {
				return true;
			}
			if (secureService.equals("true")) {
				String url = request.getRequestURL().toString();
				String authFromParam = request.getParameter("authorization");
				String authFromHeader = request.getHeader("authorization");
//				log.debug("kh header - " + authFromHeader);
//				log.debug("kh param - " + authFromParam);
				if ((authFromHeader != null && !authFromHeader.isEmpty())) {								
					commonUtils.setAuthenticationContext(authFromHeader);
					apiLoggerService.apiLogger(request, commonUtils.getLoggedinUserId());
					return true;
				} else if (authFromParam != null && !authFromParam.isEmpty()) {
					commonUtils.setAuthenticationContext(authFromParam);
					apiLoggerService.apiLogger(request, commonUtils.getLoggedinUserId());
					return true;
				} else {
					log.warn("Authorization is Mandatory");
					throw new Exception("Authorization is Mandatory");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			exception = new SmartOfficeException(e.getMessage());
		}

		if (exception != null) {
			ObjectMapper mapper = new ObjectMapper();
			response.setContentType("application/json");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write(mapper.writeValueAsString(exception.getMessage()));
			return false;
		} else
			return true;

	}
	

}