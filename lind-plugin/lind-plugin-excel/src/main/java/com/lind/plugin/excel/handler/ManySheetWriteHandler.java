package com.lind.plugin.excel.handler;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.lind.plugin.excel.annotation.ResponseExcel;
import com.lind.plugin.excel.annotation.Sheet;
import com.lind.plugin.excel.config.ExcelConfigProperties;
import com.lind.plugin.excel.enhance.WriterBuilderEnhancer;
import com.lind.plugin.excel.kit.ExcelException;
import org.springframework.beans.factory.ObjectProvider;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author lengleng
 * @author L.cm
 * @date 2020/3/29
 */
public class ManySheetWriteHandler extends AbstractSheetWriteHandler {

	public ManySheetWriteHandler(ExcelConfigProperties configProperties,
                                 ObjectProvider<List<Converter<?>>> converterProvider, WriterBuilderEnhancer excelWriterBuilderEnhance) {
		super(configProperties, converterProvider, excelWriterBuilderEnhance);
	}

	/**
	 * 当且仅当List不为空且List中的元素也是List 才返回true
	 * @param obj 返回对象
	 * @return boolean
	 */
	@Override
	public boolean support(Object obj) {
		if (obj instanceof List) {
			List<?> objList = (List<?>) obj;
			return !objList.isEmpty() && objList.get(0) instanceof List;
		}
		else {
			throw new ExcelException("@ResponseExcel 返回值必须为List类型");
		}
	}

	@Override
	public void write(Object obj, HttpServletResponse response, ResponseExcel responseExcel) {
		List<?> objList = (List<?>) obj;
		ExcelWriter excelWriter = getExcelWriter(response, responseExcel);

		Sheet[] sheets = responseExcel.sheets();
		WriteSheet sheet;
		for (int i = 0; i < sheets.length; i++) {
			List<?> eleList = (List<?>) objList.get(i);
			Class<?> dataClass = eleList.get(0).getClass();
			// 创建sheet
			sheet = this.sheet(sheets[i], dataClass, responseExcel.template(), responseExcel.headGenerator());
			// 填充 sheet
			if (responseExcel.fill()) {
				excelWriter.fill(eleList, sheet);
			}
			else {
				// 写入sheet
				excelWriter.write(eleList, sheet);
			}
		}
		excelWriter.finish();
	}

}
