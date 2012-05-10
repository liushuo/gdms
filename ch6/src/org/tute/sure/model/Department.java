package org.tute.sure.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * Department entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="department" ,catalog="ch6")

public class Department  implements java.io.Serializable {
	private static final long serialVersionUID = 8861320857491321484L;
	// Fields
     private Integer id;
     private String name;
     private String telephone;
     private List<User> users = new LinkedList<User>();


    // Constructors

    /** default constructor */
    public Department() {
    }

	/** minimal constructor */
    public Department(String name) {
        this.name = name;
    }
    
    /** full constructor */
    public Department(String name, String telephone, List<User> users) {
        this.name = name;
        this.telephone = telephone;
        this.users = users;
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
    
    @Column(name="name", nullable=false, length=30)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="telephone", length=20)

    public String getTelephone() {
        return this.telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="department")

    public List<User> getUsers() {
        return this.users;
    }
    
    public void setUsers(List<User> users) {
        this.users = users;
    }
   








}