package com.project.professorallocation.entity;

import java.time.DayOfWeek;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "allocation")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Allocation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(value = EnumType.STRING)
	@Column(nullable = false, unique = false)
	private DayOfWeek dayOfWeek;
	
	@Temporal(value = TemporalType.TIME)
	@Column(nullable = false, unique = false)
	private Date start;
	
	@Temporal(value = TemporalType.TIME)
	@Column(nullable = false, unique = false)
	private Date end;
	
	@ManyToOne(optional = false)
	private Professor professor;
	
	@ManyToOne(optional = false)
	private Course course;

}
