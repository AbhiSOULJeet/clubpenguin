package com.abhijeet.web.models;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.abhijeet.web.dto.ClubDto;

@EnableJpaRepositories
public interface ClubRepository extends JpaRepository<Club, Long> {
	Optional<Club> findByTitle(String url);

	ClubDto save(ClubDto clubDto);
	
	void deleteById(Long clubId);
	
	@Query("Select c from Club c where c.title like CONCAT('%', :query, '%')")
	List<Club> searchClubs(String query);
}
