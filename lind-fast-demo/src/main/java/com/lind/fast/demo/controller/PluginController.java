package com.lind.fast.demo.controller;

import com.lind.common.core.util.R;
import com.lind.common.oss.service.OssTemplate;
import com.lind.plugin.anti_reptile.annotation.AntiReptile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author lind
 * @date 2022/9/2 15:26
 * @since 1.0.0
 */
@RestController
@RequestMapping("plugin")
public class PluginController {

	@Autowired
	private OssTemplate template;

	@AntiReptile
	@GetMapping("anti")
	public String demo() {
		return "Hello，World!";
	}

	@PostMapping("/upload")
	public R upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
		template.putObject("lind-demo", file.getName(), file.getInputStream());
		return R.ok();
	}

}
