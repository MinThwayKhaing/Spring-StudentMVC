package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import DAO.MyConnection;
import DTO.CourseStudentRequestDTO;

@Service("CourseStudentDAO")
public class CourseStudentDAO {
	public static Connection con = null;
	static {
		con = MyConnection.getConnection();
	}
	
	public int insertCourseStudnetData(DTO.CourseStudentRequestDTO dto) {
        int result=0;
        String sql="insert into course_student (stu_id,course_name) values(?,?)";
        try {

                PreparedStatement ps=con.prepareStatement(sql);

                ps.setString(1,dto.getStuid());
                ps.setString(2,dto.getCoursename());
                result=ps.executeUpdate();
        } catch (SQLException e) {
        	
            
            e.printStackTrace();
        }
        return result;
    }
	
	public ArrayList<String> selectOne(String id) {
		ArrayList<String> list = new ArrayList<>();
		String sql = "select * from course_student where stu_id=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				
				
				list.add(rs.getString("course_name")	);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	

	public ArrayList<String> searchcourse(String id) {
		ArrayList<String> list = new ArrayList<>();
		String sql = "select * from course_student where course_name=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				
				
				list.add(rs.getString("course_name")	);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	public ArrayList<String> selectReverse(String course) {
		ArrayList<String> list = new ArrayList<>();
		String sql = "select * from course_student where course_name = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,course);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add( rs.getString("stu_id"));
			}
		} catch (SQLException e) {
			 
			e.printStackTrace();
		}
		return list;
	}
	
	public void deleteData(CourseStudentRequestDTO dto) {
		String sql = "delete from course_student where stu_id=?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getStuid());
			ps.executeUpdate();
		} catch (SQLException e) {
			
		}

	}
}
