package KDU.IS.Utils;

public class CommonConstants {

	/** Constant for config.properties key for query file path */
	public static final String QUERY_XML = "queryFilePath";

	/** Constant for file path of config.properties */
	public static final String PROPERTY_FILE = "config.properties";

	/** Constant for query tag in PersonQuery.xml */
	public static final String TAG_NAME = "query";

	/** Constant for query id in PersonQuery.xml */
	public static final String ATTRIB_ID = "id";
	
	/** Constant for comma */
	public static final String COMMA = ",";

	/** Constant for url key of MySQL database in config.properties */
	public static final String URL = "url";

	/** Constant for user name key of MySQL database in config.properties */
	public static final String USERNAME = "username";

	/** Constant for password key of MySQL database in config.properties */
	public static final String PASSWORD = "password";

	/** Constant for driver name key of MySQL database in config.properties */
	public static final String DRIVER_NAME = "driverName";
	
	/** Constant for Column index one */
	public static final int COLUMN_INDEX_ONE = 1;
	
	/** Constant for Column index two */
	public static final int COLUMN_INDEX_TWO = 2;
	
	/** Constant for Column index three */
	public static final int COLUMN_INDEX_THREE = 3;
	
	/** Constant for Column index four */
	public static final int COLUMN_INDEX_FOUR = 4;
	
	/** Constant for Column index five */
	public static final int COLUMN_INDEX_FIVE = 5;
	
	/** Constant for Column index six */
	public static final int COLUMN_INDEX_SIX = 6;
	
	/** Constant for Column index seven */
	public static final int COLUMN_INDEX_SEVEN = 7;
	
	/** Constant for Column index eight */
	public static final int COLUMN_INDEX_EIGHT = 8;
	
	
//For Users 
	
	/** Constant for company id prefix */
	public static final String ID_USER_PREFIX = "USER00";
	
	/** Constant for query id of drop_table in Query.xml */
	public static final String QUERY_ID_DROP_USER_TABLE = "drop_user_table";
	
	/** Constant for query id of create_table in Query.xml */
	public static final String QUERY_ID_CREATE_USER_TABLE = "create_user_table";
	
	/** Constant for query id of insert persons in Query.xml */
	public static final String QUERY_ID_INSERT_USER = "insert_user";

	/** Constant for query id of get an person in Query.xml */
	public static final String QUERY_ID_GET_USER = "user_by_id";  

	/** Constant for query id of get an person in Query.xml */
	public static final String QUERY_EMAIL_GET_USER = "user_by_email"; 
	
	/** Constant for query id of get all persons in Query.xml */
	public static final String QUERY_ID_ALL_USERS = "all_users";
	
	/** Constant for query id of remove a person in Query.xml */
	public static final String QUERY_ID_REMOVE_USER = "remove_user";
	
	/** Constant for query id of update a person in Query.xml */
	public static final String QUERY_ID_UPDATE_USER = "update_user";
	
	/** Constant for query id of update a person in Query.xml */
	public static final String QUERY_ID_UPDATE_USER_STATUS = "update_user_status";
	
	/** Constant for query id of get all person ids in Query.xml */
	public static final String QUERY_ID_GET_USER_IDS = "user_ids";
	
	//For Courses 
	
		/** Constant for company id prefix */
		public static final String ID_COURSE_PREFIX = "COURSE00";
		
		/** Constant for query id of drop_table in Query.xml */
		public static final String QUERY_ID_DROP_COURSE_TABLE = "drop_course_table";
		
		/** Constant for query id of create_table in Query.xml */
		public static final String QUERY_ID_CREATE_COURSE_TABLE = "create_course_table";
		
		/** Constant for query id of insert persons in Query.xml */
		public static final String QUERY_ID_INSERT_COURSE = "insert_course";

		/** Constant for query id of get an person in Query.xml */
		public static final String QUERY_ID_GET_COURSE = "course_by_id";  

		/** Constant for query id of get an person in Query.xml */
		public static final String QUERY_EMAIL_GET_COURSE = "course_by_email"; 
		
		/** Constant for query id of get all persons in Query.xml */
		public static final String QUERY_ID_ALL_COURSES = "all_courses";
		
		/** Constant for query id of remove a person in Query.xml */
		public static final String QUERY_ID_REMOVE_COURSE = "remove_course";
		
		/** Constant for query id of update a person in Query.xml */
		public static final String QUERY_ID_UPDATE_COURSE = "update_course";
		
		/** Constant for query id of update a person in Query.xml */
		public static final String QUERY_ID_UPDATE_COURSE_STATUS = "update_course_status";
		
		/** Constant for query id of get all person ids in Query.xml */
		public static final String QUERY_ID_GET_COURSE_IDS = "course_ids";
		
		
		
		
		//For StudentCourses 
		
			/** Constant for company id prefix */
			public static final String ID_STUDENTCOURSE_PREFIX = "ST_CR00";
			
			/** Constant for query id of drop_table in Query.xml */
			public static final String QUERY_ID_DROP_STUDENTCOURSE_TABLE = "drop_studentCourse_table";
			
			/** Constant for query id of create_table in Query.xml */
			public static final String QUERY_ID_CREATE_STUDENTCOURSE_TABLE = "create_studentCourse_table";
			
			/** Constant for query id of insert persons in Query.xml */
			public static final String QUERY_ID_INSERT_STUDENTCOURSE = "insert_studentCourse";

			/** Constant for query id of get an person in Query.xml */
			public static final String QUERY_ID_GET_STUDENTCOURSE = "studentCourse_by_id";  

			/** Constant for query id of get an person in Query.xml */
			public static final String QUERY_EMAIL_GET_STUDENTCOURSE = "studentCourse_by_email"; 
			
			/** Constant for query id of get all persons in Query.xml */
			public static final String QUERY_ID_ALL_STUDENTCOURSES = "all_studentCourses";
			
			/** Constant for query id of remove a person in Query.xml */
			public static final String QUERY_ID_REMOVE_STUDENTCOURSE = "remove_studentCourse";
			
			/** Constant for query id of update a person in Query.xml */
			public static final String QUERY_ID_UPDATE_STUDENTCOURSE = "update_studentCourse";
			
			/** Constant for query id of update a person in Query.xml */
			public static final String QUERY_ID_UPDATE_STUDENTCOURSE_STATUS = "update_studentCourse_status";
			
			/** Constant for query id of get all person ids in Query.xml */
			public static final String QUERY_ID_GET_STUDENTCOURSE_IDS = "studentCourse_ids";
			
			
			
			
			
			//For Lecture 
			
				/** Constant for company id prefix */
				public static final String ID_LECTURE_PREFIX = "LEC_00";
				
				/** Constant for query id of drop_table in Query.xml */
				public static final String QUERY_ID_DROP_LECTURE_TABLE = "drop_lecture_table";
				
				/** Constant for query id of create_table in Query.xml */
				public static final String QUERY_ID_CREATE_LECTURE_TABLE = "create_lecture_table";
				
				/** Constant for query id of insert persons in Query.xml */
				public static final String QUERY_ID_INSERT_LECTURE = "insert_lecture";

				/** Constant for query id of get an person in Query.xml */
				public static final String QUERY_ID_GET_LECTURE = "lecture_by_id";  

				/** Constant for query id of get an person in Query.xml */
				public static final String QUERY_EMAIL_GET_LECTURE = "lecture_by_email"; 
				
				/** Constant for query id of get all persons in Query.xml */
				public static final String QUERY_ID_ALL_LECTURES = "all_lectures";
				
				/** Constant for query id of remove a person in Query.xml */
				public static final String QUERY_ID_REMOVE_LECTURE = "remove_lecture";
				
				/** Constant for query id of update a person in Query.xml */
				public static final String QUERY_ID_UPDATE_LECTURE = "update_lecture";
				
				/** Constant for query id of update a person in Query.xml */
				public static final String QUERY_ID_UPDATE_LECTURE_STATUS = "update_lecture_status";
				
				/** Constant for query id of get all person ids in Query.xml */
				public static final String QUERY_ID_GET_LECTURE_IDS = "lecture_ids";
	
}
