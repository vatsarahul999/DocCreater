package com.datametica.DC.CommentReader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DCShellComment implements DCCommentIdentifier {
	Pattern p1 = Pattern.compile("[#][@][@][^@,^\\n][^~,^\n]*[#]*[\n]");
	Matcher m1;

	@Override
	public String readComments(String Contents) {
		// TODO Auto-generated method stub
		m1 = p1.matcher(Contents);
		StringBuffer sb = new StringBuffer();
		while (m1.find()) {
			sb.append(Contents.substring(m1.start() + 3, m1.end())).append("\n");
		}

		String res = sb.toString();

		return res;
	}

}
