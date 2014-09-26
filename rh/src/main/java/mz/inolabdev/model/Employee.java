package mz.inolabdev.model;

import java.util.Date;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "employees")
public class Employee extends IdEntity {

	private static final long serialVersionUID = 1L;
	private String name;
	private String last_name;
	private String middle_names;
	private Date birthday;
	private String identity_document_type;
	private String id_number;
	private String gender;
	private String nationality;
	private String academic_level;
	private String marital_status;
	private int number_children;
	private JobPosition job_position;
	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;
	@OneToMany(mappedBy = "Employee", fetch = FetchType.LAZY)
	private List<Task> tasks;
	@OneToMany(mappedBy = "Employee", fetch = FetchType.LAZY)
	private List<Document> documents;
	private List<Cellphone> cellphones;
	private Date created_at;
	private Date updated_at;

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getMiddle_names() {
		return middle_names;
	}

	public void setMiddle_names(String middle_names) {
		this.middle_names = middle_names;
	}

	public String getIdentity_document_type() {
		return identity_document_type;
	}

	public void setIdentity_document_type(String identity_document_type) {
		this.identity_document_type = identity_document_type;
	}

	public String getId_number() {
		return id_number;
	}

	public void setId_number(String id_number) {
		this.id_number = id_number;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getAcademic_level() {
		return academic_level;
	}

	public void setAcademic_level(String academic_level) {
		this.academic_level = academic_level;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public JobPosition getJob_position() {
		return job_position;
	}

	public void setJob_position(JobPosition job_position) {
		this.job_position = job_position;
	}

	public List<Cellphone> getCellphones() {
		return cellphones;
	}

	public void setCellphones(List<Cellphone> cellphones) {
		this.cellphones = cellphones;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public String getMarital_status() {
		return marital_status;
	}

	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}

	public int getNumber_children() {
		return number_children;
	}

	public void setNumber_children(int number_children) {
		this.number_children = number_children;
	}

}
