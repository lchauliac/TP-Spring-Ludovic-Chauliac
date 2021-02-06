package fr.paris8univ.iut.csid.csidwebrepositorybase;

import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;

@Repository
public class GitRepositoryRepository {
	
	private final GitRepositoryDao gitRepositoryDao;
	private final GitHubRepositoryDao gitHubRepositoryDao;
	private final StatDao statDao;
	
	@Autowired
	public GitRepositoryRepository(GitRepositoryDao gitRepositoryDao,GitHubRepositoryDao gitHubRepositoryDao,StatDao statDao) {
		this.gitRepositoryDao=gitRepositoryDao;
		this.gitHubRepositoryDao=gitHubRepositoryDao;
		this.statDao=statDao;
	}
	
	public List<GitRepository> getRepositories(){
		List<GitRepositoryEntity> repositoryEntities = gitRepositoryDao.findAll();
		return repositoryEntities.stream()
				.map(x -> new GitRepository(x.getName(),x.getOwner(),x.getIssues(),x.getFork(),x.getlastUpdate()))
				.collect(Collectors.toList());
	}
	
	public Optional<GitRepository> findOneRepoForPatch(String name){
		return Optional.of(gitRepositoryDao.findById(name).map(x-> new GitRepository(x.getName(),x.getOwner(),x.getIssues(),x.getFork(),x.getlastUpdate())).get());
	}
	
	public Optional<GitRepository> findOneRepository(String name) throws RestClientException, URISyntaxException{
		GitRepositoryEntity actualRepository = gitRepositoryDao.findById(name).get();
		GitRepository toReturn= new GitRepository(actualRepository.getName(),actualRepository.getOwner(),actualRepository.getIssues(),actualRepository.getFork(),actualRepository.getlastUpdate());
		
		if((Instant.now().getEpochSecond()-toReturn.getLastUpdate())>300) {
			GitRepositoryDTO gitInfo = gitHubRepositoryDao.getGitInfo(toReturn.getName(), toReturn.getOwner());
			toReturn.setIssues(gitInfo.getIssues());
			toReturn.setFork(gitInfo.getForks());
			toReturn.setLastUpdate(Instant.now().getEpochSecond());
			
			statDao.save(new StatEntity(0,toReturn.getName(),"issues",toReturn.getLastUpdate(),toReturn.getIssues()));
			statDao.save(new StatEntity(0,toReturn.getName(),"forks",toReturn.getLastUpdate(),toReturn.getFork()));
			
			patchRepository(toReturn);
		}
		
		return Optional.of(toReturn);
	}
	
	public void creatRepository(GitRepository gitRepository){
		gitRepositoryDao.save(new GitRepositoryEntity(gitRepository.getName(),gitRepository.getOwner(),gitRepository.getIssues(),gitRepository.getFork(),gitRepository.getLastUpdate()));
	}
	
	public void putRepository(String name,GitRepository gitRepository) {
		
		Optional<GitRepositoryEntity> repository = gitRepositoryDao.findById(name);
		
		if(repository.isEmpty()) {
			creatRepository(gitRepository);
		}
		else {
			
			GitRepositoryEntity repositoryModified = repository.get();
			
			repositoryModified.setOwner(gitRepository.getOwner());
			repositoryModified.setIssues(gitRepository.getIssues());
			repositoryModified.setFork(gitRepository.getFork());
			
			gitRepositoryDao.save(repositoryModified);
		}
		
	}
	
	public void patchRepository(GitRepository gitRepo) {
		GitRepositoryEntity repositoryPatched = new GitRepositoryEntity(gitRepo.getName(),gitRepo.getOwner(),gitRepo.getIssues(),gitRepo.getFork(),gitRepo.getLastUpdate());
		gitRepositoryDao.save(repositoryPatched);
	}
	
	public void deleteRepository(String name) {
		gitRepositoryDao.deleteById(name);
	}

	public IssueResponse createIssue(String nameRepo, IssueRequest issue) throws RestClientException, URISyntaxException {
		GitRepositoryEntity gitRepo = this.gitRepositoryDao.getOne(nameRepo);
		return this.gitHubRepositoryDao.createIssue(gitRepo.getOwner(),nameRepo,issue);
		
	}

}
