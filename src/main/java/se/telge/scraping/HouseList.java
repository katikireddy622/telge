package se.telge.scraping;
public class HouseList{
	
	private String houseName;
	private String place;
	private String rooms;
	private String size;
	private String rent;
	private String date;
	private String applicants;
	public String getHouseName() {
		return houseName;
	}
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getRooms() {
		return rooms;
	}
	public void setRooms(String rooms) {
		this.rooms = rooms;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getRent() {
		return rent;
	}
	public void setRent(String rent) {
		this.rent = rent;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getApplicants() {
		return applicants;
	}
	public void setApplicants(String applicants) {
		this.applicants = applicants;
	}
	public HouseList(String houseName, String place, String rooms, String size, String rent, String date,
			String applicants) {
		super();
		this.houseName = houseName;
		this.place = place;
		this.rooms = rooms;
		this.size = size;
		this.rent = rent;
		this.date = date;
		this.applicants = applicants;
	}
	
	@Override
	public String toString() {
		return "HouseList [houseName=" + houseName + ", place=" + place + ", rooms=" + rooms + ", size=" + size
				+ ", rent=" + rent + ", date=" + date + ", applicants=" + applicants + "]";
	}
	public HouseList()
	{}
	
	@Override
	public int hashCode() {
		String s=houseName+rooms+size+rent;
		return s.hashCode();
	}
}
