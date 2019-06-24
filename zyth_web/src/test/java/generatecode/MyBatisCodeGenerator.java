package generatecode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

public class MyBatisCodeGenerator {
	public static void main(String[] args) throws Exception {
		System.out.println("+++++++++generate begin++++++++++");
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		ConfigurationParser cp = new ConfigurationParser(warnings);
		String path = MyBatisCodeGenerator.class.getResource("").getPath()
				.substring(1);
		String confPath = "D:\\ylx\\dt\\zyth_web\\src\\test\\java\\generatecode\\"+"MBG_configuration.xml";
		System.out.println(confPath);
		File configFile = new File(confPath);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
				callback, warnings);
		myBatisGenerator.generate(null);
		for (String str : warnings) {
			System.out.println(str);
		}
		System.out.println("+++++++++generate end+++++++++++");
	}


}
