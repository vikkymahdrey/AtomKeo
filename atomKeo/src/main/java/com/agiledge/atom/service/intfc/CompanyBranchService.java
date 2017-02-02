package com.agiledge.atom.service.intfc;

import java.util.List;

import com.agiledge.atom.entities.Branch;
import com.agiledge.atom.entities.Company;

public interface CompanyBranchService {
	public Company addNewCompany(Company comp) throws Exception;
	public Company getCompany() throws Exception;
	public Company modifyCompany(Company comp) throws Exception;
	/*public Branch addBranch(Branch branch) throws Exception;*/
	public Branch modifyBranch(Branch branch) throws Exception;
	public Company getCompDetails(int companyId) throws Exception;
	public Company addBranchToCompany(String companyId, String branchLocation) throws Exception;
	public List<Branch> getBranchesByCompanyId(int companyId) throws Exception;
	public Branch getBranchById(int branchId) throws Exception;
	public List<Branch> getBranchs() throws Exception;
}
