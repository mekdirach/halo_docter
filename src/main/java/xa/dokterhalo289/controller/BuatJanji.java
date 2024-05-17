package xa.dokterhalo289.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import xa.dokterhalo289.models.T_appointment;
import xa.dokterhalo289.repositories.BuatJanjiRepo;

@Controller
@RequestMapping(value="/buat/")
public class BuatJanji {
	@GetMapping("janji")
	public ModelAndView form() {
		ModelAndView formjanji = new ModelAndView("/buatjanji/buat_janji");
		T_appointment appointment= new T_appointment();
		formjanji.addObject("appointment", appointment);
		return formjanji;
	}
}
