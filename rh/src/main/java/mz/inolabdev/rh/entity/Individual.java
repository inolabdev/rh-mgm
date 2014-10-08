package mz.inolabdev.rh.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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

	@Column(name = "identity_document_type")
	private String identityDocumentType;

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
	@JoinColumn(name = "type_id")
	private IndividualType individualType;

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

	public String getIdentityDocumentType() {
		return identityDocumentType;
	}

	public void setIdentityDocumentType(String identityDocumentType) {
		this.identityDocumentType = identityDocumentType;
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
}
