package xa.dokterhalo289.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

@Entity
@Table(name="t_token")
public class T_token {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long Id;
	
	@Nullable
	@Column(name="email", length=100)
	private String Email;
	
	@ManyToOne
	@JoinColumn(name="user_id", insertable=false, updatable=false)
	public M_user m_user;
	
	@Nullable
	@Column(name="user_id")
	private Long User_id;
	
	@Nullable
	@Column(name="token", length=50)
	private String Token;
	
	@Nullable
	@Column(name="expired_on")
	private LocalDateTime Expired_on;

	@Nullable
	@Column(name="is_expired")
	private Boolean Is_expired;
	
	@Nullable
	@Column(name="used_for", length=20)
	private String Used_for;
	
	@ManyToOne
	@JoinColumn(name="create_by", insertable=false, updatable=false)
	public M_user User_create;
	
	@NotNull
	@Column(name="create_by")
	private Long Create_by;
	
	@NotNull
	@Column(name="create_on")
	private LocalDateTime Create_on;
	
	@ManyToOne
	@JoinColumn(name="modified_by", insertable=false, updatable=false)
	public M_user User_modified;
	
	@Nullable
	@Column(name="modified_by")
	private Long Modified_by;
	
	@Nullable
	@Column(name="modified_on")
	private LocalDateTime Modified_on;
	
	@ManyToOne
	@JoinColumn(name="deleted_by", insertable=false, updatable=false)
	public M_user User_deleted;
	
	@Nullable
	@Column(name="deleted_by")
	private Long Deleted_by;
	
	@Nullable
	@Column(name="deleted_on")
	private LocalDateTime Deleted_on;
	
	@NotNull
	@Column(name="is_delete", columnDefinition="boolean default false")
	private Boolean Is_delete;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public Long getUser_id() {
		return User_id;
	}

	public void setUser_id(Long user_id) {
		User_id = user_id;
	}

	public String getToken() {
		return Token;
	}

	public void setToken(String token) {
		Token = token;
	}

	public LocalDateTime getExpired_on() {
		return Expired_on;
	}

	public void setExpired_on(LocalDateTime expired_on) {
		Expired_on = expired_on;
	}

	public Boolean getIs_expired() {
		return Is_expired;
	}

	public void setIs_expired(Boolean is_expired) {
		Is_expired = is_expired;
	}

	public String getUsed_for() {
		return Used_for;
	}

	public void setUsed_for(String used_for) {
		Used_for = used_for;
	}

	public Long getCreate_by() {
		return Create_by;
	}

	public void setCreate_by(Long create_by) {
		Create_by = create_by;
	}

	public LocalDateTime getCreate_on() {
		return Create_on;
	}

	public void setCreate_on(LocalDateTime create_on) {
		Create_on = create_on;
	}

	public Long getModified_by() {
		return Modified_by;
	}

	public void setModified_by(Long modified_by) {
		Modified_by = modified_by;
	}

	public LocalDateTime getModified_on() {
		return Modified_on;
	}

	public void setModified_on(LocalDateTime modified_on) {
		Modified_on = modified_on;
	}

	public Long getDeleted_by() {
		return Deleted_by;
	}

	public void setDeleted_by(Long deleted_by) {
		Deleted_by = deleted_by;
	}

	public LocalDateTime getDeleted_on() {
		return Deleted_on;
	}

	public void setDeleted_on(LocalDateTime deleted_on) {
		Deleted_on = deleted_on;
	}

	public Boolean getIs_delete() {
		return Is_delete;
	}

	public void setIs_delete(Boolean is_delete) {
		Is_delete = is_delete;
	}

	
	

}
