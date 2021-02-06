package fr.paris8univ.iut.csid.csidwebrepositorybase;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;



@Component
public class GitHubRepositoryDao {
	
	private final RestTemplate restTemplate;
	
	@Autowired
	public GitHubRepositoryDao(RestTemplateBuilder restTemplate) {
		this.restTemplate=restTemplate.build();
	}
	
	public GitRepositoryDTO getGitInfo(String repositoy,String owner) throws RestClientException, URISyntaxException{
		return restTemplate.getForEntity(new URI("https://api.github.com/repos/"+owner+"/"+repositoy),GitRepositoryDTO.class).getBody();
	}

	public IssueResponse createIssue(String owner, String nameRepo, IssueRequest request) throws RestClientException, URISyntaxException {
		HttpHeaders newHttpHeaders = new HttpHeaders();
        newHttpHeaders.set(HttpHeaders.AUTHORIZATION,"Bearer 7133c57fa5290740979f9b9c867fb4ca0d27239d");
        HttpEntity<IssueRequest> newEntity = new HttpEntity<>(request, newHttpHeaders);
        return this.restTemplate.postForEntity(new URI("https://api.github.com/repos/"+owner+"/"+nameRepo+"/issue"),newEntity,IssueResponse.class).getBody();
		
	}
	
}
