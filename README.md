# DBMS_JavaProgram
Repository that contains the java source code of the DBMS final project.

This Java application is conformed by three Java classes:  Author.java, Paper.java, and PaperReviewDriver2.java

* Author.Java Class:

  - The Author class was built with the purpose of managing the data of the T_Author table. This class contains the following methods:
  
  getCountAuthors(): Method that returns the number of authors in T_Author table.
  Query implemented: SELECT COUNT(*) as Num_Authors FROM T_Author;
  
  authorExist(String authorEmail): Method that validates if an author exists in T_Author table. This method receives as input   parameter the author's email address.
  Query implemented: SELECT COUNT(*) as COUNT_AUTHOR" + 
				" FROM T_Author" + 
				" WHERE" + 
				" A_EmailAddr = ?";
  
  addNewAuthor (String email, String fname, String lname): Method that inserts a new author in the T_Author table. This method   receives as input parameters the author's email, first name, and last name.
  Query implemented: INSERT INTO `T_Author` (`A_EmailAddr`, `FirstName`, `LastName`) VALUES (?,?,?);
  
  deleteAuthor(int authorID): Method that deletes an author from T_Author table. This method receives as input parameter the     author's ID.
  Query implemented: DELETE FROM `T_Author` WHERE Author_id = ?;


* Paper.Java Class:

  - The Paper class was built with the purpose of managing the data of the T_Paper table. This class contains the following methods:
  
  getCountPapersSubmitted(): Method that returns the number of papers submitted in T_Paper.
  Query implemented: SELECT COUNT(*) as Papers_Submitted FROM T_Paper;
  
  getPaperByAuthorEmail(String authorEmail): Method that returns the information of all papers submitted by a particular         author. This method receive as input parameter the author's email.
  Query implemented: SELECT P.Paper_ID, P.Title, P.Abstract, P.A_EmailAddr, A.FirstName, A.LastName" + 
				" FROM T_Paper P" + 
				" INNER JOIN T_Author A" + 
				" ON P.A_EmailAddr = A.A_EmailAddr" + 
				" WHERE" + 
				" P.A_EmailAddr = ?";
  
  getPaperReviews(int paperID): Method that returns all the reviews registered in the T_Review table for a particular paper     recommended to be published. This method receives as input parameter the paper ID.
  Query implemented: SELECT R.*" + 
				" FROM" + 
				" T_Review R" + 
				" INNER JOIN T_Paper P" + 
				" ON R.Paper_ID = P.Paper_ID" + 
				" WHERE" + 
				" R.Paper_ID = ?" + 
				" AND R.Recommendation = 'RECOMMENDED'";
        
  addNewPaper(String authorEmail, String title, String abst, String fileN): Method thet add/inserts a new paper into the         T_Paper table. This method receives as input parameter the author's email, paper title, paper abstract, and the paper file     name.
  Query Implemented: INSERT INTO `T_Paper` (`A_EmailAddr`, `Title`, `Abstract`, `FileName`) VALUES (?,?,?,?);

* PaperReviewDriver2.java Class:

  - The PaperRevireDriver2.java class contains the main program where all the methods from the Author and Paper classes are executed. To call and execute these methods the user must create Author and Paper data type objects. 

  Example:
  
  Paper _paper = new Paper();
	Author _author = new Author();
	
  // Get submitted paper’s details by the author’s Email Primary Key
  _paper.getPaperByAuthorEmail("jkrowling@aol.com");
  
  // Get all reviews for a paper by the paper’s Id = 2
	_paper.getPaperReviews(2);
  
  // Get a count of all papers submitted
	_paper.getCountPapersSubmitted();
		
  if(_author.authorExist(authorEmail))
			System.out.println("Author Exist. Add a new book for " + authorEmail + "");
	else {
      // Get the total number of existing authors
			_author.getCountAuthors();
      
      // Calling addNewAuthor method to add a new author
			_author.addNewAuthor(authorEmail, "Peter", "Parker Brown");
      
      // Getting Total of records in T_Paper
			_paper.getCountPapersSubmitted();
      
      // Calling addNewPaper method to add a new paper
			_paper.addNewPaper(authorEmail, "Small Great Things", "Small Great Things tells the story of an African American          labor...", "PPB_SGT.docx");
      
      // Calling getPaperByAuthorEmail to show a paper by author's email
			_paper.getPaperByAuthorEmail(authorEmail);
		}
		
    // Method to delete the an Author record in Author table by passinf the Author's ID.
    _author.deleteAuthor(1);

To run this application the user just have to execute the PaperReviewDriver2 java program via the Eclipse IDE console.
		
  
  
  
  
  
  
