package fr.paris8univ.iut.csid.csidwebrepositorybase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StatDao extends JpaRepository<StatEntity, Integer> {
	
	@Query(value = "select * from statistique as s where s.name= ?1 and s.entry_type= ?2 order by s.creationDate asc ",nativeQuery=true)
	List<StatEntity> findAllStatForNameOrderByDate(String repository, String statType);

}
