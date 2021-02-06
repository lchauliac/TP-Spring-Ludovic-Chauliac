package fr.paris8univ.iut.csid.csidwebrepositorybase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/repositories")
public class StatController {
	
	private final StatService statService;
	
	@Autowired
	public StatController(StatService statService) {
		this.statService=statService;
	}
	
	@GetMapping("/{nameRepo}/stat")
	public List<Stat> getStats(@PathVariable String nameRepo, @RequestParam String statType) {
		return statService.GetStat(nameRepo, statType);
	}

}
