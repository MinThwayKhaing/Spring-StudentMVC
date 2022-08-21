package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import DTO.CourseRequestDTO;
import DTO.CourseResponseDTO;
import DTO.StudentRequestDTO;
import DTO.StudentResponseDTO;
import DTO.UserRequestDTO;
import DTO.UserResponseDTO;
@Service("StudentDAO")
public class StudentDAO 
{
	public static Connection con=null;
	static
	{
		con=MyConnection.getConnection();
	}
	public int  insertData(StudentRequestDTO dto)
	{
		int result=0;
		String sql="insert into studenttable(studentid,studentname,dob,gender,phone,education)"+
					"values(?,?,?,?,?,?)";
		try 
		{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString (1,dto.getStudentid());
			ps.setString (2,dto.getStudentname());
			ps.setString (3,dto.getDob());
			ps.setString (4,dto.getGender());
			ps.setString (5,dto.getPhone());
			ps.setString (6,dto.getEducation());
		
			
			result=ps.executeUpdate();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return result;
	}
	public ArrayList<StudentResponseDTO>selectAll()
	{
		ArrayList<StudentResponseDTO>list=new ArrayList();
		String sql="select * from studenttable";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				StudentResponseDTO res=new StudentResponseDTO();
				
				res.setStudentid(rs.getString("studentid"));
				res.setStudentname(rs.getString("studentname"));
				res.setDob(rs.getString("dob"));
				res.setGender(rs.getString("gender"));
				res.setPhone(rs.getString("phone"));
				res.setEducation(rs.getString("education"));
	
				list.add(res);
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public int  updateData(StudentRequestDTO dto)
	{
		int result=0;
		String sql="update studenttable set studentname=?,dob=?,gender=?,phone=?,education=?"+"where studentid=?";
		try 
		{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString (7,dto.getStudentid());
			ps.setString (1,dto.getStudentname());
			ps.setString (2,dto.getDob());
			ps.setString (3,dto.getGender());
			ps.setString (4,dto.getPhone());
			ps.setString (5,dto.getEducation());
			
			
			
			result=ps.executeUpdate();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return result;
	}
	public int  deleteData(StudentRequestDTO dto)
	{
		int result=0;
		String sql="delete from studenttable where studentid=?";
		try 
		{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString (1,dto.getStudentid());
			result=ps.executeUpdate();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return result;
	}
	public   StudentResponseDTO selectOne(StudentRequestDTO dto)
	{
		
		StudentResponseDTO res=new StudentResponseDTO();
		String sql="select * from studenttable where studentid=?";
		try 
		{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,dto.getStudentid());
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				
				
				res.setStudentid(rs.getString("studentid"));
				res.setStudentname(rs.getString("studentname"));
				res.setDob(rs.getString("dob"));
				res.setGender(rs.getString("gender"));
				res.setPhone(rs.getString("phone"));
				res.setEducation(rs.getString("education"));
				
				
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return res;
	}
	public   List<StudentResponseDTO> search(StudentRequestDTO dto)
	{
		
		
		List <StudentResponseDTO>list=new ArrayList<>();
		String sql="select * from studenttable where studentid=? or studentname=?";
		try 
		{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,dto.getStudentid());
			ps.setString(2,dto.getStudentname());
		
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				
				StudentResponseDTO res=new StudentResponseDTO();
				res.setStudentid(rs.getString("studentid"));
				res.setStudentname(rs.getString("studentname"));
				res.setDob(rs.getString("dob"));
				res.setGender(rs.getString("gender"));
				res.setPhone(rs.getString("phone"));
				res.setEducation(rs.getString("education"));
		
				list.add(res);
				
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return list;
	}
	
	


}
