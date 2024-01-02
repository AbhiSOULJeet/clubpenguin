package com.abhijeet.web.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.abhijeet.web.models.Event;
import com.abhijeet.web.models.UserEntity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class ClubDto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "club title cannot be empty")
	private String title;
	@NotEmpty(message = "club photo cannot be empty")
	private String photoUrl;
	@NotEmpty(message = "club content cannot be empty")
	private String content;
	private UserEntity createdBy;
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;
	private List<EventDto> events;

}
