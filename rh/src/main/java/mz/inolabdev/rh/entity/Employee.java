package mz.inolabdev.rh.entity;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employees")
public class Employee extends Individual {

	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "jobPosition_id")
	private JobPosition jobPosition;

	@ManyToMany(mappedBy = "hiringManagers")
	private Set<Vacancy> vacancies = new HashSet<Vacancy>();

	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	@NotNull
	private User userLogin;

	public Department getDepartment() {
		return department;
	}

	public String since() {

		Locale ptBr = new Locale("pt", "BR");
		
		SimpleDateFormat sdf = new SimpleDateFormat("MMM, yyyy", ptBr);
		
		return sdf.format(getCreated());
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setJobPosition(JobPosition jobPosition) {
		this.jobPosition = jobPosition;
	}

	public JobPosition getJobPosition() {
		return jobPosition;
	}

	public void setJob_position(JobPosition jobPosition) {
		this.jobPosition = jobPosition;
	}

	public Set<Vacancy> getVacancies() {
		return vacancies;
	}

	public void setVacancies(Set<Vacancy> vacancies) {
		this.vacancies = vacancies;
	}

	public User getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(User userLogin) {
		this.userLogin = userLogin;
	}

	@Override
	public String toString() {
		return fullName();
	}

}
