package com.abhijeet.web.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.abhijeet.web.dto.ClubDto;
import com.abhijeet.web.models.Club;
import com.abhijeet.web.models.ClubRepository;
import com.abhijeet.web.models.UserEntity;
import com.abhijeet.web.security.SecurityUtil;
import com.abhijeet.web.service.ClubService;
import com.abhijeet.web.service.UserRepository;
import com.abhijeet.web.mappers.*;

@Service
public class ClubServiceImpl implements ClubService {

	private ClubRepository clubRepository;
	private UserRepository userRepository;

	public ClubServiceImpl(ClubRepository clubRepository, UserRepository userRepository) {
		this.clubRepository = clubRepository;
		this.userRepository = userRepository;
	}

	@Override
	public List<ClubDto> findAllClubs() {
		List<Club> clubs = clubRepository.findAll();
		return clubs.stream().map((club) -> ClubMapper.mapToClubDto(club)).collect(Collectors.toList());
	}

	@Override
	public List<ClubDto> searchClubs(String query) {
		List<Club> clubs = clubRepository.searchClubs(query);
		return clubs.stream().map((club) -> ClubMapper.mapToClubDto(club)).collect(Collectors.toList());
	}

	public Club saveClub(ClubDto clubDto) {
		String username = SecurityUtil.getSessionuser();
		UserEntity user = userRepository.findByEmail(username);
		Club club = ClubMapper.mapToClub(clubDto);
		club.setCreatedBy(user);
		return clubRepository.save(club);
	}

	public void deleteById(Long clubId) {
		clubRepository.deleteById(clubId);
	}

	public void updateClub(ClubDto clubDto) {
		String username = SecurityUtil.getSessionuser();
		UserEntity user = userRepository.findByEmail(username);
		Club club = ClubMapper.mapToClub(clubDto);
		club.setCreatedBy(user);
		clubRepository.save(club);
	}

	public ClubDto findClubById(long clubId) {
		Club club = clubRepository.findById(clubId).get();
		return ClubMapper.mapToClubDto(club);
	}

}
