package com.jabasoft.mms.documentmanagement.domain.service.filepath;

import com.jabasoft.mms.documentmanagement.domain.model.FilePath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FilePathServiceImplTest {

    FilePathServiceImpl pathService;

    @BeforeEach
    void setUp(){
        pathService = new FilePathServiceImpl();
    }

    @Test
    void testSplitPathWithEmptyPathReturnsListWithOneElement(){
        List<FilePath> actual = pathService.splitPath(new FilePath(""));

        List<FilePath> expected = Stream.of("").map(FilePath::new).toList();
        assertEquals(expected, actual);
    }

    @Test
    void testSplitPathWithDepthOnePathReturnsListWithOneElement(){
        List<FilePath> actual = pathService.splitPath(new FilePath("root"));

        List<FilePath> expected = Stream.of("root").map(FilePath::new).toList();
        assertEquals(expected, actual);
    }

    @Test
    void testSplitPathWithEmptyDepthFivePathReturnsListWithFiveElements(){
        List<FilePath> actual = pathService.splitPath(new FilePath("root/a/b/c/d"));

        List<FilePath> expected = Stream.of("root", "a", "b", "c", "d").map(FilePath::new).toList();
        assertEquals(expected, actual);
    }


}