package com.tenco.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {
	private int id;
	private int userId;
	private String title;
	private String description;
	private Date dueDate;
	private boolean completed;
}
