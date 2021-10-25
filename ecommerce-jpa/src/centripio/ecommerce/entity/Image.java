package centripio.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

@Embeddable
public class Image {
	@Lob
	@Column(name = "file_image")
	private byte[] image;
	
	@Column(name = "file_name", length = 100)
	private String file_name;
	
	@Column(name = "file_type", length = 30)
	private String file_type;

	@Column(name = "file_length")
	private Long file_length;

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_type() {
		return file_type;
	}

	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}

	public Long getFile_length() {
		return file_length;
	}

	public void setFile_length(Long file_length) {
		this.file_length = file_length;
	}
	
}
