package com.cx.controller;

import java.util.UUID;


public final class UUIDUtil {
	/**
	 * <p>Ëæ»úÉú³ÉUUID×Ö·û´®</p>
	 * @return	UUID×Ö·û´®
	 */
	public static final String randomUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
}
