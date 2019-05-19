
public class PaperReviewDriver2 {

	public static void main(String[] args) {
		Paper _paper = new Paper();
		Author _author = new Author();
		
		System.out.println("1) Get submitted paper’s details by the author’s Primary Key jkrowling@aol.com\n");
		_paper.getPaperByAuthorEmail("jkrowling@aol.com");
		System.out.println("");
		
		System.out.println("2) Get all reviews for a paper by the paper’s Id = 2\n");
		_paper.getPaperReviews(2);
		System.out.println("");
		
		System.out.println("3) Get a count of all papers submitted\n");
		System.out.println("Amount of papers submitted: " + _paper.getCountPapersSubmitted() + "\n");
		System.out.println("");
		
		System.out.println("4) Create a new paper submission including new records in the Author and Paper tables\n");
		
		//String authorEmail = "pcoelho@msn.com";
		String authorEmail = "peter.parker@yahoo.com";
		
		if(_author.authorExist(authorEmail))
			System.out.println("Author Exist. Add a new book for " + authorEmail + "");
		else {
			System.out.println("Author " + authorEmail + " does not exist. Add a new author first before adding a new paper.\n");
			System.out.println("Total number of existing authors prior processing a new author: " + _author.getCountAuthors() + "\n");
			
			// Calling addNewAuthor method to add a new author
			_author.addNewAuthor(authorEmail, "Peter", "Parker Brown");
			System.out.println("Total number of existing authors after processing a new author: " + _author.getCountAuthors() + "\n");
			
			System.out.println("Total of records in T_Paper prior insert: " + _paper.getCountPapersSubmitted() + "");
			// Calling addNewPaper method to add a new paper
			_paper.addNewPaper(authorEmail, "Small Great Things", "Small Great Things tells the story of an African American labor...", "PPB_SGT.docx");
			System.out.println("Total of records in T_Paper after insert: " + _paper.getCountPapersSubmitted() + "\n");
			
			// Calling getPaperByAuthorEmail to show a paper by author's email
			System.out.println("- New Paper Information -");
			_paper.getPaperByAuthorEmail(authorEmail);
		}
		System.out.println("");
		
		System.out.println("5) Delete the first Author row in your Author table by the author’s ID\n");
		// Calling deleteAuthor() method to delete the first Author row in Author table
		_author.deleteAuthor(1);
		System.out.println("");
	}

}
