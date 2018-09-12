package com.example.lenovo.textviewspannerdalogexercise.bean;

/***
 * 针对控件Spinner 绑定数据带有键值功能
 *
 */
public class KeyToValueList {

	private String value = "";
	private String text = "";

	public KeyToValueList() {
		value = "";
		text = "";
	}

	public KeyToValueList(String _value, String _text) {
		value = _value;
		text = _text;
	}

	@Override
	public String toString() { 
		return text;
	}

	public String getValue() {
		return value;
	}

	public String getText() {
		return text;
	}
}
