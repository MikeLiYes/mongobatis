package com.github.mikeliyes.mongobatis.model;

import java.util.HashMap;
import java.util.Map;

public class Mapper {

	/*********config.xml mapper start**************/ 
	private String resource;
	/*********config.xml mapper end**************/ 
	
	/**********mapper.xml start*****************/
	private String nameSpace;
	
	private Map<String,Aggregate> aggregates;

	/**********mapper.xml end*****************/

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getNameSpace() {
		return nameSpace;
	}

	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}
	
	public Map<String, Aggregate> getAggregate() {
		return aggregates;
	}

	public void setAggregate(Map<String, Aggregate> aggregates) {
		this.aggregates = aggregates;
	}
	
	public void setAggregate(Aggregate aggregate) {
		
		if (this.aggregates == null || this.aggregates.size() == 0) {
            this.aggregates = new HashMap<String,Aggregate>();
		}
		
		if (aggregate != null && aggregate.getId() != null && aggregate.getId().trim() != "") {
			this.aggregates.put(aggregate.getId(),aggregate);
		}
	}
}
