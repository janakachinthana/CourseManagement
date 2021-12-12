package KDU.IS.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommonUtil {

	public static final Properties properties = new Properties();

	static {
		try {
			
			// Read the property only once when load the class
			properties.load(QueryUtil.class.getResourceAsStream(CommonConstants.PROPERTY_FILE));
			
		} catch (IOException e) {
			/* log.log(Level.SEVERE, e.getMessage()); */
		}
	}

	public static String generateIDs(ArrayList<String> arrayList) {

		String id;
		int next = arrayList.size();
		next++;
		id = CommonConstants.ID_USER_PREFIX + next;
		if (arrayList.contains(id)) {
			next++;
			id = CommonConstants.ID_USER_PREFIX + next;
		}
		return id;
	}
	
	public static String generateCourseIDs(ArrayList<String> arrayList) {

		String id;
		int next = arrayList.size();
		next++;
		id = CommonConstants.ID_COURSE_PREFIX + next;
		if (arrayList.contains(id)) {
			next++;
			id = CommonConstants.ID_COURSE_PREFIX + next;
		}
		return id;
	}
	
	public static String generateStudentCourseIDs(ArrayList<String> arrayList) {

		String id;
		int next = arrayList.size();
		next++;
		id = CommonConstants.ID_STUDENTCOURSE_PREFIX + next;
		if (arrayList.contains(id)) {
			next++;
			id = CommonConstants.ID_STUDENTCOURSE_PREFIX + next;
		}
		return id;
	}
	
	public static String generateLectureIDs(ArrayList<String> arrayList) {

		String id;
		int next = arrayList.size();
		next++;
		id = CommonConstants.ID_LECTURE_PREFIX + next;
		if (arrayList.contains(id)) {
			next++;
			id = CommonConstants.ID_LECTURE_PREFIX + next;
		}
		return id;
	}
	
}
