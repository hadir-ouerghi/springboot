package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


@Entity
public class Ads implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int adsid;
	@Enumerated(EnumType.STRING)
	Type type;
	@Enumerated(EnumType.STRING)
	Category category;
	@Enumerated(EnumType.STRING)
	Region region;
	@Enumerated(EnumType.STRING)
	State state;
	@NotNull(message="Title is compulsory")
	@Column(name = "title", nullable=false, unique= true)
	private String title;
	private String description;
	private String city;
	@NotNull(message="Price is compulsory")
	@Column(name = "price", nullable=false)
	private float price;
	@Column(name = "livingArea", nullable=true)
	private float livingArea;
	@Column(name = "landArea", nullable=true)
	private float landArea;
	@Column(name = "bedrooms", nullable=true)
	private int bedrooms;
	@Column(name = "bathrooms", nullable=true)
	private int bathrooms;
	@Column(name = "floor", nullable=true)
	private int floor;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOfAds = new Date();
	
	
	@OneToMany(mappedBy="ads",cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER)
	private List<Comment> Comments = new ArrayList<>();
	@OneToMany(cascade = CascadeType.ALL, mappedBy="aadss")
	private Set<PicturesAds> Picads;

	@ManyToOne
	User user;
	
	
	
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getAdsid() {
		return adsid;
	}
	public void setAdsid(int adsid) {
		this.adsid = adsid;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getLivingArea() {
		return livingArea;
	}
	public void setLivingArea(float livingArea) {
		this.livingArea = livingArea;
	}
	public float getLandArea() {
		return landArea;
	}
	public void setLandArea(float landArea) {
		this.landArea = landArea;
	}
	public int getBedrooms() {
		return bedrooms;
	}
	public void setBedrooms(int bedrooms) {
		this.bedrooms = bedrooms;
	}
	public int getBathrooms() {
		return bathrooms;
	}
	public void setBathrooms(int bathrooms) {
		this.bathrooms = bathrooms;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public Date getDateOfAds() {
		return dateOfAds;
	}
	public void setDateOfAds(Date dateOfAds) {
		this.dateOfAds = dateOfAds;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Ads() {
	}
	@Override
	public String toString() {
		return "Ads [adsid=" + adsid + ", type=" + type + ", category=" + category + ", region=" + region + ", state="
				+ state + ", title=" + title + ", description=" + description + ", city=" + city + ", price=" + price
				+ ", livingArea=" + livingArea + ", landArea=" + landArea + ", bedrooms="
				+ bedrooms + ", bathrooms=" + bathrooms + ", floor=" + floor + ", dateOfAds=" + dateOfAds + "]";
	}
	public List<Comment> getComments() {
		return Comments;
	}
	
}
