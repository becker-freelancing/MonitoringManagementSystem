package com.jabasoft.mms.documentmanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.jabasoft.mms.documentmanagement.domain.model.FileType;

class FileTypeMapperTest {

	@ParameterizedTest
	@MethodSource("getMappings")
	void testMappingsAreCorrect(FileType fileType, Long expectedMapping) {

		FileTypeMapper fileTypeMapper = new FileTypeMapper();

		Long actualMapping = fileTypeMapper.convertToDatabaseColumn(fileType);

		assertEquals(expectedMapping, actualMapping);
	}

	@Test
	void testAllFileTypesAreMapped(){

		long testedFileMappingCounts = getMappings().count();

		assertEquals(FileType.values().length, testedFileMappingCounts);
	}

	private static Stream<Arguments> getMappings() {

		return Stream.of(
			Arguments.of(FileType.PP, 0L),
			Arguments.of(FileType.CSS, 1L),
			Arguments.of(FileType.CSV, 2L),
			Arguments.of(FileType.PY, 3L),
			Arguments.of(FileType.WSF, 4L),
			Arguments.of(FileType.HS, 5L),
			Arguments.of(FileType.OST, 6L),
			Arguments.of(FileType.MIDI, 7L),
			Arguments.of(FileType.EXE, 8L),
			Arguments.of(FileType.JAVA, 9L),
			Arguments.of(FileType.NIM, 10L),
			Arguments.of(FileType.BAT, 11L),
			Arguments.of(FileType.MOV, 12L),
			Arguments.of(FileType.XML, 13L),
			Arguments.of(FileType.JAR, 14L),
			Arguments.of(FileType.ADOC, 15L),
			Arguments.of(FileType.SCM, 16L),
			Arguments.of(FileType.ZIP, 17L),
			Arguments.of(FileType.OTF, 18L),
			Arguments.of(FileType.CLJ, 19L),
			Arguments.of(FileType.EXS, 20L),
			Arguments.of(FileType.RAR, 21L),
			Arguments.of(FileType._7Z, 22L),
			Arguments.of(FileType.MPG, 23L),
			Arguments.of(FileType.TEX, 24L),
			Arguments.of(FileType.PNG, 25L),
			Arguments.of(FileType.AI, 26L),
			Arguments.of(FileType.TORRENT, 27L),
			Arguments.of(FileType.EPS, 28L),
			Arguments.of(FileType.RA, 29L),
			Arguments.of(FileType.CDR, 30L),
			Arguments.of(FileType.RB, 31L),
			Arguments.of(FileType.DWF, 32L),
			Arguments.of(FileType.GROOVY, 33L),
			Arguments.of(FileType.SLN, 34L),
			Arguments.of(FileType.DWG, 35L),
			Arguments.of(FileType.DOC, 36L),
			Arguments.of(FileType.ACCDB, 37L),
			Arguments.of(FileType.ODP, 38L),
			Arguments.of(FileType.RM, 39L),
			Arguments.of(FileType.ODT, 40L),
			Arguments.of(FileType.AIF, 41L),
			Arguments.of(FileType.ODS, 42L),
			Arguments.of(FileType.RS, 43L),
			Arguments.of(FileType.JL, 44L),
			Arguments.of(FileType.OTHER, 45L),
			Arguments.of(FileType.LOG, 46L),
			Arguments.of(FileType.VBS, 47L),
			Arguments.of(FileType.JSP, 48L),
			Arguments.of(FileType.FLAC, 49L),
			Arguments.of(FileType.JS, 50L),
			Arguments.of(FileType.MID, 51L),
			Arguments.of(FileType.VMDK, 52L),
			Arguments.of(FileType.WMA, 53L),
			Arguments.of(FileType.KML, 54L),
			Arguments.of(FileType.DART, 55L),
			Arguments.of(FileType.FCP, 56L),
			Arguments.of(FileType.SH, 57L),
			Arguments.of(FileType.PGN, 58L),
			Arguments.of(FileType.EPUB, 59L),
			Arguments.of(FileType.JPEG, 60L),
			Arguments.of(FileType.MOBI, 61L),
			Arguments.of(FileType.RKT, 62L),
			Arguments.of(FileType.KEY, 63L),
			Arguments.of(FileType.ERL, 64L),
			Arguments.of(FileType.XLSX, 65L),
			Arguments.of(FileType.WMV, 66L),
			Arguments.of(FileType.MAT, 67L),
			Arguments.of(FileType.C, 68L),
			Arguments.of(FileType.RTF, 69L),
			Arguments.of(FileType.SVG, 70L),
			Arguments.of(FileType.CFG, 71L),
			Arguments.of(FileType.MAX, 72L),
			Arguments.of(FileType.TGZ, 73L),
			Arguments.of(FileType.F, 74L),
			Arguments.of(FileType.H, 75L),
			Arguments.of(FileType.HPP, 76L),
			Arguments.of(FileType.KT, 77L),
			Arguments.of(FileType.CFM, 78L),
			Arguments.of(FileType.M, 79L),
			Arguments.of(FileType.DOCX, 80L),
			Arguments.of(FileType.TC, 81L),
			Arguments.of(FileType.CS, 82L),
			Arguments.of(FileType.TXT, 83L),
			Arguments.of(FileType.FLV, 84L),
			Arguments.of(FileType.R, 85L),
			Arguments.of(FileType.DPROJ, 86L),
			Arguments.of(FileType.PPT, 87L),
			Arguments.of(FileType.V, 88L),
			Arguments.of(FileType.PHP, 89L),
			Arguments.of(FileType.VDI, 90L),
			Arguments.of(FileType.ASM, 91L),
			Arguments.of(FileType.PYC, 92L),
			Arguments.of(FileType.DB, 93L),
			Arguments.of(FileType.ASP, 94L),
			Arguments.of(FileType.TS, 95L),
			Arguments.of(FileType.MSG, 96L),
			Arguments.of(FileType.SWF, 97L),
			Arguments.of(FileType.BMP, 98L),
			Arguments.of(FileType.ISO, 99L),
			Arguments.of(FileType.SCSS, 100L),
			Arguments.of(FileType.NUMBERS, 101L),
			Arguments.of(FileType.PROJECT, 102L),
			Arguments.of(FileType.VHDL, 103L),
			Arguments.of(FileType.OGG, 104L),
			Arguments.of(FileType.TIF, 105L),
			Arguments.of(FileType.DV, 106L),
			Arguments.of(FileType.PAGES, 107L),
			Arguments.of(FileType.MA, 108L),
			Arguments.of(FileType.MB, 109L),
			Arguments.of(FileType.VCXPROJ, 110L),
			Arguments.of(FileType.MD, 111L),
			Arguments.of(FileType.ASPX, 112L),
			Arguments.of(FileType.VEG, 113L),
			Arguments.of(FileType.MKV, 114L),
			Arguments.of(FileType.HTML, 115L),
			Arguments.of(FileType.OGV, 116L),
			Arguments.of(FileType.SWIFT, 117L),
			Arguments.of(FileType.ML, 118L),
			Arguments.of(FileType.YAML, 119L),
			Arguments.of(FileType.CPP, 120L),
			Arguments.of(FileType.PAS, 121L),
			Arguments.of(FileType.TAR, 122L),
			Arguments.of(FileType.SQLITE, 123L),
			Arguments.of(FileType.LISP, 124L),
			Arguments.of(FileType.MDB, 125L),
			Arguments.of(FileType.FCPX, 126L),
			Arguments.of(FileType.LESS, 127L),
			Arguments.of(FileType.MLI, 128L),
			Arguments.of(FileType.VB, 129L),
			Arguments.of(FileType.PPTX, 130L),
			Arguments.of(FileType.EX, 131L),
			Arguments.of(FileType.MPEG, 132L),
			Arguments.of(FileType.PSD, 133L),
			Arguments.of(FileType.TIFF, 134L),
			Arguments.of(FileType.GIF, 135L),
			Arguments.of(FileType.AIFF, 136L),
			Arguments.of(FileType.SCALA, 137L),
			Arguments.of(FileType.BLEND, 138L),
			Arguments.of(FileType.PRPROJ, 139L),
			Arguments.of(FileType.FOR, 140L),
			Arguments.of(FileType.AEP, 141L),
			Arguments.of(FileType.FS, 142L),
			Arguments.of(FileType.SQL, 143L),
			Arguments.of(FileType.XSL, 144L),
			Arguments.of(FileType.PST, 145L),
			Arguments.of(FileType.AVI, 146L),
			Arguments.of(FileType.YML, 147L),
			Arguments.of(FileType.BZ2, 148L),
			Arguments.of(FileType.M4A, 149L),
			Arguments.of(FileType.TCL, 150L),
			Arguments.of(FileType.HTM, 151L),
			Arguments.of(FileType.JPG, 152L),
			Arguments.of(FileType.TTF, 153L),
			Arguments.of(FileType.GO, 154L),
			Arguments.of(FileType.WAV, 155L),
			Arguments.of(FileType.MP4, 156L),
			Arguments.of(FileType.CBP, 157L),
			Arguments.of(FileType.MP3, 158L),
			Arguments.of(FileType.PDF, 159L),
			Arguments.of(FileType.VHD, 160L),
			Arguments.of(FileType.GZ, 161L),
			Arguments.of(FileType.COFFEE, 162L),
			Arguments.of(FileType.LUA, 163L),
			Arguments.of(FileType.PUB, 164L),
			Arguments.of(FileType.PL, 165L),
			Arguments.of(FileType.F90, 166L),
			Arguments.of(FileType.XLS, 167L),
			Arguments.of(FileType.DMG, 168L)
		);
	}

}