package belt.spring.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import belt.spring.models.User;

@Entity
@Table(name = "shows")
public class Show {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Size(min=1, message ="titulo debe tener a lo mas 4 caracteres")
	
	private String title;
	
	
	@Size(min=1, message ="red debe tener a lo mas 4 caracteres")
	private String network;
	
	private Integer rating;
	
	@Column(updatable = false)
	private Date createdAt;
	private Date updatedAt;
	
	// user 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	// rating
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable (
				name = "rating",
				joinColumns = @JoinColumn(name = "show_id"),
				inverseJoinColumns = @JoinColumn(name = "user_id")
			)
	private List<User> ratingUser;
	
	// PrePresist and PreUpdate
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
    
  
	// zero argument constructor
    public Show() {
    }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<User> getRatingUser() {
		return ratingUser;
	}
	public void setRatingUser(List<User> ratingUser) {
		this.ratingUser = ratingUser;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNetwork() {
		return network;
	}
	public void setNetwork(String network) {
		this.network = network;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}

	

	
    
    
}