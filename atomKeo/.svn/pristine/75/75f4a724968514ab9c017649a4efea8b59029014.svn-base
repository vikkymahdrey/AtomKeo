package com.agiledge.atom.dao.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import com.agiledge.atom.config.files.OtherFunctions;
import com.agiledge.atom.dao.intfc.DistanceListDao;
import com.agiledge.atom.dto.AplDistanceDto;
import com.agiledge.atom.dto.RoutingDto;
import com.agiledge.atom.entities.Distchart;
import com.agiledge.atom.entities.Landmark;
import com.agiledge.atom.service.intfc.LandmarkService;
import com.agiledge.atom.utils.APLDto;
@Repository
public class DistanceListDaoImpl extends AbstractDao implements DistanceListDao {
	private static final Logger logger = Logger.getLogger(DistanceListDaoImpl.class);
	
	public static int lastId=346;
	public static int uptoId=346;
	private ArrayList<String> errorMessageList = new ArrayList<String>();

	public float[] getDistances(int[] landmarks, RoutingDto routingDto)	throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public float getDistance(String srcid, String destid,Distchart distc) throws Exception {
		
		float distance=(float) 0.1;
		String query="";
		Distchart distchart=null;
		try {
			query="from Distchart dc where (dc.landmark2.id="+srcid+" and dc.landmark1.id="+destid+") or (dc.landmark2.id="+destid+" and dc.landmark2.id="+srcid+")";
			Query q=getEntityManager().createQuery(query);
			List<Distchart> distchartList=q.getResultList();
			
			if(distchartList.size()!=0 || distchartList!=null || !distchartList.isEmpty()){
				for(Distchart dc : distchartList){
					distance=(float)dc.getDistance();
				}
			}
			else
			{
					distance=getGooglMapDistance(srcid,destid);	
						if(distance!=0.1||distance!=0.0){
							distc.setDistance(distance);
								 persist(distc);
								 flush();
						}
			
			}
		}catch(Exception e){
			logger.error("Error in getDistance ",e);
		}
			
		return distance;

	}
	
	public float getGooglMapDistance(String srcId,String destId) {
		float distance = (float)0.0;
		try{
		
	
		/* get distances from map api */
		DefaultHttpClient client1 = new DefaultHttpClient();					
	     String sourceLatLng=getLandmarkLatitudeLongitude(srcId).getLocation();
	     String destinationLatLng=getLandmarkLatitudeLongitude(destId).getLocation();																																			 
	     String url="http://maps.googleapis.com/maps/api/distancematrix/json?origins="+sourceLatLng+"&destinations="+destinationLatLng;
	     String keyedURL=OtherFunctions.encryptTheMapKey(url);
		HttpGet request = new HttpGet(keyedURL);
	    HttpResponse response1 = 
	    		 client1.execute(request);
	      
	     BufferedReader rd = new BufferedReader (new InputStreamReader(response1.getEntity().getContent()));
	     String line = "";
	     String nLine="";
	
	     while ((line = rd.readLine()) != null) {
	    	 nLine+=line;
	        }
	    // System.out.println(nLine);
	     JSONObject obj = new JSONObject(nLine);
	     
	     obj=(JSONObject) (obj.getJSONArray("rows")).get(0);
			obj =(JSONObject) (obj.getJSONArray("elements")).get(0);
			obj=(JSONObject) obj.get("distance");
			int distanceM =(Integer)obj.get("value");	
			distance=(float)distanceM/1000;
		
		}catch(Exception e) {
			System.out.println(srcId+"  dest"+destId+"  Error in getMapDistance : "+ e);

		}
		
		return distance;
	}
	
	public APLDto getLandmarkLatitudeLongitude(String landMarkID) {
		String query="from Landmark where id=:landMarkID";
		Query q=getEntityManager().createQuery(query);
		q.setParameter("landMarkID",landMarkID);
		 List<Landmark> landmarkList=q.getResultList();
	
		 APLDto dto= new APLDto();
			for(Landmark lm : landmarkList){
				dto.setLandMarkID(lm.getId());
				dto.setLattitude(Double.toString(lm.getLatitude()));
				dto.setLongitude(Double.toString(lm.getLongitude()));
				dto.setLocation(Double.toString(lm.getLatitude())+","+Double.toString(lm.getLongitude()));	
			}

	
		return dto;
	}

public void insertDistanceFromDistanceList(ArrayList<AplDistanceDto> distanceList) throws Exception{
		
		
			int returnInt = 0;
			String checkQuery ="from Distchart dc where (dc.landmark2.id=:srcId and dc.landmark1.id=:destId) or (dc.landmark2.id=:destId and dc.landmark2.id=:srcId)";
			
			Query q=getEntityManager().createQuery(checkQuery);
				
				for(AplDistanceDto dto: distanceList)
				{
					if(dto.getSourceId().equals(dto.getTargetId())) {
						returnInt = returnInt + 1;
					} else {
						q.setParameter("srcId", dto.getSourceId());
						q.setParameter("destId", dto.getTargetId());
						List<Distchart> dcList=q.getResultList();
						Distchart distchart=null;
						
						if(dcList!=null && dcList.size()!=0){
							distchart=new Distchart();
							Landmark lmSrcId=new LandmarkDaoImpl().getLandmarkById(dto.getSourceId());
							Landmark lmTargId=new LandmarkDaoImpl().getLandmarkById(dto.getTargetId());
							distchart.setDistance(dto.getDistance());
							distchart.setLandmark2(lmSrcId);
							distchart.setLandmark1(lmTargId);
								update(distchart);
							
						}else {
						
							String errorMsg ="from Distchart dc where (dc.landmark2.id=? and dc.landmark1.id=?) or (dc.landmark2.id=? and dc.landmark2.id=?)";

							errorMsg = errorMsg.replace("?", "'%s'");
							errorMsg = String.format(errorMsg,dto.getSourceId(), dto.getTargetId(), dto.getTargetId(), dto.getSourceId());
							getErrorMessageList().add(errorMsg);
							
							Distchart distc=new Distchart();
							Landmark lmSrcId=new LandmarkDaoImpl().getLandmarkById(dto.getSourceId());
							Landmark lmTargId=new LandmarkDaoImpl().getLandmarkById(dto.getTargetId());
							distc.setLandmark2(lmSrcId);
							distc.setLandmark1(lmTargId);
							distc.setDistance(dto.getDistance());
							persist(distc);
							
						}
						
					}
			}
	} 

		public ArrayList<String> getErrorMessageList() {
			return errorMessageList;
		}

		public void setErrorMessageList(ArrayList<String> errorMessageList) {
			this.errorMessageList = errorMessageList;
		}
}
