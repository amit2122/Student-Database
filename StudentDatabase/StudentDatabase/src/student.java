import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;


class student_data
{

    int no;
    String name, dob, doj;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }
}

public class student
{
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException
	{
		
		Scanner sc = new Scanner(System.in);
        student_data s = new student_data();
        int ch;
        
		Class.forName("com.mysql.jdbc.Driver");
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/School","root","@mit7070");
			
		do
		{
			System.out.println();
			System.out.println("1.Insert student data into Student table -");
	        System.out.println("2.Update student data into Student table -");
	        System.out.println("3.Delete student data from Student table -");
	        System.out.println("4.Get a lit of all students");
	        System.out.println("5.Get one student information depending on the student id filter-");
	        System.out.println("6.Exit");
	        System.out.println("enter ur choice-");
	        ch = sc.nextInt();
	            
	        switch(ch)
	        {
	        case 1:
	        	System.out.println("Enter Student no - ");
	        	s.setNo(sc.nextInt());
	        	System.out.println("Enter Student name - ");
	        	s.setName(sc.next());
	        	System.out.println("Enter Student dob - ");
	        	s.setDob(sc.next());
	        	System.out.println("Enter Student doj - ");
	        	s.setDoj(sc.next());
	        	
	        	String str = "insert into student values(?, ?, ?, ?)";
                PreparedStatement pst = cn.prepareStatement(str);
                pst.setInt(1, s.getNo());
                pst.setString(2, s.getName());
                pst.setString(3, s.getDob());
                pst.setString(4, s.getDoj());
                pst.executeUpdate();
                System.out.println("Student_data added successfully");
                break;
                
	        case 2:
	        	System.out.println("enter student no to be update the details-");
                int u = sc.nextInt();
                PreparedStatement pstmt3 = cn.prepareStatement("select * from student where student_no =? ");
                pstmt3.setInt(1, u);
                ResultSet rs3 = pstmt3.executeQuery();

                String str3;
                str3 = "update student set student_no = ?,  student_name = ?, student_dob = ?, student_doj = ?  where student_no = ?";
                PreparedStatement pst3 = cn.prepareStatement(str3);

                while (rs3.next()) {
                    System.out.println("No\t-" + rs3.getString("student_no"));
                    // pst.setString(1, email.getText());
                    System.out.println("Name\t-" + rs3.getString("student_name"));
                    System.out.println("DOB\t-" + rs3.getString("student_dob"));
                    System.out.println("DOJ\t-" + rs3.getString("student_doj"));
                }

                System.out.println("Enter student name-");
                s.setName(sc.next());
                System.out.println("Enter student dob-");
                s.setDob(sc.next());
                System.out.println("Enter student doj-");
                s.setDoj(sc.next());
                break;
                
	        case 3:
                System.out.println("enter student_no to be deleted-");
                int n = sc.nextInt();
                PreparedStatement pstmt = cn.prepareStatement("Delete From student " + " Where student_no = ?");
                pstmt.setInt(1, n);
                pstmt.execute();
                System.out.println("Deleted Successfully");
                break;
                
            case 4:
                String str2 = "select * from student";
                PreparedStatement pst2 = cn.prepareStatement(str2);
                ResultSet rs;
                rs = pst2.executeQuery();
                System.out.println("List of all students- ");
                System.out.println("No  \tName  \tDOB   \t\tDOJ");
                while (rs.next())
                {
//                    Object o[] = {rs.getString("student_no"), rs.getString("student_name"), rs.getString("student_dob"), rs.getString("student_doj4")};
//                    tm.addRow(o);
                    System.out.println(rs.getString("student_no") + "\t" + rs.getString("student_name") + "\t" + rs.getString("student_dob") + "\t" + rs.getString("student_doj"));
                }
                break;
            case 5:
                System.out.println("enter student no to be search-");
                int no = sc.nextInt();
                PreparedStatement pstmt2 = cn.prepareStatement("select * from student where student_no =? ");
                pstmt2.setInt(1, no);
                ResultSet rs2 = pstmt2.executeQuery();
                System.out.println("No  \tName  \tDOB   \t\tDOJ");
                while (rs2.next()) {
                    System.out.println(rs2.getString("student_no") + "\t" + rs2.getString("student_name") + "\t" + rs2.getString("student_dob") + "\t" + rs2.getString("student_doj"));
                }
                break;
                
            case 6:
                System.exit(0);

            default:
                System.out.println("wrong choice");
        }
    } while (ch != 7);
}
}

//			String str = "insert into student values(?,?,?,?);";
//			PreparedStatement pst = cn.prepareStatement(str);
//			pst.setInt(1, no.getId());
//			pst.setString(2, name.getName());
//			pst.setString(3, "2000-05-19");
//			pst.setString(4, "2000-12-12");
//			
//			int rowsInserted = pst.executeUpdate();
//			System.out.println("A new data is added in Database");