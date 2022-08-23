package com.lind.common.mybatis.handler;

import lombok.SneakyThrows;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据返回时，对字符进行脱敏处理.
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(String.class)
public class SensitiveTypeHandler extends BaseTypeHandler<String> {

	/**
	 * 项目中经常需要将客户信息脱敏屏蔽
	 * @param start 头部保留的个数
	 * @param end 尾部保留的个数
	 * @param data 需要脱敏的数据
	 */
	static String tuoMin(int start, int end, String data) {
		if (data == null || data.trim() == null || data.length() < start + end) {
			return data;// 不符合脱敏条件，原数据返回，也可以抛出异常
		}
		// 先截取保留的前面字符
		StringBuffer sb = new StringBuffer(data.substring(0, start));
		// 每遍历一位加一个*
		for (int i = start; i < data.length() - end; i++) {
			sb.append("*");
		}

		// 添加后面需要保留字符
		sb.append(data.substring(data.length() - end));
		return sb.toString();

	}

	@SneakyThrows
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
			throws SQLException {
		if (parameter == null) {
			ps.setString(i, null);
			return;
		}

		ps.setString(i, parameter);
	}

	@SneakyThrows
	@Override
	public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
		try {
			return tuoMin(3, 3, rs.getString(columnName));
		}
		catch (Exception exception) {
			exception.getStackTrace();
			return null;
		}
	}

	@SneakyThrows
	@Override
	public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		try {
			return tuoMin(3, 3, rs.getString(columnIndex));
		}
		catch (Exception exception) {
			exception.getStackTrace();
			return null;
		}
	}

	@SneakyThrows
	@Override
	public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		try {
			return tuoMin(3, 3, cs.getString(columnIndex));
		}
		catch (Exception exception) {
			exception.getStackTrace();
			return null;
		}
	}

}
