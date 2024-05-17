 package xa.dokterhalo289.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/")
public class HomeController {
	@GetMapping (value="/home")
	public ModelAndView homr() {
		ModelAndView view = new ModelAndView("/home");
				return view;
	}
	
	@GetMapping (value="/")
	public ModelAndView index() {
		ModelAndView view = new ModelAndView("/index");
				return view;
	}
	
	@GetMapping (value="/login")
	public ModelAndView loginall() {
		ModelAndView view = new ModelAndView("/loginform");
				return view;
	}
	@GetMapping (value="/daftaremail")
	public ModelAndView daftar() {
		ModelAndView view = new ModelAndView("/daftar");
				return view;
	}
	
	@GetMapping (value="/konfirmasiotp")
	public ModelAndView konfotp() {
		ModelAndView view = new ModelAndView("/konfirmasiotp");
				return view;
	}
	
	@GetMapping (value="/password")
	public ModelAndView pass() {
		ModelAndView view = new ModelAndView("/password");
				return view;
	}
	
	@GetMapping (value="/daftarall")
	public ModelAndView daftarall() {
		ModelAndView view = new ModelAndView("/daftarall");
				return view;
	}
}
