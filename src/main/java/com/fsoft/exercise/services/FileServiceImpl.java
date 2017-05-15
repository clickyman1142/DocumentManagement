package com.fsoft.exercise.services;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fsoft.exercise.entities.FileEntity;
import com.fsoft.exercise.repositories.FileRepository;

@Service("fileService")
public class FileServiceImpl implements FileService {
	@Autowired
	private FileRepository fileRepository;

	@Override
	public void addFile(MultipartFile[] files, String pathFile) throws IllegalStateException, IOException {
		File store = new File(pathFile);
		if (!store.exists()) {
			store.mkdir();
		}

		for (MultipartFile multipartFile : files) {
			// Create file on server
			File file = new File(pathFile + multipartFile.getOriginalFilename());

			if (file.exists()) {
				// Replace file
				multipartFile.transferTo(file);
			} else {
				// Create a file if not exist
				file.createNewFile();
				// Upload file
				multipartFile.transferTo(file);

				// Get file's info and store to database
				String fileName = multipartFile.getOriginalFilename();
				String fileType = FilenameUtils.getExtension(fileName);
				fileName = fileName.substring(0, fileName.lastIndexOf("."));
				Timestamp uploadDate = new Timestamp(new Date().getTime());
				String description = fileName;
				String url = file.getPath();

				FileEntity fileEntity = new FileEntity();
				fileEntity.setFileName(fileName);
				fileEntity.setFileType(fileType);
				fileEntity.setUploadDate(uploadDate);
				fileEntity.setDescription(description);
				fileEntity.setUrl(url);
				fileRepository.save(fileEntity);
			}

		}
	}

	@Override
	public void deleteFile(int[] ids) {
		for (int id : ids) {
			FileEntity fileEntity = getById(id);
			if (fileEntity != null) {
				// Delete file
				File file = new File(fileEntity.getUrl());
				file.delete();
				// Delete file's info in database
				fileRepository.delete(id);
			}
		}
	}

	@Override
	public List<FileEntity> getAll() {
		return fileRepository.findAll();
	}

	@Override
	public FileEntity getById(int id) {
		return fileRepository.findOne(id);
	}

}
