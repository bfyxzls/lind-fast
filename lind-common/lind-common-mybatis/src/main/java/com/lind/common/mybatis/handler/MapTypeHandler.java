package com.lind.common.mybatis.handler;

import cn.hutool.json.JSONUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

/**
 * k/v字典map到字符串的转换.
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(Map.class)
public class MapTypeHandler extends BaseTypeHandler<Map> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Map parameter, JdbcType jdbcType) throws SQLException {
		ps.setString(i, JSONUtil.toJsonStr(parameter));
	}

	@Override
	public Map getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return Optional.ofNullable(rs.getString(columnName)).map(o -> JSONUtil.toBean(o, Map.class)).orElse(null);
	}

	@Override
	public Map getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return Optional.ofNullable(rs.getString(columnIndex)).map(o -> JSONUtil.toBean(o, Map.class)).orElse(null);
	}

	@Override
	public Map getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return Optional.ofNullable(cs.getString(columnIndex)).map(o -> JSONUtil.toBean(o, Map.class)).orElse(null);
	}

}
