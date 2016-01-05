package com.datametica.DC.CommentReader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DCPigComment implements DCCommentIdentifier {
	Pattern p1 = Pattern.compile("[//][*][@][@][^~]*[@][@][*][//]");
	Matcher m1;
	Pattern p2 = Pattern.compile("[-][-][@][@][^~,^\\n]*[\\n]");
	Matcher m2;

	@Override
	public String readComments(String Contents) {
		m1 = p1.matcher(Contents);
		StringBuffer sb = new StringBuffer();
		while (m1.find()) {
			sb.append(Contents.substring(m1.start() + 4, m1.end() - 4));

		}
		m2 = p2.matcher(Contents);
		while (m2.find()) {
			sb.append(Contents.substring(m2.start() + 4, m2.end() - 4));

		}
		String res = sb.toString();

		return res;

	}

}
