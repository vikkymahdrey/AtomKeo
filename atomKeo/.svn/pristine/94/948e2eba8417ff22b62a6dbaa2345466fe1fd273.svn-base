package com.agiledge.atom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agiledge.atom.dao.intfc.BranchDao;
import com.agiledge.atom.dao.intfc.CompanyDao;
import com.agiledge.atom.entities.Branch;
import com.agiledge.atom.entities.Company;
import com.agiledge.atom.service.intfc.CompanyBranchService;

@Service
public class CompanyBranchServiceImpl  implements CompanyBranchService {

	@Autowired
	private CompanyDao companyDao;
	
	@Autowired
	private BranchDao branchDao;
	
	public Company addNewCompany(Company comp) throws Exception  {
	 return companyDao.addNewCompany(comp);
	}
	public Company getCompany() throws Exception {
		return companyDao.getCompany();
	}
	public Company modifyCompany(Company comp) throws Exception {
		return companyDao.modifyCompany(comp);
	}
	/*public Branch addBranch(Branch branch) throws Exception {
		return branchDao.addBranch(branch);
	}*/
	
	public Branch modifyBranch(Branch branch) throws Exception {
		return branchDao.modifyBranch(branch);
	}
	public Company getCompDetails(int companyId) throws Exception {
		return companyDao.getCompDetails(companyId);
	}
	public Company addBranchToCompany(String companyId, String branchLocation)  throws Exception{
		Company comp = companyDao.getCompDetails(Integer.parseInt(companyId));
		Branch br = new Branch();
		br.setLocation(branchLocation);
		comp.addBranch(br);
		branchDao.addingBranch(br);
		//companyDao.updateCompany(comp);
		return comp;
	}
	
	public List<Branch> getBranchesByCompanyId(int companyId) throws Exception {
		return companyDao.getBranchesByCompId(companyId);
	}
	public Branch getBranchById(int branchId) throws Exception {
		return branchDao.getBranchById(branchId);
	}
	public List<Branch> getBranchs() throws Exception {
		// TODO Auto-generated method stub
		return branchDao.getBranchs();
	}
	
}
