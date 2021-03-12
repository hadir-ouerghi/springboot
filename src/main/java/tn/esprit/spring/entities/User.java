package tn.esprit.spring.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;



@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idUser")
	private int id;
	
	@NotNull(message="Email is compulsory")
	@Column(name = "email", nullable=false, unique= true)
	@Email(message = "Please provide a valid e-mail")
	@NotEmpty(message="Please provide an e-mail")
	private String email;
	
	@NotNull(message="Password is compulsory")
	@Column(name = "password")
	@Size(min=5, max=16, message="Password should be at least 5 characters")
	private String password;
	
	
	@NotNull(message="First name is compulsory")
	@Column(name = "first_name")
	@NotEmpty(message = "Please provide your first name")
	private String firstName;
	
	@NotNull(message="Last name is compulsory")
	@Column(name = "last_name")
	@NotEmpty(message = "Please provide your last name")
	private String lastName;
	
	//@Column(name = "enabled")
	//private boolean enabled;
	
	@Column(name = "status")
	private String status;

	@Pattern(regexp="(^$|[0-9]{8}",message="Mobile number must be 8 digits")
	private String phone;
	
	
	
//***********Association********///
	
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="user_Role", joinColumns= @JoinColumn(name="idUser"), inverseJoinColumns= @JoinColumn(name="idRole"))
	private Set<Role> roles;


	@OneToMany(cascade = CascadeType.ALL, mappedBy="user")
	private Set<Ads> Adss;
	
	/*@OneToMany(cascade = CascadeType.ALL, mappedBy="user")
	private Set<Comment> Comments;*/
	
	
	//*********Getter&Setter********//
	

	public Set<Ads> getAdss() {
		return Adss;
	}

	public void setAdss(Set<Ads> adss) {
		Adss = adss;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	
}
