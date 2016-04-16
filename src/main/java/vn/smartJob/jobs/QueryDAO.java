/*
 * Copyright (C) 2016 - SmartJob.vn
 * SmartJob VN proprietary/confidential. Use is subject to license terms.
 * Website: http://smartJob.vn
 * Email: contact@smartJob.vn
 * Telephone: (84)-4-6294 44 47
 */

package vn.smartJob.jobs;

public interface QueryDAO {

	public void insertOneJob();

	public void insertManyJobs();

	public void searchJob();

	public void updateJob();

	public void deleteJob();

	public void truncate();

	public void listAll();

}
