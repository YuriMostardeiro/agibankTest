package com.nt;

import com.nt.domain.OutputFile;
import com.nt.service.FileService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileServiceTest {

    @InjectMocks
    private FileService fileService = new FileService();

    @Test
    public void fileSucessTest() {
        List<String> file = new ArrayList<>();
        file.add("001ç1234567891234çDiegoç50000");
        file.add("001ç3245678865434çRenatoç40000.99");
        file.add("002ç2345675434544345çJosedaSilvaçRural");
        file.add("002ç2345675433444345çEduardoPereiraçRural");
        file.add("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego");
        file.add("003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çRenato");

        OutputFile outputFile = fileService.fillOutputFile(file);
        assertAll(
                () -> assertEquals(outputFile.getTotalOfClient(), 2),
                () -> assertEquals(outputFile.getTotalOfSalesman(), 2),
                () -> assertEquals(outputFile.getMostExpensiveSale(), "10"),
                () -> assertEquals(outputFile.getWorstSalesman(), "Renato")
        );
    }

    @Test
    public void fileFailCodeTest() {
        List<String> file = new ArrayList<>();
        file.add("099ç1234567891234çDiegoç50000");
        file.add("001ç3245678865434çRenatoç40000.99");
        file.add("002ç2345675434544345çJosedaSilvaçRural");
        file.add("002ç2345675433444345çEduardoPereiraçRural");

        OutputFile outputFile = fileService.fillOutputFile(file);
        assertAll(
                () -> assertEquals(outputFile.getTotalOfClient(), 2),
                () -> assertEquals(outputFile.getTotalOfSalesman(), 1)
        );
    }

    @Test
    public void fileFailSaleTest() {
        List<String> file = new ArrayList<>();
        file.add("001ç1234567891234çDiegoç50000");
        file.add("001ç3245678865434çRenatoç40000.99");
        file.add("002ç2345675434544345çJosedaSilvaçRural");
        file.add("002ç2345675433444345çEduardoPereiraçRural");
        file.add("003ç10ç[1-103423100,22340-2.50,3-40-3.10]çDiego");
        file.add("003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çRenato");

        OutputFile outputFile = fileService.fillOutputFile(file);
        assertAll(
                () -> assertEquals(outputFile.getTotalOfClient(), 2),
                () -> assertEquals(outputFile.getTotalOfSalesman(), 2),
                () -> assertEquals(outputFile.getMostExpensiveSale(), "8"),
                () -> assertEquals(outputFile.getWorstSalesman(), "Renato")
        );
    }
}
