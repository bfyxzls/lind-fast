package com.lind.common.core.util;

import org.springframework.util.ResourceUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.springframework.util.Assert.notNull;

/**
 * 文件读取工具.
 */
public class FileUtils {

	public static final String OBJ_NO_NULL = "对象不能为空";

	private static final File SYSTEM_TEMP_DIR = SystemUtils.tempDir();
	static Function<String, String> resourceFun;

	/**
	 * 系统的临时文件夹
	 */
	private static File tempDir;

	static {
		updateTmpDir("ballcat");
	}

	/**
	 * 禁止实例化.
	 */
	private FileUtils() {
	}

	/**
	 * 设置文件获取的路径为资源resource路径.
	 */
	public static void setResourcePath() {
		resourceFun = (path) -> {
			try {
				return ResourceUtils.getFile("classpath:" + path).getPath();
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			return null;
		};
	}

	/**
	 * IO方式读取文件到对象.
	 * @param name .
	 * @return .
	 */
	public static byte[] readResourceByteArray(String name) throws IOException {
		if (resourceFun != null) {
			name = resourceFun.apply(name);
		}
		File f = new File(name);
		notNull(f, OBJ_NO_NULL);

		ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(f));
			int bufSize = 1024;
			byte[] buffer = new byte[bufSize];
			int len = 0;
			while (-1 != (len = in.read(buffer, 0, bufSize))) {
				bos.write(buffer, 0, len);
			}
			return bos.toByteArray();
		}
		catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			try {
				if (in != null) {
					in.close();
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			bos.close();
		}
	}

	/**
	 * NIO方式读取.
	 * @param name .
	 * @return .
	 * @throws IOException .
	 */
	public static byte[] readResourceByteArrayNIO(String name) throws IOException {
		if (resourceFun != null) {
			name = resourceFun.apply(name);
		}
		File f = new File(name);
		notNull(f, OBJ_NO_NULL);
		FileChannel channel = null;
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(f);
			notNull(fs, OBJ_NO_NULL);
			channel = fs.getChannel();
			ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
			while ((channel.read(byteBuffer)) > 0) {
				System.out.println("reading");
			}
			return byteBuffer.array();
		}
		catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			try {
				if (channel != null) {
					channel.close();
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (fs != null) {
					fs.close();
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 大文件读取.
	 * @param name .
	 * @return .
	 * @throws IOException .
	 */
	public static byte[] readResourceByteArrayBigFileNIO(String name) throws IOException {
		FileChannel fc = null;
		try {
			if (resourceFun != null) {
				name = resourceFun.apply(name);
			}
			File f = new File(name);
			notNull(f, OBJ_NO_NULL);

			fc = new RandomAccessFile(f.getAbsoluteFile(), "r").getChannel();
			MappedByteBuffer byteBuffer = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size()).load();
			System.out.println(byteBuffer.isLoaded());
			byte[] result = new byte[(int) fc.size()];
			if (byteBuffer.remaining() > 0) {
				byteBuffer.get(result, 0, byteBuffer.remaining());
			}
			return result;
		}
		catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			try {
				if (fc != null) {
					fc.close();
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * 将对象写到资源文件.
	 */
	public static void writeResourceFromByteArrayNIO(String path, byte[] obj) {
		FileOutputStream fos = null;
		try {
			File file = new File(path);
			notNull(file, OBJ_NO_NULL);
			if (!file.exists()) {
				if (!file.createNewFile()) {
					throw new IllegalArgumentException("文件建立不成功");
				}
			}
			fos = new FileOutputStream(file.getAbsoluteFile());
			notNull(fos, OBJ_NO_NULL);
			FileChannel channel = fos.getChannel();
			ByteBuffer src = ByteBuffer.wrap(obj);
			// 字节缓冲的容量和limit会随着数据长度变化，不是固定不变的
			System.out.println("初始化容量和limit：" + src.capacity() + "," + src.limit());
			int length = 0;
			while ((length = channel.write(src)) != 0) {
				/*
				 * 注意，这里不需要clear，将缓冲中的数据写入到通道中后 第二次接着上一次的顺序往下读
				 */
				System.out.println("写入长度:" + length);
			}

		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (fos != null) {
				try {
					fos.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 读取文本文件.
	 * @param filePath
	 */
	public static String readTxtFile(String filePath) {
		try {
			String encoding = "utf-8";
			File file = new File(filePath);
			StringBuffer buffer = new StringBuffer();
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					buffer.append(lineTxt);
				}
				read.close();
				return buffer.toString();
			}
			else {
				throw new IllegalArgumentException("找不到指定的文件");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 通过流的方式读取，注意jar包中的文件只能使用这种方式.
	 * @param inputStream
	 * @return
	 */
	public static String readTxtFile(InputStream inputStream) {
		try {
			String encoding = "utf-8";
			StringBuffer buffer = new StringBuffer();
			if (inputStream != null) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(inputStream, encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					buffer.append(lineTxt);
				}
				read.close();
				inputStream.close();
				return buffer.toString();
			}
			else {
				throw new IllegalArgumentException("找不到指定的文件");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 更新临时文件路径
	 * @author lingting 2021-10-18 17:10
	 */
	public static void updateTmpDir(String dirName) {
		tempDir = new File(SYSTEM_TEMP_DIR, dirName);
	}

	/**
	 * 获取临时文件, 不会创建文件
	 * @author lingting 2021-04-19 10:48
	 */
	public static File getTemplateFile(String name) throws IOException {
		if (!tempDir.exists()) {
			tempDir.mkdirs();
		}

		File file;

		do {
			file = new File(tempDir, System.currentTimeMillis() + "." + name);
		}
		while (!file.createNewFile());

		return file;
	}

	/**
	 * 根据网络路径获取文件输入流
	 */
	public static InputStream getInputStreamByUrlPath(String path) throws IOException {
		// 从文件链接里获取文件流
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// 设置超时间为3秒
		conn.setConnectTimeout(5 * 1000);
		// 得到输入流
		return conn.getInputStream();
	}

	/**
	 * 扫描指定路径下所有文件
	 * @param path 指定路径
	 * @param recursive 是否递归
	 * @return java.util.List<java.lang.String>
	 */
	public static List<String> scanFile(String path, boolean recursive) {
		List<String> list = new ArrayList<>();
		File file = new File(path);
		if (!file.exists()) {
			return list;
		}

		if (file.isFile()) {
			list.add(file.getAbsolutePath());
		}
		// 文件夹
		else {
			File[] files = file.listFiles();

			if (files == null || files.length < 1) {
				return list;
			}

			for (File childFile : files) {
				// 如果递归
				if (recursive && childFile.isDirectory()) {
					list.addAll(scanFile(childFile.getAbsolutePath(), true));
				}
				// 是文件
				else if (childFile.isFile()) {
					list.add(childFile.getAbsolutePath());
				}
			}
		}

		return list;
	}

	/**
	 * 复制文件
	 * @param source 源文件
	 * @param target 目标文件
	 * @param override 如果目标文件已存在是否覆盖
	 * @param options 其他文件复制选项 {@link StandardCopyOption}
	 * @return 目标文件地址
	 */
	public static Path copy(File source, File target, boolean override, CopyOption... options) throws IOException {
		List<CopyOption> list = new ArrayList<>();
		if (override) {
			list.add(StandardCopyOption.REPLACE_EXISTING);
		}

		if (options != null && options.length > 0) {
			list.addAll(Arrays.asList(options));
		}

		return Files.copy(source.toPath(), target.toPath(), list.toArray(new CopyOption[0]));
	}

}
