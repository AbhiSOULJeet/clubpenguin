package com.abhijeet.web.service;

import java.util.List;

import com.abhijeet.web.dto.ClubDto;
import com.abhijeet.web.models.Club;

public interface ClubService {
	List<ClubDto> findAllClubs();

	Club saveClub(ClubDto clubDto);
	
	void updateClub(ClubDto clubDto);
	
	void deleteById(Long clubId);

	ClubDto findClubById(long clubId);
	
	List<ClubDto> searchClubs(String query);
}
