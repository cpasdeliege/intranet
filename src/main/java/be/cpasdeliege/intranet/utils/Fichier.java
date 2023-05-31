package be.cpasdeliege.intranet.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class Fichier {
	/**
	 * 
	 * @param source
	 * @param target
	 * @throws IOException
	 */
	public static void copy(String source, String target) throws IOException {
		copy(new File(source), new File(target));
	}

	/**
	 * 
	 * @param source
	 * @param target
	 * @throws IOException
	 */
	public static void copy(File source, String target) throws IOException {
		copy(source, new File(target));
	}

	/**
	 * 
	 * @param source
	 * @param target
	 * @throws IOException
	 */
	public static void copy(String source, File target) throws IOException {
		copy(new File(source), target);
	}

	/**
	 * 
	 * @param source
	 * @param target
	 * @throws IOException
	 */
	public static void copy(File source, File target) throws IOException {
		if (source.isDirectory()) {
			cpDirectory(source, target);
		} else {
			cpFile(source, new File(target.getAbsolutePath() + File.separator + source.getName()));
		}
	}

	/**
	 * 
	 * @param source
	 * @param target
	 * @throws IOException
	 */
	private static void cpDirectory(File source, File target) throws IOException {
		File[] filesRepSrc = source.listFiles();
		target = new File(target.getAbsolutePath() + File.separator + source.getName());
		target.mkdirs();
		for (int i = 0; i < filesRepSrc.length; i++) {
			if (filesRepSrc[i].isDirectory()) {
				cpDirectory(filesRepSrc[i], target);
			} else {

				cpFile(filesRepSrc[i], new File(target.getAbsolutePath() + File.separator + filesRepSrc[i].getName()));
			}
		}
	}

	/**
	 * 
	 * @param source
	 * @param target
	 * @throws UtilsException
	 */
	private static void cpFile(File source, File target) throws IOException {
		FileChannel in = null;
		FileChannel out = null;
		try {
			in = new FileInputStream(source).getChannel();
			out = new FileOutputStream(target).getChannel();
			in.transferTo(0, in.size(), out);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
	}

	/**
	 * 
	 * @param file
	 * @return
	 */
	public static boolean delete(String file) {
		return delete(new File(file));
	}

	/**
	 * 
	 * @param file
	 * @return
	 */
	public static boolean delete(File file) {
		if (file.isDirectory()) {
			return deleteDirectory(file);
		} else {
			return file.delete();
		}
	}

	/**
	 * 
	 * @param directory
	 * @return
	 */
	private static boolean deleteDirectory(File directory) {
		File[] filesRep = directory.listFiles();
		for (int i = 0; i < filesRep.length; i++) {
			if (filesRep[i].isDirectory()) {
				deleteDirectory(new File(directory.getAbsolutePath() + File.separator + filesRep[i].getName()));
			} else {
				filesRep[i].delete();
			}
		}
		return directory.delete();
	}

	/**
	 * 
	 * @param source
	 * @param target
	 * @throws IOException
	 */
	public static void move(String source, String target) throws IOException {
		copy(source, target);
		delete(source);
	}

	public static void move(String source, File target) throws IOException {
		copy(source, target);
		delete(source);
	}

	/**
	 * 
	 * @param source
	 * @param target
	 * @throws IOException
	 */
	public static void move(File source, String target) throws IOException {
		copy(source, target);
		delete(source);
	}

	/**
	 * 
	 * @param source
	 * @param target
	 * @throws IOException
	 */
	public static void move(File source, File target) throws IOException {
		copy(source, target);
		delete(source);
	}
}
