package com.github.mikeliyes.mongobatis;

import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;

public interface GoodsMapper{
   
	public void update(Long goodsId);
	
	public void delete(Long goodsId);
	
	public void insert(BasicDBObject goods);
	
	public List<Document> findPage(Integer price);
	
	public List<Document> findGoodsPage(Goods goods);
	
	public Document findDetail(Long goodsId);
	
}
