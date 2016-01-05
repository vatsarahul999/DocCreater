package com.datametica.DC.CommentWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DCCommentWriter implements DCFileWriter {
	StringBuffer currentContents;
	String finalFileCotents;
	String location;

	public DCCommentWriter() {
		super();
		// TODO Auto-generated constructor stub
		currentContents = new StringBuffer();
	}

	@Override
	public void writeToFile(String str) throws IOException {
		// TODO Auto-generated method stub
		finalFileCotents = this.currentContents.toString();
		String fname = "readme.md";
		Path pathToFile = Paths.get(str + fname);

		Files.createDirectories(pathToFile.getParent());
		File f = new File(str + fname);
		if (!f.exists())
			Files.createFile(pathToFile);

		BufferedWriter writer = new BufferedWriter(new FileWriter(str + fname));
		writer.write(finalFileCotents);
		writer.close();

	}

	@Override
	public void appendtoFile(String te) {
		// TODO Auto-generated method stub
		this.currentContents.append(te);

	}

}
