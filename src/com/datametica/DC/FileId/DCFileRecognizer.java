	package com.datametica.DC.FileId;
	
	import java.io.IOException;
	import java.nio.file.FileSystems;
	import java.nio.file.Files;
	import java.nio.file.Path;
	import java.util.ArrayList;
	import java.util.List;
	
	public class DCFileRecognizer implements DCFileInfo {
		String fileType;
		String fileContents;
		String fileName;
	
		public String getFileName() {
			return fileName;
		}

		

		public DCFileRecognizer(String fileType) {
			super();
			this.fileType = setFileType(fileType);
			this.fileContents = setFileContents(fileType);
		}
	
		private String setFileContents(String fileType2) {
			// TODO Auto-generated method stub
			Path path = FileSystems.getDefault().getPath(fileType2);
			StringBuffer contents = new StringBuffer();
			List<String> list = new ArrayList<String>();
			try {
				list = Files.readAllLines(path);
			} catch (IOException e) {
	
				e.printStackTrace();
			}
			for (String l : list) {
				contents.append(l);
				contents.append("\n");
			}
	
			return contents.toString();
	
		}
	
		public String setFileType(String string) {
			String[] temp = string.split("/");
			String temp1 = temp[temp.length - 1];
			String[] finalstr = temp1.split("\\.");
			this.fileName = finalstr[0];
			return finalstr[1];
	
		}
	
		@Override
		public String getFileContents() {
			// TODO Auto-generated method stub
			return this.fileContents;
		}
	
		@Override
		public String getFileType() {
			// TODO Auto-generated method stub
			return this.fileType;
		}
	
	}
