package com.eric.tomcat;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import org.apache.coyote.Adapter;
import org.apache.coyote.Request;
import org.apache.coyote.Response;
import org.apache.tomcat.util.buf.ByteChunk;
import org.apache.tomcat.util.net.SocketStatus;

public class MyHandler implements Adapter {

	@Override
	public void service(Request req, Response res) throws Exception {
		System.out.println("service");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(baos));
		writer.println("Hello World");
		writer.flush();

		ByteChunk byteChunk = new ByteChunk();
		byteChunk.append(baos.toByteArray(), 0, baos.size());
		res.doWrite(byteChunk);
	}

	@Override
	public boolean event(Request req, Response res, SocketStatus status)
			throws Exception {
		System.out.println("Event");
		return false;
	}

	@Override
	public void log(Request req, Response res, long time) {

	}

}
