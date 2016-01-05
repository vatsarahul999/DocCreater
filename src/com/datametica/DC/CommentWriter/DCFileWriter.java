package com.datametica.DC.CommentWriter;

import java.io.IOException;

public interface DCFileWriter {
	public void writeToFile(String str) throws IOException;
	public void appendtoFile(String te);

}
