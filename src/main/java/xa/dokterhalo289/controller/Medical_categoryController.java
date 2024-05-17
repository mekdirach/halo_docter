package xa.dokterhalo289.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/category/")
public class Medical_categoryController {
	@GetMapping(value="index")
	public ModelAndView index() {
		ModelAndView view = new ModelAndView("/medical_category/index");
		return view;
	}
}
