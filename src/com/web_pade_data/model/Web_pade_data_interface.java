package com.web_pade_data.model;

import java.util.List;

public interface Web_pade_data_interface {

	public Web_pade_dataVO insert(Web_pade_dataVO web_pade_data_no);

	public void update(Web_pade_dataVO web_pade_data_no);

	public void delete(Integer web_pade_data_no);

	public Web_pade_dataVO findByWeb_pade_data_noPK(Integer WEB_PADE_DATA_NO);

	public List<Web_pade_dataVO> getweb_pade_data_no();

}
