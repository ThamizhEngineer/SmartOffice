package com.ss.smartoffice.soservice.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import com.ss.smartoffice.soservice.transaction.model.UploadPayslipLine;
@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
	
	 @Autowired
	 public JobBuilderFactory jobBuilderFactory;
	 
	 @Autowired
	 public StepBuilderFactory stepBuilderFactory;
	 
	 @Autowired
	 public JobRepository jobRepository;
	  
	@Bean
    public Job job(JobBuilderFactory jobBuilderFactory,
                   StepBuilderFactory stepBuilderFactory,
                   ItemReader<UploadPayslipLine> itemReader,
                   ItemProcessor<UploadPayslipLine, UploadPayslipLine> itemProcessor,
                   ItemWriter<UploadPayslipLine> itemWriter
    ) {

        Step step = stepBuilderFactory.get("ETL-file-load")
                .<UploadPayslipLine, UploadPayslipLine>chunk(100)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();


        return jobBuilderFactory.get("ETL-Load")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<UploadPayslipLine> itemReader(@Value("#{jobParameters['file.name']}") Resource fileLocation) {
    
        FlatFileItemReader<UploadPayslipLine> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(fileLocation);
        flatFileItemReader.setName("CSV-Reader");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(lineMapper());
        return flatFileItemReader;
    }

    @Bean
    public LineMapper<UploadPayslipLine> lineMapper() {

        DefaultLineMapper<UploadPayslipLine> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setIncludedFields(1,2,3,4,5,6,7,8,9,
        		10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 
        		20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 
        		30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 
        		40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 
        		50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 
        		60, 61, 62, 63, 64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,
        		79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,
        		104,105,106,107,108,109,110,111,112,113,114,115,116,
        		117,118,119,120,121,122,123,124,125,126,127,128,129,130,131,132,133,134,135,
        		136,137,138,139,140,141,142,143,144,145,146,147,148,149,150,151,152,153,154,155,156,157,158,159,160,161,162,163,164,165,166,167,168,169,170,171,172,173,174,175,176,177,178,179,180,181,182,183,184,185,186,187,188);
        lineTokenizer.setNames(new String[]{"employee_code",
        		"employee_name",
        		"email",
        		"mobile_no",
        		"dob",
        		"doj",
        		"department",
        		"designation",
        		"grade",
        		"special_designation",
        		"skill",
        		"business",
        		"nature_of_position",
        		"salary_month",
        		"salary_year",
        		"location_of_position",
        		"pf_no",
        		"eps_no",
        		"esi_no",
        		"bank_account_number",
        		"bank",
        		"ifsc_code",
        		"pan_no",
        		"aadhar_no",
        		"uan_no",
        		"currency",
        		"period_days",
        		"period_holidays",
        		"worked_days",
        		"lop_and_lwop",
        		"arrear_days",
        		"late_days_deduction",
        		"overtime_hours",
        		"wage_days",
        		"additional_info_1",
        		"additional_info_2",
        		"additional_info_3",
        		"a11_name",
        		"a11_value_earning_period",
        		"a11_value_arrears",
        		"a11_value_current_period",
        		"a11_value_ytd_earnings",
        		"a12_name",
        		"a12_value_earning_period",
        		"a12_value_arrears",
        		"a12_value_current_period",
        		"a12_value_ytd_earnings",
        		"a13_name",
        		"a13_value_earning_period",
        		"a13_value_arrears",
        		"a13_value_current_period",
        		"a13_value_ytd_earnings",
        		"a14_name",
        		"a14_value_earning_period",
        		"a14_value_arrears",
        		"a14_value_current_period",
        		"a14_value_ytd_earnings",
        		"a15_name",
        		"a15_value_earning_period",
        		"a15_value_arrears",
        		"a15_value_current_period",
        		"a15_value_ytd_earnings",
        		"a16_name",
        		"a16_value_earning_period",
        		"a16_value_arrears",
        		"a16_value_current_period",
        		"a16_value_ytd_earnings",
        		"a17_name",
        		"a17_value_earning_period",
        		"a17_value_arrears",
        		"a17_value_current_period",
        		"a17_value_ytd_earnings",
        		"a18_name",
        		"a18_value_earning_period",
        		"a18_value_arrears",
        		"a18_value_current_period",
        		"a18_value_ytd_earnings",
        		"a19_name",
        		"a19_value_earning_period",
        		"a19_value_arrears",
        		"a19_value_current_period",
        		"a19_value_ytd_earnings",
        		"a21_name",
        		"a21_value_earning_period",
        		"a21_value_arrears",
        		"a21_value_current_period",
        		"a21_value_ytd_earnings",
        		"a22_name",
        		"a22_value_earning_period",
        		"a22_value_arrears",
        		"a22_value_current_period",
        		"a22_value_ytd_earnings",
        		"a23_name",
        		"a23_value_earning_period",
        		"a23_value_arrears",
        		"a23_value_current_period",
        		"a23_value_ytd_earnings",
        		"a24_name",
        		"a24_value_earning_period",
        		"a24_value_arrears",
        		"a24_value_current_period",
        		"a24_value_ytd_earnings",
        		"a25_name",
        		"a25_value_earning_period",
        		"a25_value_arrears",
        		"a25_value_current_period",
        		"a25_value_ytd_earnings",
        		"a26_name",
        		"a26_value_earning_period",
        		"a26_value_arrears",
        		"a26_value_current_period",
        		"a26_value_ytd_earnings",
        		"a27_name",
        		"a27_value_earning_period",
        		"a27_value_arrears",
        		"a27_value_current_period",
        		"a27_value_ytd_earnings",
        		"b11_name",
        		"b11_value_earning_period",
        		"b11_value_arrears",
        		"b11_value_current_period",
        		"b11_value_ytd_earnings",
        		"b12_name",
        		"b12_value_earning_period",
        		"b12_value_arrears",
        		"b12_value_current_period",
        		"b12_value_ytd_earnings",
        		"b13_name",
        		"b13_value_earning_period",
        		"b13_value_arrears",
        		"b13_value_current_period",
        		"b13_value_ytd_earnings",
        		"b14_name",
        		"b14_value_earning_period",
        		"b14_value_arrears",
        		"b14_value_current_period",
        		"b14_value_ytd_earnings",
        		"d11_name",
				"d11_value_current_period",
				"d11_value_ytd_deductions",
				"d12_name",
				"d12_value_current_period",
				"d12_value_ytd_deductions",
				"d13_name",
				"d13_value_current_period",
				"d13_value_ytd_deductions",
				"d14_name",
				"d14_value_current_period",
				"d14_value_ytd_deductions",
				"d15_name",
				"d15_value_current_period",
				"d15_value_ytd_deductions",
				"d16_name",
				"d16_value_current_period",
				"d16_value_ytd_deductions",
				"d17_name",
				"d17_value_current_period",
				"d17_value_ytd_deductions",
				"leave1_name",
				"leave1_value_opening",
				"leave1_value_accured",
				"leave1_availed",
				"leave1_value_balance",
				"leave2_name",
				"leave2_value_opening",
				"leave2_value_accured",
				"leave2_availed",
				"leave2_value_balance",
				"leave3_name",
				"leave3_value_opening",
				"leave3_value_accured",
				"leave3_availed",
				"leave3_value_balance",
				"leave4_name",
				"leave4_value_opening",
				"leave4_value_accured",
				"leave4_availed",
				"leave4_value_balance",
				"remarks_name",
				"remarks_value",
				"net_pay_period_name",
				"net_pay_period_value",
				"net_pay_words_name",
				"net_pay_words_value",
				"pf_accumulation_by_employer",
				"pf_opening_value",
				"pf_closing_value",
				"pf_balance_value"});
        BeanWrapperFieldSetMapper<UploadPayslipLine> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(UploadPayslipLine.class);

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }
}
