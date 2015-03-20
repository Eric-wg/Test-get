package com.ebay.eric.xstream_test;

public class EntityField {
	private String FieldName;
	private String FieldDisplayName;
	private String FieldType;
	private boolean IsMultivalued = false;

	public String getFieldName() {
		return FieldName;
	}

	public void setFieldName(String fieldName) {
		FieldName = fieldName;
	}

	public String getFieldDisplayName() {  
		return FieldDisplayName;
	}

	public void setFieldDisplayName(String fieldDisplayName) {
		FieldDisplayName = fieldDisplayName;
	}

	public String getFieldType() {
		return FieldType;
	}

	public void setFieldType(String fieldType) {
		FieldType = fieldType;
	}

	public boolean getIsMultivalued() {
		return IsMultivalued;
	}

	public void setIsMultivalued(boolean isMultivalued) {
		IsMultivalued = isMultivalued;
	}
}
