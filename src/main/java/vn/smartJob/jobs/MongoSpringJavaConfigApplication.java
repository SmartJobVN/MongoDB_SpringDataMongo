/*
 * Copyright (C) 2016 - SmartJob.vn
 * SmartJob VN proprietary/confidential. Use is subject to license terms.
 * Website: http://smartJob.vn
 * Email: contact@smartJob.vn
 * Telephone: (84)-4-6294 44 47
 */

package vn.smartJob.jobs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import vn.smartJob.jobs.config.MongoConfig;
import vn.smartJob.jobs.model.Job;

/**
 * Application entry point.
 * 
 * @author SmartJob
 *
 */
@SpringBootApplication
@ComponentScan
public class MongoSpringJavaConfigApplication implements QueryDAO {

	/**
	 * For logging.
	 */
	private static final Logger logger = LogManager.getLogger();

	/**
	 * Connection configuration by annotation.
	 */
	static ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfig.class);

	/**
	 * Entry point. Command for testing in MongoDB console: use smartjob;
	 * db.jobs.find().pretty();
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(MongoSpringJavaConfigApplication.class, args);
		MongoSpringJavaConfigApplication app = new MongoSpringJavaConfigApplication();
		// Comment other methods, run only on.
		app.insertOneJob();
		app.insertManyJobs();
		// app.searchJob();
		// app.updateJob();
		// app.deleteJob();
		// app.truncate();
		// app.listAll();
	}

	/**
	 * Insert job data into MongoDB.
	 * 
	 */
	public void insertOneJob() {
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		Job japanessInterpretersJob = new Job("Tuyển thực tập sinh tiếng Nhật", "DCV",
				"Dịch tài liệu chuyên ngành IT tiếng Nhật sang Việt Nam.", "138 Trần Bình - Nam Từ Liêm - Hà Nội");
		// Save to MongoDB.
		mongoOperation.save(japanessInterpretersJob);
		logger.info("Lưu japanessInterpretersJob thành công.");
	}

	/**
	 * Insert many records in same time.
	 */
	public void insertManyJobs() {
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		Job internetTVJob = new Job("Tuyển lập trình viên Java", "FPT software",
				"Xây dựng hệ thống Java enterprise cho một hãng internet TV tại Mỹ",
				"Tòa nhà FPT - Số 6 - Duy Tân - Cầu Giấy - Hà Nội");
		Job crmJob = new Job("Tuyển lập trình viên Java", "FPT software",
				"CRM bảo hiểm tai nạn giao thông trên nền PaaS cho một tập đoàn viễn thông lớn tại Nhật Bản",
				"FPT Ville - Khu công nghệ cao Hòa Lạc - Thạch Thất - Hà Nội");
		// Save to MongoDB.
		List<Job> jobs = new ArrayList<>();
		jobs.add(internetTVJob);
		jobs.add(crmJob);
		mongoOperation.insertAll(jobs);
		logger.info("Lưu các công việc của FSOFT thành công.");
	}

	/**
	 * Looking for job by criteria.
	 */
	public void searchJob() {
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		Query query = new Query(Criteria.where("company").is("DCV"));
		Job job = mongoOperation.findOne(query, Job.class);
		if (job == null) {
			logger.info("Công ty DCV không đăng tin tuyển dụng nào cả.");
		} else {
			logger.info("Tìm kiếm được công việc tại DCV:" + job.toString());
		}
	}

	/**
	 * Update job by condition.
	 */
	public void updateJob() {
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		Query searchQuery = new Query(Criteria.where("title").is("Tuyển thực tập sinh tiếng Nhật"));
		mongoOperation.updateFirst(searchQuery,
				Update.update("title", "Tuyển phiên dịch viên làm việc onsite tại Tokyo"), Job.class);
		logger.info("Cập nhật thành công.");
	}

	/**
	 * Delete by condition(s).
	 */
	public void deleteJob() {
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		Query searchQuery = new Query(Criteria.where("company").is("DCV"));
		mongoOperation.remove(searchQuery, Job.class);
		logger.info("Đã xóa các công việc đăng bởi DCV.");
	}

	/**
	 * Delete all jobs.
	 */
	public void truncate() {
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		mongoOperation.dropCollection(Job.class);
		logger.info("Đã xóa tất cả.");
	}

	/**
	 * List all jobs.
	 */
	public void listAll() {
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		List<Job> allJobs = mongoOperation.findAll(Job.class);
		logger.info(Arrays.toString(allJobs.toArray()));
	}

}
