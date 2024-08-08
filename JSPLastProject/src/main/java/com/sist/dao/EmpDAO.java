package com.sist.dao;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.vo.*;
public class EmpDAO {
   private static SqlSessionFactory ssf;
   static
   {
	   ssf=CreateSqlSessionFactory.getSsf();
   }
   
   public static List<EmpVO> empListData()
   {
	   SqlSession session=ssf.openSession();
	   List<EmpVO> list=session.selectList("empListData");
	   session.close();
	   return list;
   }
}
