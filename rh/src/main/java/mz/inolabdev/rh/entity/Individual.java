package mz.inolabdev.rh.entity;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import mz.inolabdev.rh.util.DateFormat;

import org.hibernate.annotations.Where;

@Entity
@Table(name = "individuals")
public class Individual extends IdEntity {

	private static final long serialVersionUID = 1L;
	@Column(name = "name")
	private String name;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "birthday")
	private Date birthday;

	@OneToOne
	@JoinColumn(name = "Individual_id")
	private IdentityDocumentType type;

	@Column(name = "location_id")
	private Location location;

	@Column(name = "id_number")
	private String idNumber;

	@Column(name = "gender")
	private String gender;

	@Column(name = "nationality")
	private String nationality;

	@Column(name = "academic_level")
	private String academicLevel;

	@Column(name = "marital_status")
	private String maritalStatus;

	@Column(name = "number_children")
	private int numberChildren;

	@OneToOne
	@JoinColumn(name = "individual_type_id")
	private IndividualType individualType;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "holder_id", insertable = false, updatable = false)
	@Where(clause = "cp_type='email'")
	private Set<Email> emails;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "holder_id", insertable = false, updatable = false)
	private Set<Document> documents;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "holder_id", insertable = false, updatable = false)
	@Where(clause = "cp_type='cell_phone'")
	private Set<Cellphone> cellPhones;

	public String fullName() {

		return name + " " + middleName + " " + lastName;
	}

	public String dateFormat() {

		return DateFormat.formated(this.birthday);
	}

	public Integer age() {

		int currentYear = Calendar.getInstance().get(Calendar.YEAR);

		Calendar cal = Calendar.getInstance();
		cal.setTime(birthday);

		int brdY = cal.get(Calendar.YEAR);

		return currentYear - brdY;
	}

	// getter and setters
	public IndividualType getIndividualType() {
		return individualType;
	}

	public void setIndividualType(IndividualType individualType) {
		this.individualType = individualType;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public int getNumberChildren() {
		return numberChildren;
	}

	public void setNumberChildren(int numberChildren) {
		this.numberChildren = numberChildren;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
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

	public String getAcademicLevel() {
		return academicLevel;
	}

	public void setAcademicLevel(String academicLevel) {
		this.academicLevel = academicLevel;
	}

	public Set<Email> getEmails() {
		return emails;
	}

	public void setEmails(Set<Email> emails) {
		this.emails = emails;
	}

	public Set<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
	}

	public Set<Cellphone> getCellPhones() {
		return cellPhones;
	}

	public void setCellPhones(Set<Cellphone> cellPhones) {
		this.cellPhones = cellPhones;
	}

	public IdentityDocumentType getType() {
		return type;
	}

	public void setType(IdentityDocumentType type) {
		this.type = type;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}
