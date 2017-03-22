package com.csc.team2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csc.team2.model.File;
import com.csc.team2.repository.IFileRepository;

@Service
public class FileServiceImpl implements IFileService{
	
	@Autowired
	IFileRepository fileRepos;

	@Override
	public File findById(int id) {
		return fileRepos.findOne(id);
	}

	@Override
	public List<File> findByFileId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveFile(File file) {
		fileRepos.saveAndFlush(file);
		
	}

	@Override
	public void updateFile(File file) {
		saveFile(file);
		
	}

	@Override
	public void deleteFileById(int id) {
		fileRepos.delete(id);
		
	}

	@Override
	public void deleteAllFile() {
		fileRepos.deleteAll();
		
	}

	@Override
	public List<File> findAllFiles() {
		return (List<File>)fileRepos.findAll();
	}

	@Override
	public boolean isFileExist(File file) {
		// TODO Auto-generated method stub
		return false;
	}

}
