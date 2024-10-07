package com.ved_api.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MySite {
	
	@Id
    private String site_id;
    private String site_name;
    private String site_url;
    private String site_info;
    private String github_repo;
    private Date created_date;
	public String getSite_id() {
		return site_id;
	}
	
	public MySite() {}
	
	public MySite(String site_id, String site_name, String site_url, String site_info, String github_repo,
			Date created_date) {
		super();
		this.site_id = site_id;
		this.site_name = site_name;
		this.site_url = site_url;
		this.site_info = site_info;
		this.github_repo = github_repo;
		this.created_date = created_date;
	}



	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}
	public String getSite_name() {
		return site_name;
	}
	public void setSite_name(String site_name) {
		this.site_name = site_name;
	}
	public String getSite_url() {
		return site_url;
	}
	public void setSite_url(String site_url) {
		this.site_url = site_url;
	}
	public String getSite_info() {
		return site_info;
	}
	public void setSite_info(String site_info) {
		this.site_info = site_info;
	}
	public String getGithub_repo() {
		return github_repo;
	}
	public void setGithub_repo(String github_repo) {
		this.github_repo = github_repo;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
}
