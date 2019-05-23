package io.msa.content.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "content")
public class Content {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ctnId;
	private String name;
	private String tagTitle;
	private String position;// Left or Right
	private String type;// Box,Page,Section
	private String fileType;// STATIC_FILE, IMAGE, LOGO, PRODUCT,
							// PRODUCTLG,PROPERTY,MANUFACTURER,PRODUCT_DIGITAL
	private String description;
	@CreationTimestamp
	private Date createDate;
	@CreationTimestamp
	private Date updateDate;

	public Content() {
	}

	public Content(Long ctnId, String name, String tagTitle, String position, String type, String fileType,
			String description, Date createDate, Date updateDate) {
		super();
		this.ctnId = ctnId;
		this.name = name;
		this.tagTitle = tagTitle;
		this.position = position;
		this.type = type;
		this.fileType = fileType;
		this.description = description;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public Long getCtnId() {
		return ctnId;
	}

	public void setCtnId(Long ctnId) {
		this.ctnId = ctnId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTagTitle() {
		return tagTitle;
	}

	public void setTagTitle(String tagTitle) {
		this.tagTitle = tagTitle;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
