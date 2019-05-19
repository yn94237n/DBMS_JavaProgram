import java.sql.*;
public class Paper {
	
	private String p_authorEmail;
	private String p_title;
	private String p_abstract;
	private String p_fileName;
	
	public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	public static final String HOST_DB = "jdbc:mysql://localhost/DB_PAPER_REVIEWERS?useTimezone=true&serverTimezone=UTC";
	public static final String USER = "root";
	public static final String PASSWORD = "root";
	
	Paper () {}
	
	Paper (String authorEmail, String title, String abst, String file) {
		p_authorEmail = authorEmail;
		p_title = title;
		p_abstract = abst;
		p_fileName = file;
	}
	
	// Paper class Getters
	public String getAuthorEmail() {
		return p_authorEmail;
	}
	
	public String getPaperTitle() {
		return p_title;
	}
	
	public String getPaperAbstract() {
		return p_abstract;
	}
	
	public String getFileName () {
		return p_fileName;
	}
	
	// Method getCountPapersSubmitted() that returns the number of papers submitted in T_Paper.
	public int getCountPapersSubmitted() {
		
		int paperSubmitted = 0;
		String sqlQuery = "SELECT COUNT(*) as Papers_Submitted FROM T_Paper";
		
		try {
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(HOST_DB,USER,PASSWORD);
             
            PreparedStatement preparedSelect = conn.prepareStatement(sqlQuery);
            ResultSet rs = preparedSelect.executeQuery();
             
            while (rs.next()) {
            	paperSubmitted = rs.getInt(1);
            }
             
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		return paperSubmitted;
	}
	
	// Method getPaperByAuthorEmail(String authorEmail) that returns the information of all papers submitted by a particular author.
	public void getPaperByAuthorEmail(String authorEmail) {
		String sqlQuery = "SELECT P.Paper_ID, P.Title, P.Abstract, P.A_EmailAddr, A.FirstName, A.LastName" + 
				" FROM T_Paper P" + 
				" INNER JOIN T_Author A" + 
				" ON P.A_EmailAddr = A.A_EmailAddr" + 
				" WHERE" + 
				" P.A_EmailAddr = ?";
		
		try {
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(HOST_DB,USER,PASSWORD);
             
            PreparedStatement preparedSelect = conn.prepareStatement(sqlQuery);
            preparedSelect.setString(1, authorEmail);
            ResultSet rs = preparedSelect.executeQuery();
             
            while (rs.next()) {
                int paper_id = rs.getInt(1);
                String paper_title = rs.getString(2);
                String paper_abs = rs.getString(3);
                String paper_authorEmail = rs.getString(4);
                String paper_firstName = rs.getString(5);
                String paper_lastName = rs.getString(6);
            
                //Display values
		        System.out.println("Paper ID:\t" + paper_id);
		        System.out.println("Title:\t\t" + paper_title);
		        System.out.println("Abstract:\t" + paper_abs);
		        System.out.println("Author Email:\t " + paper_authorEmail);
		        System.out.println("A.First Name:\t " + paper_firstName);
		        System.out.println("A.Last Name:\t " + paper_lastName);
		        System.out.println();
            
            }
             
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	
	}
	
	// Method getPaperReviews(int paperID) that returns the information of a particular paper.
	public void getPaperReviews(int paperID) {
		String sqlQuery = "SELECT R.*" + 
				" FROM" + 
				" T_Review R" + 
				" INNER JOIN T_Paper P" + 
				" ON R.Paper_ID = P.Paper_ID" + 
				" WHERE" + 
				" R.Paper_ID = ?" + 
				" AND R.Recommendation = 'RECOMMENDED'";
		
		try {
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(HOST_DB,USER,PASSWORD);
             
            PreparedStatement preparedSelect = conn.prepareStatement(sqlQuery);
            preparedSelect.setInt(1, paperID);
            ResultSet rs = preparedSelect.executeQuery();
             
            while (rs.next()) {
                int review_id = rs.getInt(1);
                int reviewer_id = rs.getInt(2);
                int paper_id = rs.getInt(3);
                float merit_score = rs.getFloat(4);
                float readAbility_score = rs.getFloat(5);
                float originality_score = rs.getFloat(6);
                float relevance_score = rs.getFloat(7);
                String recommendation = rs.getString(8);
            
                //Display values
		        System.out.println("Review ID:\t" + review_id);
		        System.out.println("Reviewer ID:\t" + reviewer_id);
		        System.out.println("Paper ID:\t" + paper_id);
		        System.out.println("Merit:\t " + merit_score);
		        System.out.println("Readability:\t " + readAbility_score);
		        System.out.println("Originality:\t " + originality_score);
		        System.out.println("Relevance:\t " + relevance_score);
		        System.out.println("Recommendation:\t " + recommendation);
		        System.out.println();
            
            }
             
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	
	}
	
	// Method addNewPaper(String authorEmail, String title, String abst, String fileN) that inserts a new paper into T_Paper table.
	public void addNewPaper(String authorEmail, String title, String abst, String fileN) {
		
		String sqlQuery = "INSERT INTO `T_Paper` (`A_EmailAddr`, `Title`, `Abstract`, `FileName`) VALUES (?,?,?,?)";
        
         
        try {
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(HOST_DB,USER,PASSWORD);
             
            PreparedStatement preparedInsert = conn.prepareStatement(sqlQuery);
             
            int insertions = 0;
            
            preparedInsert.setString(1, authorEmail);
            preparedInsert.setString(2, title);
            preparedInsert.setString(3, abst);
            preparedInsert.setString(4, fileN);
                 
            insertions += preparedInsert.executeUpdate();
            System.out.println(insertions + " record added into T_Paper");
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
		
	}
}
