package se.telge.scraping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.stereotype.Component;

@Component
public class TelgeScrape {

	public static ArrayList<HouseList> listOfHousesCurrent=new ArrayList<>();
	
	public void scrapeWebsite() throws InterruptedException {
		
		listOfHousesCurrent.clear();
		
		java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
	    java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);

		WebDriver webDriver = new HtmlUnitDriver(true);
		
		webDriver.get("https://hyresborsen.telge.se/ledigt/lagenhet");
		
		int pageSize=webDriver.findElements(By.cssSelector(".right>a")).size();
		 
		for(int i=1;i<=pageSize;i++)
		{
			String paginationSelector=".right>a:nth-child("+i+")";
			webDriver.findElement(By.cssSelector(paginationSelector)).click();
			String blockId="#ctl00_ctl01_DefaultSiteContentPlaceHolder1_Col1_ObjectList_block > div> ul";
			
			int rows=webDriver.findElements(By.cssSelector
					(blockId)).size();
			
			for(int j=1;j<=rows;j++)
			{
				
				String houseName=webDriver.findElement(By.cssSelector(blockId+":nth-child("+j+")> li:nth-child(1)>h2>a")).getAttribute("innerText");
				String place=webDriver.findElement(By.cssSelector(blockId+":nth-child("+j+")> li:nth-child(4)>span")).getAttribute("innerText");
				String rooms=webDriver.findElement(By.cssSelector(blockId+":nth-child("+j+")> li:nth-child(5)>span")).getAttribute("innerText");
				String size=webDriver.findElement(By.cssSelector(blockId+":nth-child("+j+")> li:nth-child(6)>span")).getAttribute("innerText");
				String rent=webDriver.findElement(By.cssSelector(blockId+":nth-child("+j+")> li:nth-child(7)>span")).getAttribute("innerText");
				String date=webDriver.findElement(By.cssSelector(blockId+":nth-child("+j+")> li:nth-child(8)>span")).getAttribute("innerText");
				String applicants=webDriver.findElement(By.cssSelector(blockId+":nth-child("+j+")> li:nth-child(9)>span")).getAttribute("innerText");
				HouseList hl=new HouseList(houseName,place,rooms,size,rent,date,applicants);
				
				listOfHousesCurrent.add(hl);
				
			}
			
		}
		
		System.out.println("--------------------");
		System.out.println("Title"+webDriver.getTitle());
		System.out.println(pageSize);
		System.out.println(listOfHousesCurrent.size());
		System.out.println("--------------------");
	}
	
	public ArrayList<HouseList> getUpdatedHousesList(ArrayList<HouseList> previous,ArrayList<HouseList> current)
	{
		ArrayList<HouseList> hsTemp=new ArrayList<>();
		for (HouseList houseList : current) {
			hsTemp.add(houseList);
		}
		
		int k=0;
		for (int i = 0; i < previous.size(); i++) {
			
			for (int j = 0; j < current.size(); j++) {
				
				if(previous.get(i).getHouseName().equalsIgnoreCase(current.get(j).getHouseName()) &&
						previous.get(i).getSize().equalsIgnoreCase(current.get(j).getSize()) &&
						previous.get(i).getRent().equalsIgnoreCase(current.get(j).getRent()) &&
						previous.get(i).getRooms().equalsIgnoreCase(current.get(j).getRooms()))
				{
					k++;
					hsTemp.remove(current.get(j));
				}
			}
		}
		
		System.out.println("current List Size"+k);
		System.out.println("hsTemp Size"+hsTemp.size());
		return hsTemp;
	}
	
}
