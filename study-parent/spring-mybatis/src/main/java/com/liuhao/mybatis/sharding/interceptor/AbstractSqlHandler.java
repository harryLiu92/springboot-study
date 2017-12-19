package com.liuhao.mybatis.sharding.interceptor;

import java.util.Map;

import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import com.google.common.collect.Maps;
import com.liuhao.mybatis.sharding.parser.SqlParser;
import com.liuhao.mybatis.sharding.parser.impl.DeleteParser;
import com.liuhao.mybatis.sharding.parser.impl.InsertParser;
import com.liuhao.mybatis.sharding.parser.impl.SelectParser;
import com.liuhao.mybatis.sharding.parser.impl.UpdateParser;

import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.update.Update;

public abstract class AbstractSqlHandler {

	protected static Map<Class<?>, SqlParser> parserMap = Maps.newHashMap(); 	    

	protected static Map<String, String> keyMap = Maps.newHashMap(); 	    

	private MetaObject metaStatementHandler;
	
	static {
		parserMap.put(Select.class, new SelectParser());
	    parserMap.put(Insert.class, new InsertParser());
	    parserMap.put(Update.class, new UpdateParser());
	    parserMap.put(Delete.class, new DeleteParser());

        keyMap.put("mappedStatement", "delegate.mappedStatement");
        keyMap.put("boundSql", "delegate.boundSql");
        keyMap.put("rowBounds", "delegate.rowBounds");
        keyMap.put("sql", "delegate.boundSql.sql");
	}

	public void register(RoutingStatementHandler statementHandler) {
		SqlInterceptor.providers.add(this);

		metaStatementHandler = SystemMetaObject.forObject(statementHandler);
	}

	public Object getValue(String key) {
		return metaStatementHandler.getValue(keyMap.get(key));
	}

	public abstract void doInterceptor();
}
