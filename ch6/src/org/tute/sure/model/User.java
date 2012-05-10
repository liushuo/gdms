package org.tute.sure.model;
// default package

import java.sql.Blob;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="users" ,catalog="ch6")

public class User  implements java.io.Serializable {
	private static final long serialVersionUID = -8388099638856431640L;

    // Fields    
     private Integer id;
     private Department department;
     private String name;
     private String password;
     private Date birthday;
     private Double income;
     private Boolean gender;
     private Blob photo;


    // Constructors

    /** default constructor */
    public User() {
    }

	/** minimal constructor */
    public User(Department department) {
        this.department = department;
    }
    
    /** full constructor */
    public User(Department department, String name, String password, Date birthday, Double income, Boolean gender, Blob photo) {
        this.department = department;
        this.name = name;
        this.password = password;
        this.birthday = birthday;
        this.income = income;
        this.gender = gender;
        this.photo = photo;
    }

   
    // Property accessors
    @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="id", unique=true, nullable=false)

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="departmentId", nullable=false)

    public Department getDepartment() {
        return this.department;
    }
    
    public void setDepartment(Department department) {
        this.department = department;
    }
    
    @Column(name="name", length=20)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="password", length=20)

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="birthday", length=10)

    public Date getBirthday() {
        return this.birthday;
    }
    
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    @Column(name="income", precision=22, scale=0)

    public Double getIncome() {
        return this.income;
    }
    
    public void setIncome(Double income) {
        this.income = income;
    }
    
    @Column(name="gender")

    public Boolean getGender() {
        return this.gender;
    }
    
    public void setGender(Boolean gender) {
        this.gender = gender;
    }
    
    @Column(name="photo")

    public Blob getPhoto() {
        return this.photo;
    }
    
    public void setPhoto(Blob photo) {
        this.photo = photo;
    }
   








}