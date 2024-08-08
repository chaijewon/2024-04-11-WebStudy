package com.sist.dao;
import java.util.*;

import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.dao.*;
public class MusicDAO {
  private static SqlSessionFactory ssf;
  static
  {
	  ssf=CreateSqlSessionFactory.getSsf();
  }
}
