package KDU.IS.Models;

public class Quiz {
	
	private String QuizID;
	private String LectureID;
	private String Question;
	private String Ans1;
	private String Ans2;
	private String Ans3;
	private String Ans4;
	private String Ans5;
	private String Answer;
	
	public Quiz() {
		super();
	}

	public Quiz(String quizID, String lectureID, String question, String ans1, String ans2, String ans3, String ans4,
			String ans5, String answer) {
		super();
		QuizID = quizID;
		LectureID = lectureID;
		Question = question;
		Ans1 = ans1;
		Ans2 = ans2;
		Ans3 = ans3;
		Ans4 = ans4;
		Ans5 = ans5;
		Answer = answer;
	}

	public String getQuizID() {
		return QuizID;
	}

	public void setQuizID(String quizID) {
		QuizID = quizID;
	}

	public String getLectureID() {
		return LectureID;
	}

	public void setLectureID(String lectureID) {
		LectureID = lectureID;
	}

	public String getQuestion() {
		return Question;
	}

	public void setQuestion(String question) {
		Question = question;
	}

	public String getAns1() {
		return Ans1;
	}

	public void setAns1(String ans1) {
		Ans1 = ans1;
	}

	public String getAns2() {
		return Ans2;
	}

	public void setAns2(String ans2) {
		Ans2 = ans2;
	}

	public String getAns3() {
		return Ans3;
	}

	public void setAns3(String ans3) {
		Ans3 = ans3;
	}

	public String getAns4() {
		return Ans4;
	}

	public void setAns4(String ans4) {
		Ans4 = ans4;
	}

	public String getAns5() {
		return Ans5;
	}

	public void setAns5(String ans5) {
		Ans5 = ans5;
	}

	public String getAnswer() {
		return Answer;
	}

	public void setAnswer(String answer) {
		Answer = answer;
	}

	@Override
	public String toString() {
		return "Quiz [QuizID=" + QuizID + ", LectureID=" + LectureID + ", Question=" + Question + ", Ans1=" + Ans1
				+ ", Ans2=" + Ans2 + ", Ans3=" + Ans3 + ", Ans4=" + Ans4 + ", Ans5=" + Ans5 + ", Answer=" + Answer
				+ "]";
	}


}
