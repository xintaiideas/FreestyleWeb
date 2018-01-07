package com.freestyle.service.admin;

public interface IEmailService {

	void send(String subject, String content, String to);
}
