package com.csc.team2.service;

import java.util.List;

import com.csc.team2.model.File;

public interface IFileService {
	File findById(int id);
	 
	List<File> findByFileId(int id);
 
    void saveFile(File file);
 
    void updateFile(File file);
 
    void deleteFileById(int id);
 
    void deleteAllFile();
 
    List<File> findAllFiles();
 
    boolean isFileExist(File file);
}
