package next.controller;

import next.dao.QuestionDao;
import next.model.Question;
import next.model.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	private QuestionDao questionDao = QuestionDao.getInstance();

	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public ModelAndView home(User user) throws Exception {
//	public ModelAndView home(Model model) throws Exception {
//	public ModelAndView home(Question question, User loginUser,  Model model) throws Exception {
	public ModelAndView home(@LoginUser User loginUser,  Model model) throws Exception {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("questions", questionDao.findAll());
		return mav;
	}
}
