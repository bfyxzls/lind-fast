package com.lind.fast.demo.controller;

import com.lind.fast.demo.entity.Demo;
import com.lind.plugin.excel.annotation.RequestExcel;
import com.lind.plugin.excel.annotation.ResponseExcel;
import com.lind.plugin.excel.annotation.Sheet;
import com.lind.plugin.excel.vo.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lind
 * @date 2022/8/31 11:28
 * @since 1.0.0
 */
@RestController
@Slf4j
@RequestMapping("excel")
public class ExcelController {

	@PostMapping("/upload")
	public void upload(@RequestExcel List<Demo> dataList, BindingResult bindingResult) {
		// JSR 303 校验通用校验获取失败的数据
		List<ErrorMessage> errorMessageList = (List<ErrorMessage>) bindingResult.getTarget();
		dataList.stream().map(o -> o.getUsername()).forEach(log::info);
	}

	@ResponseExcel(name = "test", sheets = @Sheet(sheetName = "testSheet1"))
	@GetMapping("/e1")
	public List<Demo> e1() {
		List<Demo> dataList = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			Demo data = new Demo();
			data.setUsername("tr1" + i);
			data.setPassword("tr2" + i);
			dataList.add(data);
		}
		return dataList;
	}

}
