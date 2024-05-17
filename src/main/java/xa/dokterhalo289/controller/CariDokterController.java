package xa.dokterhalo289.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@CrossOrigin("*")
@RequestMapping(value="/cari_dokter")

public class CariDokterController {
	@GetMapping(value="/hasil_cari_dokter")
	public ModelAndView hasilCari() {
		ModelAndView view = new ModelAndView("/cari_dokter/hasil_cari_dokter");
		return view;
	}
	
	@RequestMapping("/caridokter")
	public String cari_dokter() {
		return "cari_dokter/cari_dokter";
	}

}
