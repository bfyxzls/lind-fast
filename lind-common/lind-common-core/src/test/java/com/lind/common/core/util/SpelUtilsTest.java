package com.lind.common.core.util;

import org.junit.jupiter.api.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lind
 * @date 2022/12/22 14:20
 * @since 1.0.0
 */
public class SpelUtilsTest {

	@Test
	public void analysis() {
		List<Integer> primes = new ArrayList<Integer>();
		primes.addAll(Arrays.asList(2, 3, 5, 7, 11, 13, 17));
		ExpressionParser parser = new SpelExpressionParser();
		StandardEvaluationContext context = new StandardEvaluationContext();
		context.setVariable("primes", primes);
		List<Integer> primesGreaterThanTen = (List<Integer>) parser.parseExpression("#primes.?[#this>10]")
				.getValue(context);
		primesGreaterThanTen.forEach(System.out::println);
	}

	@Test
	public void spelSetValueTest() {
		// 1. 设置变量
		Car car = new Car("比亚迪");
		StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext();
		standardEvaluationContext.setVariable("car", car);
		// 2. 创建解析器
		ExpressionParser expressionParser = new SpelExpressionParser();

		// 3. 执行表达式,取得表达式结果
		String carExpressionStr = "#car.name";
		Expression carExpression = expressionParser.parseExpression(carExpressionStr);

		// 4. 修改变量值
		String nowValue = carExpression.getValue(standardEvaluationContext, String.class);
		carExpression.setValue(standardEvaluationContext, nowValue + "-海豹");
		System.out.println(car.getName());
	}

	// 例如: SpelUtils.parseExpression("'Hello '+#msg", new String[]{"msg"}, new
	// Object[]{"World"});
	@Test
	public void utils() {
		Car car = new Car("普拉多");
		try {
			Object[] params = new Object[] { car };
			String result = SpelUtils.parseValueToString(car, Car.class.getMethod("print", String.class),
					new String[] { "dudu" }, "'Hello '+#msg");
			System.out.println("result=" + result);
		}
		catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}

	}

	static class Car {

		private String name;

		public Car(String name) {
			this.name = name;
		}

		public String print(String msg) {
			return "打印 " + msg;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

}
