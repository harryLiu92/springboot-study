package com.liuhao.user.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.liuhao.user.dao.BaseJdbcDao;
import com.liuhao.user.jdbc.Conn;

/**
 * @author liuhao
 * @com.cpic.shz.repository.base.impl BaseinformixDaoImpl.java
 * @2015��2��10�� @����10:58:53
 * @version 1.0
 * @comments DAO�������h�Ĳ���b T��ʵ����
 */
@Repository
public class BaseJdbcDaoImpl<T extends Serializable> implements BaseJdbcDao<T> {

	Logger log = Logger.getLogger(BaseJdbcDaoImpl.class);

	private String sql;
	private Connection c;
	private Statement st;

	public ArrayList<T> findEntity(String sql, Class<T> clazz) {
		Connection conn = new Conn().getConn();

		PreparedStatement ps = null;

		ResultSet rs = null;

		ArrayList<T> list = Lists.newArrayList();

		try {

			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {

				// һ��Ҫ���һ���µ�Objectʵ�� ��Ȼlist.add(object)��ʱ��
				// �����object����ǰ��object����ͬ��class���Ժ͵�ַ,��list�и�����ǰ������
				T t = clazz.newInstance();

				Field[] fields = clazz.getDeclaredFields();
				for (Field field : fields) {

					if ("serialVersionUID".equals(field.getName()))
						continue;

					field.setAccessible(true);

					StringBuilder methodName = new StringBuilder();
					methodName.append("get");
					String fieldTypeName = field.getType().toString();
//					fieldTypeName = StringUtil.captureName2(fieldTypeName
//							.substring(fieldTypeName.lastIndexOf(".") + 1));
					methodName.append("Integer".equals(fieldTypeName) ? "Int"
							: fieldTypeName);
					// getString,getInt,getDate
					Object rsValue = rs
							.getClass()
							.getDeclaredMethod(methodName.toString(),
									String.class).invoke(rs, field.getName());

					if (field.getType() == String.class)
//						rsValue = StringUtil.trim((String) rsValue);
					field.set(t, rsValue);
				}
				list.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public Object findObject(Class<T> clazz) {
		Connection conn = new Conn().getConn();

		PreparedStatement ps = null;

		ResultSet rs = null;

		Object object = new Object();
		try {

			log.debug("findObject- " + clazz.getName() + ":" + sql);

			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				object = rs.getObject(1) == null ? null : rs.getObject(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return object;
	}

	// ��ɾ��
	public int insert(String sql) {
		return excuteUpdate("insert :", sql);
	}

	public int delete(String sql) {
		return excuteUpdate("delete :", sql);
	}

	public int update(String sql) {
		return excuteUpdate("update :", sql);
	}

	private int excuteUpdate(String str, String sql) {
		Connection conn = new Conn().getConn();

		PreparedStatement ps = null;

		int count = -1;

		try {
			log.debug(str + sql);

			ps = conn.prepareStatement(sql);

			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	// ��������
	public void createStatement() {
		this.c = new Conn().getConn();

		try {
			this.c.setAutoCommit(false);
			this.st = c.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addBatch(String sql) {
		try {
			log.debug("addBatch :" + sql);
			this.st.addBatch(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int executeBatch() {
		int[] count = {};
		try {
			count = this.st.executeBatch();
			this.c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (this.st != null)
					this.st.close();
				if (this.c != null)
					this.c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count.length;
	}

	public String getSQL() {
		return this.sql;
	}

	public static void main(String[] args) {
		// Connection conn = new Conn().getConn();
		//
		// Appuser appuser = new Appuser();
		// Class clazz = appuser.getClass();
		//
		// PreparedStatement ps = null;
		//
		// ResultSet rs = null;
		//
		// ArrayList<Appuser> list =Lists.newArrayList();
		//
		// try {
		//
		// ps =
		// conn.prepareStatement("select  * from shz_appusers where username = '����' ");
		//
		// rs = ps.executeQuery();
		//
		// while (rs.next()) {
		//
		// // һ��Ҫ���һ���µ�Objectʵ�� ��Ȼlist.add(object)��ʱ��
		// // �����object����ǰ��object����ͬ��class���Ժ͵�ַ,��list�и�����ǰ������
		// Appuser t = (Appuser) clazz.newInstance();
		//
		// Field[] fields = clazz.getDeclaredFields();
		// Appuser o = new Appuser();
		// for (Field field : fields) {
		//
		// if("serialVersionUID".equals(field.getName()))
		// continue;
		//
		// field.setAccessible(true);
		//
		// // System.out.println(field.getType()+"  "+
		// rs.getString(field.getName()));
		// // String value = StringUtil.trim(rs.getString(field.getName()));
		// // if(value!=null){
		// // Constructor constructor =
		// field.getType().getDeclaredConstructor(String.class);
		// // field.set(o, constructor.newInstance(value));
		// // }else{
		// // field.set(o, null);
		// // }
		//
		// StringBuilder methodName = new StringBuilder();
		// methodName.append("get");
		// String fieldTypeName = field.getType().toString();
		// fieldTypeName =
		// StringUtil.captureName2(fieldTypeName.substring(fieldTypeName.lastIndexOf(".")+1));
		// methodName.append("Integer".equals(fieldTypeName)?"Int":fieldTypeName);
		// //getString,getInt,getDate
		// System.out.println(methodName.toString());
		// System.out.println(field.getName());
		// Object rsValue =
		// rs.getClass().getDeclaredMethod(methodName.toString(), String.class)
		// .invoke(rs, field.getName());
		//
		// if(field.getType()==String.class)
		// rsValue = StringUtil.trim((String)rsValue);
		// field.set(o, rsValue);
		// }
		// list.add(o);
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// } finally {
		// try {
		// if (rs != null)
		// rs.close();
		// if (ps != null)
		// ps.close();
		// if (conn != null)
		// conn.close();
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		// }
		//
		// for(Appuser a :list){
		// System.out.println(a);
		// }
	}
}
