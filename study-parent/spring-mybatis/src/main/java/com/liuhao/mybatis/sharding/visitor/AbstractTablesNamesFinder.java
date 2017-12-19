package com.liuhao.mybatis.sharding.visitor;

import java.util.ArrayList;
import java.util.List;

import net.sf.jsqlparser.expression.AllComparisonExpression;
import net.sf.jsqlparser.expression.AnalyticExpression;
import net.sf.jsqlparser.expression.AnyComparisonExpression;
import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.CaseExpression;
import net.sf.jsqlparser.expression.CastExpression;
import net.sf.jsqlparser.expression.DateValue;
import net.sf.jsqlparser.expression.DoubleValue;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.ExpressionVisitor;
import net.sf.jsqlparser.expression.ExtractExpression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.HexValue;
import net.sf.jsqlparser.expression.IntervalExpression;
import net.sf.jsqlparser.expression.JdbcNamedParameter;
import net.sf.jsqlparser.expression.JdbcParameter;
import net.sf.jsqlparser.expression.JsonExpression;
import net.sf.jsqlparser.expression.KeepExpression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.MySQLGroupConcat;
import net.sf.jsqlparser.expression.NullValue;
import net.sf.jsqlparser.expression.NumericBind;
import net.sf.jsqlparser.expression.OracleHierarchicalExpression;
import net.sf.jsqlparser.expression.OracleHint;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.RowConstructor;
import net.sf.jsqlparser.expression.SignedExpression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.TimeValue;
import net.sf.jsqlparser.expression.TimestampValue;
import net.sf.jsqlparser.expression.UserVariable;
import net.sf.jsqlparser.expression.WhenClause;
import net.sf.jsqlparser.expression.WithinGroupExpression;
import net.sf.jsqlparser.expression.operators.arithmetic.Addition;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseAnd;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseOr;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseXor;
import net.sf.jsqlparser.expression.operators.arithmetic.Concat;
import net.sf.jsqlparser.expression.operators.arithmetic.Division;
import net.sf.jsqlparser.expression.operators.arithmetic.Modulo;
import net.sf.jsqlparser.expression.operators.arithmetic.Multiplication;
import net.sf.jsqlparser.expression.operators.arithmetic.Subtraction;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.Between;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExistsExpression;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.GreaterThan;
import net.sf.jsqlparser.expression.operators.relational.GreaterThanEquals;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.IsNullExpression;
import net.sf.jsqlparser.expression.operators.relational.ItemsListVisitor;
import net.sf.jsqlparser.expression.operators.relational.LikeExpression;
import net.sf.jsqlparser.expression.operators.relational.Matches;
import net.sf.jsqlparser.expression.operators.relational.MinorThan;
import net.sf.jsqlparser.expression.operators.relational.MinorThanEquals;
import net.sf.jsqlparser.expression.operators.relational.MultiExpressionList;
import net.sf.jsqlparser.expression.operators.relational.NotEqualsTo;
import net.sf.jsqlparser.expression.operators.relational.RegExpMatchOperator;
import net.sf.jsqlparser.expression.operators.relational.RegExpMySQLOperator;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.SetStatement;
import net.sf.jsqlparser.statement.StatementVisitor;
import net.sf.jsqlparser.statement.Statements;
import net.sf.jsqlparser.statement.alter.Alter;
import net.sf.jsqlparser.statement.create.index.CreateIndex;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import net.sf.jsqlparser.statement.create.view.CreateView;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.drop.Drop;
import net.sf.jsqlparser.statement.execute.Execute;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.merge.Merge;
import net.sf.jsqlparser.statement.replace.Replace;
import net.sf.jsqlparser.statement.select.AllColumns;
import net.sf.jsqlparser.statement.select.AllTableColumns;
import net.sf.jsqlparser.statement.select.FromItemVisitor;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.LateralSubSelect;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.SelectItem;
import net.sf.jsqlparser.statement.select.SelectItemVisitor;
import net.sf.jsqlparser.statement.select.SelectVisitor;
import net.sf.jsqlparser.statement.select.SetOperationList;
import net.sf.jsqlparser.statement.select.SubJoin;
import net.sf.jsqlparser.statement.select.SubSelect;
import net.sf.jsqlparser.statement.select.TableFunction;
import net.sf.jsqlparser.statement.select.ValuesList;
import net.sf.jsqlparser.statement.select.WithItem;
import net.sf.jsqlparser.statement.truncate.Truncate;
import net.sf.jsqlparser.statement.update.Update;

public class AbstractTablesNamesFinder implements SelectVisitor, FromItemVisitor, ExpressionVisitor, ItemsListVisitor,
SelectItemVisitor, StatementVisitor {

	private List<String> tables = new ArrayList<String>();
	private List<String> otherItemNames = new ArrayList<String>();

	public List<String> getTableList(Delete delete) {
		delete.accept(this);
		return this.tables;
	}

	public List<String> getTableList(Insert insert) {
		insert.accept(this);
		return this.tables;
	}

	public List<String> getTableList(Replace replace) {
		replace.accept(this);
		return this.tables;
	}

	public List<String> getTableList(Select select) {
		select.accept(this);
		return this.tables;
	}

	public List<String> getTableList(Update update) {
		update.accept(this);
		return this.tables;
	}

	public List<String> getTableList(CreateTable create) {
		create.accept(this);
		return this.tables;
	}

	public List<String> getTableList(Expression expr) {
		expr.accept(this);
		return this.tables;
	}

	public void visit(Select select) {
		if (select.getWithItemsList() != null) {
			for (WithItem withItem : select.getWithItemsList()) {
				withItem.accept(this);
			}
		}
		select.getSelectBody().accept(this);
	}

	public void visit(WithItem withItem) {
		this.otherItemNames.add(withItem.getName().toLowerCase());
		withItem.getSelectBody().accept(this);
	}

	public void visit(PlainSelect plainSelect) {
		if (plainSelect.getSelectItems() != null) {
			for (SelectItem item : plainSelect.getSelectItems()) {
				item.accept(this);
			}
		}
		plainSelect.getFromItem().accept(this);
		if (plainSelect.getJoins() != null) {
			for (Join join : plainSelect.getJoins()) {
				join.getRightItem().accept(this);
			}
		}
		if (plainSelect.getWhere() != null) {
			plainSelect.getWhere().accept(this);
		}
		if (plainSelect.getOracleHierarchical() != null)
			plainSelect.getOracleHierarchical().accept(this);
	}

	public void visit(Table table) {

		String tableWholeName = table.getFullyQualifiedName();
		if ((!this.otherItemNames.contains(tableWholeName.toLowerCase())) && (!this.tables.contains(tableWholeName)))
			this.tables.add(tableWholeName);
	}

	public void visit(SubSelect subSelect) {
		subSelect.getSelectBody().accept(this);
	}

	public void visit(Addition addition) {
		visitBinaryExpression(addition);
	}

	public void visit(AndExpression andExpression) {
		visitBinaryExpression(andExpression);
	}

	public void visit(Between between) {
		between.getLeftExpression().accept(this);
		between.getBetweenExpressionStart().accept(this);
		between.getBetweenExpressionEnd().accept(this);
	}

	public void visit(Column tableColumn) {
	}

	public void visit(Division division) {
		visitBinaryExpression(division);
	}

	public void visit(DoubleValue doubleValue) {
	}

	public void visit(EqualsTo equalsTo) {
		visitBinaryExpression(equalsTo);
	}

	public void visit(Function function) {
	}

	public void visit(GreaterThan greaterThan) {
		visitBinaryExpression(greaterThan);
	}

	public void visit(GreaterThanEquals greaterThanEquals) {
		visitBinaryExpression(greaterThanEquals);
	}

	public void visit(InExpression inExpression) {
		inExpression.getLeftExpression().accept(this);
		inExpression.getRightItemsList().accept(this);
	}

	public void visit(SignedExpression signedExpression) {
		signedExpression.getExpression().accept(this);
	}

	public void visit(IsNullExpression isNullExpression) {
	}

	public void visit(JdbcParameter jdbcParameter) {
	}

	public void visit(LikeExpression likeExpression) {
		visitBinaryExpression(likeExpression);
	}

	public void visit(ExistsExpression existsExpression) {
		existsExpression.getRightExpression().accept(this);
	}

	public void visit(LongValue longValue) {
	}

	public void visit(MinorThan minorThan) {
		visitBinaryExpression(minorThan);
	}

	public void visit(MinorThanEquals minorThanEquals) {
		visitBinaryExpression(minorThanEquals);
	}

	public void visit(Multiplication multiplication) {
		visitBinaryExpression(multiplication);
	}

	public void visit(NotEqualsTo notEqualsTo) {
		visitBinaryExpression(notEqualsTo);
	}

	public void visit(NullValue nullValue) {
	}

	public void visit(OrExpression orExpression) {
		visitBinaryExpression(orExpression);
	}

	public void visit(Parenthesis parenthesis) {
		parenthesis.getExpression().accept(this);
	}

	public void visit(StringValue stringValue) {
	}

	public void visit(Subtraction subtraction) {
		visitBinaryExpression(subtraction);
	}

	public void visitBinaryExpression(BinaryExpression binaryExpression) {
		binaryExpression.getLeftExpression().accept(this);
		binaryExpression.getRightExpression().accept(this);
	}

	public void visit(ExpressionList expressionList) {
		for (Expression expression : expressionList.getExpressions())
			expression.accept(this);
	}

	public void visit(DateValue dateValue) {
	}

	public void visit(TimestampValue timestampValue) {
	}

	public void visit(TimeValue timeValue) {
	}

	public void visit(CaseExpression caseExpression) {
	}

	public void visit(WhenClause whenClause) {
	}

	public void visit(AllComparisonExpression allComparisonExpression) {
		allComparisonExpression.getSubSelect().getSelectBody().accept(this);
	}

	public void visit(AnyComparisonExpression anyComparisonExpression) {
		anyComparisonExpression.getSubSelect().getSelectBody().accept(this);
	}

	public void visit(SubJoin subjoin) {
		subjoin.getLeft().accept(this);
		subjoin.getJoin().getRightItem().accept(this);
	}

	public void visit(Concat concat) {
		visitBinaryExpression(concat);
	}

	public void visit(Matches matches) {
		visitBinaryExpression(matches);
	}

	public void visit(BitwiseAnd bitwiseAnd) {
		visitBinaryExpression(bitwiseAnd);
	}

	public void visit(BitwiseOr bitwiseOr) {
		visitBinaryExpression(bitwiseOr);
	}

	public void visit(BitwiseXor bitwiseXor) {
		visitBinaryExpression(bitwiseXor);
	}

	public void visit(CastExpression cast) {
		cast.getLeftExpression().accept(this);
	}

	public void visit(Modulo modulo) {
		visitBinaryExpression(modulo);
	}

	public void visit(AnalyticExpression analytic) {
	}

	public void visit(SetOperationList list) {
		for (SelectBody plainSelect : list.getSelects())
			plainSelect.accept(this);
	}

	public void visit(ExtractExpression eexpr) {
	}

	public void visit(LateralSubSelect lateralSubSelect) {
		lateralSubSelect.getSubSelect().getSelectBody().accept(this);
	}

	public void visit(MultiExpressionList multiExprList) {
		for (ExpressionList exprList : multiExprList.getExprList())
			exprList.accept(this);
	}

	public void visit(ValuesList valuesList) {
	}

	public void visit(IntervalExpression iexpr) {
	}

	public void visit(JdbcNamedParameter jdbcNamedParameter) {
	}

	public void visit(OracleHierarchicalExpression oexpr) {
		if (oexpr.getStartExpression() != null) {
			oexpr.getStartExpression().accept(this);
		}
		if (oexpr.getConnectExpression() != null)
			oexpr.getConnectExpression().accept(this);
	}

	public void visit(RegExpMatchOperator rexpr) {
		visitBinaryExpression(rexpr);
	}

	public void visit(RegExpMySQLOperator rexpr) {
		visitBinaryExpression(rexpr);
	}

	public void visit(JsonExpression jsonExpr) {
	}

	public void visit(AllColumns allColumns) {
	}

	public void visit(AllTableColumns allTableColumns) {
	}

	public void visit(SelectExpressionItem item) {
		item.getExpression().accept(this);
	}

	public void visit(WithinGroupExpression wgexpr) {
	}

	public void visit(UserVariable var) {
	}

	public void visit(NumericBind bind) {
	}

	public void visit(KeepExpression aexpr) {
	}

	public void visit(MySQLGroupConcat groupConcat) {
	}

	public void visit(Delete delete) {
		this.tables.add(delete.getTable().getName());

		if (delete.getWhere() != null)
			delete.getWhere().accept(this);
	}

	public void visit(Update update) {
		for (Table table : update.getTables()) {
			this.tables.add(table.getName());
		}
		if (update.getExpressions() != null) {
			for (Expression expression : update.getExpressions()) {
				expression.accept(this);
			}
		}

		if (update.getFromItem() != null) {
			update.getFromItem().accept(this);
		}
		if (update.getJoins() != null) {
			for (Join join : update.getJoins()) {
				join.getRightItem().accept(this);
			}
		}
		if (update.getWhere() != null)
			update.getWhere().accept(this);
	}

	public void visit(Insert insert) {
		if (insert.getItemsList() != null) {
			insert.getItemsList().accept(this);
		}
		if (insert.getSelect() != null)
			visit(insert.getSelect());
	}

	public void visit(Replace replace) {
		this.tables.add(replace.getTable().getName());
		if (replace.getExpressions() != null) {
			for (Expression expression : replace.getExpressions()) {
				expression.accept(this);
			}
		}
		if (replace.getItemsList() != null)
			replace.getItemsList().accept(this);
	}

	public void visit(Drop drop) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void visit(Truncate truncate) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void visit(CreateIndex createIndex) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void visit(CreateTable create) {
		this.tables.add(create.getTable().getFullyQualifiedName());
		if (create.getSelect() != null)
			create.getSelect().accept(this);
	}

	public void visit(CreateView createView) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void visit(Alter alter) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void visit(Statements stmts) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void visit(Execute execute) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void visit(SetStatement set) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void visit(RowConstructor rowConstructor) {
		for (Expression expr : rowConstructor.getExprList().getExpressions())
			expr.accept(this);
	}

	public void visit(HexValue hexValue) {
	}

	public void visit(Merge arg0) {
	}

	public void visit(OracleHint arg0) {
	}

	public void visit(TableFunction arg0) {
	}
}
