package com.jabasoft.mms.documentmanagement.domain.model;

import java.util.Objects;

public class FileType {

	public static FileType PP = new FileType("pp");
	public static FileType CSS = new FileType("css");
	public static FileType CSV = new FileType("csv");
	public static FileType PY = new FileType("py");
	public static FileType WSF = new FileType("wsf");
	public static FileType HS = new FileType("hs");
	public static FileType OST = new FileType("ost");
	public static FileType MIDI = new FileType("midi");
	public static FileType EXE = new FileType("exe");
	public static FileType JAVA = new FileType("java");
	public static FileType NIM = new FileType("nim");
	public static FileType BAT = new FileType("bat");
	public static FileType MOV = new FileType("mov");
	public static FileType XML = new FileType("xml");
	public static FileType JAR = new FileType("jar");
	public static FileType ADOC = new FileType("adoc");
	public static FileType SCM = new FileType("scm");
	public static FileType ZIP = new FileType("zip");
	public static FileType OTF = new FileType("otf");
	public static FileType CLJ = new FileType("clj");
	public static FileType EXS = new FileType("exs");
	public static FileType RAR = new FileType("rar");
	public static FileType _7Z = new FileType("7z");
	public static FileType MPG = new FileType("mpg");
	public static FileType TEX = new FileType("tex");
	public static FileType PNG = new FileType("png");
	public static FileType AI = new FileType("ai");
	public static FileType TORRENT = new FileType("torrent");
	public static FileType EPS = new FileType("eps");
	public static FileType RA = new FileType("ra");
	public static FileType CDR = new FileType("cdr");
	public static FileType RB = new FileType("rb");
	public static FileType DWF = new FileType("dwf");
	public static FileType GROOVY = new FileType("groovy");
	public static FileType SLN = new FileType("sln");
	public static FileType DWG = new FileType("dwg");
	public static FileType DOC = new FileType("doc");
	public static FileType ACCDB = new FileType("accdb");
	public static FileType ODP = new FileType("odp");
	public static FileType RM = new FileType("rm");
	public static FileType ODT = new FileType("odt");
	public static FileType AIF = new FileType("aif");
	public static FileType ODS = new FileType("ods");
	public static FileType RS = new FileType("rs");
	public static FileType JL = new FileType("jl");
	public static FileType OTHER = new FileType("other");
	public static FileType LOG = new FileType("log");
	public static FileType VBS = new FileType("vbs");
	public static FileType JSP = new FileType("jsp");
	public static FileType FLAC = new FileType("flac");
	public static FileType JS = new FileType("js");
	public static FileType MID = new FileType("mid");
	public static FileType VMDK = new FileType("vmdk");
	public static FileType WMA = new FileType("wma");
	public static FileType KML = new FileType("kml");
	public static FileType DART = new FileType("dart");
	public static FileType FCP = new FileType("fcp");
	public static FileType SH = new FileType("sh");
	public static FileType PGN = new FileType("pgn");
	public static FileType EPUB = new FileType("epub");
	public static FileType JPEG = new FileType("jpeg");
	public static FileType MOBI = new FileType("mobi");
	public static FileType RKT = new FileType("rkt");
	public static FileType KEY = new FileType("key");
	public static FileType ERL = new FileType("erl");
	public static FileType XLSX = new FileType("xlsx");
	public static FileType WMV = new FileType("wmv");
	public static FileType MAT = new FileType("mat");
	public static FileType C = new FileType("c");
	public static FileType RTF = new FileType("rtf");
	public static FileType SVG = new FileType("svg");
	public static FileType CFG = new FileType("cfg");
	public static FileType MAX = new FileType("max");
	public static FileType TGZ = new FileType("tgz");
	public static FileType F = new FileType("f");
	public static FileType H = new FileType("h");
	public static FileType HPP = new FileType("hpp");
	public static FileType KT = new FileType("kt");
	public static FileType CFM = new FileType("cfm");
	public static FileType M = new FileType("m");
	public static FileType DOCX = new FileType("docx");
	public static FileType TC = new FileType("tc");
	public static FileType CS = new FileType("cs");
	public static FileType TXT = new FileType("txt");
	public static FileType FLV = new FileType("flv");
	public static FileType R = new FileType("r");
	public static FileType DPROJ = new FileType("dproj");
	public static FileType PPT = new FileType("ppt");
	public static FileType V = new FileType("v");
	public static FileType PHP = new FileType("php");
	public static FileType VDI = new FileType("vdi");
	public static FileType ASM = new FileType("asm");
	public static FileType PYC = new FileType("pyc");
	public static FileType DB = new FileType("db");
	public static FileType ASP = new FileType("asp");
	public static FileType TS = new FileType("ts");
	public static FileType MSG = new FileType("msg");
	public static FileType SWF = new FileType("swf");
	public static FileType BMP = new FileType("bmp");
	public static FileType ISO = new FileType("iso");
	public static FileType SCSS = new FileType("scss");
	public static FileType NUMBERS = new FileType("numbers");
	public static FileType PROJECT = new FileType("project");
	public static FileType VHDL = new FileType("vhdl");
	public static FileType OGG = new FileType("ogg");
	public static FileType TIF = new FileType("tif");
	public static FileType DV = new FileType("dv");
	public static FileType PAGES = new FileType("pages");
	public static FileType MA = new FileType("ma");
	public static FileType MB = new FileType("mb");
	public static FileType VCXPROJ = new FileType("vcxproj");
	public static FileType MD = new FileType("md");
	public static FileType ASPX = new FileType("aspx");
	public static FileType VEG = new FileType("veg");
	public static FileType MKV = new FileType("mkv");
	public static FileType HTML = new FileType("html");
	public static FileType OGV = new FileType("ogv");
	public static FileType SWIFT = new FileType("swift");
	public static FileType ML = new FileType("ml");
	public static FileType YAML = new FileType("yaml");
	public static FileType CPP = new FileType("cpp");
	public static FileType PAS = new FileType("pas");
	public static FileType TAR = new FileType("tar");
	public static FileType SQLITE = new FileType("sqlite");
	public static FileType LISP = new FileType("lisp");
	public static FileType MDB = new FileType("mdb");
	public static FileType FCPX = new FileType("fcpx");
	public static FileType LESS = new FileType("less");
	public static FileType MLI = new FileType("mli");
	public static FileType VB = new FileType("vb");
	public static FileType PPTX = new FileType("pptx");
	public static FileType EX = new FileType("ex");
	public static FileType MPEG = new FileType("mpeg");
	public static FileType PSD = new FileType("psd");
	public static FileType TIFF = new FileType("tiff");
	public static FileType GIF = new FileType("gif");
	public static FileType AIFF = new FileType("aiff");
	public static FileType SCALA = new FileType("scala");
	public static FileType BLEND = new FileType("blend");
	public static FileType PRPROJ = new FileType("prproj");
	public static FileType FOR = new FileType("for");
	public static FileType AEP = new FileType("aep");
	public static FileType FS = new FileType("fs");
	public static FileType SQL = new FileType("sql");
	public static FileType XSL = new FileType("xsl");
	public static FileType PST = new FileType("pst");
	public static FileType AVI = new FileType("avi");
	public static FileType YML = new FileType("yml");
	public static FileType BZ2 = new FileType("bz2");
	public static FileType M4A = new FileType("m4a");
	public static FileType TCL = new FileType("tcl");
	public static FileType HTM = new FileType("htm");
	public static FileType JPG = new FileType("jpg");
	public static FileType TTF = new FileType("ttf");
	public static FileType GO = new FileType("go");
	public static FileType WAV = new FileType("wav");
	public static FileType MP4 = new FileType("mp4");
	public static FileType CBP = new FileType("cbp");
	public static FileType MP3 = new FileType("mp3");
	public static FileType PDF = new FileType("pdf");
	public static FileType VHD = new FileType("vhd");
	public static FileType GZ = new FileType("gz");
	public static FileType COFFEE = new FileType("coffee");
	public static FileType LUA = new FileType("lua");
	public static FileType PUB = new FileType("pub");
	public static FileType PL = new FileType("pl");
	public static FileType F90 = new FileType("f90");
	public static FileType XLS = new FileType("xls");
	public static FileType DMG = new FileType("dmg");

	private String fileEnding;

	public FileType(String fileEnding) {

		this.fileEnding = fileEnding;
	}

	public String getFileEnding() {

		return fileEnding;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		FileType fileType = (FileType) o;
		return Objects.equals(fileEnding, fileType.fileEnding);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(fileEnding);
	}
}
