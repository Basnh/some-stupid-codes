CREATE TABLE PHICONG(
	MPC smallint PRIMARY KEY,
	hoten varchar2(50) NOT NULL,
	diachi varchar2(50),
	quocgia varchar2(20)
);

CREATE TABLE CONGTY(
	MCT smallint PRIMARY KEY,
	tencty varchar2(50) UNIQUE,
	quocgia varchar2(20)
);

CREATE TABLE LOAIMAYBAY(
	loai varchar2(20) PRIMARY KEY,
	NSX date,
	socho smallint
);

CREATE TABLE MAYBAY(
	MMB smallint PRIMARY KEY,
	loai varchar2(20) REFERENCES LOAIMAYBAY(loai),
	MCT smallint REFERENCES CONGTY(MCT)
);

CREATE TABLE CHUYENBAY(
	SOCB smallint,
	ngaybay date,
	MPC smallint REFERENCES PHICONG(MPC),
	MMB smallint REFERENCES MAYBAY(MMB),
	noidi varchar2(100) DEFAULT ‘Paris’,
	noiden varchar2(100),
	khoangcach int CHECK(khoangcach > 0),
	giodi char(5),
	gioden char(5),
	PRIMARY KEY(SOCB, ngaybay)
);  

CREATE TABLE LAMVIEC(
	MPC smallint REFERENCES PHICONG(MPC),
	MCT smallint REFERENCES CONGTY(MCT),
	ngayBD date NOT NULL,
	songay int CHECK(songay >= 0),
	PRIMARY KEY(MPC, MCT, ngayBD)
);

INSERT INTO PHICONG VALUES(1, 'Nguyen Van A', 'Can Tho', 'Viet Nam');
INSERT INTO PHICONG VALUES(2, 'Tran Van B', 'Soc Trang', 'Viet Nam');

INSERT INTO CONGTY VALUES(1, 'Vietnam Airlines', 'Viet Nam');
INSERT INTO CONGTY VALUES(2, 'VietJet Air', 'Viet Nam');

INSERT INTO LOAIMAYBAY VALUES('Boeing', '12/30/2004', 100);
INSERT INTO LOAIMAYBAY VALUES('Airbus', '04/23/1999', 90);

INSERT INTO MAYBAY VALUES(1, 'Boeing', 1);
INSERT INTO MAYBAY VALUES(2, 'Airbus', 2);

INSERT INTO CHUYENBAY VALUES(1, '1/10/2020', 1, 1, 'Ha Noi', 10000, '06:30', '04:00');
INSERT INTO CHUYENBAY VALUES(2, '2/11/2020', 2, 2, 'TP.HCM', 500, '06:30', '08:00');

INSERT INTO LAMVIEC VALUES(1, 1, '2/11/2019', 100);
INSERT INTO LAMVIEC VALUES(2, 2, '1/9/2019', 90);
