package com.github.mikeliyes.mongobatis;

import java.io.Reader;

import com.github.mikeliyes.mongobatis.io.Resources;

public class Test {

	public static void main(String[] args) {
		 try {
	            // 基本mybatis环境
	            // 1.定义mybatis_config文件地址
	            String resources = "mongobatis-config.xml";
	            // 2.获取InputStreamReaderIo流
	            Reader reader = Resources.getResourceAsReader(resources);
//	            // 3.获取SqlSessionFactory
	            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
//	            // 4.获取Session
//	            SqlSession sqlSession = sqlSessionFactory.openSession();
//	            // 5.操作Mapper接口
//	            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//	            UserEntity user = mapper.getUser(2);
	            System.out.println("");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}

}
