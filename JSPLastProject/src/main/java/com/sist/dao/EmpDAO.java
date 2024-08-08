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
   /*
    *   <select id="empEnameList" resultType="String">
         SELECT ename FROM emp
        </select>
    */
   public static List<String> empEnameList()
   {
	   SqlSession session=ssf.openSession();
	   List<String> list=session.selectList("empEnameList");
	   session.close();
	   return list;
   }
   public static List<EmpVO> empFindData(Map map)
   {
	   SqlSession session=ssf.openSession();
	   List<EmpVO> list=session.selectList("empFindData",map);
	   session.close();
	   return list;
   }
}
