package com.github.mikeliyes.mongobatis;

import java.io.Reader;

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
	            GoodsMapper mapper = shellSession.getMapper(GoodsMapper.class);
//	            UserEntity user = mapper.getUser(2);
	            System.out.println("");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}

}
