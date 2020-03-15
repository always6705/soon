import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NormalTest {
	public static void main(String args[]) {
		Pattern p = Pattern.compile("SELECT");
		String inString = "SELECT name FROM MASK_01".toUpperCase();
		Matcher m = p.matcher(inString);
		String tmp = m.replaceFirst("SELECT TOP 10000");
		System.out.println(tmp);

		System.out.println(replaceFirstString(inString, "SELECT", "SELECT TOP 10000"));
		System.out.println(includeString(inString, " NAME "));
	}

	public static String replaceFirstString(String temp, String replaced, String replaceString) {
		Pattern pattern = Pattern.compile(replaced);
		Matcher matcher = pattern.matcher(temp);
		temp = matcher.replaceFirst(replaceString);
		return temp;
	}

	public static boolean includeString(String string, String flag) {
		Pattern p = Pattern.compile(".*" + flag + ".*");
		Matcher m = p.matcher(string);
		return m.matches();
	}
}
