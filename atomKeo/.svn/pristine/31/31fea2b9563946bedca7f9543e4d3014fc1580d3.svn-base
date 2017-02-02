package com.agiledge.atom.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agiledge.atom.dao.intfc.APLDao;
import com.agiledge.atom.dao.intfc.AreaDao;
import com.agiledge.atom.dao.intfc.LandmarkDao;
import com.agiledge.atom.dao.intfc.PlaceDao;
import com.agiledge.atom.entities.Area;
import com.agiledge.atom.entities.Branch;
import com.agiledge.atom.entities.Landmark;
import com.agiledge.atom.entities.Place;
import com.agiledge.atom.service.intfc.AplService;
import com.agiledge.atom.utils.APLDto;


@Service("aplService")
public class AplServiceImpl  implements AplService{
	private String message;
	
	@Autowired
	private LandmarkDao landmarkDao;
	
	@Autowired
	private PlaceDao placeDao;
	
	@Autowired
	private AreaDao areaDao;
	
	@Autowired
	private APLDao aplDao;
	
	public List<Landmark> getMatchingLandmarks(String landmarkText)
			throws Exception {
		return landmarkDao.getMatchingLandmarks(landmarkText);
	}

	public List<Place> getmatchingPlaces(String placeText) throws Exception {
		return placeDao.getMatchingPlaces(placeText);
	}

	public List<Area> getMatchingAreas(String areaText) throws Exception {
		return areaDao.getMatchingAreas(areaText);
	}

	public Landmark getLandmarkById(String landmarkId) throws Exception {
		return landmarkDao.getLandmarkById(landmarkId);
	}
	
	public List<Landmark> getCDLandMarks(String landMarkName,String location,String site) throws Exception{
		
		return aplDao.getCDLandMarks(landMarkName, location, site);
	}
	public List<Landmark> getLandMarks() throws Exception{
		
		return aplDao.getLandMarks();
	}
	public List<Landmark> getLandMarks(String landMarkName, String location,String site) throws Exception{
		// TODO Auto-generated method stub
		return aplDao.getLandMarks(landMarkName, location, site);
	}


	public List<Branch> getBranches() throws Exception {
			return aplDao.getBranches();
	}


	public List<Area> getAreasBybranchId(int branchId) throws Exception {
			return aplDao.getAreasBybranchId(branchId);
	}

	
	public List<Area> getAreas() throws Exception {
		return aplDao.getAreas();
	
	}

	
	public List<Landmark> getLandmarksByPlaceId(String placeforLandmark)
			throws Exception {
		return aplDao.getLandmarksByPlaceId(placeforLandmark);
	}

	
	public List<Landmark> getSpecificLandmarks(String area, String place,
			String location) throws Exception {
		return aplDao.getSpecificLandmarks(area,place,location);
	}

	
	public Area insertArea(Area ar,String location) throws Exception {
		// TODO Auto-generated method stub
		return aplDao.insertArea(ar,location);
	}

	
	public Area getAreaById(String areaId,int branchId) throws Exception {
		return aplDao.getAreaById(areaId,branchId);
	}

	
	public Area updateArea(Area area) {
			return aplDao.updateArea(area);
	}

	
	public List<Place> getPlaceByAreaId(String areaId) throws Exception {
			return aplDao.getPlaceByAreaId(areaId);
	}

	
	public Area getAreaById(String areaId) throws Exception {
				return aplDao.getAreaById(areaId);
	}

	
	public Place insertPlace(Place place,String areaId) throws Exception {
		// TODO Auto-generated method stub
		return aplDao.insertPlace(place,areaId);
	}

	
	public Place getPlaceByPlaceId(String placeId) throws Exception {
		return aplDao.getPlaceByPlaceId(placeId);
	}

	
	public Place updatePlace(Place place,String areaId) throws Exception {
		return aplDao.updatePlace(place,areaId);
	}

	
	public List<Landmark> getLandmarkByPlaceId(String placeId) throws Exception {
		return aplDao.getLandmarksByPlaceId(placeId);
	}

	
	public Landmark insertLandmark(Landmark landmark, String placeId) throws Exception {
		
		return aplDao.insertLandmark(landmark, placeId);
	}

	
	public Landmark updateLandmark(Landmark lm, String placeId)	throws Exception {
		
		return aplDao.updateLandmark(lm,placeId);
	}

	/*public int uploadAPL(InputStream in, boolean areaDupe, boolean placeDupe, boolean landmarkDupe, String location) throws Exception{
		int val = 0;
		try {
			if(location.isEmpty()||OtherFunctions.isInteger(location)==false) {
				message = "Location is invalid";
			} else {
		ArrayList<APLDto > aplList = uploadAPLFile(in);
		for(APLDto apldto : aplList)
		System.out.println(apldto.getArea() + " " + apldto.getPlace() + " " + apldto.getLandMark());
		System.out.println("apl uploaded ...");
		
		AplTree aplTree = createAplTee(aplList);
		System.out.println("apl tree created ...");
		showTree(aplTree);
		val = new APLDao().uploadAPL(aplTree, areaDupe, placeDupe, landmarkDupe, location);
			}
		}catch(Exception e) {
			val = 0;
			message = e.getMessage();
		}
		return val;
	}
	
	public ArrayList<APLDto >  uploadAPLFile( InputStream in) throws Exception{
		int r=0;
		ArrayList<APLDto > dtoList = new ArrayList<APLDto >();
		try {
			
			boolean flag = false;
			XSSFWorkbook workbook = new XSSFWorkbook(in);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next();
			System.out.println("row 0");
			while (rowIterator.hasNext()) {
			
				if (flag)
					break;

				Row row = rowIterator.next();
				System.out.println("row " + row.getRowNum());
				// For each row, iterate through each columns

				Iterator<Cell> cellIterator = row.cellIterator();
				APLDto dto = new APLDto();
				while (cellIterator.hasNext()) {
					
					Cell cell = cellIterator.next();
					System.out.println("col " + cell.getColumnIndex());
					r=cell.getColumnIndex();
					if (cell.getColumnIndex() < 5) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						String value = cell.getStringCellValue();
						
						if (value == null || value.equals("")) {
							System.out.println("End of file");
							flag = true;
							break;

						}

					}
 
					if (cell.getColumnIndex() == 0) {
						dto.setArea (cell.getStringCellValue().trim());
					 

					} else if (cell.getColumnIndex() == 1) {
						dto.setPlace (cell.getStringCellValue().trim());
						 

					} else if (cell.getColumnIndex() == 2) {
						 
						dto.setLandMark(cell.getStringCellValue().trim());
						 

					}  else if (cell.getColumnIndex() == 3) {
						 
						   
						  dto.setLattitude(cell.getStringCellValue().trim() );
					   
					} else if (cell.getColumnIndex() == 4) {
						 
						dto.setLongitude(cell.getStringCellValue().trim());
					
					}
					
				
				}
				
				if (!flag) {
					dtoList.add(dto);
				}
				

			}
 			//System.out.println("returnint" + returnint);
		//	file.close();
			in.close();
			
		}

		catch (FileNotFoundException fnfe) {
			dtoList = null;
			fnfe.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			dtoList = null;
			System.out.println("ERRRO on cell "+r+"  cause"+e);
			e.printStackTrace();
			
		}
		return dtoList;
	}
	
	public AplTree createAplTee(ArrayList<APLDto> apls) throws Exception {
		int value =1;
		 
		AplTree areaTree = new AplTree(AplTree.AREA);
		if(apls!=null && apls.size()>0) {
			
			for(APLDto dto : apls) {
				areaTree.getMap().put(dto.getArea(), null);
			}
			System.out.println("Areas " + areaTree.getMap().size());
			
			for(APLDto dto : apls) {
				AplTree placeTree = areaTree.getMap().get(dto.getArea());
				if(placeTree==null) {
					placeTree = new AplTree(AplTree.PLACE);
					areaTree.getMap().put(dto.getArea(), placeTree);
				}
					placeTree.getMap().put(dto.getPlace(), null);
				
				 
			}
			 
			
			for(APLDto dto : apls) {
				AplTree placeTree = areaTree.getMap().get(dto.getArea());
				if(placeTree!=null) {
					 
				
					AplTree	landmarkTree = placeTree.getMap().get(dto.getPlace());
					if(landmarkTree==null ) {
						landmarkTree = new AplTree(AplTree.LANDMARK);
						placeTree.getMap().put(dto.getPlace(), landmarkTree);
					}
					AplTree latLong = null;
					try {
						if( OtherFunctions.isDouble(dto.getLattitude()) && 
								OtherFunctions.isDouble(dto.getLongitude()) ) {
							System.out.println("ZERRo");
							latLong = new AplTree();
							latLong.setAplDto(dto);
						}
					} catch(Exception e) {}
						landmarkTree.getMap().put(dto.getLandMark(), latLong);
				} else {
					 value = 0;
					message ="No places under "+ dto.getArea();
					areaTree= null;
				}
				
				 
			}
			
			
		}
		return areaTree;
	}
	
	
	public void showTree(AplTree aplTree) throws Exception { 
		
		if(aplTree!=null && aplTree.getMap().size()>0) {
			
			for(String area : aplTree.getMap().keySet()) {
				if(aplTree.getMap().get(area)!=null  ) {
					AplTree placeTree = aplTree.getMap().get(area);
					 for(String place : placeTree.getMap().keySet()) {
						 if(placeTree.getMap().get(place)!=null) {
							 AplTree landmarkTree = placeTree.getMap().get(place);
							 for(String landmark : landmarkTree.getMap().keySet()) {
								 System.out.println(String.format(" AREA : %s | PLACE : %s | LANDMARK : %s ", area, place, landmark));
							 }
						 } else {
							 System.out.println(String.format(" AREA : %s | PLACE : %s | LANDMARK : %s ", area, place, "------------"));
						 }
					 }
				}  else {
					 System.out.println(String.format(" AREA : %s | PLACE : %s | LANDMARK : %s ", area, "--------------", "------------"));
				 }
			}
			
			
		}
		
	}*/
	public HashMap<String,APLDto> getAllAPL(int landmark[]) throws Exception {
		return aplDao.getAllAPL(landmark);
	}

}
