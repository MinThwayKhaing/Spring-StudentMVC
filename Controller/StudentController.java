package Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import DAO.CourseStudentDAO;
import DAO.CourseDAO;
import DAO.StudentDAO;
import DAO.UserDAO;
import DTO.CourseRequestDTO;
import DTO.CourseResponseDTO;
import DTO.CourseStudentRequestDTO;
import DTO.CourseStudentResponseDTO;
import DTO.StudentRequestDTO;
import DTO.StudentResponseDTO;
import DTO.UserRequestDTO;
import DTO.UserResponseDTO;
import Model.StudentBean;
import Model.UserBean;

@Controller
public class StudentController 
{

	@Autowired
	private CourseStudentDAO CourseStudentDAO;
	@Autowired
	private CourseDAO CourseDAO;
	@Autowired
	private StudentDAO StudentDAO;
	
	@RequestMapping(value="/studentaddpage",method=RequestMethod.GET)
	public ModelAndView studentaddpage(ModelMap model)
	{
		CourseDAO.selectAll();
		  List<CourseResponseDTO> courseList = CourseDAO.selectAll();            
          model.addAttribute("courseList", courseList);
  		return new ModelAndView("STU001", "studentbean", new StudentBean());
	}
	 @RequestMapping(value = "/StudentAddPage", method = RequestMethod.POST)
		public String addStu(@ModelAttribute("studentbean") StudentBean studentbean, ModelMap model) {
//insert  
		 List<String> attendArray = studentbean.getCourse();
		 if (studentbean.getStudentname().isBlank() || studentbean.getDob().isBlank() || studentbean.getGender().isBlank()||studentbean.getPhone().isBlank() ||studentbean.getEducation().isBlank() ||studentbean.getStudentid().isBlank()) 
		 {
				return "STU001";
			}else {
				StudentDAO dao = new StudentDAO();
				StudentResponseDTO res = new StudentResponseDTO();
				StudentRequestDTO dto = new StudentRequestDTO();
				DAO.CourseStudentDAO csdao = new DAO.CourseStudentDAO();
				CourseStudentRequestDTO csdto = new CourseStudentRequestDTO();

				for(String a : attendArray ) {
					csdto.setStuid(studentbean.getStudentid());
					csdto.setCoursename(a);
					csdao.insertCourseStudnetData(csdto);
				}
				dto.setStudentid(studentbean.getStudentid());
				dto.setStudentname(studentbean.getStudentname());
				dto.setDob(studentbean.getDob());
				dto.setGender(studentbean.getGender());
				dto.setPhone(studentbean.getPhone());
				dto.setEducation(studentbean.getEducation());
				dao.insertData(dto);
				
						
				return "redirect:/studentsearchpage";
				
			}
		}
	 //show&search
	 @RequestMapping(value = "/studentsearchpage", method = RequestMethod.GET)
		public ModelAndView stuSearchPage(ModelMap model) 
	 	{
		 
		 	StudentDAO dao = new StudentDAO();
			CourseStudentDAO csdao = new CourseStudentDAO();
			List<StudentResponseDTO> list = dao.selectAll();
			for(StudentResponseDTO a : list) {
				List<String> courselist = csdao.selectOne(a.getStudentid());
				a.setCourse(courselist);   
			}
			model.addAttribute("studentList", list);
			return new ModelAndView("STU003", "studentbean", new StudentBean());
		}
	
	 
	 @RequestMapping(value = "/StudentSearchPage", method = RequestMethod.POST)
		public ModelAndView searchStudent(@ModelAttribute("studentbean") StudentBean studentbean, ModelMap model) 
	 {
		 
		 StudentRequestDTO req = new StudentRequestDTO();
			req.setStudentid(studentbean.getSearchid());
			req.setStudentname(studentbean.getSearchname());

			CourseStudentDAO csdao = new CourseStudentDAO();
			StudentDAO dao = new StudentDAO();
			if(dao.search(req).isEmpty()) {
			
				return new ModelAndView("STU003", "studentbean", new StudentBean());
			}
			else {
				List<StudentResponseDTO> list = dao.search(req);
				for(StudentResponseDTO a : list) {
					List<String> courselist = csdao.selectOne(a.getStudentid());
					a.setCourse(courselist);   
				}
				model.addAttribute("studentList", list);
			
			List<StudentResponseDTO> dto=dao.search(req);
			
			return new ModelAndView("STU003", "studentbean", new StudentBean());
			}
		}
	 
	 //Student Update
	 @RequestMapping(value="/studentupdatedpage/{studentid}" ,method=RequestMethod.GET)
		public ModelAndView studentupdatepage(@PathVariable String studentid,ModelMap model)
		{
			StudentRequestDTO dto=new StudentRequestDTO();
			dto.setStudentid(studentid);
			CourseDAO.selectAll();
			  List<CourseResponseDTO> courseList = CourseDAO.selectAll();            
	          model.addAttribute("courseList", courseList);
			return new ModelAndView("STU002","studentbean",StudentDAO.selectOne(dto));
		}
	 @RequestMapping(value = "/studentupdatedpage/UpdateStudentUpdatePage" , method = RequestMethod.POST)
	 public String updateStu(@ModelAttribute("studentbean")StudentBean studentbean,ModelMap model) {
		 List<String> attendArray = studentbean.getCourse();
			
			
	
			if (studentbean.getStudentname().isBlank() ) {
				model.addAttribute("errorFill", "Fill the Blank!!!");
				model.addAttribute("studentbean", studentbean);
				return "USR003";
			} else {
				StudentResponseDTO res = new StudentResponseDTO();
				StudentRequestDTO dto = new StudentRequestDTO();
				StudentDAO dao=new StudentDAO();
				CourseStudentDAO csdao = new CourseStudentDAO();
				CourseStudentRequestDTO csdto = new CourseStudentRequestDTO();
				
				csdto.setStuid(studentbean.getStudentid());
				csdao.deleteData(csdto);
				
				for(String a : attendArray ) {
					csdto.setStuid(studentbean.getStudentid());
					csdto.setCoursename(a);
					csdao.insertCourseStudnetData(csdto);
				}
				dto.setStudentid(studentbean.getStudentid());
				dto.setStudentname(studentbean.getStudentname());
				dto.setDob(studentbean.getDob());
				dto.setGender(studentbean.getGender());
				dto.setPhone(studentbean.getPhone());
				dto.setEducation(studentbean.getEducation());
				dao.updateData(dto);
				
				
				return "redirect:/studentsearchpage";

			}
	 }
	 @RequestMapping(value = "/DeleteStudent/{studentid}", method = RequestMethod.GET)
		public String deleteStu(@PathVariable String studentid, ModelMap model) {
		 StudentDAO dao=new StudentDAO();
		 StudentRequestDTO dto = new StudentRequestDTO();
			CourseStudentDAO csdao = new CourseStudentDAO(); 
			CourseStudentRequestDTO csdto = new CourseStudentRequestDTO();
		 dto.setStudentid(studentid);
			csdto.setStuid(studentid);
			dao.deleteData(dto);
			csdao.deleteData(csdto);
				model.addAttribute("errorFill", "Success delete");
				
				return "redirect:/stuSearchPage";
		}
}
