package se.telge.scraping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class MainController {

	@Autowired
	TelgeScrape telegScrape;
	
	
	public static ArrayList<HouseList> listOfHousesPrevious=new ArrayList<>();
	
	@GetMapping("/status")
	public String getHelloWorld()
	{
		return "It is UP!!!";
	}
	
	@GetMapping("/list")
	public List<HouseList> getListOfPreviousHouses()
	{
		return listOfHousesPrevious;
	}
	
	
	@Scheduled(fixedRate = 25000)
	public void getChangedInfo() throws InterruptedException
	{
		telegScrape.scrapeWebsite();
		
		ArrayList<HouseList> listCurrent=telegScrape.listOfHousesCurrent;
		ArrayList<HouseList> hl=null;
		if(listOfHousesPrevious.size()!=0)
		{
		hl=telegScrape.getUpdatedHousesList(listOfHousesPrevious,listCurrent);
		}
		listOfHousesPrevious=listCurrent;
		
		System.out.println("----list of previous elements");
		listOfHousesPrevious.stream().forEach(a->System.out.println(a.toString()));
		
		if(hl!=null)
		{
			if(hl.size()>0)
			{
				hl.stream().forEach(System.out::println);
			}
		}
	}
		
}
