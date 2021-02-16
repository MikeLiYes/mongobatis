package com.github.mikeliyes.mongobatis;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;

import com.github.mikeliyes.mongobatis.io.Resources;
import com.github.mikeliyes.mongobatis.session.Session;
import com.github.mikeliyes.mongobatis.session.ShellSessionFactory;
import com.github.mikeliyes.mongobatis.session.ShellSessionFactoryBuilder;
import com.mongodb.DBObject;

public class Test {

	public static void main(String[] args) {
		 try {
	            // 基本mybatis环境
	            // 1.定义mybatis_config文件地址
	            String resources = "mongobatis-config.xml";
	            // 2.获取InputStreamReaderIo流
	            Reader reader = Resources.getResourceAsReader(resources);
//	            // 3.获取SqlSessionFactory
	            ShellSessionFactory shellSessionFactory = new ShellSessionFactoryBuilder().build(reader);
//	            // 4.获取Session
	            Session shellSession = shellSessionFactory.openSession();
//	            // 5.操作Mapper接口
	            GoodsMapper mapper = shellSession.getMapper(GoodsMapper.class);
	            List<Document>  list = mapper.findPage(9);
//	            Goods goods = new Goods();
//	            List<DBObject>  glist = mapper.findGoodsPage(goods);
	            Iterator<Document> it = list.iterator();
	            while(it.hasNext()){
	            	Document n = it.next();
	            	System.out.println("next : "+n);
	            }
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}

}
