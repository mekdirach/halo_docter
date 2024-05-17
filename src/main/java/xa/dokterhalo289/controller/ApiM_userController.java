package xa.dokterhalo289.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import xa.dokterhalo289.models.M_role;
import xa.dokterhalo289.models.M_user;
import xa.dokterhalo289.models.T_doctor_treatment;
import xa.dokterhalo289.models.T_token;
import xa.dokterhalo289.repositories.M_biodataRepo;
import xa.dokterhalo289.repositories.M_userRepo;
import xa.dokterhalo289.repositories.RoleRepo;
import xa.dokterhalo289.repositories.T_tokenRepo;

@Controller
@RequestMapping(value = "user")
public class ApiM_userController {

	@Autowired
	M_userRepo userrepo;
	@Autowired
	T_tokenRepo tokenrepo;

	@Autowired
	M_biodataRepo biorepo;

	@GetMapping("/loginn")
	public ResponseEntity<List<M_user>> getAlluser() {
		try {
			List<M_user> users = this.userrepo.findAll();
			return new ResponseEntity<List<M_user>>(users, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<M_user>>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/catcekemail/{cek}")
	public ResponseEntity<List<M_user>> getCekById(@PathVariable("cek") String cek) {
		try {
			List<M_user> categorycek = userrepo.getEmail(cek);
			return new ResponseEntity<>(categorycek, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	@GetMapping("/cekemaillogin/{cek}")
	public ResponseEntity<List<M_user>> emailLogin(@PathVariable("cek") String cek) {
		try {
			List<M_user> categorycek = userrepo.getEmail(cek);
			return new ResponseEntity<>(categorycek, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	/*@GetMapping("/cekotpemail/{cek}")
	public ResponseEntity<List<M_user>> getOTp(@PathVariable("cek") String cek) {
		try {
			List<M_user> categorycek = userrepo.getEmail(cek);
			return new ResponseEntity<>(categorycek, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}*/

	@GetMapping("/cekpassword/{cek}")
	public ResponseEntity<List<M_user>> cekPass(@PathVariable("cek") String cek) {
		try {
			List<M_user> categorycek = userrepo.getPass(cek);
			return new ResponseEntity<>(categorycek, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	/*
	 * @PostMapping("/insertEmail") public ResponseEntity<M_user>
	 * insertCategory(@RequestBody M_user m_user, @ModelAttribute T_token t_token,
	 * BindingResult result) throws Exception {
	 * m_user.setModified_on(LocalDateTime.now());
	 * m_user.setCreated_on(LocalDateTime.now()); m_user.getEmail();
	 * this.userrepo.save(m_user);
	 * 
	 * String otp = getOTP(6);
	 * 
	 * t_token.setIs_delete(false); t_token.setCreate_by((long) 1);
	 * t_token.setCreate_on(LocalDateTime.now()); t_token.setToken(otp);
	 * t_token.setIs_delete(false);
	 * 
	 * this.tokenrepo.save(t_token);
	 * 
	 * return new ResponseEntity<M_user>(m_user, HttpStatus.OK);
	 * 
	 * }
	 */

	@PostMapping(value = "cekemail")
	public ModelAndView cekemail(@ModelAttribute M_user m_user, @ModelAttribute T_token t_token, BindingResult result,
			HttpSession sees, RedirectAttributes reg) throws Exception {
		String redirect = "";
		if (!result.hasErrors()) {

			String email = (String) result.getFieldValue("Email");
			List<M_user> getuser = this.userrepo.getEmail(email);

			try {
				sees.setAttribute("id", getuser.get(0).getId());
				reg.addFlashAttribute("message1", "Salah");
				redirect = "redirect:/daftaremail";
			} catch (Exception e) {
				m_user.setCreated_by((long) 1);
				m_user.setDeleted_by((long) 1);
				m_user.setModified_by((long) 1);
				LocalDateTime datetime = LocalDateTime.now();
				m_user.setCreated_on(datetime);
				this.userrepo.save(m_user);
				System.out.println("Sending...");
				String otp = getOTP(6);
				System.out.println(otp);
				sendmail(email, otp);
				System.out.println("Done!");

				t_token.setCreate_by((long) 1);
				t_token.setCreate_on(datetime);
				t_token.setToken(otp);
				t_token.setIs_delete(false);

				this.tokenrepo.save(t_token);
				reg.addFlashAttribute("messagekirimotp", "Cek Email Anda");
				redirect = "redirect:/konfirmasiotp";
			}
		}
		return new ModelAndView(redirect);

	}

	@PostMapping(value = "/cekemailulang/{email}")
	public ModelAndView cekemailulang(@ModelAttribute M_user m_user, @ModelAttribute T_token t_token,
			BindingResult result, HttpSession sees, RedirectAttributes reg, @PathVariable("email") String email)
			throws Exception {
		String redirect = "";
		if (!result.hasErrors()) {

			t_token.setEmail(email);
			List<M_user> getuser = this.userrepo.getEmail(email);

			try {
				sees.setAttribute("id", getuser.get(0).getId());
				LocalDateTime datetime = LocalDateTime.now();
				String otp = getOTP(6);
				System.out.println(otp);
				sendmail(email, otp);

				t_token.setCreate_by((long) 1);
				t_token.setCreate_on(datetime);
				t_token.setToken(otp);
				t_token.setIs_delete(false);

				this.tokenrepo.save(t_token);
				redirect = "redirect:/daftaremail";
			} catch (Exception e) {

				reg.addFlashAttribute("message", "Cek Email Anda");
				redirect = "redirect:/konfirmasiotp";
			}
		}
		return new ModelAndView(redirect);

	}

	@PostMapping(value = "ceklogin")
	ModelAndView ceklogin(@ModelAttribute M_user users, BindingResult result, HttpSession sess,
			RedirectAttributes reg) {
		String redirect = "";
		if (!result.hasErrors()) {
			String email = (String) result.getFieldValue("Email");
			String password = (String) result.getFieldValue("Password");

			List<M_user> getuser = this.userrepo.getLogin(email, password);

			try {
				sess.setAttribute("uid", getuser.get(0).getId());
				sess.setAttribute("email", getuser.get(0).getEmail());
				sess.setAttribute("created_by", getuser.get(0).getCreated_by());
				sess.setAttribute("created_on", getuser.get(0).getCreated_on());
				sess.setAttribute("is_delete", getuser.get(0).getDeleted_by());
				System.out.println("Access Granted!");
				reg.addFlashAttribute("Salah");
				redirect = "redirect:/home";

			} catch (Exception e) {
				reg.addFlashAttribute("message", "Salah");

				redirect = "redirect:/";
				System.out.println("Access Denied!");
			}
		}
		return new ModelAndView(redirect);
	}

	@PostMapping(value = "setpassword")
	public ModelAndView setpass(@ModelAttribute M_user user, BindingResult result) {
		if (!result.hasErrors()) {
			String password = result.getFieldValue("Password").toString();

			user.setCreated_by((long) 1);
			LocalDateTime datetime = LocalDateTime.now();
			user.setCreated_on(datetime);
			user.setPassword(password);
			this.userrepo.save(user);
		}
		return new ModelAndView("redirect:/daftarall");
	}

	@PostMapping("/setpass")
	public ResponseEntity<M_user> setpassword(@RequestBody M_user user, BindingResult result, HttpSession sess) {

		String password = (String) result.getFieldValue("password").toString();

		try {
			user.setCreated_by((long) 1);
			LocalDateTime datetime = LocalDateTime.now();
			user.setCreated_on(datetime);
			user.setPassword(password);
			this.userrepo.save(user);
			return new ResponseEntity<M_user>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@Autowired
	RoleRepo rolerepo;

	@GetMapping("/role")
	public ResponseEntity<List<M_role>> getRole() {
		try {
			java.util.List<M_role> buatjanji = this.rolerepo.findAll();
			return new ResponseEntity<List<M_role>>(buatjanji, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<M_role>>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/setdaftarakun")
	public ResponseEntity<M_user> setDaftar(@RequestBody M_user user, BindingResult result) {
		String email = result.getFieldValue("email").toString();
		String password = result.getFieldValue("password").toString();
		// String role = result.getFieldValue("role").toString();
		// String fullname = result.getFieldValue("fullname").toString();
		// String nomberhp = result.getFieldValue("phone").toString();

		try {
			LocalDateTime datetime = LocalDateTime.now();
			/*
			 * m_biodata.setCreated_by((long) 1);
			 * 
			 * m_biodata.setCreated_on(datetime); m_biodata.setFullname(fullname);
			 * m_biodata.setMobile_phone(nomberhp); this.biorepo.save(m_biodata);
			 */

			user.setCreated_by((long) 1);
			user.setCreated_on(datetime);
			user.setEmail(email);
			user.setPassword(password);
			// user.setId(Long.parseLong(role));
			this.userrepo.save(user);
			return new ResponseEntity<M_user>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/daftarall")
	public ResponseEntity<M_user> kirimEmail(@RequestBody M_user user) {

		try {

			LocalDateTime datetime = LocalDateTime.now();

			user.setCreated_by((long) 1);
			user.setCreated_on(datetime);
			this.userrepo.save(user);
			return new ResponseEntity<M_user>(user, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	/*
	 * @PostMapping(value = "setdaftar") public ModelAndView
	 * setdaftar(@ModelAttribute M_user m_user, @ModelAttribute M_biodata m_biodata,
	 * BindingResult result) { if (!result.hasErrors()) { String fullname =
	 * result.getFieldValue("Fullname").toString(); String nomberhp =
	 * result.getFieldValue("phone").toString();
	 * 
	 * m_biodata.setCreated_by((long) 1); LocalDateTime datetime =
	 * LocalDateTime.now(); m_biodata.setCreated_on(datetime);
	 * m_biodata.setFullname(fullname); m_biodata.setMobile_phone(nomberhp);
	 * this.biorepo.save(m_biodata);
	 * 
	 * String role = result.getFieldValue("role").toString(); // yang bikin error
	 * dude m_user.setCreated_by((long) 1); m_user.setCreated_on(datetime);
	 * m_user.setId(Long.parseLong(role)); ((M_biodata)
	 * this.userrepo).setId(Long.parseLong(role)); this.userrepo.save(m_user);
	 * 
	 * } return new ModelAndView("redirect:/transaksi/index"); }
	 */

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
