package xa.dokterhalo289.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import xa.dokterhalo289.models.M_medical_facility_category;
import xa.dokterhalo289.models.M_user;
import xa.dokterhalo289.models.T_token;
import xa.dokterhalo289.repositories.T_tokenRepo;

@RestController
@CrossOrigin("*")
@Controller
@RequestMapping(value = "/otp/")
public class T_tokenController {

	@Autowired
	T_tokenRepo tokenrepo;

	@GetMapping("/cekToken/{cek}")
	public ResponseEntity<List<T_token>> insertOtp(@PathVariable("cek") String cek) {
		try {
			List<T_token> cektoken = tokenrepo.getOtp(cek);
			return new ResponseEntity<>(cektoken, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping("/kirimemail")
	public ResponseEntity<T_token> kirimEmail(@RequestBody T_token t_token, BindingResult result,RedirectAttributes reg) {
		try {
			String email = (String) result.getFieldValue("email");
			String otp = getOTP(6);
			System.out.println(otp);
			sendmail(email, otp);
			System.out.println("Done!");

			LocalDateTime datetime = LocalDateTime.now();
			t_token.setCreate_by((long) 1);
			t_token.setCreate_on(datetime);
			t_token.setToken(otp);
			t_token.setIs_delete(false);

			this.tokenrepo.save(t_token);
			reg.addFlashAttribute("message", "OTP Terkirim Ke Email");
			return new ResponseEntity<T_token>(t_token, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping("/cekotp")
	public ResponseEntity<T_token> verifikasi(@RequestBody T_token t_token, BindingResult result,HttpSession sess) {
		
		String token = (String) result.getFieldValue("token");

		List<T_token> getotp = this.tokenrepo.getOtp(token);
		try {
			sess.setAttribute("uid", getotp.get(0).getId());
			return new ResponseEntity<T_token>(t_token, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping(value = "konfirotp")
	ModelAndView cekotp(@ModelAttribute T_token t_token, BindingResult result, HttpSession sess,
			RedirectAttributes reg) {
		String redirect = "";
		String token = (String) result.getFieldValue("token");

		List<T_token> getotp = this.tokenrepo.getOtp(token);

		try {
			sess.setAttribute("uid", getotp.get(0).getId());

			System.out.println("Access Granted!");
			reg.addFlashAttribute("Benarr");
			redirect = "redirect:/password";

		} catch (Exception e) {
			reg.addFlashAttribute("message", "CeK Lagi");

			redirect = "redirect:/konfirmasiotp";
			System.out.println("Access Denied!");
		}

		return new ModelAndView(redirect);
	}

	@PutMapping("kirimulang/{id}/{email}")
	public ResponseEntity<List<T_token>> putOTP(@RequestBody T_token token, @PathVariable Long id,
			@PathVariable String email) {
		try {
			token.setId(id);
			token.setEmail(email);
			List<T_token> ulangtoken = tokenrepo.getUlangOtp(email, token);
			String otp = getOTP(6);
			sendmail(email, otp);
			token.setToken(otp);
			this.tokenrepo.save(token);
			return new ResponseEntity<>(ulangtoken, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	static String getOTP(int n) {
		String getOTP = "0123456789";

		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {
			int index = (int) (getOTP.length() * Math.random());

			sb.append(getOTP.charAt(index));
		}
		return sb.toString();
	}

	@Autowired
	private JavaMailSender javamailsender;

	public void sendmail(String emailto, String otp) throws Exception {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom("rachmadilobda@gmail.com");
		msg.setTo(emailto);
		msg.setSubject("Hallo " + emailto + " Kamu telah berhasil mendaftar");
		msg.setText("OTPnya ini  = " + otp);
		javamailsender.send(msg);
	}
}
