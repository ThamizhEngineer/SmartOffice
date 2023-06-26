package com.ss.smartoffice.shared.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.ss.smartoffice.shared.model.QueryRequest;
import com.ss.smartoffice.shared.model.QueryResponse;
import com.ss.smartoffice.shared.seed.querysetup.QuerySetup;
import com.ss.smartoffice.shared.seed.querysetup.QuerySetupRepository;

@Service
public class QueryHelper {
	private static Logger log = LoggerFactory.getLogger(QueryHelper.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	QuerySetupRepository querySetupRepo;

	boolean validFlag = false;
	public List<QuerySetup> setupList = new ArrayList<QuerySetup>();

	public List<QuerySetup> fetchSetup(String purpose, String enitiyName) {
		List<QuerySetup> filteredRes = new ArrayList<QuerySetup>();
		try {
			if (setupList.isEmpty() && setupList.size() == 0) {
				setupList = querySetupRepo.fetchAll();
			}
			filteredRes = setupList.stream().filter(x -> x.getPurpose().equals(purpose) && x.getEntity().equals(enitiyName))
					.collect(Collectors.toList());
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return filteredRes;
	}

	public QueryResponse queryDb(QueryRequest qr) {
		QueryResponse response = new QueryResponse();
		try {
			List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
			List<QuerySetup> querySetups = fetchSetup(qr.getPurpose(), qr.getEntity());
			if (!querySetups.isEmpty()) {
				if (isValidInput(querySetups.get(0))) {
					String validQuery = querySetups.get(0).getQuery();
					if (qr.getPageSize()==0 && qr.getPageNo()==0) {
						rows = jdbcTemplate.query(validQuery, new AnyObjectMapper());
					} else {
						String modifiedQuery=validQuery+" LIMIT "+qr.getPageNo()+","+qr.getPageSize();
						rows = jdbcTemplate.query(modifiedQuery, new AnyObjectMapper());
					}
					response.setPageNo(qr.getPageNo());
					response.setPageSize(qr.getPageSize());
					response.setResponse(rows);
					response.setResponseStatus("Success");
				}else {
					response = handlleErrorMessage("ERROR","Not a valid input: "+qr.getPurpose()+"-"+qr.getEntity());
				}
			}else {
				response = handlleErrorMessage("ERROR","No  valid query setup exists for "+qr.getPurpose()+"-"+qr.getEntity());
			}
		} catch (DataAccessException e) {
			response = handlleErrorMessage(e.getLocalizedMessage(),e.getMessage());
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return response;
	}
	
	private QueryResponse handlleErrorMessage(String errorCode, String errorDesc) {
		QueryResponse qr = new QueryResponse();
		qr.setResponseStatus("Error");
		qr.setErrorCode(errorCode);
		qr.setErrorDescription(errorDesc);
		return qr;
	}

	private boolean isValidInput(QuerySetup querySetup) {
		log.info("**********: Checking if input is null :*************");
		boolean validFlag = false;
		if (querySetup.getQuery().isEmpty()) {
			validFlag = false;
		}
		else {
			validFlag = true;
		}
		return validFlag;
	}

	class AnyObjectMapper implements RowMapper<Map<String, Object>> {
		public AnyObjectMapper() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
			ResultSetMetaData rsMeta = rs.getMetaData();
			int colCount = rsMeta.getColumnCount();
			Map<String, Object> columns = new HashMap<String, Object>();
			for (int i = 1; i <= colCount; i++) {
				columns.put(rsMeta.getColumnLabel(i), rs.getObject(i));
			}
			rs.close();
			return columns;
		}
	}
}
