package com.iferino.playbasistestproj.ScreenThird.Model;

import com.google.gson.annotations.SerializedName;

public class Image {
@SerializedName("public_id")
private String  publicId;
@SerializedName("format")
private String format;
@SerializedName("version")
private String  ver;
@SerializedName("resource_type")
private String  resourceType;
@SerializedName("type")
private String type;
@SerializedName("created_at")
private String  createdAt;
@SerializedName("bytes")
private String  bytes;
@SerializedName("width")
private int  width;
@SerializedName("height")
private int  height;
@SerializedName("url")
private String  url;
@SerializedName("secure_url")
private String secureUrl;


public Image(String publicId, String format, String  ver, String  resourceType, String type, String  createdAt,
             String  bytes, int  width, int  height, String  url, String secureUrl) {
	this.publicId = publicId;
	this.format = format;
	this.ver = ver;
	this.resourceType = resourceType;
	this.type = type;
	this.createdAt = createdAt;
	this.bytes = bytes;
	this.width = width;
	this.height = height;
	this.url = url;
	this.secureUrl = secureUrl;
}

public String getPublicId() {
	return publicId;
}

public void setPublicId(String publicId) {
	this.publicId = publicId;
}

public String getFormat() {
	return format;
}

public void setFormat(String format) {
	this.format = format;
}

public String getVer() {
	return ver;
}

public void setVer(String ver) {
	this.ver = ver;
}

public String getResourceType() {
	return resourceType;
}

public void setResourceType(String resourceType) {
	this.resourceType = resourceType;
}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

public String getCreatedAt() {
	return createdAt;
}

public void setCreatedAt(String createdAt) {
	this.createdAt = createdAt;
}

public String getBytes() {
	return bytes;
}

public void setBytes(String bytes) {
	this.bytes = bytes;
}

public int getWidth() {
	return width;
}

public void setWidth(int width) {
	this.width = width;
}

public int getHeight() {
	return height;
}

public void setHeight(int height) {
	this.height = height;
}

public String getUrl() {
	return url;
}

public void setUrl(String url) {
	this.url = url;
}

public String getSecureUrl() {
	return secureUrl;
}

public void setSecureUrl(String secureUrl) {
	this.secureUrl = secureUrl;
}

}
