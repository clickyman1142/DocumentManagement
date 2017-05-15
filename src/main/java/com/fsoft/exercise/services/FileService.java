package com.fsoft.exercise.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fsoft.exercise.entities.FileEntity;

public interface FileService {
	public void addFile(MultipartFile[] files, String pathFile) throws IllegalStateException, IOException;
	public void deleteFile(int[] id);
	public List<FileEntity> getAll();
	public FileEntity getById(int id);
}
