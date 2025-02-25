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
@Table(name="t_appointment")

public class T_appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long Id;
	
	@ManyToOne
	@JoinColumn(name="costumer_id" ,insertable=false, updatable=false)
	public M_customer m_customer;
	
	@Nullable
	@Column(name="customer_id")
	private Long Customer_id;
	
	@ManyToOne
	@JoinColumn(name="doctor_office_id" ,insertable=false, updatable=false)
	public T_doctor_office t_doctor_office;
	
	@Nullable
	@Column(name="doctor_office_id")
	private Long Doctor_office_id;
	
	@ManyToOne
	@JoinColumn(name="doctor_office_schedule_id" ,insertable=false, updatable=false)
	public T_doctor_office_schedule t_doctor_office_schedule;
	
	@Nullable
	@Column(name="doctor_office_schedule_id")
	private Long Doctor_office_schedule_id;
	
	@ManyToOne
	@JoinColumn(name="doctor_office_treatment_id" ,insertable=false, updatable=false)
	public T_doctor_office_treatment t_doctor_office_treatment;
	
	@Nullable
	@Column(name="doctor_office_treatment_id")
	private Long Doctor_office_treatment_id;

	@Nullable
	@Column(name="appointment_date")
	private LocalDateTime Appointment_date;

	//base properties
		@ManyToOne
	    @JoinColumn(name="created_by",insertable=false, updatable=false)
	    public M_user User_create;

	    @NotNull
	    @Column(name="created_by")
	    private Long Created_by;

	    @NotNull
	    @Column(name="created_on")
	    private LocalDateTime Created_on;

	    @ManyToOne
	    @JoinColumn(name="modified_by",insertable=false, updatable=false)
	    public M_user User_modified;

	    @Nullable
	    @Column(name="modified_by")
	    private Long Modified_by;

	    @Nullable
	    @Column(name="modified_on")
	    private LocalDateTime Modified_on;

	    @ManyToOne
	    @JoinColumn(name="deleted_by",insertable=false, updatable=false)
	    public M_user User_deleted;

	    @Nullable
	    @Column(name="deleted_by")
	    private Long Deleted_by;

	    @Nullable
	    @Column(name="deleted_on")
	    private LocalDateTime Deleted_on;

	    @NotNull
	    @Column(name="is_delete",columnDefinition = "boolean default false")
	    private boolean Is_delete;

		public Long getId() {
			return Id;
		}

		public void setId(Long id) {
			Id = id;
		}

		public M_customer getM_customer() {
			return m_customer;
		}

		public void setM_customer(M_customer m_customer) {
			this.m_customer = m_customer;
		}

		public Long getCustomer_id() {
			return Customer_id;
		}

		public void setCustomer_id(Long customer_id) {
			Customer_id = customer_id;
		}

		public T_doctor_office getT_doctor_office() {
			return t_doctor_office;
		}

		public void setT_doctor_office(T_doctor_office t_doctor_office) {
			this.t_doctor_office = t_doctor_office;
		}

		public Long getDoctor_office_id() {
			return Doctor_office_id;
		}

		public void setDoctor_office_id(Long doctor_office_id) {
			Doctor_office_id = doctor_office_id;
		}

		public T_doctor_office_schedule getT_doctor_office_schedule() {
			return t_doctor_office_schedule;
		}

		public void setT_doctor_office_schedule(T_doctor_office_schedule t_doctor_office_schedule) {
			this.t_doctor_office_schedule = t_doctor_office_schedule;
		}

		public Long getDoctor_office_schedule_id() {
			return Doctor_office_schedule_id;
		}

		public void setDoctor_office_schedule_id(Long doctor_office_schedule_id) {
			Doctor_office_schedule_id = doctor_office_schedule_id;
		}

		public T_doctor_office_treatment getT_doctor_office_treatment() {
			return t_doctor_office_treatment;
		}

		public void setT_doctor_office_treatment(T_doctor_office_treatment t_doctor_office_treatment) {
			this.t_doctor_office_treatment = t_doctor_office_treatment;
		}

		public Long getDoctor_office_treatment_id() {
			return Doctor_office_treatment_id;
		}

		public void setDoctor_office_treatment_id(Long doctor_office_treatment_id) {
			Doctor_office_treatment_id = doctor_office_treatment_id;
		}

		public LocalDateTime getAppointment_date() {
			return Appointment_date;
		}

		public void setAppointment_date(LocalDateTime appointment_date) {
			Appointment_date = appointment_date;
		}

		public M_user getUser_create() {
			return User_create;
		}

		public void setUser_create(M_user user_create) {
			User_create = user_create;
		}

		public Long getCreated_by() {
			return Created_by;
		}

		public void setCreated_by(Long created_by) {
			Created_by = created_by;
		}

		public LocalDateTime getCreated_on() {
			return Created_on;
		}

		public void setCreated_on(LocalDateTime created_on) {
			Created_on = created_on;
		}

		public M_user getUser_modified() {
			return User_modified;
		}

		public void setUser_modified(M_user user_modified) {
			User_modified = user_modified;
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

		public M_user getUser_deleted() {
			return User_deleted;
		}

		public void setUser_deleted(M_user user_deleted) {
			User_deleted = user_deleted;
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

		public boolean isIs_delete() {
			return Is_delete;
		}

		public void setIs_delete(boolean is_delete) {
			Is_delete = is_delete;
		}
	
}
