package com.agiledge.atom.dao.intfc;

import java.util.List;

import com.agiledge.atom.entities.Branch;

public interface BranchDao {
	
	public Branch modifyBranch(Branch branch);
	public String getBranchLocation(int branchId);
	public void addingBranch(Branch branch);
	public Branch getBranchById(int branchId) throws Exception;
	public List<Branch> getBranchs()  throws Exception;
	
}
