package com.proj.java.onlineexaminationsystem.controller;

import java.util.List;

import com.proj.java.onlineexaminationsystem.entity.Quiz;
import com.proj.java.onlineexaminationsystem.service.QuizService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/quiz")
public class QuizController {

	@Autowired
	private QuizService quizService;
	@GetMapping("/{id}")
	public String getQuiz(@PathVariable int id, ModelMap quizModel) {
		Quiz quiz = quizService.getQuiz(id);
		quizModel.addAttribute("quiz", quiz);
		quizModel.addAttribute("questions",quiz.getQuestions());
		return "quiz/home_page";
	}
	@GetMapping("/addQuiz")
	public String addPage(HttpServletRequest request, ModelMap quizModel) {
		HttpSession session = request.getSession();
		if(!session.isNew() && session.getAttribute("role").equals("teacher")){
			Quiz quiz = new Quiz();
			quizModel.addAttribute("quiz", quiz);
			return "quiz/update_form";
		}
		return "redirect:/";
	}
	@GetMapping("/update/{id}")
	public String updatePage(@PathVariable("id") int id, ModelMap quizModel) {
		quizModel.addAttribute("id", id);
		Quiz quiz = quizService.getQuiz(id);
		quizService.deleteQuiz(quiz.getQuiz_id());
		quizModel.addAttribute("quiz", quiz);
		return "quiz/update_form";
	}

	@PostMapping("/update")
	public String updateQuiz(@ModelAttribute("quiz") Quiz quiz, HttpServletRequest request, ModelMap quizModel) {
		HttpSession session = request.getSession();
		if(!session.isNew() && session.getAttribute("role").equals("teacher")) {
			quizService.addQuiz(quiz);
			List<Quiz> quizs = quizService.getQuizs();
			quizModel.addAttribute("quizs", quizs);
			quizModel.addAttribute("msg", "Quiz updated successfully");
		}
		return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String deleteQuiz(@PathVariable int id, HttpServletRequest request, ModelMap quizModel) {
		HttpSession session = request.getSession();
		if(!session.isNew() && session.getAttribute("role").equals("teacher")){
			quizService.deleteQuiz(id);
			List<Quiz> quizs = quizService.getQuizs();
			quizModel.addAttribute("quizs", quizs);
			quizModel.addAttribute("msg", "Quiz deleted successfully");
		}return "redirect:/";
	}

}
