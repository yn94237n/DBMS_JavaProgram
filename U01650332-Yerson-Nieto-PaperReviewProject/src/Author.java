import java.sql.*;
public class Author {
	
	private String authorEmail;
	private String authorFirstName;
	private String authorLastName;
	
	public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	public static final String HOST_DB = "jdbc:mysql://localhost/DB_PAPER_REVIEWERS?useTimezone=true&serverTimezone=UTC";
	public static final String USER = "root";
	public static final String PASSWORD = "root";
	
	Author () {}
	
	Author (String email, String fname, String lname) {
		authorEmail = email;
		authorFirstName = fname;
		authorLastName = lname;
	}
	
	// Author class setters
	public void setAuthorEmail(String email) {
		authorEmail = email;
	}
	
	public void setAuthorFirstName(String fname) {
		authorFirstName = fname;
	}
	
	public void setAuthorLastName(String lname) {
		authorLastName = lname;
	}
	
	// Author class getters
	public String getAuthorEmail() {
		return authorEmail;
	}
	
	public String getAuthorFirstName() {
		return authorFirstName;
	}
	
	public String getAuthorLastName() {
		return authorLastName;
	}
	
	// Method getCountAuthors() that returns the number of authors in T_Author table.
	public int getCountAuthors() {
		
		int countAuthors = 0;
		String sqlQuery = "SELECT COUNT(*) as Num_Authors FROM T_Author";
		
		try {
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(HOST_DB,USER,PASSWORD);
             
            PreparedStatement preparedSelect = conn.prepareStatement(sqlQuery);
            ResultSet rs = preparedSelect.executeQuery();
             
            while (rs.next()) {
            	countAuthors = rs.getInt(1);
            }
             
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		return countAuthors;
	}
	
	// Method authorExist() that validates if an author exists in T_Author table.
	public boolean authorExist(String authorEmail) {
		
		boolean authorFound = false;
		
		String sqlQuery = "SELECT COUNT(*) as COUNT_AUTHOR" + 
				" FROM T_Author" + 
				" WHERE" + 
				" A_EmailAddr = ?";
		
		try {
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(HOST_DB,USER,PASSWORD);
             
            PreparedStatement preparedSelect = conn.prepareStatement(sqlQuery);
            preparedSelect.setString(1, authorEmail);
            ResultSet rs = preparedSelect.executeQuery();
             
            while (rs.next()) {
                int author_count = rs.getInt(1);
               
                if(author_count > 0)
		        	authorFound = true;
            
            }
             
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		return authorFound;
	
	}
	
	// Method addNewAuthor(String email, String fname, String lname) that inserts a new author in T_Author table.
	public void addNewAuthor (String email, String fname, String lname) {
		
		String sqlQuery = "INSERT INTO `T_Author` (`A_EmailAddr`, `FirstName`, `LastName`) VALUES (?,?,?)";
        
         
        try {
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(HOST_DB,USER,PASSWORD);
             
            PreparedStatement preparedInsert = conn.prepareStatement(sqlQuery);
             
            int insertions = 0;
            
            preparedInsert.setString(1,email);
            preparedInsert.setString(2, fname);
            preparedInsert.setString(3, lname);
                 
            insertions += preparedInsert.executeUpdate();
          
             
            System.out.println(insertions + " records added into T_Author");
            System.out.println("Author " + fname + " " + lname + " (" + email + ") was successfully added into T_Author");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
		
	}
	
	// Method deleteAuthor(int authorID) that deletes an author from T_Author table.
	public void deleteAuthor(int authorID) {
		
		String sqlQuery = "DELETE FROM `T_Author` WHERE Author_id = ?";
        
         
        try {
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(HOST_DB,USER,PASSWORD);
             
            PreparedStatement preparedDelete = conn.prepareStatement(sqlQuery);
             
            int deletions = 0;
            
            preparedDelete.setInt(1, authorID);
                 
            deletions += preparedDelete.executeUpdate();
          
             
            System.out.println(deletions + " records deleted from T_Author");
        } catch (ClassNotFoundException | SQLException e) {
            //e.printStackTrace();
            System.out.println(e);
        }
		
	}

}
