package com.freestyle.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class RenderUtil {

	public static void renderJson(Object obj, HttpServletResponse response) throws IOException {
		response.getWriter().write(JsonUtil.toJson(obj));
	}

}
