/*
 * Copyright (C) 2016 - SmartJob.vn
 * SmartJob VN proprietary/confidential. Use is subject to license terms.
 * Website: http://smartJob.vn
 * Email: contact@smartJob.vn
 * Telephone: (84)-4-6294 44 47
 */

package vn.smartJob.jobs.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entity job.
 * 
 * @author SmartJob
 */
@Document(collection = "jobs")
public class Job {

	/**
	 * Job identity number.
	 */
	@Id
	private String id;

	/**
	 * Title of a specific job.
	 */
	String title;

	/**
	 * Company name who need new employee(s).
	 */
	String company;

	/**
	 * Recruitment detail information.
	 */
	String content;

	/**
	 * Company/workplace location.
	 */
	String workplace;

	public Job(String title, String company, String content, String workplace) {
		this.title = title;
		this.company = company;
		this.content = content;
		this.workplace = workplace;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWorkplace() {
		return workplace;
	}

	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}

	@Override
	public String toString() {
		return "Job [id=" + id + ", title=" + title + ", company=" + company + ", content=" + content + ", workplace="
				+ workplace + "]";
	}

}
