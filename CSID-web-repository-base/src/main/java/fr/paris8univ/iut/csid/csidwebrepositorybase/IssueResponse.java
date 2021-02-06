package fr.paris8univ.iut.csid.csidwebrepositorybase;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IssueResponse {
	private int id;
	@JsonProperty("html_url")
	private String htmlUrl;
	private String title;
	private String body;
	private int number;

	public IssueResponse() {

	}

	public IssueResponse(int id, String htmlUrl, String title, String body, int number) {
		this.id = id;
		this.htmlUrl = htmlUrl;
		this.title = title;
		this.body = body;
		this.number = number;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHtmlUrl() {
		return htmlUrl;
	}

	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}
