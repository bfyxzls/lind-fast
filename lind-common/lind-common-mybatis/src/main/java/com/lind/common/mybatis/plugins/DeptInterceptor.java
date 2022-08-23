package com.lind.common.mybatis.plugins;

import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.parser.JsqlParserSupport;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.lind.common.mybatis.util.DPHelper;
import com.lind.common.mybatis.base.CurrentAuditor;
import lombok.RequiredArgsConstructor;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据权限过滤.
 */
@RequiredArgsConstructor
@Intercepts({ @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = { Statement.class }) })
public class DeptInterceptor extends JsqlParserSupport implements InnerInterceptor {

	private final static Column USER_PERMISSION = new Column("dept_id");

	private final CurrentAuditor currentAuditor;

	@Override
	public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds,
			ResultHandler resultHandler, BoundSql boundSql) {
		// 需处理方法
		if (handleMethod()) {
			PluginUtils.MPBoundSql mpBs = PluginUtils.mpBoundSql(boundSql);
			mpBs.sql(parserSingle(mpBs.sql(), null));
		}
	}

	@Override
	protected void processSelect(Select select, int index, String sql, Object obj) {
		SelectBody selectBody = select.getSelectBody();
		if (selectBody == null) {
			return;
		}
		processPlainSelect((PlainSelect) selectBody);
	}

	/**
	 * 处理 PlainSelect
	 */
	protected void processPlainSelect(PlainSelect plainSelect) {
		plainSelect.setWhere(builderExpression(plainSelect.getWhere()));
	}

	/**
	 * 处理条件
	 */
	protected Expression builderExpression(Expression currentExpression) {

		InExpression userTypeExpression = new InExpression();
		userTypeExpression.setLeftExpression(USER_PERMISSION);
		ExpressionList expressionList = new ExpressionList();
		List<Expression> expressionList1 = new ArrayList<>();
		currentAuditor.getDeptIdList().forEach(o -> expressionList1.add(new LongValue(o)));
		expressionList.setExpressions(expressionList1);
		userTypeExpression.setRightItemsList(expressionList);
		if (currentExpression == null) {
			return userTypeExpression;
		}
		return new AndExpression(currentExpression, userTypeExpression);
	}

	/**
	 * 是否需要进行数据权限过滤
	 * @return
	 */
	private Boolean handleMethod() {
		return DPHelper.getLocalDataPermissions();

	}

}
