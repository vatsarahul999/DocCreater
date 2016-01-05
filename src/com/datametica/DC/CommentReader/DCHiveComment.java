package com.datametica.DC.CommentReader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DCHiveComment implements DCCommentIdentifier {
	Pattern p1 = Pattern.compile("[-][-][@][@][^~,^\\n]*[\\n]");
	Matcher m1;

	@Override
	public String readComments(String Contents) {
		m1 = p1.matcher(Contents);
		StringBuffer sb = new StringBuffer();
		while (m1.find()) {
			sb.append(Contents.substring(m1.start() + 4, m1.end()));
		}

		String res = sb.toString();
		return res;
	}

}
