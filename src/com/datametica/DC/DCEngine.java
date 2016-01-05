package com.datametica.DC;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.datametica.DC.CommentReader.DCCommentIdentifier;
import com.datametica.DC.CommentReader.DCHiveComment;
import com.datametica.DC.CommentReader.DCPigComment;
import com.datametica.DC.CommentReader.DCShellComment;
import com.datametica.DC.CommentWriter.DCCommentWriter;
import com.datametica.DC.CommentWriter.DCFileWriter;
import com.datametica.DC.FileId.DCFileInfo;
import com.datametica.DC.FileId.DCFileRecognizer;

public class DCEngine {

	List<String> Files;
	List<DCFileInfo> FileInfo;

	public DCEngine() {
		super();
		// TODO Auto-generated constructor stub
		this.Files = new ArrayList<String>();
		this.FileInfo = new ArrayList<DCFileInfo>();

	}

	public static void main(String[] args) throws IOException {

		DCEngine eng = new DCEngine();
		eng.getAllFiles(args[0]);
		System.out.println("Files Loaded");
		eng.identifyFiles();
		System.out.println("Files Identified");
		String out = eng.readComments();
		DCFileWriter wr = new DCCommentWriter();
		wr.appendtoFile(out);
		wr.writeToFile(args[0]);
		System.out.println("This process is completed.");

	}

	private String readComments() {

		Iterator<DCFileInfo> itr = this.FileInfo.iterator();
		DCFileInfo finfo;
		String type;
		DCCommentIdentifier com;
		StringBuffer poutput = new StringBuffer();
		StringBuffer houtput = new StringBuffer();
		StringBuffer soutput = new StringBuffer();
		while (itr.hasNext()) {
			finfo = itr.next();
			type = finfo.getFileType();
			switch (type) {
			case "pig":
				com = new DCPigComment();
				poutput.append(
						"\n-----------------------------------------------------------------------------------------------------\n")
						.append(finfo.getFileName())
						.append("\n-----------------------------------------------------------------------------------------------------\n")
						.append("\n\n").append(com.readComments(finfo.getFileContents())).append("\n\n");
				break;
			case "hql":
				com = new DCHiveComment();
				houtput.append(
						"\n-----------------------------------------------------------------------------------------------------\n")
						.append(finfo.getFileName())
						.append("\n-----------------------------------------------------------------------------------------------------\n")
						.append("\n\n").append(com.readComments(finfo.getFileContents())).append("\n\n");
				break;
			case "sh":
				com = new DCShellComment();
				soutput.append(
						"\n-----------------------------------------------------------------------------------------------------\n")
						.append(finfo.getFileName())
						.append("\n-----------------------------------------------------------------------------------------------------\n")
						.append("\n\n").append(com.readComments(finfo.getFileContents())).append("\n\n");
				break;
			default:

				System.out.println("This file is not handled by DocCreator. It is of type " + type);
				break;

			}

		}
		StringBuffer fstr = new StringBuffer();
		for (int i = 0; i < 100; i++)
			fstr.append("#");
		fstr.append("\n");
		for (int i = 0; i < 44; i++)
			fstr.append(" ");
		fstr.append("Shell Script");
		for (int i = 0; i < 44; i++)
			fstr.append(" ");
		fstr.append("\n");
		for (int i = 0; i < 100; i++)
			fstr.append("#");
		fstr.append(soutput.toString() + "\n\n");
		for (int i = 0; i < 100; i++)
			fstr.append("#");
		fstr.append("\n");
		for (int i = 0; i < 45; i++)
			fstr.append(" ");
		fstr.append("Pig Script");
		for (int i = 0; i < 45; i++)
			fstr.append(" ");
		fstr.append("\n");
		for (int i = 0; i < 100; i++)
			fstr.append("#");
		fstr.append("\n" + poutput.toString() + "\n\n");

		for (int i = 0; i < 100; i++)
			fstr.append("#");
		fstr.append("\n");
		for (int i = 0; i < 45; i++)
			fstr.append(" ");
		fstr.append("Hive Script");
		for (int i = 0; i < 45; i++)
			fstr.append(" ");
		fstr.append("\n");
		for (int i = 0; i < 100; i++)
			fstr.append("#");
		fstr.append("\n" + houtput.toString() + "\n\n");

		return fstr.toString();

	}

	private void identifyFiles() {
		// TODO Auto-generated method stub
		DCFileInfo dcrg;
		Iterator<String> itr = Files.iterator();
		String temp;
		while (itr.hasNext()) {
			temp = itr.next();
			dcrg = new DCFileRecognizer(temp);
			this.FileInfo.add(dcrg);
		}

	}

	private void getAllFiles(String string) {
		File folder = new File(string);
		if (folder.isDirectory()) {
			File[] listOfFiles = folder.listFiles();

			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile() && !listOfFiles[i].getName().endsWith("~")) {
					this.Files.add(string + listOfFiles[i].getName());
					// System.out.println(string + listOfFiles[i].getName());
				}
			}
		} else {
			if (folder.isFile()) {
				this.Files.add(string);
			}
		}

	}

}
