package edu.fpoly.edict.data;

import java.io.Serializable;

/**
 * Quáº£n lÃ½ thÃ´ng tin cá»§a má»™t má»¥c tá»« Ä‘Æ°á»£c hiá»ƒn thá»‹ trÃªn danh
 * sÃ¡ch tá»«
 * 
 * @author Nguyá»…n Ngá»�c Anh - Tel: 0905. 119948 - Email: anhnnst@yahoo.com -
 *         Facebook: anhnnst
 * @CreatedDate: 18/10/2014
 * 
 * @Purpose: Nháº±m táº¡o má»™t Ä‘á»“ Ã¡n máº«u giÃºp cho sinh viÃªn Fpoly Ä�Ã 
 *           Náºµng cÃ³ thá»ƒ hiá»ƒu vÃ  váº­n dá»¥ng vÃ o lÃ m má»™t Ä‘á»“ Ã¡n
 *           thá»±c táº¿
 * @version 1.0.1
 * @Reference: ChÆ°Æ¡ng trÃ¬nh cÃ³ sá»­ dá»¥ng má»™t sá»‘ tÃ i nguyÃªn cá»§a
 *             Ä‘á»“ Ã¡n tá»‘t nghiá»‡p cá»§a nhÃ³m TaxiCalling thuá»™c FU Ä�Ã 
 *             Náºµng
 */
public class ContactListItem implements Serializable {
	int _id;
	String tu;
	String nghiaTu;
	String loaiTu;

	public ContactListItem() {

	}

	public ContactListItem(int _id, String tu, String loaiTu, String nghiaTu) {
		this._id = _id;
		this.tu = loaiTu;
		this.loaiTu = loaiTu;
		this.nghiaTu = nghiaTu;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getTu() {
		return tu;
	}

	public void setTu(String tu) {
		this.tu = tu;
	}

	public String getNghiaTu() {
		return nghiaTu;
	}

	public void setNghiaTu(String nghiaTu) {
		this.nghiaTu = nghiaTu;
	}

	public String getLoaiTu() {
		return loaiTu;
	}

	public void setLoaiTu(String loaiTu) {
		this.loaiTu = loaiTu;
	}

	
}
