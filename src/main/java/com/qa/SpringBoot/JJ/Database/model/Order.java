package com.qa.SpringBoot.JJ.Database.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long personid;
	
	@NotNull
	private String title;
	
	@NotNull
	@Lob
	private String des;
	
	@ManyToOne(fetch = FetchType.LAZY, optional =false)
	@JoinColumn(name = "person_id",nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private SpringBootDataModel person;

	public long getId() {
		return personid;
	}

	public void setId(long id) {
		this.personid = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public SpringBootDataModel getPerson() {
		return person;
	}

	public void setPerson(SpringBootDataModel person) {
		this.person = person;
	}
}
