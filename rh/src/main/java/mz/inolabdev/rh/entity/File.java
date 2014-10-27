package mz.inolabdev.rh.entity;

import javax.persistence.Column;
import javax.persistence.Lob;

import com.mysql.jdbc.Blob;

public class File extends IdEntity {

	private static final long serialVersionUID = 2889402583854838920L;

	@Column(name = "file_name")
	private String fileName;

	@Column(name = "file_type")
	private String fileType;

	@Column(name = "content")
	@Lob
	private byte[] content;

	@Column(name = "description")
	private String description;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return fileName + "." + fileType ;
	}
}
