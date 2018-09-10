package test;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

public class TestMbg {

	public static void main(String[] args) throws Exception {
		// MBG ִ�й����еľ�����Ϣ
		List<String> warnings = new ArrayList<String>();
		// �����ɵĴ����ظ�ʱ������ԭ����
		boolean overwrite = true;
		// ��ȡMBG�����ļ�
		InputStream is = TestMbg.class.getClassLoader().getResourceAsStream("mbg.xml");
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(is);
		is.close();

		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		// ���� MBG
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		// ִ�����ɴ���
		myBatisGenerator.generate(null);
		// ���������Ϣ
		for (String warning : warnings) {
			System.out.println(warning);
		}
	}

	//����mbg���ɵĴ���
	public void testMbg() throws Exception {
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		File configFile = new File("mbg.xml");
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
				callback, warnings);
		myBatisGenerator.generate(null);
	}

}