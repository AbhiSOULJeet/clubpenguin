package com.abhijeet.web.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.abhijeet.web.dto.ClubDto;
import com.abhijeet.web.models.Club;
import com.abhijeet.web.models.UserEntity;
import com.abhijeet.web.security.SecurityUtil;
import com.abhijeet.web.service.ClubService;
import com.abhijeet.web.service.UserRepository;

import jakarta.validation.Valid;

@Controller
public class ClubController {

	private ClubService clubService;
	private UserRepository userRepository;

	public ClubController(ClubService clubService, UserRepository userRepository) {
		this.clubService = clubService;
		this.userRepository = userRepository;
	}

	@GetMapping("/clubs")
	public String listClubs(Model model) {
		List<ClubDto> clubs = clubService.findAllClubs();
		model.addAttribute("clubs", clubs);

		return "index";
	}

	@GetMapping("/clubs/new")
	public String createClubForm(Model model) {
		Club club = new Club();
		model.addAttribute("club", club);

		return "club-create";
	}

	@GetMapping("/clubs/{clubId}/events")
	public String showDetails(@PathVariable Long clubId, Model model) {

		ClubDto clubDto = clubService.findClubById(clubId);
		model.addAttribute("club", clubDto);
		return "club-detail";
	}

	@GetMapping("/clubs/{clubId}/delete")
	public String deleteClub(@PathVariable Long clubId) {
		clubService.deleteById(clubId);
		return "redirect:/clubs";
	}

	@GetMapping("/clubs/search")
	public String searchClubs(@RequestParam String query, Model model) {
		List<ClubDto> clubs = clubService.searchClubs(query);
		model.addAttribute("clubs", clubs);
		return "index";

	}

	@PostMapping("/clubs/new")
	public String saveClub(@Valid @ModelAttribute("club") ClubDto clubDto, BindingResult result, Model model) {
		String username = SecurityUtil.getSessionuser();
		UserEntity user = userRepository.findByEmail(username);
		clubDto.setCreatedBy(user);
		if (result.hasErrors()) {
			model.addAttribute("club", clubDto);
			return "club-create";
		}

		clubService.saveClub(clubDto);
		return "redirect:/clubs";
	}

	@GetMapping("/clubs/{clubId}/edit")
	public String editClubForm(@PathVariable Long clubId, Model model) {
		ClubDto club = clubService.findClubById(clubId);
		model.addAttribute("club", club);

		return "club-edit";
	}

	@PostMapping("/clubs/{clubId}/edit")
	public String updateClub(@Valid @PathVariable @ModelAttribute("club") Long clubId, Model model, ClubDto club,
			BindingResult result) {

		if (result.hasErrors()) {
			model.addAttribute("club", club);
			return "club-edit";
		}
		club.setId(clubId);
		clubService.saveClub(club);

		return "redirect:/clubs";
	}

}
