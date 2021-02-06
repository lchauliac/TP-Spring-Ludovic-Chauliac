package fr.paris8univ.iut.csid.csidwebrepositorybase;

public class IssueRequest {

    private String title;
    private String body;

    public IssueRequest(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public IssueRequest() {
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
}
