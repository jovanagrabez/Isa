package DTO;

import model.Hotel;

public class HotelDTO {
  
	private Long id;
    private String name;
    private String adress;
    private String description;
	
    public HotelDTO() {
		
	}

	public HotelDTO(Long id, String name, String adress, String description) {
		super();
		this.id = id;
		this.name = name;
		this.adress = adress;
		this.description = description;
	}

	public HotelDTO(Hotel hotel) {
		this(hotel.getId(), hotel.getName(), hotel.getAdress(), hotel.getDescription());
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAdress() {
		return adress;
	}

	public String getDescription() {
		return description;
	}
 
    
}
