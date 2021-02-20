package com.github.mikeliyes.mongobatis;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;

import com.github.mikeliyes.mongobatis.io.Resources;
import com.github.mikeliyes.mongobatis.session.Session;
import com.github.mikeliyes.mongobatis.session.ShellSessionFactory;
import com.github.mikeliyes.mongobatis.session.ShellSessionFactoryBuilder;

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
	            MongoBatisGoodsDao dao = shellSession.getDao(MongoBatisGoodsDao.class);
	            
	            List<Document>  list = dao.findPage(9);
	            Iterator<Document> it = list.iterator();
	            while(it.hasNext()){
	            	Document n = it.next();
	            	System.out.println("next : "+n);
	            }
	            
	            MongoBatisGoods goods = new MongoBatisGoods();
	            goods.setPrice(9d);
	            goods.setWeight(2d);
	            List<Document>  glist = dao.findGoodsPage(goods);
	            Iterator<Document> it2 = glist.iterator();
	            while(it2.hasNext()){
	            	Document n = it2.next();
	            	System.out.println("it2 next : "+n);
	            }
	            
	            List<Document>  nlist = dao.findNoParam();
	            Iterator<Document> it3 = nlist.iterator();
	            while(it3.hasNext()){
	            	Document n = it3.next();
	            	System.out.println("it3 next : "+n);
	            }
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}

}
