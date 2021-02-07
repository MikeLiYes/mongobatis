package com.github.mikeliyes.mongobatis;

import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public interface GoodsMapper{
   
	public void update(Long goodsId);
	
	public void delete(Long goodsId);
	
	public void insert(BasicDBObject goods);
	
	public List<DBObject> findPage(Long goodsKindId);
	
	public DBObject findDetail(Long goodsId);
	
}
