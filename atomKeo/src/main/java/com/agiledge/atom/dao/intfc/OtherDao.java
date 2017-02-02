package com.agiledge.atom.dao.intfc;



public interface OtherDao {
	String[] getCity(String site) throws Exception;
	String getEmployeeDet(String empId);

	
	
	/*public String getUserName(long uid);
	public String getEmployeesNotSccheduledforTomorrow(String date);
	public String getEmpRoute(long uid);
	public String getEmployeeDet(long uid);
	public String Location(int landmark);
	public int insertPageToDatabase(ArrayList<String> pages, String type);
	public int insertRolePages(String usertype, String[] pages,String display);
	public ArrayList<SiteDto> selectPagesNoAccess(String usertype);
	public ArrayList<SiteDto> selectPagesAccess(String userType);
	
	public boolean isRegisterd(String employeeId);*/
	
}
