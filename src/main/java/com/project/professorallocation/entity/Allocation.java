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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

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
	@Column(nullable = false)
	private DayOfWeek dayOfWeek;

	@ApiModelProperty(example = "HH:mmZ")
	@JsonFormat(pattern = "HH:mmZ")
	@Temporal(value = TemporalType.TIME)
	@Column(nullable = false)
	private Date start;

	@ApiModelProperty(example = "HH:mmZ")
	@JsonFormat(pattern = "HH:mmZ")
	@Temporal(value = TemporalType.TIME)
	@Column(nullable = false)
	private Date end;

	@JsonIgnoreProperties({"allocations"})
	@ManyToOne(optional = false)
	private Professor professor;

	@JsonIgnoreProperties({"allocations"})
	@ManyToOne(optional = false)
	private Course course;

}
