package com.web_pade_data.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class Web_pade_dataService {

	public Web_pade_data_interface dao;

	public Web_pade_dataService() {
		dao = new Web_pade_dataDAO();
	}

	public Web_pade_dataVO insertWeb_pade_data(Integer web_pade_data, Date web_pade_date) {

		Web_pade_dataVO web_pade_data1 = new Web_pade_dataVO();

		web_pade_data1.setWeb_pade_data(web_pade_data);
		web_pade_data1.setWeb_pade_date(web_pade_date);

		web_pade_data1 = dao.insert(web_pade_data1);

		return web_pade_data1;
	}

	public void updateweb_pade_data(Integer web_pade_data, Date web_pade_date, Integer web_pade_data_no) {

		Web_pade_dataVO web_pade_data1 = new Web_pade_dataVO();

		web_pade_data1.setWeb_pade_data(web_pade_data);
		web_pade_data1.setWeb_pade_date(web_pade_date);
		web_pade_data1.setWeb_pade_data(web_pade_data_no);
		dao.update(web_pade_data1);
	}

	public Web_pade_dataVO findByWeb_pade_data_noPK(Integer WEB_PADE_DATA_NO) {

		Web_pade_dataVO web_pade_data1 = new Web_pade_dataVO();

		web_pade_data1 = dao.findByWeb_pade_data_noPK(WEB_PADE_DATA_NO);

		return web_pade_data1;

	}

	public List<Web_pade_dataVO> getweb_pade_data_no() {

		List<Web_pade_dataVO> web_pade_data1 = dao.getweb_pade_data_no();

		return web_pade_data1;
	}

	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}

}
