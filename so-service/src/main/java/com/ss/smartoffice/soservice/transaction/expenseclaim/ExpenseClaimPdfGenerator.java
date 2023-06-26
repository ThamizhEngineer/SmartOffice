package com.ss.smartoffice.soservice.transaction.expenseclaim;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.ss.smartoffice.shared.common.CommonUtils;


public class ExpenseClaimPdfGenerator {
private static final String EXPENSE_CLAIM_TEMPLATE_NAME = "html/expense-claim-printout";
	
	@Autowired
	private TemplateEngine htmlTemplateEngine;
	@Autowired
	CommonUtils commonUtils;
	public void generatePdf(ExpenseClaim expenseClaim) {
		ITextRenderer renderer = new ITextRenderer();
		final Context ctx = new Context(Locale.ENGLISH);
		ctx.setVariable("expenseClaimCode", expenseClaim.getExpenseClaimCode());
		
		ctx.setVariable("isJobBased", expenseClaim.getIsJobBased());
		ctx.setVariable("jobId", expenseClaim.getJobId());
		ctx.setVariable("jobCode", expenseClaim.getJobCode());
		ctx.setVariable("jobName", expenseClaim.getJobName());
	
		ctx.setVariable("costCenterId", expenseClaim.getCostCenterId());
		ctx.setVariable("costCenterCode", expenseClaim.getCostCenterCode());
		ctx.setVariable("costCenterName", expenseClaim.getCostCenterName());
		ctx.setVariable("expenseClaimStatus", expenseClaim.getExpenseClaimStatus());
		ctx.setVariable("employeeId", expenseClaim.getEmployeeId());
		ctx.setVariable("employeeCode", expenseClaim.getEmployeeCode());
		ctx.setVariable("employeeFName", expenseClaim.getEmployeeFName());
		ctx.setVariable("employeeLName", expenseClaim.getEmployeeLName());
		ctx.setVariable("expenseClaimAmount", expenseClaim.getExpenseClaimAmount());
		ctx.setVariable("n1EmpId", expenseClaim.getN1EmpId());
		ctx.setVariable("n1EmpFName", expenseClaim.getN1EmpFName());
		ctx.setVariable("n1EmpFName", expenseClaim.getN1EmpLName());
		ctx.setVariable("n2EmpId", expenseClaim.getN2EmpId());
		ctx.setVariable("n2EmpFName", expenseClaim.getN2EmpFName());
		ctx.setVariable("n2EmpFName", expenseClaim.getN2EmpLName());
		ctx.setVariable("appliedEmpId", expenseClaim.getAppliedEmpId());
		ctx.setVariable("appliedEmpCode", expenseClaim.getAppliedEmpCode());
		ctx.setVariable("appliedEmpFName", expenseClaim.getAppliedEmpFName());
		ctx.setVariable("appliedEmpLName", expenseClaim.getAppliedEmpLName());
		ctx.setVariable("inputCreditAmount", expenseClaim.getInputCreditAmount());
		final String htmlContent = this.htmlTemplateEngine.process(EXPENSE_CLAIM_TEMPLATE_NAME, ctx);
		String fileNamePrefix = "expense-claim" + commonUtils.generateId();
		String fileNameSuffix = ".pdf";
//		String fileLocation = applicantPdfLocation + fileNamePrefix + fileNameSuffix;
	}
}
