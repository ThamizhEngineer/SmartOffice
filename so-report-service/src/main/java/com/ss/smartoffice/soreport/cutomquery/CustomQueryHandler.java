package com.ss.smartoffice.soreport.cutomquery;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.ss.smartoffice.soreport.model.CustomOutput;
import com.ss.smartoffice.soreport.model.RevenuePerBu;
import com.ss.smartoffice.soreport.model.RevenuePerCustomer;

@Component
public class CustomQueryHandler {

	String[] keyTypes = { "screen", "client", "vendor", "client-purchase-order", "sale-order", "supplier-invoice",
			"purchase-order", "job", "expense", "travel-advances" };

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<RevenuePerCustomer> fetchRevenuePerCustomer() {
		String sql = "select  round(sum(ifnull(inv_paid_amt,0))/100000,1)  total_revenue, ifnull(client_name,'-') client_name from v_revenue group by  ifnull(client_name,'-')";
		return jdbcTemplate.query(sql,
				(rs, rowNum) -> new RevenuePerCustomer(rs.getString("total_revenue"), rs.getString("client_name")));
	}

	public List<RevenuePerBu> fetchRevenuePerBu() {
		String sql = "select  round(sum(ifnull(inv_paid_amt,0))/100000,1)  total_revenue, ifnull(bu_name,'-') bu_name from v_revenue group by  ifnull(bu_name,'-')";
		return jdbcTemplate.query(sql,
				(rs, rowNum) -> new RevenuePerBu(rs.getString("total_revenue"), rs.getString("bu_name")));
	}

	public List<CustomOutput> queryPicker(String key) {
		String sql = "";
		switch (key) {
		case "client":
			sql = "select x.id as op1,x.client_code as op2, x.client_name as op3, x.mobile_no as op4, x.email_id as op5,'' as op6, '' as op7, '' as op8, '' as op9, '' as op10 from m_partner x where x.is_client='Y'";
			break;
		case "vendor":
			sql = "select x.id as op1,x.vendor_code as op2, x.vendor_name as op3, x.mobile_no as op4, x.email_id as op5,'' as op6, '' as op7, '' as op8, '' as op9, '' as op10 from m_partner x where x.is_vendor='Y'";
			break;
		case "client-purchase-order":
			sql = "select x.id as op1,y.client_name as op2,x.po_desc as op3, x.po_date as op4, x.order_amount as op5,'' as op6, '' as op7, '' as op8, '' as op9, '' as op10 from t_client_po x left join m_partner y on y.id=x.client_id and y.is_client='Y'";
			break;
		case "sale-order":
			sql = "select x.id as op1, y.client_name as op2, x.sale_order_code as op3, x.sale_order_status as op4, x.order_amount as op5,'' as op6, '' as op7, '' as op8, '' as op9, '' as op10 from t_sale_order x left join m_partner  y on y.id=x.partner_id and y.is_client='Y'";
			break;
		case "purchase-order":
			sql = "select x.id as op1,x.po_code as op2,x.vendor_name as op3, x.po_dt op4,x.net_po_amt as op5,'' as op6, '' as op7, '' as op8, '' as op9, '' as op10 from t_purc_order x";
			break;
		case "job":
			sql = "select x.id as op1, x.job_code as op2, x.job_name as op3,y.client_name as op4, x.start_dt as op5,'' as op6, '' as op7, '' as op8, '' as op9, '' as op10 from t_job x left join m_partner y on y.id=x.partner_id and y.is_client ='Y'";
			break;
		case "expense":
			sql = "select x.id as op1, x.expense_claim_code as op2,y.emp_name as op3, x.expense_claim_status as op4, x.expense_claim_amount as op5,'' as op6, '' as op7, '' as op8, '' as op9, '' as op10 from t_expense_claim x left join m_employee y on y.id=x.employee_id";
			break;
		case "travel-advances":
			sql = "select x.id as op1, x.tar_code as op2,y.emp_name as op3, x.req_adv_amt as op4, x.net_advance_amount as op5,'' as op6, '' as op7, '' as op8, '' as op9, '' as op10 from t_tvl_adv_req x left join m_employee y on y.id=x.m_employee_id";
			break;
		default:
			break;
		}
		return jdbcTemplate.query(sql,
				(rs, rowNum) -> new CustomOutput(rs.getString("op1"), rs.getString("op2"), rs.getString("op3"),
						rs.getString("op4"), rs.getString("op5"), rs.getString("op6"), rs.getString("op7"),
						rs.getString("op8"), rs.getString("op9"), rs.getString("op10")));
	}
}
