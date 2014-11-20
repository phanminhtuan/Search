package edu.fpoly.edict.data.table;

import java.io.Serializable;

/**
 * Lá»›p Ä‘á»‹nh nghÄ©a cÃ¡c trÆ°á»�ng á»Ÿ trong báº£ng WordTable. Báº£ng WordTable dÃ¹ng Ä‘á»ƒ chá»©a táº¥t cáº£ cÃ¡c thÃ´ng tin cá»§a tá»« cÃ³ trong tá»« Ä‘iá»ƒn
 * 
 * @author Nguyá»…n Ngá»�c Anh - Tel: 0905. 119948 - Email: anhnnst@yahoo.com -
 *         Facebook: anhnnst
 * @CreatedDate: 18/10/2014
 * 
 * @Purpose: Nháº±m táº¡o má»™t Ä‘á»“ Ã¡n máº«u giÃºp cho sinh viÃªn Fpoly Ä�Ã  Náºµng cÃ³ thá»ƒ hiá»ƒu
 *           vÃ  váº­n dá»¥ng vÃ o lÃ m má»™t Ä‘á»“ Ã¡n thá»±c táº¿
 * @version 1.0.1
 * @Reference: ChÆ°Æ¡ng trÃ¬nh cÃ³ sá»­ dá»¥ng má»™t sá»‘ tÃ i nguyÃªn cá»§a Ä‘á»“ Ã¡n tá»‘t nghiá»‡p
 *             cá»§a nhÃ³m TaxiCalling thuá»™c FU Ä�Ã  Náºµng
 */
public class ContactTable implements Serializable {
	public static final String SQL_CREATE_TABLE = "CREATE TABLE if not exists " + ContactTable.TABLE_NAME + " ("
			+ ContactTable.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ ContactTable.FIELD_TU + " TEXT,"
			+ ContactTable.FIELD_LOAITU + " TEXT,"
			+ ContactTable.FIELD_NGHIA + " TEXT);";
	public static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + ContactTable.TABLE_NAME;
	
	// MÃ£ duy nháº¥t
	public static final String ID = "_id";
	
	// Tá»« khÃ³a
	public static final String FIELD_TU = "tu";
	//loai tu
	public static final String FIELD_LOAITU = "loaiTu";
	//NghÄ©a tá»«
	public static final String FIELD_NGHIA = "nghiaTu";
	

	//TÃªn báº£ng
	public static final String TABLE_NAME = "contacts";
}
